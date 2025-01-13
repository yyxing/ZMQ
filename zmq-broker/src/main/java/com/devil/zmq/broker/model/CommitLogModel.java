package com.devil.zmq.broker.model;

import com.devil.zmq.broker.constants.Constants;
import com.devil.zmq.broker.utils.CommitLogUtil;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class CommitLogModel implements Serializable {
    private String filename;
    private AtomicInteger offset;
    private AtomicInteger index;
    private int offsetLimit;

    public int diff() {
        return offsetLimit - offset.get();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public AtomicInteger getOffset() {
        return offset;
    }

    public void setOffset(AtomicInteger offset) {
        this.offset = offset;
    }

    public AtomicInteger getIndex() {
        return index;
    }

    public void setIndex(AtomicInteger index) {
        this.index = index;
    }

    public int getOffsetLimit() {
        return offsetLimit;
    }

    public void setOffsetLimit(int offsetLimit) {
        this.offsetLimit = offsetLimit;
    }

    public void handleSpaceFull() {
        this.setOffset(new AtomicInteger(0));
        this.setOffsetLimit(Constants.defaultMMapSize);
        this.getIndex().addAndGet(1);
        this.setFilename(CommitLogUtil.generateFileName(this.getIndex().get()));
    }

    @Override
    public String toString() {
        return "CommitLogModel{" +
                "filename='" + filename + '\'' +
                ", offset=" + offset +
                ", index=" + index +
                ", offsetLimit=" + offsetLimit +
                '}';
    }
}
