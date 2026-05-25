package com.Hospital.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hospital.entity.Medication;
import com.Hospital.service.MedicationService;

import java.util.List;  

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    private final MedicationService medicationService;

    // Manual Constructor Injection
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    // ── POST: Create ─────────────────────────────────────────────────────────
    // URL: POST http://localhost:8080/api/medications/encounter/1
    @PostMapping("/encounter/{encounterId}")
    public ResponseEntity<Medication> createMedication(@PathVariable Long encounterId, 
                                                       @RequestBody Medication medication) {
        Medication savedMedication = medicationService.createMedication(encounterId, medication);
        return new ResponseEntity<>(savedMedication, HttpStatus.CREATED);
    }

    // ── GET: All Records ─────────────────────────────────────────────────────
    // URL: GET http://localhost:8080/api/medications
    @GetMapping
    public ResponseEntity<List<Medication>> getAllMedications() {
        return ResponseEntity.ok(medicationService.getAllMedications());
    }

    // ── GET: By Primary Key ID ───────────────────────────────────────────────
    // URL: GET http://localhost:8080/api/medications/1
    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable Long id) {
        return ResponseEntity.ok(medicationService.getMedicationById(id));
    }

    // ── GET: Filter By Encounter ID ──────────────────────────────────────────
    // URL: GET http://localhost:8080/api/medications/encounter/1
    @GetMapping("/encounter/{encounterId}")
    public ResponseEntity<Medication> getMedicationByEncounterId(@PathVariable Long encounterId) {
        return ResponseEntity.ok(medicationService.getMedicationByEncounterId(encounterId));
    }

    // ── PUT: Update Entire Matrix ────────────────────────────────────────────
    // URL: PUT http://localhost:8080/api/medications/1
    @PutMapping("/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable Long id, 
                                                       @RequestBody Medication medicationDetails) {
        Medication updated = medicationService.updateMedication(id, medicationDetails);
        return ResponseEntity.ok(updated);
    }

    // ── DELETE: Remove Record ────────────────────────────────────────────────
    // URL: DELETE http://localhost:8080/api/medications/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return ResponseEntity.ok("Medication record successfully dropped for ID: " + id);
    }
}