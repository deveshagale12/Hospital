package com.Hospital.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Hospital.ResourceNotFoundException;
import com.Hospital.entity.Patient;
import com.Hospital.repo.PatientRepository;

import java.util.List;
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    // Manual Constructor Injection (No Lombok)
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // ── POST: Register New Patient ───────────────────────────────────────────
    @Transactional
    public Patient createPatient(Patient patient) {
        // Business Rule validation: Ensure duplicate patient numbers are caught early
        if (patientRepository.findByPatientNbr(patient.getPatientNbr()).isPresent()) {
            throw new RuntimeException("Patient with number " + patient.getPatientNbr() + " already exists.");
        }
        return patientRepository.save(patient);
    }

    // ── GET: Retrieve All Patients ───────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // ── GET: Find By Primary Key ID ──────────────────────────────────────────
    @Transactional(readOnly = true)
 // Example usage inside PatientService.java:
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with internal ID: " + id));
    }

    // ── GET: Find By Unique Business Number (patient_nbr) ────────────────────
    @Transactional(readOnly = true)
    public Patient getPatientByNumber(Long patientNbr) {
        return patientRepository.findByPatientNbr(patientNbr)
            .orElseThrow(() -> new RuntimeException("Patient record not found with Number: " + patientNbr));
    }

    // ── PUT: Update Patient Demographics ─────────────────────────────────────
    @Transactional
    public Patient updatePatient(Long id, Patient updatedDetails) {
        Patient existingPatient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found with Internal ID: " + id));

        // Update demographic metadata fields
        existingPatient.setRace(updatedDetails.getRace());
        existingPatient.setGender(updatedDetails.getGender());
        existingPatient.setAge(updatedDetails.getAge());
        existingPatient.setWeight(updatedDetails.getWeight());
        
        // Note: We deliberately exclude patientNbr here to prevent modifying unique tracking keys

        return existingPatient; // Updated in DB on method exit via dirty checking transaction logs
    }

    // ── DELETE: Remove Patient and Cascade Purge ─────────────────────────────
    @Transactional
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found with Internal ID: " + id));
        patientRepository.delete(patient);
    }
    
 // ── POST: Authenticate / Login Patient ─────────────────────────────────────
    @Transactional(readOnly = true)
    public Patient loginPatient(String email, String password) {
        Patient patient = patientRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Authentication Failure: Invalid email credentials."));

        if (!patient.getPassword().equals(password)) {
            throw new RuntimeException("Authentication Failure: Incorrect password.");
        }
        return patient;
    }
}