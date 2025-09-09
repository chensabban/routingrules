package com.knock.routingrules.dto;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class RoutingRulesDto {

    @NotEmpty(message = "Name is required")
    private String name;
    
    @NotEmpty(message = "Rules are required")
    @Valid
    private List<RuleDto> rules;
    
    @NotEmpty(message = "Default member ID is required")
    private String defaultMemberId;
}