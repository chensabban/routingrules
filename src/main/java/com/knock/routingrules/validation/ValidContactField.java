package com.knock.routingrules.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidContactFieldValidator.class)
public @interface ValidContactField {
    String message() default "Field must be one of: contactCountry, companySize, companyHqCountry, companyIndustry, companyName, contactDevice, firstPage, firstSeen, lastSeen";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}