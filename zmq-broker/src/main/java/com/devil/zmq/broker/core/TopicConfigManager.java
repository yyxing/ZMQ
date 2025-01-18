package com.devil.zmq.broker.core;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.devil.zmq.broker.constants.LoggerName;
import com.devil.zmq.broker.model.TopicConfig;
import com.devil.zmq.broker.utils.BrokerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TopicConfigManager extends ConfigManager {

    protected static final Logger log = LoggerFactory.getLogger(LoggerName.BROKER_LOGGER_NAME);
    protected ConcurrentMap<String, TopicConfig> topicConfigTable = new ConcurrentHashMap<>(1024);

    @Override
    public String encode() {
        return encode(false);
    }

    @Override
    public String encode(boolean prettyFormat) {
        List<TopicConfig> configs = getTopicConfigList();
        return JSONObject.toJSONString(configs, prettyFormat);
    }

    @Override
    public void decode(String json) {
        List<TopicConfig> topicConfigConfigs = JSONObject.parseArray(json, TopicConfig.class);
        for (TopicConfig topicConfig : topicConfigConfigs) {
            this.topicConfigTable.put(topicConfig.getTopic(), topicConfig);
        }
    }

    @Override
    public String getConfigFilePath() {
        return BrokerUtil.getConfigPath();
    }

    public TopicConfig selectTopicConfig(String topic) {
        return this.topicConfigTable.get(topic);
    }

    public TopicConfig putTopicConfig(final TopicConfig topicConfig) {
        return this.topicConfigTable.put(topicConfig.getTopic(), topicConfig);
    }

    public List<TopicConfig> getTopicConfigList() {
        return CollectionUtil.sort(this.topicConfigTable.values(), Comparator.comparingInt(TopicConfig::getOrder));
    }

}
