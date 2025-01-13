package com.devil.zmq.broker.model;

import java.io.Serializable;

public class QueueModel implements Serializable {

    private int id;

    private long minOffset;
    private long currentOffset;
    private long maxOffset;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMinOffset() {
        return minOffset;
    }

    public void setMinOffset(long minOffset) {
        this.minOffset = minOffset;
    }

    public long getCurrentOffset() {
        return currentOffset;
    }

    public void setCurrentOffset(long currentOffset) {
        this.currentOffset = currentOffset;
    }

    public long getMaxOffset() {
        return maxOffset;
    }

    public void setMaxOffset(long maxOffset) {
        this.maxOffset = maxOffset;
    }

    @Override
    public String toString() {
        return "QueueModel{" +
                "id=" + id +
                ", minOffset=" + minOffset +
                ", currentOffset=" + currentOffset +
                ", maxOffset=" + maxOffset +
                '}';
    }
}
