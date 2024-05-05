package com.example.customvalidation.entity;

import com.example.customvalidation.custom.Hi1Validate;
import com.example.customvalidation.custom.ValidateType;
import lombok.Data;

@Data

public class HI1Message {
	@Hi1Validate(ValidateType.OBJECT_DATA_TYPE)
	public Header Header;
	@Hi1Validate(ValidateType.OBJECT_DATA_TYPE)
	public Payload Payload;
	public String xmlns;
	public String xsi;
	public String common;
	public String task;
	public String auth;
	public String text;
}
