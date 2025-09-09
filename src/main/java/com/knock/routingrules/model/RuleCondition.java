package com.knock.routingrules.model;

import com.knock.routingrules.model.enums.Operator;
import lombok.Data;

@Data
public class RuleCondition {
    private String field;
    private Operator operator;
    private Object value;
}