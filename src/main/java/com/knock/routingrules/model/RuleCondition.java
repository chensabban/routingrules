package com.knock.routingrules.model;

import com.knock.routingrules.model.enums.Operator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleCondition implements Serializable {
    private String field;
    private Operator operator;
    private Object value;
}