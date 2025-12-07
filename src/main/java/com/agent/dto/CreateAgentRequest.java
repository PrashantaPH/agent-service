package com.agent.dto;

import lombok.Data;

@Data
public class CreateAgentRequest {

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
