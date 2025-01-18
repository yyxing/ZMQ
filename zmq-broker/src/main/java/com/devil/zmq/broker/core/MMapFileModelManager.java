package com.devil.zmq.broker.core;

import java.util.HashMap;
import java.util.Map;

public class MMapFileModelManager {

    private Map<String, MappedFile> mMapFileModelMap = new HashMap<>();

    public void put(String topic, MappedFile mappedFile) {
        if (mappedFile == null) {
            throw new IllegalArgumentException("topic file is not exist");
        }
        mMapFileModelMap.put(topic, mappedFile);
    }

    public MappedFile get(String topic) {
        return mMapFileModelMap.get(topic);
    }
}
