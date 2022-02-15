package com.mindtree.propertytax.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.propertytax.model.PropertyDetails;
import com.mindtree.propertytax.model.PropertyTax;
import com.mindtree.propertytax.model.UserDetails;
import com.mindtree.propertytax.service.PropertyTaxService;

/**
 * @author M1026220
 * 
 *         This is the Controller Class which will accept Request from
 *         UserInterfcae and send Response to the UserInterface
 *
 */
@Controller
public class PropertyTaxController {

	private Logger logger = Logger.getLogger(PropertyTaxController.class);

	@Autowired
	private PropertyTaxService propertyTaxService;

	/**
	 * This method will get ZoneList and PropertyList from Database and sends to
	 * PropertyRegistrationPage(UI) to display in the DropDown.
	 * 
	 * @return ModelAndView
	 */

	@RequestMapping("/zonePropertyDetails")
	public ModelAndView getZoneAndPropertyDetails() {

		logger.info("Inside getZoneAndPropertyDetails Method");
		ModelAndView zoneAndPropertyModel = null;

		try {

			List<String> zoneList = propertyTaxService.getPropertyZoneDetails();
			List<String> propertyList = propertyTaxService.getPropertyDescriptionDetails();
			List<String> propertyStatusList = propertyTaxService.getPropertyStatusDetails();

			zoneAndPropertyModel = new ModelAndView();
			zoneAndPropertyModel.setViewName("propertyregistrationpage");
			zoneAndPropertyModel.addObject("zoneList", zoneList);
			zoneAndPropertyModel.addObject("propertyList", propertyList);
			zoneAndPropertyModel.addObject("propertyStatusList", propertyStatusList);

		} catch (NullPointerException exception) {
			logger.error("Exception while Saving Data" + exception.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}

		logger.info("Zone and Property Details Retreived Successfully");
		return zoneAndPropertyModel;
	}

	/**
	 * This method is used to accept Request from PropertyRegistrationPage(UI) and
	 * persist the Data to Database and on success redirects the response to the
	 * Successful page(UI)
	 * 
	 * @param httpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping("/propertyRegistration")
	public ModelAndView savePropertyDetails(@ModelAttribute("propertyTax") PropertyTax propertyTax) {

		logger.info("Inside savePropertyDetails Method");
		ModelAndView propertyDetailsModel = null;

		try {
			UserDetails userDetails = new UserDetails();
			userDetails.setUserName(propertyTax.getOwnerName());
			userDetails.setEmail(propertyTax.getEmail());
			userDetails.setAddress(propertyTax.getAddressOfProperty());

			PropertyDetails propertyDetails = new PropertyDetails();
			propertyDetails.setAssessmentYear(Integer.parseInt(propertyTax.getYearOfAssessment()));

			propertyDetails.setZoneType(propertyTax.getZoneType());
			propertyDetails.setPropertyDescription(propertyTax.getPropertyDescription());
			propertyDetails.setPropertyStatus(propertyTax.getPropertyStatus());
			propertyDetails.setBuildingConstructedYear(Integer.parseInt(propertyTax.getBuildingConstructedYear()));
			propertyDetails.setBuiltupArea(Integer.parseInt(propertyTax.getBuiltupArea()));
			propertyDetails.setTotalTax(Double.parseDouble(propertyTax.getTotalTax()));

			userDetails.setPropertyDetails(propertyDetails);
			propertyDetails.setUserDetails(userDetails);

			propertyTaxService.savePropertyDetails(userDetails, propertyDetails);

			propertyDetailsModel = new ModelAndView();
			propertyDetailsModel.setViewName("success");
			propertyDetailsModel.addObject("message", "PropertyDetails are added Successfully");

		} catch (NullPointerException | NumberFormatException exception) {
			logger.error("Exception while Saving Data" + exception.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}

		logger.info("Property Details Saved Successfully");
		return propertyDetailsModel;
	}

	/**
	 * This method is to get ZoneDetails and PropertyStatusDetails from Database and
	 * display the ZoneWiseReport based on each Zone and PropertyStatus on
	 * ZoneWiseReportPage(UI)
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/zoneWiseReport")
	public ModelAndView displayZoneWiseReport() {

		logger.info("Inside DisplayZoneWiseReport Method");
		ModelAndView zoneWiseReportModel = null;

		try {

			List<String> zoneList = propertyTaxService.getPropertyZoneDetails();
			List<String> propertyStatusList = propertyTaxService.getPropertyStatusDetails();
			HashMap<String, HashMap<String, Double>> zoneMap = propertyTaxService.getZoneWiseReport(zoneList,propertyStatusList);

			zoneWiseReportModel = new ModelAndView();
			zoneWiseReportModel.setViewName("zonewisereportpage");
			zoneWiseReportModel.addObject("zoneMap", zoneMap);

		} catch (NullPointerException exception) {
			logger.error("Exception while Saving Data" + exception.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}

		logger.info("Displaying the Tax calculated for Each Zone");
		return zoneWiseReportModel;
	}

	/**
	 * This method is to get ZoneValue based on ZoneType,PropertyDescription and
	 * PropertyStatus selected from UI Once the ZoneValue is returned from Database
	 * based on buildingConstructedYear and builtUpArea which are selected from
	 * PropertyRegistrationPage(UI) Total Tax is calculated based on
	 * buildingConstructedYear,builtUpArea and ZoneValue and Total Tax is returned
	 * to PropertyRegistrationPage(UI).
	 * 
	 * @param propertyDetails
	 * @return ResponseEntity<Double
	 */
	@PostMapping("/zoneValueAndCalculateTax")
	public ResponseEntity<Double> getZoneValueAndCalculateTax(@RequestBody PropertyDetails propertyDetails) {

		logger.info("Inside ZoneValue And Calculate Tax Method");
		Double totalTax = 0.0;

		try {
			Double zoneValue = propertyTaxService.getZoneValue(propertyDetails.getZoneType(),propertyDetails.getPropertyDescription(), propertyDetails.getPropertyStatus());
			totalTax = propertyTaxService.calculateTax(zoneValue, propertyDetails.getBuildingConstructedYear(),propertyDetails.getBuiltupArea());

		} catch (NullPointerException exception) {
			logger.error("Exception while Saving Data" + exception.getMessage());
			exception.printStackTrace();
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}

		logger.info("Property Tax Calculated Successfully and the Tax Value is " + totalTax);
		return ResponseEntity.ok().body(totalTax);
	}

}