package com.Hospital.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hospital.entity.Encounter;

import java.util.List;
import java.util.Optional;
@Repository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {
    Optional<Encounter> findByEncounterId(Long encounterId);
    List<Encounter> findByPatient_Id(Long patientId);    
}