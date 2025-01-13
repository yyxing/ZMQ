package com.devil.zmq.broker.config;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.devil.zmq.broker.cache.CommonCache;
import com.devil.zmq.broker.constants.Constants;
import com.devil.zmq.broker.model.TopicModel;
import com.devil.zmq.broker.utils.BrokerUtil;
import com.devil.zmq.broker.utils.ThreadPoolUtil;
import com.devil.zmq.common.UtilAll;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TopicConfigLoader {

    private TopicConfig topicConfig;

    public TopicConfigLoader() {
        this.loadConfig();
    }

    public void loadConfig() {
        String configPath = BrokerUtil.getConfigPath();
        String json = UtilAll.readFromFile(configPath);
        List<TopicModel> topicConfigs = JSONObject.parseArray(json, TopicModel.class);
        CommonCache.setTopicModelMap(topicConfigs.stream().collect(Collectors.toMap(TopicModel::getTopic, item -> item)));
    }


    public void startRefreshConfigJob() {
        ThreadPoolUtil.addScheduledTask(() -> {
            List<TopicModel> values = new ArrayList<>(CommonCache.getTopicModelMap().values());
            values.sort(Comparator.comparingInt(TopicModel::getOrder));
            FileUtil.writeString(JSONObject.toJSONString(values), BrokerUtil.getConfigPath(), Charset.defaultCharset());
        }, Constants.defaultFlushInterval, TimeUnit.SECONDS);
    }
}
