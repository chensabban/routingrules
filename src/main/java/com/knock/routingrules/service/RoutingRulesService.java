package com.knock.routingrules.service;

import com.knock.routingrules.dto.ContactDto;
import com.knock.routingrules.dto.RoutingResponseDto;
import com.knock.routingrules.dto.RoutingRulesDto;
import com.knock.routingrules.model.RoutingRules;

import java.util.List;

public interface RoutingRulesService {
    RoutingRules createRoutingRules(RoutingRulesDto routingRulesDto);
    RoutingRules updateRoutingRules(String id, RoutingRulesDto routingRulesDto);
    RoutingRules getRoutingRulesById(String id);
    List<RoutingRules> getAllRoutingRules();
    RoutingResponseDto calculateRouting(String ruleId, ContactDto contactDto);
}