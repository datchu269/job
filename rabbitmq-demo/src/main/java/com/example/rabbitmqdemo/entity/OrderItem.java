package com.example.rabbitmqdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class OrderItem {
    private Long id;
    private Long orderId;
    private String productName;
    private int quantity;
    private double price;

    // constructor, getters và setters
}
