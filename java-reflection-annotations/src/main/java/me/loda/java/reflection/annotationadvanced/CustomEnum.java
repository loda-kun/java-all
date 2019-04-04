package me.loda.java.reflection.annotationadvanced;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Convertible
@AllArgsConstructor
@Getter
public enum CustomEnum {
    HELLO(1),
    HAHA(2);

    @Convertible.Value
    private int value;


    @Convertible.Parser
    public static CustomEnum of(Integer value){
        switch (value){
            case 1: return HELLO;
            case 2: return HAHA;
            default: return null;
        }
    }
}
