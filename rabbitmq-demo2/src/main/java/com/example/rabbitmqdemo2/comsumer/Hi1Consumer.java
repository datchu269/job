package com.example.rabbitmqdemo2.comsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Hi1Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hi1Consumer.class);

//    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
//    public void consume(String message){
//        LOGGER.info(String.format("Received message -> %s", message));
//    }
}
