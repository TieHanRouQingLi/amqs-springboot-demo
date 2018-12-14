package com.its.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Date;

@Component
@EnableScheduling
public class MyProductor {
    @Resource
    private JmsMessagingTemplate innerActivemqTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay=2000)//每2s执行1次
    public void send() {
        //send queue.
        this.innerActivemqTemplate.convertAndSend(this.queue, "hi,activeMQ(queue)");
        //send topic.
        //this.innerActivemqTemplate.convertAndSend(this.topic, "hi,activeMQ(topic)");
    }
}
