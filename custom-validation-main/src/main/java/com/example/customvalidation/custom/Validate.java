package com.example.customvalidation.custom;

import com.example.customvalidation.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class Validate {
    public static final String CREATE_NAME = "ActionCreate";

    public static Boolean validateCreate(Class<?> clazz, Object object, String nameSpace) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
//                String nameField = convertToUpperCaseWithUnderscores(field.getName());
                String nameField = convertToUpperCaseFirstLetter(field.getName());
                StringBuilder nameSpaceNew = new StringBuilder(nameSpace).append(".").append(nameField);

                System.err.println("name space -----> " + nameSpaceNew);

                Object fieldValue = field.get(object);

                if (fieldValue != null) {
                    if (field.getType().equals(List.class)) {
                        // Nếu thuộc tính là List, xử lý từng phần tử trong danh sách
                        List<?> listValue = (List<?>) fieldValue;
                        for (int i = 0; i < listValue.size(); i++) {
                            validateCreate(listValue.get(i).getClass(), listValue.get(i), nameSpaceNew.toString());
                        }
                    } else {
                        // Nếu thuộc tính không phải là List, tiếp tục kiểm tra đệ quy
                        Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
                        for (Annotation annotation : fieldAnnotations) {
                            if (annotation.annotationType().equals(Hi1Validate.class)) {
                                Hi1Validate hi1Validate = (Hi1Validate) annotation;
                                if (hi1Validate.value().name().equals(ValidateType.OBJECT_DATA_TYPE.name())) {
                                    validateCreate(fieldValue.getClass(), fieldValue, nameSpaceNew.toString());
                                }
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
//    public static String convertToUpperCaseWithUnderscores(String input) {
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < input.length(); i++) {
//            char currentChar = input.charAt(i);
//            if (Character.isUpperCase(currentChar)) {
//                    result.append('_');
//            }
//            result.append(currentChar);
//        }
//        return result.toString().toUpperCase(Locale.ROOT);
//    }

    // c1
    public static String convertToUpperCaseWithUnderscores(String input) {
        String result = input.replaceAll("([a-z0-9])([A-Z])", "$1_$2");
        return result.toUpperCase(Locale.ROOT);
    }


    // c2
    public static String convertToUpperCaseFirstLetter(String input) {
        return StringUtils.capitalize(input);
    }


    // create data test
    public static Create create() {
        Create create = new Create();
        HI1Object hi1Object = new HI1Object();
        AuthorisationTimespan authorisationTimespan = new AuthorisationTimespan();

        TargetIdentifier targetIdentifier = new TargetIdentifier();
        TargetIdentifierValues targetIdentifierValues = new TargetIdentifierValues();
        TargetIdentifierValue targetIdentifierValue = new TargetIdentifierValue();
        FormatType formatType = new FormatType();
        targetIdentifierValue.setFormatType(formatType);
        targetIdentifierValues.setTargetIdentifierValue(targetIdentifierValue);
        targetIdentifier.setTargetIdentifierValues(targetIdentifierValues);

        DeliveryType deliveryType = new DeliveryType();

        List<DeliveryDetails> list = new ArrayList<>();
        DeliveryDetails deliveryDetails1 = new DeliveryDetails();
        DeliveryDetails deliveryDetails2 = new DeliveryDetails();
        DeliveryDestination deliveryDestination = new DeliveryDestination();
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryDestination.setDeliveryAddress(deliveryAddress);
        deliveryDetails1.setDeliveryDestination(deliveryDestination);
        deliveryDetails2.setDeliveryDestination(deliveryDestination);
        list.add(deliveryDetails1);
        list.add(deliveryDetails2);


        hi1Object.setDeliveryDetails(list);
        hi1Object.setDeliveryType(deliveryType);
        hi1Object.setTargetIdentifier(targetIdentifier);
        hi1Object.setAuthorisationTimespan(authorisationTimespan);
        create.setHi1Object(hi1Object);

        return create;
    }

    public static void main(String[] args) {
        Create create = create();

        validateCreate(create.getClass(), create, CREATE_NAME);

        System.out.println(create);
    }
}