package com.knock.routingrules.service;

import com.knock.routingrules.dto.ContactDto;
import com.knock.routingrules.dto.RoutingResponseDto;
import com.knock.routingrules.exception.RoutingRulesNotFoundException;
import com.knock.routingrules.mapper.RoutingRulesMapper;
import com.knock.routingrules.model.*;
import com.knock.routingrules.repository.RoutingRulesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoutingRulesServiceImpl implements RoutingRulesService {
    
    private final RoutingRulesRepository repository;
    private final RoutingRulesMapper mapper;

    @Override
    public RoutingRules save(RoutingRules routingRules) {
        return repository.save(routingRules);
    }

    @Override
    public RoutingRules findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RoutingRulesNotFoundException("Routing rules not found with id: " + id));
    }

    @Override
    public List<RoutingRules> findAll() {
        return repository.findAll();
    }



    @Override
    public RoutingResponseDto calculateRouting(String ruleId, ContactDto contactDto) {
        RoutingRules routingRules = findById(ruleId);
        Contact contact = mapper.toEntity(contactDto);
        
        for (Rule rule : routingRules.getRules()) {
            if (evaluateRule(rule, contact)) {
                RoutingResponseDto response = new RoutingResponseDto();
                response.setMemberId(rule.getMemberId());
                response.setRuleName(routingRules.getName());
                response.setDefault(false);
                return response;
            }
        }
        
        RoutingResponseDto response = new RoutingResponseDto();
        response.setMemberId(routingRules.getDefaultMemberId());
        response.setRuleName(routingRules.getName());
        response.setDefault(true);
        return response;
    }

    private boolean evaluateRule(Rule rule, Contact contact) {
        return rule.getConditions().stream()
                .anyMatch(condition -> evaluateCondition(condition, contact));
    }

    private boolean evaluateCondition(RuleCondition condition, Contact contact) {
        Object contactValue = getContactFieldValue(condition.getField(), contact);
        if (contactValue == null) return false;
        
        return switch (condition.getOperator()) {
            case EQUALS -> contactValue.equals(condition.getValue());
            case GREATER_THAN -> compareNumbers(contactValue, condition.getValue()) > 0;
            case LESS_THAN -> compareNumbers(contactValue, condition.getValue()) < 0;
        };
    }

    private Object getContactFieldValue(String field, Contact contact) {
        return switch (field) {
            case "contactCountry" -> contact.getContactCountry();
            case "companySize" -> contact.getCompanySize();
            case "companyHqCountry" -> contact.getCompanyHqCountry();
            case "companyIndustry" -> contact.getCompanyIndustry();
            case "companyName" -> contact.getCompanyName();
            case "contactDevice" -> contact.getContactDevice();
            case "firstPage" -> contact.getFirstPage();
            case "firstSeen" -> contact.getFirstSeen();
            case "lastSeen" -> contact.getLastSeen();
            default -> null;
        };
    }

    private int compareNumbers(Object value1, Object value2) {
        if (value1 instanceof Number n1 && value2 instanceof Number n2) {
            return Double.compare(n1.doubleValue(), n2.doubleValue());
        }
        if (value1 instanceof LocalDateTime d1) {
            LocalDateTime d2 = value2 instanceof LocalDateTime ? (LocalDateTime) value2 : LocalDateTime.parse(value2.toString());
            return d1.compareTo(d2);
        }
        return 0;
    }


}