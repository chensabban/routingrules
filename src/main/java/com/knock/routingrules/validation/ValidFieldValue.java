package com.knock.routingrules.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidFieldValueValidator.class)
public @interface ValidFieldValue {
    String message() default "Value type does not match field type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}