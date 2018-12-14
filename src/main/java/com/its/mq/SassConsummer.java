package com.its.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
@Component
public class SassConsummer {

    @JmsListener(destination = "queue2",containerFactory="saasFactory")
    private void consume(final Message msg) throws JMSException, Exception{

        System.out.println("第二个amq的消费者开始消费："+msg);

    }
}
