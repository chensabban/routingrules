package com.knock.routingrules.validation;

import com.knock.routingrules.model.Contact;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;

public class ValidContactFieldValidator implements ConstraintValidator<ValidContactField, String> {

    private static final Set<String> VALID_FIELDS = Arrays.stream(Contact.class.getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.toSet());

    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        if (field == null || !VALID_FIELDS.contains(field)) {
            if (!context.getDefaultConstraintMessageTemplate().equals("{}")) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                    "Field must be one of: " + String.join(", ", VALID_FIELDS)
                ).addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}