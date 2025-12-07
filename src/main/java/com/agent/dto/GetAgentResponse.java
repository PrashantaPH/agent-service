package com.agent.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAgentResponse {

    private List<AgentResponse> agents;
    private Metadata metadata;
}
