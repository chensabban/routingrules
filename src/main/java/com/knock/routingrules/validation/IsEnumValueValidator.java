package com.knock.routingrules.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class IsEnumValueValidator implements ConstraintValidator<IsEnumValue, Object> {

    private Set<String> acceptedValues;

    @SneakyThrows
    @Override
    public void initialize(IsEnumValue annotation) {
        if (annotation.oneOf().length > 0) {
            acceptedValues = Arrays.stream(annotation.oneOf()).collect(Collectors.toSet());
        } else {
            acceptedValues = Arrays.stream(annotation.enumClass().getEnumConstants())
                    .map(Enum::name)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = acceptedValues.contains(value.toString());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Value must be one of: " + acceptedValues.toString()
            ).addConstraintViolation();
        }
        return isValid;

    }
}