package com.example.customvalidation.custom;

import com.example.customvalidation.entity.DeliveryType;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

@Component
public class Validate {
    void validate() {

    }

    static void scanFieldsRecursive(Class<?> clazz, Object object) {
    
//        //validate annotation
//        Class<?> clazz = hi1Object.getClass();
//        //lặp đệ qui
//        for (Field f : clazz.getDeclaredFields()) {
//            for (Annotation a : f.getDeclaredAnnotations()) {
//                if (a.annotationType().equals(ValidateDictionary.class)) {
//                    // lấy giá trị đối chiếu
//                    f.setAccessible(true);
//                    DeliveryType compareValue = (DeliveryType) f.get(hi1Object);
//
//                    //lấy ra giá trị enum để đối chiếu
//                    Class<?> validDIct = ((ValidateDictionary) a).clazz();
//                    Arrays.asList(validDIct.getEnumConstants()).stream().forEach(entry -> {
//                        System.out.println(entry);
//                    });
//
//                }
//            }
//        }


        // Quét annotations của các field của class hiện tại
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // Đảm bảo có thể truy cập vào trường private
            try {
                Object fieldValue = field.get(object);
                // todo: check nếu field có trong config mà giá trị null thì return error
                // Kiểm tra nếu trường là một đối tượng
                if (fieldValue != null && !field.getType().isPrimitive() && field.getType() != DeliveryType.class) {
                    // In ra các annotations của trường
                    Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
                    for (Annotation annotation : fieldAnnotations) {
                        if (annotation.annotationType().equals(ValidateDictionary.class)) {
                            // lấy giá trị đối chiếu
                            DeliveryType compareValue = (DeliveryType) fieldValue;

                            //lấy ra giá trị enum để đối chiếu
                            Class<?> validDIct = ((ValidateDictionary) annotation).clazz();
                            Arrays.asList(validDIct.getEnumConstants()).stream().forEach(entry -> {
                                Boolean result = compareValue == entry;
                                System.out.println(entry);
                            });

//                        } else if () {
//                            // todo
                        }

                    }
                    // Nếu là một đối tượng, tiếp tục quét các trường của đối tượng đó
                    scanFieldsRecursive(field.getType(), fieldValue);
                } else {
                    // In ra các annotations của trường
                    Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
                    for (Annotation annotation : fieldAnnotations) {
                        System.out.println("Found annotation: " + annotation.annotationType().getSimpleName() + " on field: " + field.getName());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}


//public class AnnotationScanner {
//
//    public static void scanAnnotations(Object object) {
//        scanFieldsRecursive(object.getClass(), object);
//    }
//
//    private static void scanFieldsRecursive(Class<?> clazz, Object object) {
//        // Quét annotations của các field của class hiện tại
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true); // Đảm bảo có thể truy cập vào trường private
//            try {
//                Object fieldValue = field.get(object);
//                // Kiểm tra nếu trường là một đối tượng
//                if (fieldValue != null && !field.getType().isPrimitive()) {
//                    // In ra các annotations của trường
//                    Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
//                    for (Annotation annotation : fieldAnnotations) {
//                        if (annotation.annotationType().isAnnotationPresent(ValidateAnnotation.class)) {
//                            System.out.println("Found validation annotation: " + annotation.annotationType().getSimpleName() + " on field: " + field.getName());
//                            // Thực hiện xử lý validate tại đây (ví dụ: gọi các phương thức validate)
//                            handleValidation(annotation, fieldValue);
//                        }
//                    }
//                    // Nếu là một đối tượng, tiếp tục quét các trường của đối tượng đó
//                    scanFieldsRecursive(field.getType(), fieldValue);
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private static void handleValidation(Annotation annotation, Object value) {
//        // Ví dụ: Xử lý validate dựa trên các loại annotation
//        if (annotation.annotationType() == NotNull.class) {
//            if (value == null) {
//                System.out.println("Validation error: Field is null");
//                // Xử lý lỗi ở đây
//            }
//        } else if (annotation.annotationType() == Range.class) {
//            Range range = (Range) annotation;
//            if (value instanceof Integer) {
//                int intValue = (int) value;
//                if (intValue < range.min() || intValue > range.max()) {
//                    System.out.println("Validation error: Field is out of range");
//                    // Xử lý lỗi ở đây
//                }
//            }
//        } else if (annotation.annotationType() == Email.class) {
//            if (value instanceof String) {
//                String emailValue = (String) value;
//                if (!isValidEmail(emailValue)) {
//                    System.out.println("Validation error: Invalid email format");
//                    // Xử lý lỗi ở đây
//                }
//            }
//        }
//        // Các loại validate khác có thể được xử lý ở đây
//    }
//
//    private static boolean isValidEmail(String email) {
//        // Phương thức kiểm tra định dạng email
//        // Đây chỉ là một ví dụ đơn giản, bạn có thể sử dụng thư viện hoặc regex phức tạp hơn
//        return email.contains("@");
//    }
//
//    public static void main(String[] args) {
//        User user = new User(null, 30, "example.com");
//        scanAnnotations(user);
//    }
//}
//
//// Một ví dụ về lớp có annotation và các thành phần khác được ánh xạ
//class User {
//    @NotNull
//    private String name;
//
//    @Range(min = 0, max = 100)
//    private int age;
//
//    @Email
//    private String email;
//
//    public User(String name, int age, String email) {
//        this.name = name;
//        this.age = age;
//        this.email = email;
//    }
//}
//
//// Các annotation validate
//@interface NotNull {}
//@interface Range {
//    int min();
//    int max();
//}
//@interface Email {}
//
//// Annotation dùng để đánh dấu các annotation validate
//@interface ValidateAnnotation {}
