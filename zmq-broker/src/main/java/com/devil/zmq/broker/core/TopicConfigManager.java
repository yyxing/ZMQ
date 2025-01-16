package com.devil.zmq.broker.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.devil.zmq.broker.cache.CommonCache;
import com.devil.zmq.broker.constants.LoggerName;
import com.devil.zmq.broker.model.TopicConfig;
import com.devil.zmq.broker.utils.BrokerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class TopicConfigManager extends ConfigManager {

    protected static final Logger log = LoggerFactory.getLogger(LoggerName.BROKER_LOGGER_NAME);
    protected ConcurrentMap<String, TopicConfig> topicConfigTable = new ConcurrentHashMap<>(1024);

    @Override
    public String encode() {
        return encode(false);
    }

    @Override
    public String encode(boolean prettyFormat) {
        return JSONObject.toJSONString(this.topicConfigTable, prettyFormat);
    }

    @Override
    public void decode(String json) {
        List<TopicConfig> topicConfigConfigs = JSONObject.parseArray(json, TopicConfig.class);
        CommonCache.setTopicConfigMap(topicConfigConfigs.stream().collect(Collectors.toMap(TopicConfig::getTopic, item -> item)));
        this.topicConfigTable.putAll(CommonCache.getTopicConfigMap());
    }

    @Override
    public String getConfigFilePath() {
        return BrokerUtil.getConfigPath();
    }
}
