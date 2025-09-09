package com.knock.routingrules.facade;

import com.knock.routingrules.dto.ContactDto;
import com.knock.routingrules.dto.RoutingResponseDto;
import com.knock.routingrules.dto.CreateRoutingRulesDto;
import com.knock.routingrules.dto.response.RoutingRulesResponseDto;

import java.util.List;

public interface RoutingRulesFacade {
    RoutingRulesResponseDto createRoutingRules(CreateRoutingRulesDto createRoutingRulesDto);
    RoutingRulesResponseDto updateRoutingRules(String id, CreateRoutingRulesDto createRoutingRulesDto);
    RoutingRulesResponseDto getRoutingRulesById(String id);
    List<RoutingRulesResponseDto> getAllRoutingRules();
    RoutingResponseDto calculateRouting(String ruleId, ContactDto contactDto);
}