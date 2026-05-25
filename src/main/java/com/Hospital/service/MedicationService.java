package com.Hospital.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Hospital.entity.Encounter;
import com.Hospital.entity.Medication;
import com.Hospital.repo.EncounterRepository;
import com.Hospital.repo.MedicationRepository;

import java.util.List;
@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final EncounterRepository encounterRepository;

    // Manual Constructor Injection (No Lombok)
    public MedicationService(MedicationRepository medicationRepository, EncounterRepository encounterRepository) {
        this.medicationRepository = medicationRepository;
        this.encounterRepository = encounterRepository;
    }

    // ── POST: Link and Save Medication for an Encounter ─────────────────────
    @Transactional
    public Medication createMedication(Long encounterId, Medication medication) {
        Encounter encounter = encounterRepository.findById(encounterId)
            .orElseThrow(() -> new RuntimeException("Encounter not found with ID: " + encounterId));
        
        // Link bidirectional mapping
        medication.setEncounter(encounter);
        return medicationRepository.save(medication);
    }

    // ── GET: Retrieve All Records ────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    // ── GET: Find By Primary Key ID ──────────────────────────────────────────
    @Transactional(readOnly = true)
    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Medication record not found with ID: " + id));
    }

    // ── GET: Find By Encounter ID ───────────────────────────────────────────
    @Transactional(readOnly = true)
    public Medication getMedicationByEncounterId(Long encounterId) {
        return medicationRepository.findByEncounterId(encounterId)
            .orElseThrow(() -> new RuntimeException("No medication record found for Encounter ID: " + encounterId));
    }

    // ── PUT: Full Matrix Update ──────────────────────────────────────────────
    @Transactional
    public Medication updateMedication(Long id, Medication updatedDetails) {
        Medication existingMed = medicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Medication record not found with ID: " + id));

        // 1. Biguanides
        existingMed.setMetformin(updatedDetails.getMetformin());

        // 2. Meglitinides
        existingMed.setRepaglinide(updatedDetails.getRepaglinide());
        existingMed.setNateglinide(updatedDetails.getNateglinide());

        // 3. Sulfonylureas
        existingMed.setChlorpropamide(updatedDetails.getChlorpropamide());
        existingMed.setGlimepiride(updatedDetails.getGlimepiride());
        existingMed.setAcetohexamide(updatedDetails.getAcetohexamide());
        existingMed.setGlipizide(updatedDetails.getGlipizide());
        existingMed.setGlyburide(updatedDetails.getGlyburide());
        existingMed.setTolbutamide(updatedDetails.getTolbutamide());
        existingMed.setTolazamide(updatedDetails.getTolazamide());

        // 4. Thiazolidinediones
        existingMed.setPioglitazone(updatedDetails.getPioglitazone());
        existingMed.setRosiglitazone(updatedDetails.getRosiglitazone());
        existingMed.setTroglitazone(updatedDetails.getTroglitazone());

        // 5. Alpha-glucosidase inhibitors
        existingMed.setAcarbose(updatedDetails.getAcarbose());
        existingMed.setMiglitol(updatedDetails.getMiglitol());

        // 6. Others & Insulin
        existingMed.setExamide(updatedDetails.getExamide());
        existingMed.setCitoglipton(updatedDetails.getCitoglipton());
        existingMed.setInsulin(updatedDetails.getInsulin());

        // 7. Combination Drugs
        existingMed.setGlyburideMetformin(updatedDetails.getGlyburideMetformin());
        existingMed.setGlipizideMetformin(updatedDetails.getGlipizideMetformin());
        existingMed.setGlimepridePioglitazone(updatedDetails.getGlimepridePioglitazone());
        existingMed.setMetforminRosiglitazone(updatedDetails.getMetforminRosiglitazone());
        existingMed.setMetforminPioglitazone(updatedDetails.getMetforminPioglitazone());

        return existingMed; // Saved automatically upon transaction commit execution block
    }

    // ── DELETE: Remove Record ────────────────────────────────────────────────
    @Transactional
    public void deleteMedication(Long id) {
        Medication medication = medicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Medication record not found with ID: " + id));
        medicationRepository.delete(medication);
    }
}