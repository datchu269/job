package com.example.customvalidation.custom;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateDictionary {
    String owner() default "ETSI";

    Class<?> clazz();

    Class<? extends Payload>[] payload() default {};
}
