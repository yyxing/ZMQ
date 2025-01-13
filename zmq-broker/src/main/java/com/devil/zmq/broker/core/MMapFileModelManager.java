package com.devil.zmq.broker.core;

import java.util.HashMap;
import java.util.Map;

public class MMapFileModelManager {

    private Map<String, MMapFileModel> mMapFileModelMap = new HashMap<>();

    public void put(String topic, MMapFileModel model) {
        if (model == null) {
            throw new IllegalArgumentException("topic file is not exist");
        }
        mMapFileModelMap.put(topic, model);
    }

    public MMapFileModel get(String topic) {
        return mMapFileModelMap.get(topic);
    }
}
