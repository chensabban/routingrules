package com.knock.routingrules.dto;

import com.knock.routingrules.model.enums.Operator;
import com.knock.routingrules.validation.ValidContactField;
import com.knock.routingrules.validation.ValidFieldValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidFieldValue
public class RuleConditionDto {

    @NotBlank(message = "Field is required")
    @ValidContactField
    private String field;
    
    @NotNull(message = "Operator is required")
    private Operator operator;
    
    @NotNull(message = "Value is required")
    private Object value;
}