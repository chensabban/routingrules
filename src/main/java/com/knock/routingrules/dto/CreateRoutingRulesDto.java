package com.knock.routingrules.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoutingRulesDto {

    @NotEmpty(message = "Name is required")
    private String name;
    
    @NotNull(message = "Rules are required")
    @Valid
    private List<RuleDto> rules;
    
    @NotEmpty(message = "Default member ID is required")
    private String defaultMemberId;
}