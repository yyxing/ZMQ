package com.devil.zmq.broker.model;

import java.io.Serializable;

public class ConsumeQueue implements Serializable {
    private int commitLogFileIndex;
    private int commitLogOffset;
    private int size;

    public int getCommitLogFileIndex() {
        return commitLogFileIndex;
    }

    public void setCommitLogFileIndex(int commitLogFileIndex) {
        this.commitLogFileIndex = commitLogFileIndex;
    }

    public int getCommitLogOffset() {
        return commitLogOffset;
    }

    public void setCommitLogOffset(int commitLogOffset) {
        this.commitLogOffset = commitLogOffset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
