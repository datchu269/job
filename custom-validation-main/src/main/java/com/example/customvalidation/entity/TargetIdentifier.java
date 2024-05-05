package com.example.customvalidation.entity;

import com.example.customvalidation.custom.Hi1Validate;
import com.example.customvalidation.custom.ValidateType;
import lombok.Data;

@Data

public class TargetIdentifier {
	@Hi1Validate(ValidateType.OBJECT_DATA_TYPE)
	public TargetIdentifierValues targetIdentifierValues;
}
