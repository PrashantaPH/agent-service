package com.agent.controller;

import com.agent.dto.CreateAgentRequest;
import com.agent.service.AgentService;
import com.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.common.utils.CommonUtil.successObject;
import static com.common.utils.Constants.*;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createAgent(@RequestHeader(API_KEY) String apiKey,
                                                           @RequestHeader(AUTHORIZATION) String authToken,
                                                           @RequestBody CreateAgentRequest agentRequest) {
        agentService.createAgent(agentRequest);
        return ResponseEntity.status(201).body(successObject("Agent created successfully"));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getAgents(@RequestHeader(API_KEY) String apiKey,
                                                         @RequestHeader(AUTHORIZATION) String authToken,
                                                         @RequestParam(name = "search", required = false) String search,
                                                         @RequestParam(name = "region", required = false) String region,
                                                         @RequestParam(name = "status", required = false) String status,
                                                         @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
                                                         @RequestParam(name = "sortDirection", defaultValue = "desc") String sortDirection,
                                                         @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        var agents = agentService.getAgents(search, region, status, pageable);
        return ResponseEntity.ok(successObject("Agents fetched successfully", agents));
    }

}
