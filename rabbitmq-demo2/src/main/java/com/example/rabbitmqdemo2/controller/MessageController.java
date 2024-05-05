package com.example.rabbitmqdemo2.controller;

import com.example.rabbitmqdemo2.dto.User;
import com.example.rabbitmqdemo2.producer.Hi1ObjectProducer;
import com.example.rabbitmqdemo2.producer.LiTaskProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    @Autowired
    private Hi1ObjectProducer producer;
    @Autowired
    private LiTaskProducer liTaskProducer;


    // http://localhost:8080/api/v1/publish?message=hello
    @GetMapping("/hi1object")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }

    @PostMapping("/liTask")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        liTaskProducer.sendLiTaskMessage(user);
        return ResponseEntity.ok("LiTask message sent to RabbitMQ ...");
    }
}
