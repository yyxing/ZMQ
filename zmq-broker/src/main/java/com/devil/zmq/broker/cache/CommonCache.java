package com.devil.zmq.broker.cache;

import com.devil.zmq.broker.config.GlobalProperties;
import com.devil.zmq.broker.config.TopicConfig;
import com.devil.zmq.broker.model.TopicModel;

import java.util.List;
import java.util.Map;

public class CommonCache {

    private static GlobalProperties globalProperties;
    private static TopicConfig topicConfig;
    private static Map<String, TopicModel> topicModelMap;

    public static GlobalProperties getGlobalProperties() {
        return globalProperties;
    }

    public static void setGlobalProperties(GlobalProperties globalProperties) {
        CommonCache.globalProperties = globalProperties;
    }

    public static TopicConfig getTopicConfig() {
        return topicConfig;
    }

    public static void setTopicConfig(TopicConfig topicConfig) {
        CommonCache.topicConfig = topicConfig;
    }

    public static Map<String, TopicModel> getTopicModelMap() {
        return topicModelMap;
    }

    public static void setTopicModelMap(Map<String, TopicModel> topicModelMap) {
        CommonCache.topicModelMap = topicModelMap;
    }
}
