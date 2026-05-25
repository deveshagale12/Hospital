package com.Hospital.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hospital.entity.Diagnosis;
import com.Hospital.service.DiagnosisService;

import java.util.List;

@RestController
@RequestMapping("/api/diagnoses")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    // Manual Constructor Injection
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    // ── POST: Create ─────────────────────────────────────────────────────────
    // URL: POST http://localhost:8080/api/diagnoses/encounter/1
    @PostMapping("/encounter/{encounterId}")
    public ResponseEntity<Diagnosis> createDiagnosis(@PathVariable Long encounterId, 
                                                     @RequestBody Diagnosis diagnosis) {
        Diagnosis savedDiagnosis = diagnosisService.createDiagnosis(encounterId, diagnosis);
        return new ResponseEntity<>(savedDiagnosis, HttpStatus.CREATED);
    }

    // ── GET: All ─────────────────────────────────────────────────────────────
    // URL: GET http://localhost:8080/api/diagnoses
    @GetMapping
    public ResponseEntity<List<Diagnosis>> getAllDiagnoses() {
        List<Diagnosis> list = diagnosisService.getAllDiagnoses();
        return ResponseEntity.ok(list);
    }

    // ── GET: By Primary Key ID ───────────────────────────────────────────────
    // URL: GET http://localhost:8080/api/diagnoses/1
    @GetMapping("/{id}")
    public ResponseEntity<Diagnosis> getDiagnosisById(@PathVariable Long id) {
        Diagnosis diagnosis = diagnosisService.getDiagnosisById(id);
        return ResponseEntity.ok(diagnosis);
    }

    // ── GET: Filter By Encounter ID ──────────────────────────────────────────
    // URL: GET http://localhost:8080/api/diagnoses/encounter/1
    @GetMapping("/encounter/{encounterId}")
    public ResponseEntity<Diagnosis> getDiagnosisByEncounterId(@PathVariable Long encounterId) {
        Diagnosis diagnosis = diagnosisService.getDiagnosisByEncounterId(encounterId);
        return ResponseEntity.ok(diagnosis);
    }

    // ── PUT: Update ──────────────────────────────────────────────────────────
    // URL: PUT http://localhost:8080/api/diagnoses/1
    @PutMapping("/{id}")
    public ResponseEntity<Diagnosis> updateDiagnosis(@PathVariable Long id, 
                                                     @RequestBody Diagnosis diagnosisDetails) {
        Diagnosis updatedDiagnosis = diagnosisService.updateDiagnosis(id, diagnosisDetails);
        return ResponseEntity.ok(updatedDiagnosis);
    }

    // ── DELETE: Remove ───────────────────────────────────────────────────────
    // URL: DELETE http://localhost:8080/api/diagnoses/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiagnosis(@PathVariable Long id) {
        diagnosisService.deleteDiagnosis(id);
        return ResponseEntity.ok("Diagnosis record deleted successfully with ID: " + id);
    }
}