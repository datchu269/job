package com.example.rabbitmqdemo2.comsumer;
import com.example.rabbitmqdemo2.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class LiTaskConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(LiTaskConsumer.class);

//    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
//    public void consumeJsonMessage(User user){
//        LOGGER.info(String.format("Received JSON message -> %s", user.toString()));
//    }
}
