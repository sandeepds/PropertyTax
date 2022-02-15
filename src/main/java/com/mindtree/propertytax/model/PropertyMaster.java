package com.mindtree.propertytax.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "propertymaster")
public class PropertyMaster {

	@Id
	@Column(name = "propertymasterid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int propertyMasterId;

	@Column(name = "category")
	private int category;

	@Column(name = "propertydescription")
	private String propertyDescription;

	@Column(name = "propertystatus")
	private String propertyStatus;

	@Column(name = "zonetype")
	private String zoneType;

	@Column(name = "zonevalue")
	private float zoneValue;
	
	public PropertyMaster() {
		
	}

	public PropertyMaster(int propertyMasterId, int category, String propertyDescription, String propertyStatus,
			String zoneType, float zoneValue) {
		super();
		this.propertyMasterId = propertyMasterId;
		this.category = category;
		this.propertyDescription = propertyDescription;
		this.propertyStatus = propertyStatus;
		this.zoneType = zoneType;
		this.zoneValue = zoneValue;
	}

	public int getPropertyMasterId() {
		return propertyMasterId;
	}

	public void setPropertyMasterId(int propertyMasterId) {
		this.propertyMasterId = propertyMasterId;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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

	public String getZoneType() {
		return zoneType;
	}

	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}

	public float getZoneValue() {
		return zoneValue;
	}

	public void setZoneValue(float zoneValue) {
		this.zoneValue = zoneValue;
	}
	
}
