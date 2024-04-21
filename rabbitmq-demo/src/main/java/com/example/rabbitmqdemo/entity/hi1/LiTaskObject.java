package com.example.rabbitmqdemo.entity.hi1;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class LiTaskObject {
    private Long id;
    private Integer status;
    private Long objectId;
}
