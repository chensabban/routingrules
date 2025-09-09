package com.knock.routingrules.dto.response;

import com.knock.routingrules.dto.RuleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutingRulesResponseDto {
    private String id;
    private String name;
    private List<RuleDto> rules;
    private String defaultMemberId;
}