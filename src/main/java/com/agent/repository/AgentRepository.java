package com.agent.repository;

import com.agent.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AgentRepository extends JpaRepository<Agent, String>, JpaSpecificationExecutor<Agent> {

    Boolean existsByEmail(String email);
}
