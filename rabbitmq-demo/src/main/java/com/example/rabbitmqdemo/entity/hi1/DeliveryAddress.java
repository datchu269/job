package com.example.rabbitmqdemo.entity.hi1;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class DeliveryAddress {
    private Long id;
    private Integer port;
    private Long deliveryInfoId;
}
