package com.Hospital.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hospital.entity.Patient;
import com.Hospital.service.PatientService;

import java.util.List;

@RestController
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

    // ── PUT: Update (Handles both clinical demographics & auth profile data) ──
    // URL: PUT http://localhost:8080/api/patients/1
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, 
                                                 @RequestBody Patient patientDetails) {
        Patient updated = patientService.updatePatient(id, patientDetails);
        return ResponseEntity.ok(updated);
    }
    
 // ── POST: Patient Registration (Alias Endpoint) ───────────────────────────
 // URL: POST http://localhost:8080/api/patients/register
 @PostMapping("/register")
 public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient) {
     Patient savedPatient = patientService.createPatient(patient);
     return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
 }

//── POST: Secure Patient Login ─────────────────────────────────────────────
//URL: POST http://localhost:8080/api/patients/login
@PostMapping("/login")
public ResponseEntity<?> loginPatient(@RequestBody com.Hospital.dto.LoginRequest loginRequest) {
  try {
      // Authenticate via service layer
      Patient patient = patientService.loginPatient(
          loginRequest.getEmail(), 
          loginRequest.getPassword()
      );
      
      // Map to secure custom response structure
      com.Hospital.dto.LoginResponse response = new com.Hospital.dto.LoginResponse(
          true,
          "Login successful! Welcome back.",
          patient.getId(),
          patient.getFullName(),
          patient.getEmail()
      );
      
      return ResponseEntity.ok(response);
      
  } catch (RuntimeException ex) {
      // Standardized failure mapping structure
      com.Hospital.dto.LoginResponse errorResponse = new com.Hospital.dto.LoginResponse(
          false,
          ex.getMessage(), // Passes "Incorrect password" or "Invalid email"
          null,
          null,
          null
      );
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
  }
}


    // ── DELETE: Remove ───────────────────────────────────────────────────────
    // URL: DELETE http://localhost:8080/api/patients/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient record along with all related clinical logs dropped successfully for ID: " + id);
    }
    
    
}