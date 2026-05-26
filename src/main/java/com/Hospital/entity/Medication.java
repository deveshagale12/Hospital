package com.Hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

/**
 * Diabetes medication dosage information for an encounter.
 * Each drug column holds a dosage-change flag: Up / Down / Steady / No
 */
@Entity
@Table(name = "medications")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Hibernate proxy fix
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Biguanides ────────────────────────────────────────────────────────────
    @Column(name = "metformin", length = 10)
    private String metformin;

    // ── Meglitinides ──────────────────────────────────────────────────────────
    @Column(name = "repaglinide", length = 10)
    private String repaglinide;

    @Column(name = "nateglinide", length = 10)
    private String nateglinide;

    // ── Sulfonylureas ─────────────────────────────────────────────────────────
    @Column(name = "chlorpropamide", length = 10)
    private String chlorpropamide;

    @Column(name = "glimepiride", length = 10)
    private String glimepiride;

    @Column(name = "acetohexamide", length = 10)
    private String acetohexamide;

    @Column(name = "glipizide", length = 10)
    private String glipizide;

    @Column(name = "glyburide", length = 10)
    private String glyburide;

    @Column(name = "tolbutamide", length = 10)
    private String tolbutamide;

    @Column(name = "tolazamide", length = 10)
    private String tolazamide;

    // ── Thiazolidinediones ────────────────────────────────────────────────────
    @Column(name = "pioglitazone", length = 10)
    private String pioglitazone;

    @Column(name = "rosiglitazone", length = 10)
    private String rosiglitazone;

    @Column(name = "troglitazone", length = 10)
    private String troglitazone;

    // ── Alpha-glucosidase inhibitors ──────────────────────────────────────────
    @Column(name = "acarbose", length = 10)
    private String acarbose;

    @Column(name = "miglitol", length = 10)
    private String miglitol;

    // ── Others ────────────────────────────────────────────────────────────────
    @Column(name = "examide", length = 10)
    private String examide;

    @Column(name = "citoglipton", length = 10)
    private String citoglipton;

    @Column(name = "insulin", length = 10)
    private String insulin;

    // ── Combination drugs ─────────────────────────────────────────────────────
    @Column(name = "glyburide_metformin", length = 10)
    private String glyburideMetformin;

    @Column(name = "glipizide_metformin", length = 10)
    private String glipizideMetformin;

    @Column(name = "glimepiride_pioglitazone", length = 10)
    private String glimepridePioglitazone;

    @Column(name = "metformin_rosiglitazone", length = 10)
    private String metforminRosiglitazone;

    @Column(name = "metformin_pioglitazone", length = 10)
    private String metforminPioglitazone;

    // ── Relationship ──────────────────────────────────────────────────────────
    /**
     * @JsonIgnore breaks the loop:
     *   Encounter → medication → encounter → medication → ...
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encounter_id", nullable = false, unique = true)
    private Encounter encounter;

    @Column(name = "encounter_id", insertable = false, updatable = false)
    private Long encounterId;

    public Medication() {
    	
    }
	public Medication(Long id, String metformin, String repaglinide, String nateglinide, String chlorpropamide,
			String glimepiride, String acetohexamide, String glipizide, String glyburide, String tolbutamide,
			String tolazamide, String pioglitazone, String rosiglitazone, String troglitazone, String acarbose,
			String miglitol, String examide, String citoglipton, String insulin, String glyburideMetformin,
			String glipizideMetformin, String glimepridePioglitazone, String metforminRosiglitazone,
			String metforminPioglitazone, Encounter encounter, Long encounterId) {
		super();
		this.id = id;
		this.metformin = metformin;
		this.repaglinide = repaglinide;
		this.nateglinide = nateglinide;
		this.chlorpropamide = chlorpropamide;
		this.glimepiride = glimepiride;
		this.acetohexamide = acetohexamide;
		this.glipizide = glipizide;
		this.glyburide = glyburide;
		this.tolbutamide = tolbutamide;
		this.tolazamide = tolazamide;
		this.pioglitazone = pioglitazone;
		this.rosiglitazone = rosiglitazone;
		this.troglitazone = troglitazone;
		this.acarbose = acarbose;
		this.miglitol = miglitol;
		this.examide = examide;
		this.citoglipton = citoglipton;
		this.insulin = insulin;
		this.glyburideMetformin = glyburideMetformin;
		this.glipizideMetformin = glipizideMetformin;
		this.glimepridePioglitazone = glimepridePioglitazone;
		this.metforminRosiglitazone = metforminRosiglitazone;
		this.metforminPioglitazone = metforminPioglitazone;
		this.encounter = encounter;
		this.encounterId = encounterId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMetformin() {
		return metformin;
	}
	public void setMetformin(String metformin) {
		this.metformin = metformin;
	}
	public String getRepaglinide() {
		return repaglinide;
	}
	public void setRepaglinide(String repaglinide) {
		this.repaglinide = repaglinide;
	}
	public String getNateglinide() {
		return nateglinide;
	}
	public void setNateglinide(String nateglinide) {
		this.nateglinide = nateglinide;
	}
	public String getChlorpropamide() {
		return chlorpropamide;
	}
	public void setChlorpropamide(String chlorpropamide) {
		this.chlorpropamide = chlorpropamide;
	}
	public String getGlimepiride() {
		return glimepiride;
	}
	public void setGlimepiride(String glimepiride) {
		this.glimepiride = glimepiride;
	}
	public String getAcetohexamide() {
		return acetohexamide;
	}
	public void setAcetohexamide(String acetohexamide) {
		this.acetohexamide = acetohexamide;
	}
	public String getGlipizide() {
		return glipizide;
	}
	public void setGlipizide(String glipizide) {
		this.glipizide = glipizide;
	}
	public String getGlyburide() {
		return glyburide;
	}
	public void setGlyburide(String glyburide) {
		this.glyburide = glyburide;
	}
	public String getTolbutamide() {
		return tolbutamide;
	}
	public void setTolbutamide(String tolbutamide) {
		this.tolbutamide = tolbutamide;
	}
	public String getTolazamide() {
		return tolazamide;
	}
	public void setTolazamide(String tolazamide) {
		this.tolazamide = tolazamide;
	}
	public String getPioglitazone() {
		return pioglitazone;
	}
	public void setPioglitazone(String pioglitazone) {
		this.pioglitazone = pioglitazone;
	}
	public String getRosiglitazone() {
		return rosiglitazone;
	}
	public void setRosiglitazone(String rosiglitazone) {
		this.rosiglitazone = rosiglitazone;
	}
	public String getTroglitazone() {
		return troglitazone;
	}
	public void setTroglitazone(String troglitazone) {
		this.troglitazone = troglitazone;
	}
	public String getAcarbose() {
		return acarbose;
	}
	public void setAcarbose(String acarbose) {
		this.acarbose = acarbose;
	}
	public String getMiglitol() {
		return miglitol;
	}
	public void setMiglitol(String miglitol) {
		this.miglitol = miglitol;
	}
	public String getExamide() {
		return examide;
	}
	public void setExamide(String examide) {
		this.examide = examide;
	}
	public String getCitoglipton() {
		return citoglipton;
	}
	public void setCitoglipton(String citoglipton) {
		this.citoglipton = citoglipton;
	}
	public String getInsulin() {
		return insulin;
	}
	public void setInsulin(String insulin) {
		this.insulin = insulin;
	}
	public String getGlyburideMetformin() {
		return glyburideMetformin;
	}
	public void setGlyburideMetformin(String glyburideMetformin) {
		this.glyburideMetformin = glyburideMetformin;
	}
	public String getGlipizideMetformin() {
		return glipizideMetformin;
	}
	public void setGlipizideMetformin(String glipizideMetformin) {
		this.glipizideMetformin = glipizideMetformin;
	}
	public String getGlimepridePioglitazone() {
		return glimepridePioglitazone;
	}
	public void setGlimepridePioglitazone(String glimepridePioglitazone) {
		this.glimepridePioglitazone = glimepridePioglitazone;
	}
	public String getMetforminRosiglitazone() {
		return metforminRosiglitazone;
	}
	public void setMetforminRosiglitazone(String metforminRosiglitazone) {
		this.metforminRosiglitazone = metforminRosiglitazone;
	}
	public String getMetforminPioglitazone() {
		return metforminPioglitazone;
	}
	public void setMetforminPioglitazone(String metforminPioglitazone) {
		this.metforminPioglitazone = metforminPioglitazone;
	}
	public Encounter getEncounter() {
		return encounter;
	}
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	public Long getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Long encounterId) {
		this.encounterId = encounterId;
	}
    
    
}
