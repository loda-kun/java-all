package me.loda.java.reflection.annotations;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 4/3/2019
 * Github: https://github.com/loda-kun
 */
public class JsonNameProcessor {
    public static String toJson(Object object) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder(); // Sung string builder de tao json tu class

        Class<?> clazz = object.getClass();
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
              .append(fields[i].get(object))
              // Neu field la String hoac Object. thi append dau ngoac kep vao
              .append(fields[i].getType() == String.class || !fields[i].getType().isPrimitive()? "\"" : "")
              .append(i != fields.length -1 ? ",\n" : "\n");
        }
        sb.append("\t}\n");
        sb.append("}");

        return sb.toString();
    }
}
