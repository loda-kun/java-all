package me.loda.java.reflection;

import java.lang.reflect.Field;

public class FieldExample {
    public static void main(String[] args) throws Exception {
        Girl girl = new Girl();
        girl.setName("Ngoc trinh");

        // Lay ra tat ca field cua object
        for(Field field : girl.getClass().getDeclaredFields()){
            System.out.println();
            System.out.println("Field: " +field.getName());
            System.out.println("Type: " +field.getType());
        }

        Field nameField = girl.getClass().getDeclaredField("name");
        nameField.setAccessible(true); // Cho phep truy cap vao field private
        nameField.set(girl, "Bella"); // Doi gia tri cua name trong object girl

        System.out.println(girl);
    }
}
