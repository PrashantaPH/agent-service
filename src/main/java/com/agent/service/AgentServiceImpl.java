package com.agent.service;

import com.agent.dto.CreateAgentRequest;
import com.agent.dto.AgentResponse;
import com.agent.dto.GetAgentResponse;
import com.agent.dto.Metadata;
import com.agent.entity.Agent;
import com.agent.exception.AgentAlreadyExistsException;
import com.agent.repository.AgentRepository;
import com.agent.repository.AgentSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;

    @Transactional
    @Override
    public void createAgent(CreateAgentRequest agentRequest) {
        if (Boolean.TRUE.equals(agentRepository.existsByEmail(agentRequest.getEmail()))) {
            throw new AgentAlreadyExistsException("Agent already exists", HttpStatus.CONFLICT.name());
        }
        Agent agent = convertToEntity(agentRequest);
        agentRepository.save(agent);
    }

    @Override
    public GetAgentResponse getAgents(String search, String region, String status, Pageable pageable) {
        Specification<Agent> agentSpecification = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (search != null && !search.isBlank()) {
            agentSpecification = agentSpecification.and(AgentSpecifications.search(search));
        }
        if (region != null && !region.equalsIgnoreCase("all")) {
            agentSpecification = agentSpecification.and(AgentSpecifications.regionEquals(region));
        }
        if (status != null && !status.equalsIgnoreCase("all")) {
            agentSpecification = agentSpecification.and(AgentSpecifications.statusEquals(status));
        }

        Page<Agent> agentList = agentRepository.findAll(agentSpecification, pageable);
        Metadata metadata = new Metadata(agentList.getTotalElements(), agentList.getNumber(), agentList.getSize(), agentList.getTotalPages());
        List<AgentResponse> responses = agentList.getContent().stream().map(this::convertToDto).toList();

        return GetAgentResponse.builder()
                .agents(responses)
                .metadata(metadata)
                .build();
    }

    private Agent convertToEntity(CreateAgentRequest agentRequest) {
        return Agent.builder()
                .name(agentRequest.getName())
                .email(agentRequest.getEmail())
                .phone(agentRequest.getPhone())
                .status(agentRequest.getStatus())
                .region(agentRequest.getRegion())
                .joinDate(agentRequest.getJoinDate())
                .salesTarget(agentRequest.getSalesTarget())
                .currentSales(agentRequest.getCurrentSales())
                .build();

    }

    private AgentResponse convertToDto(Agent agent) {
        return AgentResponse.builder()
                .agentId(agent.getAgentId())
                .name(agent.getName())
                .email(agent.getEmail())
                .phone(agent.getPhone())
                .status(agent.getStatus())
                .region(agent.getRegion())
                .joinDate(agent.getJoinDate())
                .salesTarget(agent.getSalesTarget())
                .currentSales(agent.getCurrentSales())
                .build();
    }
}
