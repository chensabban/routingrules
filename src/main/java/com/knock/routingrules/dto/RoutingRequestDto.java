package com.knock.routingrules.dto;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class RoutingRequestDto {
    @NotBlank(message = "Rule ID is required")
    private String ruleId;
    
    @NotNull(message = "Contact information is required")
    @Valid
    private ContactDto contact;
}