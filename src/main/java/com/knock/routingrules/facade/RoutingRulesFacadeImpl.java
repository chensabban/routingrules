package com.knock.routingrules.facade;

import com.knock.routingrules.dto.ContactDto;
import com.knock.routingrules.dto.CreateRoutingRulesDto;
import com.knock.routingrules.dto.RoutingResponseDto;
import com.knock.routingrules.dto.response.RoutingRulesResponseDto;
import com.knock.routingrules.mapper.RoutingRulesMapper;
import com.knock.routingrules.model.RoutingRules;
import com.knock.routingrules.service.RoutingRulesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoutingRulesFacadeImpl implements RoutingRulesFacade {

    private final RoutingRulesService routingRulesService;
    private final RoutingRulesMapper mapper;

    @Override
    public RoutingRulesResponseDto createRoutingRules(CreateRoutingRulesDto createRoutingRulesDto) {
        log.info("Creating routing rules: {}", createRoutingRulesDto.getName());
        RoutingRules entity = mapper.toEntity(createRoutingRulesDto);
        RoutingRules saved = routingRulesService.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public RoutingRulesResponseDto updateRoutingRules(String id, CreateRoutingRulesDto createRoutingRulesDto) {
        log.info("Updating routing rules with id: {}", id);
        RoutingRules existing = routingRulesService.findById(id);
        existing.setName(createRoutingRulesDto.getName());
        existing.setRules(createRoutingRulesDto.getRules().stream().map(mapper::toEntity).toList());
        existing.setDefaultMemberId(createRoutingRulesDto.getDefaultMemberId());
        RoutingRules updated = routingRulesService.save(existing);
        return mapper.toResponseDto(updated);
    }

    @Override
    public RoutingRulesResponseDto getRoutingRulesById(String id) {
        log.info("Getting routing rules by id: {}", id);
        RoutingRules entity = routingRulesService.findById(id);
        return mapper.toResponseDto(entity);
    }

    @Override
    public List<RoutingRulesResponseDto> getAllRoutingRules() {
        log.info("Getting all routing rules");
        List<RoutingRules> entities = routingRulesService.findAll();
        return mapper.toResponseDtoList(entities);
    }

    @Override
    public RoutingResponseDto calculateRouting(String ruleId, ContactDto contactDto) {
        log.info("Calculating routing for rule id: {}", ruleId);
        return routingRulesService.calculateRouting(ruleId, contactDto);
    }
}