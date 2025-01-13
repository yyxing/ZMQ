package com.devil.zmq.broker.core;

import com.devil.zmq.broker.cache.CommonCache;
import com.devil.zmq.broker.constants.Constants;
import com.devil.zmq.broker.lock.Lock;
import com.devil.zmq.broker.lock.UnfairLock;
import com.devil.zmq.broker.model.CommitLogModel;
import com.devil.zmq.broker.model.TopicModel;
import com.devil.zmq.broker.utils.CommitLogUtil;
import com.devil.zmq.common.UtilAll;
import io.netty.util.internal.PlatformDependent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

public class MMapFileModel {
    private File file;
    private MappedByteBuffer mappedByteBuffer;
    private FileChannel fileChannel;
    private String topic;

    private Lock lock;

    public MMapFileModel(String topic) {
        this.topic = topic;
        this.lock = new UnfairLock();
    }

    /**
     * 加载指定offset的文件映射到page-cache中
     *
     * @param offset
     * @param size
     */
    public void loadFile(int offset, int size) throws IOException {
        String topicFilePath = getLatestCommitLogPath();
        buildMappedBuffer(topicFilePath, offset, size);
    }

    private void buildMappedBuffer(String topicFilePath, int offset, int size) throws IOException {
        file = new File(topicFilePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("file not exist");
        }
        // 将之前的的pageCache强刷一下
        if (mappedByteBuffer != null)
            mappedByteBuffer.force();
        fileChannel = new RandomAccessFile(file, Constants.RW_MODE).getChannel();
        mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, offset, size);
    }

    /**
     * 从指定的offset读取size大小
     *
     * @param offset 起始位置
     * @param size   读取大小
     * @return
     */
    public byte[] read(int offset, int size) {
        mappedByteBuffer.position(offset);
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = mappedByteBuffer.get(i);
        }
        return bytes;
    }

    /**
     * mmap写入数据
     *
     * @param bytes mmap写入数据
     */
    public void write(byte[] bytes) throws IOException {
        write(bytes, false);
    }

    public void write(byte[] bytes, boolean force) throws IOException {
        //定位到最新的commitLog文件中，记录下当前文件是否已经写满，如果写满，则创建新的文件，并且做新的mmap映射
        //如果当前文件没有写满，对content内容做一层封装，再判断写入是否会导致commitLog写满，
        //如果不会，则选择当前commitLog，如果会则创建新文件，并且做mmap映射
        //定位到最新的commitLog文件之后，写入
        //定义一个对象专门管理各个topic的最新写入offset值，并且定时刷新到磁盘中（mmap？）
        //写入数据，offset变更，如果是高并发场景，offset是不是会被多个线程访问？
        CheckCommitLogFull(bytes.length);
        lock.lock();
        updateCommitLogOffset(bytes.length);
        mappedByteBuffer.put(bytes);
        if (force) {
            mappedByteBuffer.force();
        }
        lock.unlock();
    }

    private void updateCommitLogOffset(int offset) {
        TopicModel topicModel = CommonCache.getTopicModelMap().get(topic);
        if (null == topicModel) {
            throw new IllegalArgumentException(String.format("topic is valid! topic is %s", topic));
        }
        CommitLogModel commitLogModel = topicModel.getLastCommitLog();
        commitLogModel.getOffset().addAndGet(offset);
        topicModel.setLastCommitLog(commitLogModel);
        CommonCache.getTopicModelMap().put(topic, topicModel);
    }

    private void CheckCommitLogFull(int writeSize) throws IOException {
        TopicModel topicModel = CommonCache.getTopicModelMap().get(topic);
        if (null == topicModel) {
            throw new IllegalArgumentException(String.format("topic is valid! topic is %s", topic));
        }
        CommitLogModel commitLogModel = topicModel.getLastCommitLog();
        int diff = commitLogModel.diff() - writeSize;
        if (diff < 0) {
            createNewCommitLogFile(commitLogModel);
            topicModel.setLastCommitLog(commitLogModel);
            CommonCache.getTopicModelMap().put(topic, topicModel);
        }
    }

    public String getLatestCommitLogPath() throws IOException {
        TopicModel topicModel = CommonCache.getTopicModelMap().get(topic);
        if (null == topicModel) {
            throw new IllegalArgumentException(String.format("topic is valid! topic is %s", topic));
        }
        CommitLogModel commitLogModel = topicModel.getLastCommitLog();
        int diff = commitLogModel.diff();
        String latestCommitLogPath = "";
        if (diff > 0) {
            latestCommitLogPath = CommitLogUtil.buildAbsolutePath(topic, commitLogModel.getIndex().get());
        } else {
            latestCommitLogPath = createNewCommitLogFile(commitLogModel);
            topicModel.setLastCommitLog(commitLogModel);
            CommonCache.getTopicModelMap().put(topic, topicModel);
        }
        return latestCommitLogPath;
    }


    private String createNewCommitLogFile(CommitLogModel commitLogModel) throws IOException {
        // 更新commitLog
        commitLogModel.handleSpaceFull();
        // 生成最新的文件名称
        String newCommitLogFilePath = CommitLogUtil.buildAbsolutePath(topic, commitLogModel.getIndex().get());
        // 创建文件
        UtilAll.createFile(newCommitLogFilePath);
        // 映射最新的commitLog文件到内存中
        buildMappedBuffer(newCommitLogFilePath, commitLogModel.getOffset().get(), Constants.defaultMMapSize);
        return newCommitLogFilePath;
    }

    public void clean() {
        UtilAll.cleanBuffer(mappedByteBuffer);
    }
}
