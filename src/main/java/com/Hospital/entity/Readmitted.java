package com.Hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "readmissions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Readmitted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_readmitted", length = 10, nullable = false)
    private String isReadmitted; // "NO", "<30", ">30"

    @Column(name = "risk_score")
    private Integer riskScore; // Scaled tracking evaluation score (1-100)

    @Column(name = "primary_cause", length = 255)
    private String primaryCause; // e.g. "Post-op Infection relapse"

    @Column(name = "follow_up_scheduled")
    private Boolean followUpScheduled;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encounter_id", nullable = false)
    @JsonIgnore
    private Encounter encounter;

    // ── Constructors ──────────────────────────────────────────────────────────
    public Readmitted() {}

    public Readmitted(String isReadmitted, Integer riskScore, String primaryCause, Boolean followUpScheduled) {
        this.isReadmitted = isReadmitted;
        this.riskScore = riskScore;
        this.primaryCause = primaryCause;
        this.followUpScheduled = followUpScheduled;
    }

    // ── Getters and Setters ────────────────────────────────────────────────────
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIsReadmitted() { return isReadmitted; }
    public void setIsReadmitted(String isReadmitted) { this.isReadmitted = isReadmitted; }

    public Integer getRiskScore() { return riskScore; }
    public void setRiskScore(Integer riskScore) { this.riskScore = riskScore; }

    public String getPrimaryCause() { return primaryCause; }
    public void setPrimaryCause(String primaryCause) { this.primaryCause = primaryCause; }

    public Boolean getFollowUpScheduled() { return followUpScheduled; }
    public void setFollowUpScheduled(Boolean followUpScheduled) { this.followUpScheduled = followUpScheduled; }

    // ── Bidirectional Relationship Mapping Hooks ──────────────────────────────
    public Encounter getEncounter() { return encounter; }
    
    public void setEncounter(Encounter encounter) { 
        this.encounter = encounter; 
    }
}