package com.devil.zmq.broker;

import com.devil.zmq.broker.cache.CommonCache;
import com.devil.zmq.broker.config.GlobalPropertiesLoader;
import com.devil.zmq.broker.config.TopicConfigLoader;
import com.devil.zmq.broker.core.CommitLogAppendHandler;
import com.devil.zmq.broker.model.CommitLogMessage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BrokerApplication {
    private static GlobalPropertiesLoader globalPropertiesLoader;
    private static TopicConfigLoader topicConfigLoader;
    private static CommitLogAppendHandler commitLogAppendHandler;

    public static void initConfig() throws IOException {
        globalPropertiesLoader = new GlobalPropertiesLoader();
        topicConfigLoader = new TopicConfigLoader();
        commitLogAppendHandler = new CommitLogAppendHandler();
        commitLogAppendHandler.initTopicFile(CommonCache.getTopicModelMap().values());
        topicConfigLoader.startRefreshConfigJob();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        initConfig();
        for (int i = 0; i < 1000000; i++) {
            String hello = "hello" + i;
            CommitLogMessage commitLogMessage = new CommitLogMessage(hello.getBytes());
            commitLogAppendHandler.appendMessage("order_topic", commitLogMessage);
            System.out.println("发送第" + i + "条");
        }
        System.out.println();
    }
}
