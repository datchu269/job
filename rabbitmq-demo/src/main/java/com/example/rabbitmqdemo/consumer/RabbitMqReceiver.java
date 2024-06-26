//package com.example.rabbitmqdemo.consumer;
//
//import com.example.rabbitmqdemo.producer.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
//import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RabbitMqReceiver implements RabbitListenerConfigurer {
//    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);
//
//    @Override
//    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
//    }
//
//    @RabbitListener(queues = "${rabbitmq.queue}")
//    public void receivedMessage(User user) {
//        logger.info("User Details Received is.. " + user);
//    }
//}
