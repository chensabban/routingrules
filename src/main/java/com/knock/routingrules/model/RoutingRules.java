package com.knock.routingrules.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "RoutingRules")
public class RoutingRules {
    @Id
    @Indexed(unique = true)
    private String id;
    private String name;
    private List<Rule> rules;
    private String defaultMemberId;
}