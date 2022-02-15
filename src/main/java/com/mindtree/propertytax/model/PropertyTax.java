package com.mindtree.propertytax.model;

public class PropertyTax {
	
	private String ownerName;
	private String email;
	private String addressOfProperty;
	private String zoneType;
	private String propertyDescription;
	private String propertyStatus;
	private String buildingConstructedYear;
	private String builtupArea;
	private String totalTax;
	private String yearOfAssessment;
	
	public PropertyTax() {
		
	}
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddressOfProperty() {
		return addressOfProperty;
	}

	public void setAddressOfProperty(String addressOfProperty) {
		this.addressOfProperty = addressOfProperty;
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
	public String getBuildingConstructedYear() {
		return buildingConstructedYear;
	}
	public void setBuildingConstructedYear(String buildingConstructedYear) {
		this.buildingConstructedYear = buildingConstructedYear;
	}
	public String getBuiltupArea() {
		return builtupArea;
	}
	public void setBuiltupArea(String builtupArea) {
		this.builtupArea = builtupArea;
	}
	public String getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}
	public String getYearOfAssessment() {
		return yearOfAssessment;
	}
	public void setYearOfAssessment(String yearOfAssessment) {
		this.yearOfAssessment = yearOfAssessment;
	}
}
