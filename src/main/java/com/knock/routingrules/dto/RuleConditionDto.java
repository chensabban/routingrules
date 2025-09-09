package com.knock.routingrules.dto;

import com.knock.routingrules.model.enums.Operator;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class RuleConditionDto {
    @NotBlank(message = "Field is required")
    private String field;
    
    @NotNull(message = "Operator is required")
    private Operator operator;
    
    @NotNull(message = "Value is required")
    private Object value;
}