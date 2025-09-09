package com.knock.routingrules.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "RoutingRules")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutingRules implements Serializable {

    @Id
    @Indexed(unique = true)
    private String id;
    private String name;
    private List<Rule> rules;
    private String defaultMemberId;
}