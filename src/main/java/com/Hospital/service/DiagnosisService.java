package com.Hospital.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Hospital.entity.Diagnosis;
import com.Hospital.entity.Encounter;
import com.Hospital.repo.DiagnosisRepository;
import com.Hospital.repo.EncounterRepository;

import java.util.List;
@Service
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final EncounterRepository encounterRepository;

    // Manual Constructor Injection (No Lombok)
    public DiagnosisService(DiagnosisRepository diagnosisRepository, EncounterRepository encounterRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.encounterRepository = encounterRepository;
    }

    // ── POST: Create Diagnosis and link to Encounter ─────────────────────────
    @Transactional
    public Diagnosis createDiagnosis(Long encounterId, Diagnosis diagnosis) {
        Encounter encounter = encounterRepository.findById(encounterId)
            .orElseThrow(() -> new RuntimeException("Encounter not found with ID: " + encounterId));
        
        diagnosis.setEncounter(encounter);
        return diagnosisRepository.save(diagnosis);
    }

    // ── GET: Retrieve All ────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisRepository.findAll();
    }

    // ── GET: Find By ID ──────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public Diagnosis getDiagnosisById(Long id) {
        return diagnosisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Diagnosis record not found with ID: " + id));
    }

    // ── GET: Find By Encounter ID ───────────────────────────────────────────
    @Transactional(readOnly = true)
    public Diagnosis getDiagnosisByEncounterId(Long encounterId) {
        return diagnosisRepository.findByEncounterId(encounterId)
            .orElseThrow(() -> new RuntimeException("No diagnosis found for Encounter ID: " + encounterId));
    }

    // ── PUT: Update Entire Diagnosis Profile ─────────────────────────────────
    @Transactional
    public Diagnosis updateDiagnosis(Long id, Diagnosis updatedDetails) {
        Diagnosis existingDiagnosis = diagnosisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Diagnosis record not found with ID: " + id));

        // Update counts
        existingDiagnosis.setNumLabProcedures(updatedDetails.getNumLabProcedures());
        existingDiagnosis.setNumProcedures(updatedDetails.getNumProcedures());
        existingDiagnosis.setNumMedications(updatedDetails.getNumMedications());

        // Update ICD-9 Codes
        existingDiagnosis.setDiag1(updatedDetails.getDiag1());
        existingDiagnosis.setDiag2(updatedDetails.getDiag2());
        existingDiagnosis.setDiag3(updatedDetails.getDiag3());
        existingDiagnosis.setNumberDiagnoses(updatedDetails.getNumberDiagnoses());

        // Update Lab markers
        existingDiagnosis.setMaxGluSerum(updatedDetails.getMaxGluSerum());
        existingDiagnosis.setA1cResult(updatedDetails.getA1cResult());

        return existingDiagnosis; // Hibernate commits dirty states automatically at end of transaction
    }

    // ── DELETE: Remove Diagnosis ─────────────────────────────────────────────
    @Transactional
    public void deleteDiagnosis(Long id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Diagnosis record not found with ID: " + id));
        diagnosisRepository.delete(diagnosis);
    }
}