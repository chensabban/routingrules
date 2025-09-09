package com.knock.routingrules.controller;

import com.knock.routingrules.dto.CreateRoutingRulesDto;
import com.knock.routingrules.dto.RoutingRequestDto;
import com.knock.routingrules.dto.RoutingResponseDto;
import com.knock.routingrules.dto.response.RoutingRulesResponseDto;
import com.knock.routingrules.facade.RoutingRulesFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoutingRulesControllerImpl implements RoutingRulesController {

    private final RoutingRulesFacade routingRulesFacade;

    @Override
    public RoutingRulesResponseDto createRoutingRules(CreateRoutingRulesDto createRoutingRulesDto) {
        return routingRulesFacade.createRoutingRules(createRoutingRulesDto);
    }

    @Override
    public RoutingRulesResponseDto updateRoutingRules(String id, CreateRoutingRulesDto createRoutingRulesDto) {
        return routingRulesFacade.updateRoutingRules(id, createRoutingRulesDto);
    }

    @Override
    public RoutingRulesResponseDto getRoutingRulesById(String id) {
        return routingRulesFacade.getRoutingRulesById(id);
    }

    @Override
    public List<RoutingRulesResponseDto> getAllRoutingRules() {
        return routingRulesFacade.getAllRoutingRules();
    }

    @Override
    public RoutingResponseDto calculateRouting(RoutingRequestDto request) {
        return routingRulesFacade.calculateRouting(request.getRuleId(), request.getContact());
    }
}