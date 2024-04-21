package com.example.rabbitmqdemo.entity.hi1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
public class DeliveryInfo {
    private Long id;
    private String liid;
    private Long objectId;
    @Builder.Default
    private List<DeliveryAddress> deliveryAddresses = new ArrayList<>();

}
