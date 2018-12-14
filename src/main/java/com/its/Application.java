package com.its;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;
import javax.jms.Topic;

@SpringBootApplication
public class Application {
    @Bean
    public Queue queue(){
        return  new ActiveMQQueue("samble.queue");
    }
    @Bean
    public Topic topic(){
        return  new ActiveMQTopic("samble.topic");
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
