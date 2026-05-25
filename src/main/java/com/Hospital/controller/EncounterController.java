package com.Hospital.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hospital.entity.Encounter;
import com.Hospital.service.EncounterService;

import java.util.List;

@RestController
@CrossOrigin("*")

@RequestMapping("/api/encounters")
public class EncounterController {

    private final EncounterService encounterService;

    // Manual Constructor Injection
    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    // ── POST: Create ─────────────────────────────────────────────────────────
    // URL: POST http://localhost:8080/api/encounters/patient/1
    @PostMapping("/patient/{patientId}")
    public ResponseEntity<Encounter> createEncounter(@PathVariable Long patientId, 
                                                     @RequestBody Encounter encounter) {
        Encounter savedEncounter = encounterService.createEncounter(patientId, encounter);
        return new ResponseEntity<>(savedEncounter, HttpStatus.CREATED);
    }

    // ── GET: All ─────────────────────────────────────────────────────────────
    // URL: GET http://localhost:8080/api/encounters
    @GetMapping
    public ResponseEntity<List<Encounter>> getAllEncounters() {
        return ResponseEntity.ok(encounterService.getAllEncounters());
    }

    // ── GET: By Primary Key ID ───────────────────────────────────────────────
    // URL: GET http://localhost:8080/api/encounters/1
    @GetMapping("/{id}")
    public ResponseEntity<Encounter> getEncounterById(@PathVariable Long id) {
        return ResponseEntity.ok(encounterService.getEncounterById(id));
    }

    // ── GET: Filter By Patient ───────────────────────────────────────────────
    // URL: GET http://localhost:8080/api/encounters/patient/1
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Encounter>> getEncountersByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(encounterService.getEncountersByPatient(patientId));
    }

    // ── PUT: Update ──────────────────────────────────────────────────────────
    // URL: PUT http://localhost:8080/api/encounters/1
    @PutMapping("/{id}")
    public ResponseEntity<Encounter> updateEncounter(@PathVariable Long id, 
                                                     @RequestBody Encounter encounterDetails) {
        Encounter updated = encounterService.updateEncounter(id, encounterDetails);
        return ResponseEntity.ok(updated);
    }

    // ── DELETE: Remove ───────────────────────────────────────────────────────
    // URL: DELETE http://localhost:8080/api/encounters/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEncounter(@PathVariable Long id) {
        encounterService.deleteEncounter(id);
        return ResponseEntity.ok("Encounter record removed successfully for internal ID: " + id);
    }
}