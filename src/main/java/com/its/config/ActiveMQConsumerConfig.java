package com.its.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

@Configuration
public class ActiveMQConsumerConfig {



    @Bean(name = "saasConnectionFactory")
    @Primary
    public ActiveMQConnectionFactory secondConnectionFactory(
            @Value("${spring.activemq.saas.broker-url}")String brokerUrl,
            @Value("${spring.activemq.saas.user}") String username,
            @Value("${spring.activemq.saas.password}") String password) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setUserName(username);
        factory.setPassword(password);
        return factory;
    }



    @Bean(name = "saasActivemqTemplate")
    public JmsMessagingTemplate saasActivemqTemplate(
            @Qualifier("saasConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        JmsMessagingTemplate template = new JmsMessagingTemplate(connectionFactory);
        return template;
    }



    @Bean(name = "saasFactory")
    @Primary
    public JmsListenerContainerFactory saasFactory(
            @Qualifier("saasConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
