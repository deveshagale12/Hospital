package com.Hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Core patient demographics.
 * Columns: patient_nbr, race, gender, age, weight
 */

@Entity
@Table(name = "patients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_nbr", unique = true, nullable = false)
    @NotNull(message = "Patient number is required")
    private Long patientNbr;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "race", length = 50)
    private String race;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "age", length = 20)
    private String age;

    @Column(name = "weight", length = 20)
    private String weight;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Encounter> encounters = new ArrayList<>();

    public Patient() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPatientNbr() { return patientNbr; }
    public void setPatientNbr(Long patientNbr) { this.patientNbr = patientNbr; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }

    public List<Encounter> getEncounters() { return encounters; }
    public void setEncounters(List<Encounter> encounters) { this.encounters = encounters; }
}