package com.Hospital.service;

import com.Hospital.entity.Encounter;
import com.Hospital.entity.Readmitted;
import com.Hospital.repo.EncounterRepository;
import com.Hospital.repo.ReadmittedRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReadmittedService {

    private final ReadmittedRepository readmittedRepository;
    private final EncounterRepository encounterRepository;

    public ReadmittedService(ReadmittedRepository readmittedRepository, EncounterRepository encounterRepository) {
        this.readmittedRepository = readmittedRepository;
        this.encounterRepository = encounterRepository;
    }

    @Transactional(readOnly = true)
    public Readmitted getReadmittedByEncounter(Long encounterId) {
        return readmittedRepository.findByEncounterId(encounterId)
            .orElseThrow(() -> new RuntimeException("Readmission logs not found for encounter ID: " + encounterId));
    }

    @Transactional
    public Readmitted saveOrUpdateReadmitted(Long encounterId, Readmitted incoming) {
        Encounter encounter = encounterRepository.findById(encounterId)
            .orElseThrow(() -> new RuntimeException("Encounter parent record not found with ID: " + encounterId));

        // Check if an assessment log already exists, or initialize a new one
        Readmitted readmitted = readmittedRepository.findByEncounterId(encounterId)
            .orElse(new Readmitted());

        // Map updated attributes safely
        readmitted.setIsReadmitted(incoming.getIsReadmitted());
        readmitted.setRiskScore(incoming.getRiskScore());
        readmitted.setPrimaryCause(incoming.getPrimaryCause());
        readmitted.setFollowUpScheduled(incoming.getFollowUpScheduled());
        
        // Link bidirectional relationship
        readmitted.setEncounter(encounter);
        
        return readmittedRepository.save(readmitted);
    }
}