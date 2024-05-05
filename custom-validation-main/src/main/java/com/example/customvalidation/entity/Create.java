package com.example.customvalidation.entity;

import com.example.customvalidation.custom.Hi1Validate;
import com.example.customvalidation.custom.ValidateType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Create {
	@Hi1Validate(ValidateType.OBJECT_DATA_TYPE)
	public HI1Object hi1Object;
}
