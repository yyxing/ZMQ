package com.devil.zmq.broker.core;

import com.devil.zmq.broker.constants.Constants;
import com.devil.zmq.broker.constants.LoggerName;
import com.devil.zmq.broker.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BrokerController {
    private static final Logger log = LoggerFactory.getLogger(LoggerName.BROKER_LOGGER_NAME);
    private TopicConfigManager topicConfigManager;

    public BrokerController() {
        topicConfigManager = new TopicConfigManager();
    }

    public void startRefreshConfigJob() {
        ThreadPoolUtil.addScheduledTask(() -> {
            topicConfigManager.persist();
        }, Constants.defaultFlushInterval, TimeUnit.SECONDS);
    }

    public TopicConfigManager getTopicConfigManager() {
        return topicConfigManager;
    }


}
