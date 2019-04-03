package me.loda.java.reflection;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class MethodExample {
    public static void main(String[] args) throws Exception {
        Class<Girl> girlClass = Girl.class;

        // Su dung getDeclaredMethods de lay ra nhung method cua class va cha no tu dinh nghia thoi.
        Method[] methods = girlClass.getDeclaredMethods();
        for(Method method : methods){
            System.out.println();
            System.out.println("Method: " + method.getName());
            System.out.println("Parameters: " + Arrays.toString(method.getParameters()));
        }

        // Lay ra method ten la setName va co 1 tham so truyen vao -> setName(String name)
        Method methodSetName = girlClass.getMethod("setName", String.class);

        // Thuc hien ham setName, tren object girl
        Girl girl = new Girl();
        methodSetName.invoke(girl, "Ngoc Trinh");
        System.out.println(girl);
    }
}
