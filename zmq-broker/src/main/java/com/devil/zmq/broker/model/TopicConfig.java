package com.devil.zmq.broker.model;

import java.io.Serializable;

public class TopicConfig implements Serializable {

    private String topic;
    private CommitLog lastCommitLog;
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

    public CommitLog getLastCommitLog() {
        return lastCommitLog;
    }

    public void setLastCommitLog(CommitLog lastCommitLog) {
        this.lastCommitLog = lastCommitLog;
    }

    @Override
    public String toString() {
        return "TopicModel{" +
                "topic='" + topic + '\'' +
                ", lastCommitLog=" + lastCommitLog +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
