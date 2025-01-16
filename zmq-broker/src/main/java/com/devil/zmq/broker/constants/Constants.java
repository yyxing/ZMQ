package com.devil.zmq.broker.constants;

public class Constants {

    public static final String RW_MODE = "rw";

    public static final String ZMQ_HOME = "ZMQ_HOME";
    public static final String BROKER_CONFIG_PATH = "/broker/config/zmq-topic.json";
    public static final String BROKER_COMMIT_LOG_PATH = "/broker/store/commitlog/";
    public static final String BROKER_CONSUME_QUEUE_PATH = "/broker/store/consumequeue/";
    public static final int defaultCommitLogSize = 1024 * 1024 * 4;
    public static final int defaultConsumeQueueSize = 1024 * 1024 * 5;
    public static final int defaultFlushInterval = 10;

    public static String getMqHome() {
        return System.getenv(ZMQ_HOME);
    }

    public static String defaultTopicFile() {
        return "00000000";
    }

    public static void main(String[] args) {
        System.out.println(getMqHome());
    }

}
