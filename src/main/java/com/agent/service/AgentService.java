package com.agent.service;

import com.agent.dto.CreateAgentRequest;
import com.agent.dto.GetAgentResponse;
import org.springframework.data.domain.Pageable;

public interface AgentService {

    void createAgent(CreateAgentRequest agentRequest);

    GetAgentResponse getAgents(String search, String region, String status, Pageable pageable);
}
