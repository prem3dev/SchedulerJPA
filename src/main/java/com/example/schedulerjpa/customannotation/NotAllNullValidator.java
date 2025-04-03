package com.example.schedulerjpa.customannotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class NotAllNullValidator implements ConstraintValidator<NotAllNull, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // 객체 자체가 null이면 유효하지 않음
        }

        for (Field field : value.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(value) != null) {
                    return true; // 하나라도 값이 있으면 유효
                }
            } catch (IllegalAccessException ignored) {}
        }

        return false; // 모든 필드가 null이면 유효하지 않음
    }
}
