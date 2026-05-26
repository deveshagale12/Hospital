package com.Hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "encounters")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @Column(name = "change_flag", length = 10)
    private String change;

    @Column(name = "diabetes_med", length = 10)
    private String diabetesMed;

    // ── Relationships ─────────────────────────────────────────────────────────
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    @OneToOne(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Diagnosis diagnosis;

    @OneToOne(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Medication medication;

    // New Relationship tracking complex readmission details
    @OneToOne(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Readmitted readmitted; //  Now tracks the complex child entity!


    public Encounter() {}

    // Convenience expose property pattern using Jackson properties safely
    @JsonProperty("patientId")
    public Long getPatientId() {
        return this.patient != null ? this.patient.getId() : null;
    }
   
    // 3. Update your Getter and Setter inside Encounter.java to match:
    public Readmitted getReadmitted() { 
        return readmitted; 
    }

    public void setReadmitted(Readmitted readmitted) { 
        this.readmitted = readmitted; 
        if (readmitted != null) {
            readmitted.setEncounter(this); // Resolves the bidirectional mapping hook
        }
    }
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEncounterId() { return encounterId; }
    public void setEncounterId(Long encounterId) { this.encounterId = encounterId; }

    public Integer getAdmissionTypeId() { return admissionTypeId; }
    public void setAdmissionTypeId(Integer admissionTypeId) { this.admissionTypeId = admissionTypeId; }

    public Integer getDischargeDispositionId() { return dischargeDispositionId; }
    public void setDischargeDispositionId(Integer dischargeDispositionId) { this.dischargeDispositionId = dischargeDispositionId; }

    public Integer getAdmissionSourceId() { return admissionSourceId; }
    public void setAdmissionSourceId(Integer admissionSourceId) { this.admissionSourceId = admissionSourceId; }

    public Integer getTimeInHospital() { return timeInHospital; }
    public void setTimeInHospital(Integer timeInHospital) { this.timeInHospital = timeInHospital; }

    public String getPayerCode() { return payerCode; }
    public void setPayerCode(String payerCode) { this.payerCode = payerCode; }

    public String getMedicalSpecialty() { return medicalSpecialty; }
    public void setMedicalSpecialty(String medicalSpecialty) { this.medicalSpecialty = medicalSpecialty; }

    public Integer getNumberOutpatient() { return numberOutpatient; }
    public void setNumberOutpatient(Integer numberOutpatient) { this.numberOutpatient = numberOutpatient; }

    public Integer getNumberEmergency() { return numberEmergency; }
    public void setNumberEmergency(Integer numberEmergency) { this.numberEmergency = numberEmergency; }

    public Integer getNumberInpatient() { return numberInpatient; }
    public void setNumberInpatient(Integer numberInpatient) { this.numberInpatient = numberInpatient; }

    public String getChange() { return change; }
    public void setChange(String change) { this.change = change; }

    public String getDiabetesMed() { return diabetesMed; }
    public void setDiabetesMed(String diabetesMed) { this.diabetesMed = diabetesMed; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Diagnosis getDiagnosis() { return diagnosis; }
    public void setDiagnosis(Diagnosis diagnosis) { this.diagnosis = diagnosis; }

    public Medication getMedication() { return medication; }
    public void setMedication(Medication medication) { this.medication = medication; }

    
}