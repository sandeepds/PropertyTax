package com.mindtree.propertytax.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.propertytax.dao.PropertyTaxDAO;
import com.mindtree.propertytax.model.PropertyDetails;
import com.mindtree.propertytax.model.UserDetails;

@Service
public class PropertyTaxServiceImpl implements PropertyTaxService {

	@Autowired
	private PropertyTaxDAO propertyTaxDAO;

	public List<String> getPropertyZoneDetails() {
		return propertyTaxDAO.getPropertyZoneDetails();

	}

	public List<String> getPropertyDescriptionDetails() {
		return propertyTaxDAO.getPropertyDescriptionDetails();
	}

	public void savePropertyDetails(UserDetails userDetails, PropertyDetails propertyDetails) {
		propertyTaxDAO.savePropertyDetails(userDetails, propertyDetails);
	}

	public HashMap<String, HashMap<String, Double>> getZoneWiseReport(List<String> zoneList,
			List<String> propertyStatusList) {
		return propertyTaxDAO.getZoneWiseReport(zoneList, propertyStatusList);
	}

	public List<String> getPropertyStatusDetails() {
		return propertyTaxDAO.getPropertyStatusDetails();

	}

	/**
	 * This method will accepts zoneValue,buildingConstructedYear and builtUpArea
	 * and will calculate the Tax and returns the calculated Tax value.
	 * 
	 * @return Double
	 */
	public Double calculateTax(Double zoneValue, Integer buildingConstructedYear, Integer builtUpArea) {

		Integer currentYear = 0;
		Integer ageOfBuilding = 0;
		Double depreciation = 0.0;
		Double total1 = 0.0;
		Double total2 = 0.0;
		Double total3 = 0.0;
		Double total4 = 0.0;
		Double total5 = 0.0;

		currentYear = Calendar.getInstance().get(Calendar.YEAR);

		total1 = builtUpArea * zoneValue * 10;

		ageOfBuilding = currentYear - buildingConstructedYear;

		if (ageOfBuilding > 60)
			depreciation = (60 * total1) / 100;
		else
			depreciation = (ageOfBuilding * total1) / 100;

		total2 = total1 - depreciation;

		total3 = total2 * 20 / 100;

		total4 = total3 * 24 / 100;

		total5 = total3 + total4;

		return total5;
	}

	/**
	 * This method will accepts zoneType,propertyDescription,propertyStatus and
	 * return the ZoneValue.
	 * 
	 * @return Double
	 * 
	 */

	public Double getZoneValue(String zoneType, String propertyDescription, String propertyStatus) {
		return propertyTaxDAO.getZoneValue(zoneType, propertyDescription, propertyStatus);
	}

}
