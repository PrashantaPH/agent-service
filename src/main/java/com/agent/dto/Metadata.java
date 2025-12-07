package com.agent.dto;

public record Metadata(
        long total,
        int pageNumber,
        int pageSize,
        long totalPages) {}
