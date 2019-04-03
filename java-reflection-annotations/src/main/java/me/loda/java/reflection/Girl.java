package me.loda.java.reflection;

public class Girl extends Person {
    private String name;

    public Girl() {

    }

    public Girl(String name) {
        this.name = name;
    }

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
