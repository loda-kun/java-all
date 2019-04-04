package me.loda.java.reflection;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
@Deprecated
public class Girl extends Person {

    private String name;

    public Girl() {

    }

    public Girl(String name) {
        this.name = name;
    }

    @Nullable
    public void setName(String name){
        this.name = name;
    }

    @Override
    public int getOutfit() {
        return NAKED;
    }

    @Override
    public String toString() {
        return "Girl{" +
               "name='" + name + '\'' +
               '}';
    }
}
