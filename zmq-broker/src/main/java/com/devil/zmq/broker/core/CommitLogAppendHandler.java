package com.devil.zmq.broker.core;

import com.devil.zmq.broker.constants.Constants;
import com.devil.zmq.broker.model.CommitLogMessage;
import com.devil.zmq.broker.model.TopicConfig;
import com.devil.zmq.common.UtilAll;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class CommitLogAppendHandler {

    private MMapFileModelManager mMapFileModelManager;

    public CommitLogAppendHandler() {
        this.mMapFileModelManager = new MMapFileModelManager();
    }

    public void initTopicFile(Collection<TopicConfig> topicConfigs) throws IOException {
        for (TopicConfig topicConfig : topicConfigs) {
            String topicFilePath = Constants.getMqHome() + File.separator + Constants.BROKER_COMMIT_LOG_PATH +
                    topicConfig.getTopic() + File.separator + Constants.defaultTopicFile();
            UtilAll.createFile(topicFilePath);
            MMapFileModel mapFileModel = new MMapFileModel(topicConfig.getTopic());

            mapFileModel.loadFile(0, Constants.defaultCommitLogSize);
            mMapFileModelManager.put(topicConfig.getTopic(), mapFileModel);
        }
    }

    public void appendMessage(String topic, CommitLogMessage message) throws IOException {
        MMapFileModel model = mMapFileModelManager.get(topic);
        if (null == model) {
            throw new IllegalArgumentException("model is not exist");
        }
        model.write(message.toBytes());
    }

    public String read(String topic, int offset, int size) {
        MMapFileModel model = mMapFileModelManager.get(topic);
        if (null == model) {
            throw new IllegalArgumentException("topic is not exist");
        }
        return new String(model.read(offset, size));
    }
}
