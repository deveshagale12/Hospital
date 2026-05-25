package com.Hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Core patient demographics.
 * Columns: patient_nbr, race, gender, age, weight
 */
@Entity
@Table(name = "patients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Hibernate proxy fix
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_nbr", unique = true, nullable = false)
    @NotNull(message = "Patient number is required")
    private Long patientNbr;

    @Column(name = "race", length = 50)
    private String race;

    @Column(name = "gender", length = 20)
    private String gender;

    /** Age bracket e.g. "[70-80)" */
    @Column(name = "age", length = 20)
    private String age;

    @Column(name = "weight", length = 20)
    private String weight;

    // ── Relationship ──────────────────────────────────────────────────────────
    /**
     * @JsonIgnore prevents infinite loop:
     *   Patient → Encounter → Patient → Encounter → ...
     */
    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Encounter> encounters = new ArrayList<>();
    
    public Patient() {
    	
    }

	public Patient(Long id, @NotNull(message = "Patient number is required") Long patientNbr, String race,
			String gender, String age, String weight, List<Encounter> encounters) {
		super();
		this.id = id;
		this.patientNbr = patientNbr;
		this.race = race;
		this.gender = gender;
		this.age = age;
		this.weight = weight;
		this.encounters = encounters;
	}

	public Long getId() {
		return id;
	}      

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientNbr() {
		return patientNbr;
	}

	public void setPatientNbr(Long patientNbr) {
		this.patientNbr = patientNbr;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public List<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}
    
}

