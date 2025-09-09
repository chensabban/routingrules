package com.knock.routingrules.validation;

import com.knock.routingrules.dto.ContactDto;
import com.knock.routingrules.dto.RuleConditionDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Validator;
import jakarta.validation.Validation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ValidFieldValueValidator implements ConstraintValidator<ValidFieldValue, RuleConditionDto> {

    private static final Map<String, Class<?>> FIELD_TYPES = new HashMap<>();
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    static {
        for (Field field : ContactDto.class.getDeclaredFields()) {
            FIELD_TYPES.put(field.getName(), field.getType());
        }
    }

    @Override
    public boolean isValid(RuleConditionDto condition, ConstraintValidatorContext context) {
        if (condition.getField() == null || condition.getValue() == null) {
            return true;
        }

        Class<?> expectedType = FIELD_TYPES.get(condition.getField());
        if (expectedType == null) {
            return true;
        }

        // Create ContactDto and validate using existing annotations
        ContactDto contactDto = new ContactDto();
        try {
            Field field = ContactDto.class.getDeclaredField(condition.getField());
            field.setAccessible(true);

            Object value = condition.getValue();
            Class<?> fieldType = field.getType();
            
            // Convert value to match field type
            if (fieldType.isEnum()) {
                Class<? extends Enum> enumClass = (Class<? extends Enum>) fieldType;
                value = Enum.valueOf(enumClass, value.toString());
            } else if (fieldType == Integer.class && value instanceof Number) {
                value = ((Number) value).intValue();
            } else if (fieldType == java.time.LocalDateTime.class && value instanceof String) {
                value = java.time.LocalDateTime.parse((String) value);
            }
            
            field.set(contactDto, value);
            
            var violations = validator.validateProperty(contactDto, condition.getField());
            
            if (!violations.isEmpty()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                    violations.iterator().next().getMessage()
                ).addConstraintViolation();
                return false;
            }
            
        } catch (Exception e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Invalid value type for field: " + condition.getField()
            ).addConstraintViolation();
            return false;
        }
        
        return true;
    }
}