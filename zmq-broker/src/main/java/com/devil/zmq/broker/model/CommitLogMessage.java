package com.devil.zmq.broker.model;

import cn.hutool.core.util.ByteUtil;

import java.io.Serializable;

public class CommitLogMessage implements Serializable {

    private byte[] content;

    public CommitLogMessage(byte[] content) {
        this.content = content;
    }

    public byte[] toBytes() {
        byte[] sizeBytes = ByteUtil.intToBytes(content.length);
        byte[] bytes = new byte[content.length + sizeBytes.length];
        int i = 0;
        for (byte sizeByte : sizeBytes) {
            bytes[i++] = sizeByte;
        }
        for (byte b : content) {
            bytes[i++] = b;
        }
        return bytes;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
