//package com.devil.zmq.common;
//
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.common.message.Message;
//
//public class Producer {
//    public static void main(String[] args) throws Exception {
//        // 初始化生产者配置
//        DefaultMQProducer producer = new DefaultMQProducer("producer_group");
//        producer.setNamesrvAddr("localhost:9876"); // 设置NameServer地址
//        producer.start();
//
//        // 发送消息
//        for (int i = 0; i < 10; i++) {
//            Message msg = new Message("test_topic", "tagA", ("Hello RocketMQ " + i).getBytes());
//            producer.send(msg);
//            System.out.println("Send message: " + msg);
//        }
//
//        // 关闭生产者
//        producer.shutdown();
//    }
//}
