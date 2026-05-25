package com.Hospital.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hospital.entity.Patient;
import com.Hospital.service.PatientService;

import java.util.List;

@RestController
@CrossOrigin("*")

@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    // Manual Constructor Injection
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // ── POST: Create ─────────────────────────────────────────────────────────
    // URL: POST http://localhost:8080/api/patients
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    // ── GET: All Records ─────────────────────────────────────────────────────
    // URL: GET http://localhost:8080/api/patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // ── GET: By Primary Key ID ───────────────────────────────────────────────
    // URL: GET http://localhost:8080/api/patients/1
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    // ── GET: By Unique Patient Number ────────────────────────────────────────
    // URL: GET http://localhost:8080/api/patients/number/8222157
    @GetMapping("/number/{patientNbr}")
    public ResponseEntity<Patient> getPatientByNumber(@PathVariable Long patientNbr) {
        return ResponseEntity.ok(patientService.getPatientByNumber(patientNbr));
    }

    // ── PUT: Update ──────────────────────────────────────────────────────────
    // URL: PUT http://localhost:8080/api/patients/1
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, 
                                                 @RequestBody Patient patientDetails) {
        Patient updated = patientService.updatePatient(id, patientDetails);
        return ResponseEntity.ok(updated);
    }

    // ── DELETE: Remove ───────────────────────────────────────────────────────
    // URL: DELETE http://localhost:8080/api/patients/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient record along with all related clinical logs dropped successfully for ID: " + id);
    }
}