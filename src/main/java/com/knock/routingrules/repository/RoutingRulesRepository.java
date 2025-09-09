package com.knock.routingrules.repository;

import com.knock.routingrules.model.RoutingRules;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutingRulesRepository extends MongoRepository<RoutingRules, String> {
}