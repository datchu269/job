package com.example.customvalidation;

import com.example.customvalidation.custom.ValidateDictionary;
import com.example.customvalidation.entity.DeliveryType;
import com.example.customvalidation.entity.HI1Object;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

@SpringBootApplication
@Validated
public class CustomValidationApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(CustomValidationApplication.class, args);
//    }

    public static void main(String[] args) throws IllegalAccessException {
        HI1Object hi1Object = new HI1Object();
        DeliveryType deliveryType = new DeliveryType();
        deliveryType.setOwner("ETSI");
        deliveryType.setValue("inactive....");
        hi1Object.setDeliveryType(deliveryType);

        //validate annotation
        Class<?> clazz = hi1Object.getClass();
        //lặp đệ qui
        for (Field f : clazz.getDeclaredFields()) {
            for (Annotation a : f.getDeclaredAnnotations()) {
                if (a.annotationType().equals(ValidateDictionary.class)) {
                    // lấy giá trị đối chiếu
                    f.setAccessible(true);
                    DeliveryType compareValue = (DeliveryType) f.get(hi1Object);

                    //lấy ra giá trị enum để đối chiếu
                    Class<?> validDIct = ((ValidateDictionary) a).clazz();
                    Arrays.asList(validDIct.getEnumConstants()).stream().forEach(entry -> {
                        System.out.println(entry);
                    });

                }
            }
        }
    }
}
