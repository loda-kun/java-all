package me.loda.java.reflection;

public abstract class Person {
    public static final int NAKED = 0;
    public static final int BIKINI = 0;
    public String getLocation() {
        return "Earth";
    }

    public  abstract int getOutfit();
}
