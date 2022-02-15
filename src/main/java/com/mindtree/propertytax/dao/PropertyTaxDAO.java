package com.mindtree.propertytax.dao;

import java.util.HashMap;
import java.util.List;

import com.mindtree.propertytax.model.PropertyDetails;
import com.mindtree.propertytax.model.UserDetails;

public interface PropertyTaxDAO {

	public List<String> getPropertyZoneDetails();

	public List<String> getPropertyDescriptionDetails();

	public void savePropertyDetails(UserDetails userDetails, PropertyDetails propertyDetails);

	public HashMap<String, HashMap<String,Double>> getZoneWiseReport(List<String> zoneList, List<String> propertyStatus);

	public List<String> getPropertyStatusDetails();

	public Double getZoneValue(String zoneType, String propertyDescription, String propertyStatus);

}
