//package com.devil.zmq.common;
//
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.common.message.MessageExt;
//
//import java.util.List;
//
//public class Consumer {
//    public static void main(String[] args) throws Exception {
//        // 初始化消费者配置
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_group");
//        consumer.setNamesrvAddr("localhost:9876"); // 设置NameServer地址
//        consumer.subscribe("test_topic", "*"); // 订阅主题和标签
//
//        // 注册消息监听器
//        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
//            for (MessageExt msg : msgs) {
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), new String(msg.getBody()));            // 消费完成后关闭消费者
//            }
//            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//        });
//
//        // 启动消费者
//        consumer.start();
//        System.out.printf("Consumer Started.%n");
//    }
//}
