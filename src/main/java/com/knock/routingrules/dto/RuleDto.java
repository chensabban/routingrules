package com.knock.routingrules.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDto {
    @NotEmpty(message = "Conditions are required")
    @Valid
    private List<RuleConditionDto> conditions;
    
    @NotEmpty(message = "Member ID is required")
    private String memberId;
}