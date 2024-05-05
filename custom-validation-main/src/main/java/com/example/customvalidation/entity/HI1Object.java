package com.example.customvalidation.entity;

import com.example.customvalidation.custom.Hi1Validate;
import com.example.customvalidation.custom.ValidateType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HI1Object {
    public String objectIdentifier = "test";
    public String countryCode = "test";
    public String ownerIdentifier = "test";
    public String authorisationReference = "test";
    @Hi1Validate(ValidateType.OBJECT_DATA_TYPE)
    public AuthorisationTimespan authorisationTimespan;
    public String type = "test";
    public String text = "test";
    @Hi1Validate(ValidateType.OBJECT_DATA_TYPE)
    public AssociatedObjects associatedObjects;
    public String reference = "test";
    @Hi1Validate(ValidateType.OBJECT_DATA_TYPE)
    public TargetIdentifier targetIdentifier;
    @Hi1Validate(ValidateType.OBJECT_DATA_TYPE)
    public DeliveryType deliveryType;
    @Hi1Validate(ValidateType.OBJECT_DATA_TYPE)
    public List<DeliveryDetails> deliveryDetails;
    public CSPID cspid;
}
