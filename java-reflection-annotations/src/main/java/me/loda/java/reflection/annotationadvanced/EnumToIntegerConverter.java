package me.loda.java.reflection.annotationadvanced;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;

import com.google.common.reflect.TypeToken;

import me.loda.java.reflection.annotationadvanced.Convertible.Parser;
import me.loda.java.reflection.annotationadvanced.Convertible.Value;

public class EnumToIntegerConverter<T extends Enum> implements AttributeConverter<T, Integer> {
    private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) { };
    private final Class<? super T>  clazz = typeToken.getRawType(); // or getRawType() to return Class<? super T>

    @Override
    public Integer convertToDatabaseColumn(T attribute) {
        Convertible convertible = clazz.getDeclaredAnnotation(Convertible.class);
        if (convertible == null) {
            throw new RuntimeException("Enum is not convertible!");
        }
        Field field = Stream.of(clazz.getDeclaredFields())
                            .filter(f -> {
                                f.setAccessible(true);
                                return f.getAnnotation(Value.class) != null;
                            })
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Enum is not convertible!"));
        Integer integer;
        try {
            integer = (Integer) field.get(attribute);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return integer;
    }

    @Override
    public T convertToEntityAttribute(Integer dbData) {
        Convertible convertible = clazz.getDeclaredAnnotation(Convertible.class);
        if (convertible == null) {
            throw new RuntimeException("Enum is not convertible!");
        }
        Method method = Stream.of(clazz.getDeclaredMethods())
                             .filter(m -> m.getAnnotation(Parser.class) != null)
                             .findFirst()
                             .orElseThrow(() -> new RuntimeException("Enum is not convertible!"));

        try {
            return (T) method.invoke(null, dbData);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
