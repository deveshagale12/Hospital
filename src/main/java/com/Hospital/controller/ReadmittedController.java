package com.Hospital.controller;

import com.Hospital.entity.Readmitted;
import com.Hospital.service.ReadmittedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/readmissions")
public class ReadmittedController {

    private final ReadmittedService readmittedService;

    public ReadmittedController(ReadmittedService readmittedService) {
        this.readmittedService = readmittedService;
    }

    // GET: http://localhost:8080/api/readmissions/encounter/1
    @GetMapping("/encounter/{encounterId}")
    public ResponseEntity<Readmitted> getByEncounter(@PathVariable Long encounterId) {
        return ResponseEntity.ok(readmittedService.getReadmittedByEncounter(encounterId));
    }

    // PUT: http://localhost:8080/api/readmissions/encounter/1
    @PutMapping("/encounter/{encounterId}")
    public ResponseEntity<Readmitted> updateReadmitted(@PathVariable Long encounterId, @RequestBody Readmitted readmittedData) {
        Readmitted updated = readmittedService.saveOrUpdateReadmitted(encounterId, readmittedData);
        return ResponseEntity.ok(updated);
    }
}