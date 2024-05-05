package com.example.rabbitmqdemo.producer;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.rabbitmqdemo.config.AppConfig.*;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue}")
    public String QUEUE;

    @Value("${rabbitmq.exchange}")
    public String EXCHANGE;

    @Value("${rabbitmq.routingkey}")
    public String ROUTING_KEY;

    @Value("${spring.rabbitmq.username}")
    public String USERNAME;

    @Value("${spring.rabbitmq.password}")
    public String PASSWORD;

    @Value("${spring.rabbitmq.host}")
    public String HOST;

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(HOST);
        cachingConnectionFactory.setUsername(USERNAME);
        cachingConnectionFactory.setPassword(PASSWORD);
        return cachingConnectionFactory;
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
