package me.loda.java.reflection.annotationadvanced;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CustomEnumConverter extends EnumToIntegerConverter<CustomEnum> {
}
