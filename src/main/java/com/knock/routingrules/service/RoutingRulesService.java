package com.knock.routingrules.service;

import com.knock.routingrules.dto.ContactDto;
import com.knock.routingrules.dto.RoutingResponseDto;
import com.knock.routingrules.model.RoutingRules;

import java.util.List;

public interface RoutingRulesService {
    RoutingRules save(RoutingRules routingRules);
    RoutingRules findById(String id);
    List<RoutingRules> findAll();
    RoutingResponseDto calculateRouting(String ruleId, ContactDto contactDto);
}