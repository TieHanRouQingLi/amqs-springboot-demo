package com.its.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
public class RunlogProducer {

    /**
     * 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
     */
    @Autowired
    private JmsMessagingTemplate innerActivemqTemplate;


    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     * @param destination 使用JMS具体实现
     * @param message 消息内容
     */
    //@Scheduled(fixedDelay=2000)//每2s执行1次
    public void sendMessage(Destination destination, final String message){

        innerActivemqTemplate.convertAndSend(destination, message);

    }
}
