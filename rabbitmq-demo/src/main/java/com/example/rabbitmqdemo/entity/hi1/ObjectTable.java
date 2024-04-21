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
public class ObjectTable {
    private Long id;
    private String objectType;
    private String objectIdentify;
    @Builder.Default
    private List<LiTaskObject> liTaskObjects = new ArrayList<>();
    @Builder.Default
    private List<Target> targets = new ArrayList<>();
    @Builder.Default
    private List<DeliveryInfo> deliveryInfos = new ArrayList<>();
}
