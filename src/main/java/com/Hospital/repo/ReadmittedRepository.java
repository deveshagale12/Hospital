package com.Hospital.repo;

import com.Hospital.entity.Readmitted;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ReadmittedRepository extends JpaRepository<Readmitted, Long> {
    // Looks up a readmission record using the foreign key link back to the Encounter
    Optional<Readmitted> findByEncounterId(Long encounterId);
}