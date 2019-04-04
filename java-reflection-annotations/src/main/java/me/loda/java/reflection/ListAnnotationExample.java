package me.loda.java.reflection;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.checkerframework.checker.units.qual.A;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 4/3/2019
 * Github: https://github.com/loda-kun
 */
public class ListAnnotationExample {
    public static void main(String[] args) {
        Class<Girl> girlClass = Girl.class;
        System.out.println("Class: "+girlClass.getSimpleName()); // Lấy ra tên Class
        for(Annotation annotation : girlClass.getDeclaredAnnotations()){
            System.out.println("Annotation: " + annotation.annotationType()); // Lấy ra tên các Annatation trên class này
        }

        for(Method method: girlClass.getDeclaredMethods()){
            System.out.println("\nMethod: " + method.getName());
            for(Annotation annotation : method.getAnnotations()){
                System.out.println("Annotation: " + annotation.annotationType()); // Lấy ra tên các Annatation trên class này
            }
        }
    }
}
