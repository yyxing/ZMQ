package com.devil.zmq.broker.cache;

import com.devil.zmq.broker.config.GlobalProperties;
import com.devil.zmq.broker.model.TopicConfig;

import java.util.Map;

public class CommonCache {

    private static GlobalProperties globalProperties;
    private static com.devil.zmq.broker.config.TopicConfig topicConfig;
    private static Map<String, TopicConfig> topicConfigMap;

    public static GlobalProperties getGlobalProperties() {
        return globalProperties;
    }

    public static void setGlobalProperties(GlobalProperties globalProperties) {
        CommonCache.globalProperties = globalProperties;
    }

    public static com.devil.zmq.broker.config.TopicConfig getTopicConfig() {
        return topicConfig;
    }

    public static void setTopicConfig(com.devil.zmq.broker.config.TopicConfig topicConfig) {
        CommonCache.topicConfig = topicConfig;
    }

    public static Map<String, TopicConfig> getTopicConfigMap() {
        return topicConfigMap;
    }

    public static void setTopicConfigMap(Map<String, TopicConfig> topicModelMap) {
        CommonCache.topicConfigMap = topicModelMap;
    }
}
