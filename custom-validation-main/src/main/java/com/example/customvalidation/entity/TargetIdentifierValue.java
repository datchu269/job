package com.example.customvalidation.entity;

import com.example.customvalidation.custom.Hi1Validate;
import com.example.customvalidation.custom.ValidateType;
import lombok.Data;

@Data

public class TargetIdentifierValue {
	@Hi1Validate(ValidateType.OBJECT_DATA_TYPE)
	public FormatType formatType;
	public String value = "test";
}
