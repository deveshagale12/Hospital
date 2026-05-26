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

    public DiagnosisService(DiagnosisRepository diagnosisRepository, EncounterRepository encounterRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.encounterRepository = encounterRepository;
    }

    @Transactional
    public Diagnosis createDiagnosis(Long encounterId, Diagnosis diagnosis) {
        Encounter encounter = encounterRepository.findById(encounterId)
            .orElseThrow(() -> new RuntimeException("Encounter not found with ID: " + encounterId));
        
        diagnosis.setEncounter(encounter);
        return diagnosisRepository.save(diagnosis);
    }

    @Transactional(readOnly = true)
    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Diagnosis getDiagnosisById(Long id) {
        return diagnosisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Diagnosis record not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Diagnosis getDiagnosisByEncounterId(Long encounterId) {
        return diagnosisRepository.findByEncounterId(encounterId)
            .orElseThrow(() -> new RuntimeException("No diagnosis found for Encounter ID: " + encounterId));
    }

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

        // ── ADDED: Preserve descriptive text values ──
        existingDiagnosis.setDiag1Desc(updatedDetails.getDiag1Desc());
        existingDiagnosis.setDiag2Desc(updatedDetails.getDiag2Desc());
        existingDiagnosis.setDiag3Desc(updatedDetails.getDiag3Desc());

        // Update Lab markers
        existingDiagnosis.setMaxGluSerum(updatedDetails.getMaxGluSerum());
        existingDiagnosis.setA1cResult(updatedDetails.getA1cResult());

        return existingDiagnosis; 
    }

    @Transactional
    public void deleteDiagnosis(Long id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Diagnosis record not found with ID: " + id));
        diagnosisRepository.delete(diagnosis);
    }
}