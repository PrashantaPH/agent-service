package com.agent.repository;

import com.agent.entity.Agent;
import org.springframework.data.jpa.domain.Specification;

public class AgentSpecifications {

    public static Specification<Agent> search(String search) {
        String like = "%" + search.trim().toLowerCase() + "%";
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), like),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), like)
        );
    }

    public static Specification<Agent> regionEquals(String region) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("region"), region));
    }

    public static Specification<Agent> statusEquals(String status) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status));
    }

}
