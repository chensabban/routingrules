package com.knock.routingrules.model;

import lombok.Data;

import java.util.List;

@Data
public class Rule {
    private List<RuleCondition> conditions;
    private String memberId;
}