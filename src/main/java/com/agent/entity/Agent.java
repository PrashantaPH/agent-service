package com.agent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "agents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "agent_id")
    private String agentId;

    @Column(name = "user_id")
    private String userId;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String region;

    @Column(name = "sales_target")
    private String salesTarget;

    @Column(name = "current_sales")
    private String currentSales;

    private String commission;

    @Column(name = "join_date")
    private String joinDate;

    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private String createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private String updatedAt;
}
