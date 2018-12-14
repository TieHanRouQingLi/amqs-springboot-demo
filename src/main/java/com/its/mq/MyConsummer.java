package com.its.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

@Component
public class MyConsummer {

    @Resource
    private JmsMessagingTemplate saasActivemqTemplate;

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory  connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    /*@JmsListener(destination = "samble.queue",containerFactory = "jmsListenerContainerQueue") // 监听指定消息主题
    public void receiveQueue(String message) {
        System.out.println("Consumer的消费的内容是："+message);
    }*/
    /*@JmsListener(destination = "samble.topic",containerFactory = "jmsListenerContainerTopic")
    public void receiveTopic(String consumer) {
        System.out.println(consumer + "消息已经消费了");
    }*/

    @JmsListener(destination = "samble.queue",containerFactory = "innerFactory")
    public void receiveQueue(String consumer) {
        System.out.println(consumer + "消息已经消费了,第一个amq服务");
        this.saasActivemqTemplate.convertAndSend("queue2", consumer);
        System.out.println("开始向第二个amq服务发送消息！");


    }
}

