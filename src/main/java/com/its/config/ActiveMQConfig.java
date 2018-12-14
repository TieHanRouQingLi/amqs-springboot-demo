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
public class ActiveMQConfig {
    @Bean(name = "innerConnectionFactory")
    public ActiveMQConnectionFactory firstConnectionFactory(
            @Value("${spring.activemq.inner.broker-url}") String brokerUrl,
            @Value("${spring.activemq.inner.user}") String username,
            @Value("${spring.activemq.inner.password}") String password) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setUserName(username);
        factory.setPassword(password);
        return factory;
    }



    @Bean(name = "innerActivemqTemplate")
    public JmsMessagingTemplate innerActivemqTemplate(
            @Qualifier("innerConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        JmsMessagingTemplate template = new JmsMessagingTemplate(connectionFactory);
        return template;
    }



    @Bean(name = "innerFactory")
    public JmsListenerContainerFactory innerFactory(
            @Qualifier("innerConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }


}
