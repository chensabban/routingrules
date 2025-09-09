package com.knock.routingrules.dto;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class RuleDto {
    @NotEmpty(message = "Conditions are required")
    @Valid
    private List<RuleConditionDto> conditions;
    
    @NotEmpty(message = "Member ID is required")
    private String memberId;
}