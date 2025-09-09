package com.knock.routingrules.dto;

import lombok.Data;

@Data
public class RoutingResponseDto {
    private String memberId;
    private String ruleName;
    private boolean isDefault;
}