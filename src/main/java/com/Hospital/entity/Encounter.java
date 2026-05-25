package com.Hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Hospital encounter / admission details.
 * Columns: encounter_id, admission_type_id, discharge_disposition_id,
 *          admission_source_id, time_in_hospital, payer_code,
 *          medical_specialty, number_outpatient, number_emergency,
 *          number_inpatient, change, diabetesMed, readmitted
 */

@Entity
@Table(name = "encounters")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Hibernate proxy fix
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "encounter_id", unique = true, nullable = false)
    @NotNull(message = "Encounter ID is required")
    private Long encounterId;

    // ── Admission metadata ────────────────────────────────────────────────────
    @Column(name = "admission_type_id")
    private Integer admissionTypeId;

    @Column(name = "discharge_disposition_id")
    private Integer dischargeDispositionId;

    @Column(name = "admission_source_id")
    private Integer admissionSourceId;

    @Column(name = "time_in_hospital")
    private Integer timeInHospital;

    @Column(name = "payer_code", length = 20)
    private String payerCode;

    @Column(name = "medical_specialty", length = 100)
    private String medicalSpecialty;

    // ── Utilisation counts ────────────────────────────────────────────────────
    @Column(name = "number_outpatient")
    private Integer numberOutpatient;

    @Column(name = "number_emergency")
    private Integer numberEmergency;

    @Column(name = "number_inpatient")
    private Integer numberInpatient;

    // ── Outcome flags ─────────────────────────────────────────────────────────
    @Column(name = "change_flag", length = 10)   // "change" is reserved in some DBs
    private String change;

    @Column(name = "diabetes_med", length = 10)
    private String diabetesMed;

    @Column(name = "readmitted", length = 10)
    private String readmitted;

    // ── Relationships ─────────────────────────────────────────────────────────

    /**
     * @JsonIgnore on the owning side that references back to Patient
     * prevents: Encounter → Patient → [encounters] → Encounter → ...
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    /**
     * Expose a patientId convenience field so callers know the parent
     * without triggering a lazy-load.
     */
    @Column(name = "patient_id", insertable = false, updatable = false)
    private Long patientId;

    // Child @OneToOne sides – safe to include (they don't nest back to Encounter
    // because Diagnosis.encounter and Medication.encounter are @JsonIgnored)
    @OneToOne(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true,
              fetch = FetchType.LAZY)
    private Diagnosis diagnosis;

    @OneToOne(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true,
              fetch = FetchType.LAZY)
    private Medication medication;

    public Encounter() {
    	
    }
	public Encounter(Long id, @NotNull(message = "Encounter ID is required") Long encounterId, Integer admissionTypeId,
			Integer dischargeDispositionId, Integer admissionSourceId, Integer timeInHospital, String payerCode,
			String medicalSpecialty, Integer numberOutpatient, Integer numberEmergency, Integer numberInpatient,
			String change, String diabetesMed, String readmitted, Patient patient, Long patientId, Diagnosis diagnosis,
			Medication medication) {
		super();
		this.id = id;
		this.encounterId = encounterId;
		this.admissionTypeId = admissionTypeId;
		this.dischargeDispositionId = dischargeDispositionId;
		this.admissionSourceId = admissionSourceId;
		this.timeInHospital = timeInHospital;
		this.payerCode = payerCode;
		this.medicalSpecialty = medicalSpecialty;
		this.numberOutpatient = numberOutpatient;
		this.numberEmergency = numberEmergency;
		this.numberInpatient = numberInpatient;
		this.change = change;
		this.diabetesMed = diabetesMed;
		this.readmitted = readmitted;
		this.patient = patient;
		this.patientId = patientId;
		this.diagnosis = diagnosis;
		this.medication = medication;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Long encounterId) {
		this.encounterId = encounterId;
	}
	public Integer getAdmissionTypeId() {
		return admissionTypeId;
	}
	public void setAdmissionTypeId(Integer admissionTypeId) {
		this.admissionTypeId = admissionTypeId;
	}
	public Integer getDischargeDispositionId() {
		return dischargeDispositionId;
	}
	public void setDischargeDispositionId(Integer dischargeDispositionId) {
		this.dischargeDispositionId = dischargeDispositionId;
	}
	public Integer getAdmissionSourceId() {
		return admissionSourceId;
	}
	public void setAdmissionSourceId(Integer admissionSourceId) {
		this.admissionSourceId = admissionSourceId;
	}
	public Integer getTimeInHospital() {
		return timeInHospital;
	}
	public void setTimeInHospital(Integer timeInHospital) {
		this.timeInHospital = timeInHospital;
	}
	public String getPayerCode() {
		return payerCode;
	}
	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}
	public String getMedicalSpecialty() {
		return medicalSpecialty;
	}
	public void setMedicalSpecialty(String medicalSpecialty) {
		this.medicalSpecialty = medicalSpecialty;
	}
	public Integer getNumberOutpatient() {
		return numberOutpatient;
	}
	public void setNumberOutpatient(Integer numberOutpatient) {
		this.numberOutpatient = numberOutpatient;
	}
	public Integer getNumberEmergency() {
		return numberEmergency;
	}
	public void setNumberEmergency(Integer numberEmergency) {
		this.numberEmergency = numberEmergency;
	}
	public Integer getNumberInpatient() {
		return numberInpatient;
	}
	public void setNumberInpatient(Integer numberInpatient) {
		this.numberInpatient = numberInpatient;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getDiabetesMed() {
		return diabetesMed;
	}
	public void setDiabetesMed(String diabetesMed) {
		this.diabetesMed = diabetesMed;
	}
	public String getReadmitted() {
		return readmitted;
	}
	public void setReadmitted(String readmitted) {
		this.readmitted = readmitted;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Diagnosis getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}
	public Medication getMedication() {
		return medication;
	}
	public void setMedication(Medication medication) {
		this.medication = medication;
	}
    
    
}
