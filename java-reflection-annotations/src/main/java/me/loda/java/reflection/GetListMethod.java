package me.loda.java.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class GetListMethod {

    public @interface ReadOnly{

    }

    @ReadOnly
    public static int publicStaticMethod(int a) {
        return 0;
    }

    private static void privateStaticMethod(String b, Long c) {

    }

    public static void main(String[] args) {
        Method[] methods = GetListMethod.class.getDeclaredMethods();
        for(Method method: methods){
            System.out.println("====================================");
            System.out.println("toGenericString(): " + method.toGenericString());
            System.out.println("getName(): "+ method.getName());
            System.out.println("getModifiers(): " + Modifier.toString(method.getModifiers()));
            System.out.println("getParameters(): " + Arrays.toString(method.getParameters()));
            System.out.println("getTypeParameters(): " + Arrays.toString(method.getTypeParameters()));
            System.out.println("getAnnotatedReturnType(): " + method.getAnnotatedReturnType().getType()); // trả về type của giá trị trả về
            System.out.println("getDeclaringClass(): " + method.getDeclaringClass());
            System.out.println("getDefaultValue(): " + method.getDefaultValue());
            System.out.println("====================================");

        }
    }

    // Protected method
    protected <T> void protectedMethod(T hello) {

    }

    private void privateMethod() {

    }

    public void publicMethod() {

    }
}
