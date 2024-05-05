package com.example.rabbitmqdemo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class ProducerController {
    @Autowired
    private RabbitMQSender rabbitMqSender;


    @PostMapping(value = "user")
    public String publishUserDetails(@RequestBody UserTest user) {
        rabbitMqSender.send(user);
        return "OK";
    }
}
