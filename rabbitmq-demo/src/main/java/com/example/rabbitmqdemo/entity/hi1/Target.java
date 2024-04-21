package com.example.rabbitmqdemo.entity.hi1;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class Target {
    private Long id;
    private String identifierType;
    private Long objectId;
}
