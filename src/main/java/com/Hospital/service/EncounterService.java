package com.Hospital.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Hospital.entity.Encounter;
import com.Hospital.entity.Patient;
import com.Hospital.repo.EncounterRepository;
import com.Hospital.repo.PatientRepository;

import java.util.List;
@Service
public class EncounterService {

    private final EncounterRepository encounterRepository;
    private final PatientRepository patientRepository;

    // Manual Constructor Injection (No Lombok)
    public EncounterService(EncounterRepository encounterRepository, PatientRepository patientRepository) {
        this.encounterRepository = encounterRepository;
        this.patientRepository = patientRepository;
    }

    // ── POST: Create Encounter linked to a Patient ───────────────────────────
    @Transactional
    public Encounter createEncounter(Long patientId, Encounter encounter) {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientId));
        
        // Form the bidirectional sync
        encounter.setPatient(patient);
        
        // If nested child JSON data (Diagnosis/Medication) is present, link them too
        if (encounter.getDiagnosis() != null) {
            encounter.getDiagnosis().setEncounter(encounter);
        }
        if (encounter.getMedication() != null) {
            encounter.getMedication().setEncounter(encounter);
        }

        return encounterRepository.save(encounter);
    }

    // ── GET: All Encounters ──────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Encounter> getAllEncounters() {
        return encounterRepository.findAll();
    }

    // ── GET: Find By Primary Key ID ──────────────────────────────────────────
    @Transactional(readOnly = true)
    public Encounter getEncounterById(Long id) {
        return encounterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Encounter record not found with ID: " + id));
    }

    // ── GET: Find By Business Key (EncounterId) ──────────────────────────────
    @Transactional(readOnly = true)
    public Encounter getByEncounterId(Long encounterId) {
        return encounterRepository.findByEncounterId(encounterId)
            .orElseThrow(() -> new RuntimeException("Encounter not found with Business ID: " + encounterId));
    }

    // ── GET: Filter By Patient Database ID ───────────────────────────────────
    @Transactional(readOnly = true)
    public List<Encounter> getEncountersByPatient(Long patientId) {
        return encounterRepository.findByPatientId(patientId);
    }

    // ── PUT: Update Encounter Metrics ────────────────────────────────────────
    @Transactional
    public Encounter updateEncounter(Long id, Encounter incomingDetails) {
        Encounter existingEncounter = encounterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Encounter record not found with ID: " + id));

        // Update stay metrics
        existingEncounter.setAdmissionTypeId(incomingDetails.getAdmissionTypeId());
        existingEncounter.setDischargeDispositionId(incomingDetails.getDischargeDispositionId());
        existingEncounter.setAdmissionSourceId(incomingDetails.getAdmissionSourceId());
        existingEncounter.setTimeInHospital(incomingDetails.getTimeInHospital());
        existingEncounter.setPayerCode(incomingDetails.getPayerCode());
        existingEncounter.setMedicalSpecialty(incomingDetails.getMedicalSpecialty());

        // Update utilization counts
        existingEncounter.setNumberOutpatient(incomingDetails.getNumberOutpatient());
        existingEncounter.setNumberEmergency(incomingDetails.getNumberEmergency());
        existingEncounter.setNumberInpatient(incomingDetails.getNumberInpatient());

        // Update categorical target flags
        existingEncounter.setChange(incomingDetails.getChange());
        existingEncounter.setDiabetesMed(incomingDetails.getDiabetesMed());
        existingEncounter.setReadmitted(incomingDetails.getReadmitted());

        return existingEncounter; // Auto-committed upon transaction close
    }

    // ── DELETE: Remove Encounter ─────────────────────────────────────────────
    @Transactional
    public void deleteEncounter(Long id) {
        Encounter encounter = encounterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Encounter record not found with ID: " + id));
        encounterRepository.delete(encounter);
    }
}