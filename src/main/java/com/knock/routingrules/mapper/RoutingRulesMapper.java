package com.knock.routingrules.mapper;

import com.knock.routingrules.dto.*;
import com.knock.routingrules.dto.response.RoutingRulesResponseDto;
import com.knock.routingrules.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface RoutingRulesMapper {
    
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    RoutingRules toEntity(CreateRoutingRulesDto dto);
    
    Rule toEntity(RuleDto dto);
    
    RuleCondition toEntity(RuleConditionDto dto);
    
    Contact toEntity(ContactDto dto);
    
    RoutingRulesResponseDto toResponseDto(RoutingRules entity);
    
    List<RoutingRulesResponseDto> toResponseDtoList(List<RoutingRules> entities);
}