package com.mindtree.propertytax.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "propertydetails")
public class PropertyDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int propertyDetailId;

	@Column(name = "zonetype")
	private String zoneType;

	@Column(name = "propertydescription")
	private String propertyDescription;

	@Column(name = "propertystatus")
	private String propertyStatus;

	@Column(name = "buildingconstructedyear")
	private int buildingConstructedYear;

	@Column(name = "builtuparea")
	private int builtupArea;

	@Column(name = "totaltax")
	private Double totalTax;

	@Column(name = "assessmentyear")
	private int assessmentYear;

	@OneToOne(targetEntity = UserDetails.class)
	private UserDetails userDetails;

	
	public int getPropertyDetailId() {
		return propertyDetailId;
	}

	public void setPropertyDetailId(int propertyDetailId) {
		this.propertyDetailId = propertyDetailId;
	}

	public String getZoneType() {
		return zoneType;
	}

	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}

	public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	public String getPropertyStatus() {
		return propertyStatus;
	}

	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
	}

	public int getBuildingConstructedYear() {
		return buildingConstructedYear;
	}

	public void setBuildingConstructedYear(int buildingConstructedYear) {
		this.buildingConstructedYear = buildingConstructedYear;
	}

	public int getBuiltupArea() {
		return builtupArea;
	}

	public void setBuiltupArea(int builtupArea) {
		this.builtupArea = builtupArea;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

	public int getAssessmentYear() {
		return assessmentYear;
	}

	public void setAssessmentYear(int assessmentYear) {
		this.assessmentYear = assessmentYear;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}
