package com.devil.zmq.broker.model;

import java.io.Serializable;
import java.util.List;

public class TopicModel implements Serializable {

    private String topic;
    private CommitLogModel lastCommitLog;
    private List<QueueModel> queueList;
    private long createAt;
    private long updateAt;
    private int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<QueueModel> getQueueList() {
        return queueList;
    }

    public void setQueueList(List<QueueModel> queueList) {
        this.queueList = queueList;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(long updateAt) {
        this.updateAt = updateAt;
    }

    public CommitLogModel getLastCommitLog() {
        return lastCommitLog;
    }

    public void setLastCommitLog(CommitLogModel lastCommitLog) {
        this.lastCommitLog = lastCommitLog;
    }

    @Override
    public String toString() {
        return "TopicModel{" +
                "topic='" + topic + '\'' +
                ", lastCommitLog=" + lastCommitLog +
                ", queueList=" + queueList +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
