package com.example.customvalidation.entity;

import com.example.customvalidation.constant.LIStatusEnum;
import com.example.customvalidation.custom.ValidateDictionary;
import lombok.Data;

@Data
public class HI1Object {
    public String ObjectIdentifier;
    public String CountryCode;
    public String OwnerIdentifier;
    public String AuthorisationReference;
    public AuthorisationTimespan AuthorisationTimespan;
    public String type;
    public String text;
    public AssociatedObjects AssociatedObjects;
    public String Reference;
    public TargetIdentifier TargetIdentifier;
    @ValidateDictionary(clazz = LIStatusEnum.class)
    public DeliveryType DeliveryType;
    public DeliveryDetails DeliveryDetails;
    public CSPID CSPID;
}
