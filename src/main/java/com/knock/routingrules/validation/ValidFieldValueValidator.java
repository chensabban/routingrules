package com.knock.routingrules.validation;

import com.knock.routingrules.dto.RuleConditionDto;
import com.knock.routingrules.model.Contact;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ValidFieldValueValidator implements ConstraintValidator<ValidFieldValue, RuleConditionDto> {
    
    private static final Map<String, Class<?>> FIELD_TYPES = new HashMap<>();
    
    static {
        // Use reflection to get Contact fields dynamically
        for (Field field : Contact.class.getDeclaredFields()) {
            FIELD_TYPES.put(field.getName(), field.getType());
        }
    }

    @Override
    public boolean isValid(RuleConditionDto condition, ConstraintValidatorContext context) {
        if (condition.getField() == null || condition.getValue() == null) {
            return true; // Let other validators handle null checks
        }
        
        Class<?> expectedType = FIELD_TYPES.get(condition.getField());
        if (expectedType == null) {
            return true; // Let field validator handle invalid field names
        }
        
        Object value = condition.getValue();
        boolean isValid = isValueTypeValid(value, expectedType);
        
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                String.format("Field '%s' expects %s but got %s", 
                    condition.getField(), 
                    getExpectedTypeDescription(expectedType),
                    value.getClass().getSimpleName())
            ).addConstraintViolation();
        }
        
        return isValid;
    }
    
    private boolean isValueTypeValid(Object value, Class<?> expectedType) {
        if (expectedType.isAssignableFrom(value.getClass())) {
            return true;
        }
        
        // Handle common type conversions
        return switch (value) {
            case String s when expectedType == String.class -> true;
            case Number number when isNumericType(expectedType) -> true;
            case String s when expectedType.isEnum() -> isValidEnum(s, (Class<? extends Enum<?>>) expectedType);
            case String s when expectedType == LocalDateTime.class ->
                    true; // Assume string can be parsed to LocalDateTime

            default -> false;
        };

    }
    
    @SuppressWarnings("unchecked")
    private boolean isValidEnum(String value, Class<? extends Enum<?>> enumClass) {
        try {
            Enum.valueOf((Class<? extends Enum>) enumClass, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    private boolean isNumericType(Class<?> type) {
        return type == Integer.class || type == int.class ||
               type == Double.class || type == double.class ||
               type == Float.class || type == float.class ||
               type == Long.class || type == long.class;
    }
    
    private String getExpectedTypeDescription(Class<?> type) {
        if (type == String.class) return "String";
        if (isNumericType(type)) return "Number";
        if (type.isEnum()) return type.getSimpleName() + " enum";
        if (type == LocalDateTime.class) return "LocalDateTime or ISO date string";
        return type.getSimpleName();
    }
}