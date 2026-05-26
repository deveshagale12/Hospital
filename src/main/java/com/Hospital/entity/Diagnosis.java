package com.Hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "diagnoses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Procedure / medication counts ─────────────────────────────────────────
    @Column(name = "num_lab_procedures")
    private Integer numLabProcedures;

    @Column(name = "num_procedures")
    private Integer numProcedures;

    @Column(name = "num_medications")
    private Integer numMedications;

    // ── ICD-9 diagnosis codes ─────────────────────────────────────────────────
    @Column(name = "diag_1", length = 20)
    private String diag1;

    @Column(name = "diag_2", length = 20)
    private String diag2;

    @Column(name = "diag_3", length = 20)
    private String diag3;

    @Column(name = "number_diagnoses")
    private Integer numberDiagnoses;

    // ── ADDED: Descriptive Text Mappings (Matches Screen 5 UI Descriptions) ──
    @Column(name = "diag_1_desc", length = 255)
    private String diag1Desc;

    @Column(name = "diag_2_desc", length = 255)
    private String diag2Desc;

    @Column(name = "diag_3_desc", length = 255)
    private String diag3Desc;

    // ── Lab results ───────────────────────────────────────────────────────────
    /** Max glucose serum: >200, >300, Normal, None */
    @Column(name = "max_glu_serum", length = 20)
    private String maxGluSerum;

    /** HbA1c result: >7, >8, Normal, None */
    @Column(name = "a1c_result", length = 20)
    private String a1cResult;

    // ── Relationship ──────────────────────────────────────────────────────────
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encounter_id", nullable = false, unique = true)
    private Encounter encounter;

    @Column(name = "encounter_id", insertable = false, updatable = false)
    private Long encounterId;

    // ── Constructors ──────────────────────────────────────────────────────────
    public Diagnosis() {}

    // ── Getters and Setters ────────────────────────────────────────────────────
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNumLabProcedures() { return numLabProcedures; }
    public void setNumLabProcedures(Integer numLabProcedures) { this.numLabProcedures = numLabProcedures; }

    public Integer getNumProcedures() { return numProcedures; }
    public void setNumProcedures(Integer numProcedures) { this.numProcedures = numProcedures; }

    public Integer getNumMedications() { return numMedications; }
    public void setNumMedications(Integer numMedications) { this.numMedications = numMedications; }

    public String getDiag1() { return diag1; }
    public void setDiag1(String diag1) { this.diag1 = diag1; }

    public String getDiag2() { return diag2; }
    public void setDiag2(String diag2) { this.diag2 = diag2; }

    public String getDiag3() { return diag3; }
    public void setDiag3(String diag3) { this.diag3 = diag3; }

    public Integer getNumberDiagnoses() { return numberDiagnoses; }
    public void setNumberDiagnoses(Integer numberDiagnoses) { this.numberDiagnoses = numberDiagnoses; }

    public String getDiag1Desc() { return diag1Desc; }
    public void setDiag1Desc(String diag1Desc) { this.diag1Desc = diag1Desc; }

    public String getDiag2Desc() { return diag2Desc; }
    public void setDiag2Desc(String diag2Desc) { this.diag2Desc = diag2Desc; }

    public String getDiag3Desc() { return diag3Desc; }
    public void setDiag3Desc(String diag3Desc) { this.diag3Desc = diag3Desc; }

    public String getMaxGluSerum() { return maxGluSerum; }
    public void setMaxGluSerum(String maxGluSerum) { this.maxGluSerum = maxGluSerum; }

    public String getA1cResult() { return a1cResult; }
    public void setA1cResult(String a1cResult) { this.a1cResult = a1cResult; }

    public Encounter getEncounter() { return encounter; }
    public void setEncounter(Encounter encounter) { this.encounter = encounter; }

    public Long getEncounterId() { return encounterId; }
    public void setEncounterId(Long encounterId) { this.encounterId = encounterId; }
    
    
}