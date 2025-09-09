package com.knock.routingrules.controller;

import com.knock.routingrules.dto.RoutingRequestDto;
import com.knock.routingrules.dto.RoutingResponseDto;
import com.knock.routingrules.dto.RoutingRulesDto;
import com.knock.routingrules.model.RoutingRules;
import com.knock.routingrules.service.RoutingRulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoutingRulesControllerImpl implements RoutingRulesController {

    private final RoutingRulesService routingRulesService;

    @Override
    public RoutingRules createRoutingRules(RoutingRulesDto routingRulesDto) {
        return routingRulesService.createRoutingRules(routingRulesDto);
    }

    @Override
    public RoutingRules updateRoutingRules(String id, RoutingRulesDto routingRulesDto) {
        return routingRulesService.updateRoutingRules(id, routingRulesDto);
    }

    @Override
    public RoutingRules getRoutingRulesById(String id) {
        return routingRulesService.getRoutingRulesById(id);
    }

    @Override
    public List<RoutingRules> getAllRoutingRules() {
        return routingRulesService.getAllRoutingRules();
    }

    @Override
    public RoutingResponseDto calculateRouting(RoutingRequestDto request) {
        return routingRulesService.calculateRouting(request.getRuleId(), request.getContact());
    }
}