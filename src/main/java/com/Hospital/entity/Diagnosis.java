package com.Hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

/**
 * Diagnosis & lab-result information for an encounter.
 * Columns: num_lab_procedures, num_procedures, num_medications,
 *          diag_1, diag_2, diag_3, number_diagnoses,
 *          max_glu_serum, A1Cresult
 */

@Entity
@Table(name = "diagnoses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Hibernate proxy fix
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

    // ── Lab results ───────────────────────────────────────────────────────────
    /** Max glucose serum: >200, >300, Normal, None */
    @Column(name = "max_glu_serum", length = 20)
    private String maxGluSerum;

    /** HbA1c result: >7, >8, Normal, None */
    @Column(name = "a1c_result", length = 20)
    private String a1cResult;

    // ── Relationship ──────────────────────────────────────────────────────────
    /**
     * @JsonIgnore breaks the loop:
     *   Encounter → diagnosis → encounter → diagnosis → ...
     *
     * encounterId is exposed as a plain column so the caller can still
     * see which encounter this diagnosis belongs to.
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encounter_id", nullable = false, unique = true)
    private Encounter encounter;

    @Column(name = "encounter_id", insertable = false, updatable = false)
    private Long encounterId;
public Diagnosis() {
	
}
	public Diagnosis(Long id, Integer numLabProcedures, Integer numProcedures, Integer numMedications, String diag1,
			String diag2, String diag3, Integer numberDiagnoses, String maxGluSerum, String a1cResult,
			Encounter encounter, Long encounterId) {
		super();
		this.id = id;
		this.numLabProcedures = numLabProcedures;
		this.numProcedures = numProcedures;
		this.numMedications = numMedications;
		this.diag1 = diag1;
		this.diag2 = diag2;
		this.diag3 = diag3;
		this.numberDiagnoses = numberDiagnoses;
		this.maxGluSerum = maxGluSerum;
		this.a1cResult = a1cResult;
		this.encounter = encounter;
		this.encounterId = encounterId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumLabProcedures() {
		return numLabProcedures;
	}

	public void setNumLabProcedures(Integer numLabProcedures) {
		this.numLabProcedures = numLabProcedures;
	}

	public Integer getNumProcedures() {
		return numProcedures;
	}

	public void setNumProcedures(Integer numProcedures) {
		this.numProcedures = numProcedures;
	}

	public Integer getNumMedications() {
		return numMedications;
	}

	public void setNumMedications(Integer numMedications) {
		this.numMedications = numMedications;
	}

	public String getDiag1() {
		return diag1;
	}

	public void setDiag1(String diag1) {
		this.diag1 = diag1;
	}

	public String getDiag2() {
		return diag2;
	}

	public void setDiag2(String diag2) {
		this.diag2 = diag2;
	}

	public String getDiag3() {
		return diag3;
	}

	public void setDiag3(String diag3) {
		this.diag3 = diag3;
	}

	public Integer getNumberDiagnoses() {
		return numberDiagnoses;
	}

	public void setNumberDiagnoses(Integer numberDiagnoses) {
		this.numberDiagnoses = numberDiagnoses;
	}

	public String getMaxGluSerum() {
		return maxGluSerum;
	}

	public void setMaxGluSerum(String maxGluSerum) {
		this.maxGluSerum = maxGluSerum;
	}

	public String getA1cResult() {
		return a1cResult;
	}

	public void setA1cResult(String a1cResult) {
		this.a1cResult = a1cResult;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Long getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Long encounterId) {
		this.encounterId = encounterId;
	}
    
    
}
