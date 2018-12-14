package com.its.mq;

import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.Message;

public class InnerConsummer {

    @JmsListener(destination = "ALIM_PHASE_COUNT",containerFactory="innerFactory")
    private void consume(final Message msg) throws JMSException, Exception{
        System.out.println("第二个amq的消费者开始消费："+msg);
    }
}
