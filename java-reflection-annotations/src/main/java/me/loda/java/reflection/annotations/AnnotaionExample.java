package me.loda.java.reflection.annotations;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Optional;

public class AnnotaionExample {
    public static void main(String[] args) throws IllegalAccessException {
        SuperMan superMan = new SuperMan(); // Tao doi tuong super man
        superMan.setDateOfBirth(LocalDateTime.now());
        superMan.setName("loda");

        StringBuilder sb = new StringBuilder(); // Sung string builder de tao json tu class

        Class<?> clazz = superMan.getClass();
        JsonName jsonClassName = clazz.getDeclaredAnnotation(JsonName.class); // Lay ra annotation tren Class Superman

        sb.append("{\n")
          .append("\t\"")
          // Lay gia tri cua Annotation, neu annotation la null thi lay ten Class thay the
          .append(Optional.ofNullable(jsonClassName).map(JsonName::value).orElse(clazz.getSimpleName()))
          .append("\": {\n"); //


        Field fields[] = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true); // Set setAccessible = true. De co the truy cap vao field la private
            JsonName jsonFieldName = fields[i].getDeclaredAnnotation(JsonName.class); // get annotation tren field
            sb.append("\t\t\"")
              // Lay gia tri cua Annotation, neu annotation la null thi lay ten field thay the
              .append(Optional.ofNullable(jsonFieldName).map(JsonName::value).orElse(fields[i].getName())) // L
              .append("\": ")
              // Neu field la String hoac Object. thi append dau ngoac kep vao
              .append(fields[i].getType() == String.class || !fields[i].getType().isPrimitive() ? "\"" : "")
              // Lay gia tri cua field
              .append(fields[i].get(superMan))
              // Neu field la String hoac Object. thi append dau ngoac kep vao
              .append(fields[i].getType() == String.class || !fields[i].getType().isPrimitive()? "\"" : "")
              .append(i != fields.length -1 ? ",\n" : "\n");
        }
        sb.append("\t}\n");
        sb.append("}");

        System.out.println(sb.toString());
    }
}
