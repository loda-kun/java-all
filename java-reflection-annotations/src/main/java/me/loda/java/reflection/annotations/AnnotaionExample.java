package me.loda.java.reflection.annotations;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Optional;

public class AnnotaionExample {
    public static void main(String[] args) throws IllegalAccessException {
        SuperMan superMan = new SuperMan(); // Tao doi tuong super man
        superMan.setDateOfBirth(LocalDateTime.now());
        superMan.setName("loda");

        String json =JsonNameProcessor.toJson(superMan);
        System.out.println(json);
    }


}
