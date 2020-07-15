package com.yuan.rocketmq.producer;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * @author by yuanlai
 * @Date 2020/7/14 4:24 下午
 * @Description: TODO
 * @Version 1.0
 */
@SpringBootApplication
public class RocketMQProducerApplication  implements CommandLineRunner {
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Value("${demo.rocketmq.IMTopic}")
    private String IMTopic;

    public static void main(String[] args) {
        SpringApplication.run(RocketMQProducerApplication.class,args);

    }


    @Override
    public void run(String... args) throws Exception {
// Send string
        SendResult sendResult = rocketMQTemplate.syncSend(IMTopic, "Hello, World!");
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", IMTopic, sendResult);

        // Send string with spring Message
        sendResult = rocketMQTemplate.syncSend(IMTopic, MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        System.out.printf("syncSend2 to topic %s sendResult=%s %n", IMTopic, sendResult);

    }
}
