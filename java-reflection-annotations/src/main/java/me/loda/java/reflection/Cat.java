package me.loda.java.reflection;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Cat extends Animal implements Say {

    public static final String SAY = "Meo meo";
    public static final int NUMBER_OF_LEGS = 4;
    // Private field
    public int age;
    // Private field.
    private String name;

    @Override
    public int getNumberOfLegs() {
        return NUMBER_OF_LEGS;
    }

    @Override
    public String say() {
        return SAY;
    }
}
