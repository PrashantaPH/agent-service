package com.agent.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentResponse {

    private String agentId;

    private String name;

    private String email;

    private String phone;

    private String region;

    private String salesTarget;

    private String currentSales;

    private String commission;

    private String joinDate;

    private String status;
}
