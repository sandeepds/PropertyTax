<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PROPERTY REGISTRATION</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

	function validateform() {
		
		var yearOfAssessment = document.propertyRegistrationForm.yearOfAssessment.value;
		var ownerName = document.propertyRegistrationForm.ownerName.value;
		var email = document.propertyRegistrationForm.email.value;
		var addressOfProperty = document.propertyRegistrationForm.addressOfProperty.value;
		var buildingConstructedYear = document.propertyRegistrationForm.buildingConstructedYear.value;
		var builtupArea = document.propertyRegistrationForm.builtupArea.value;
		var totalTax = document.propertyRegistrationForm.totalTax.value;
		
		var currentYear = (new Date()).getFullYear();
		
		var numbers = /^[0-9]+$/;
		
		if(yearOfAssessment!=currentYear){
			alert("Year of Assessment should be Current Year");
			return false;
		}
		
		if(!yearOfAssessment.match(numbers)){
	      alert("Enter Only Numbers for Year Of Assessment");
	      return false;
	    }

		/* var numbers = /^[-+]?[0-9]+$/;
		 
		 if(yearOfAssessment.match(numbers)){
			 alert("Please Enter Only Number for Year Of Assessment");
			 return false;
		 }*/
		
		if (ownerName == null || ownerName == "") {
			alert("Name of the Owner Cannot Be Blank");
			return false;
		}

		var regExp=/^[a-zA-Z ]*$/;
		
		if (regExp.test(ownerName)==false) {
			alert("Name of the Owner should be Only Characters"); 
			return false;
		}
		
		if (email == null || email == "") {
			alert("Email Cannot Be Blank");
			return false;
		}

		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

		if (reg.test(email) == false) {
			alert('Invalid Email Address');
			return false;
		}

		if (addressOfProperty == null || addressOfProperty == "") {
			alert("Address Cannot Be Blank");
			return false;
		}
		
		if (buildingConstructedYear == null || buildingConstructedYear == "") {
			alert("BuildingConstructed Year Cannot Be Blank");
			return false;
		}

		/*if (isNaN(buildingConstructedYear)) {
			alert("Enter only Numbers for BuildingConstructed Year");
			return false;
		}*/
		
		if(!buildingConstructedYear.match(numbers)){
		    alert("Enter only Numbers for BuildingConstructed Year");
		    return false;
		}
		
		if(buildingConstructedYear>=currentYear){
			alert("BuildingConstructed Year cannot be Greater than Current Year");
			return false;
		}

		if (builtupArea == null || builtupArea == "") {
			alert("BuiltUp Area Cannot Be Blank");
			return false;
		}

		if(!builtupArea.match(numbers)){
		    alert("Enter only Numbers for BuiltUp Area");
		    return false;
		}
		
		if (totalTax == null || totalTax == "") {
			alert("TotalTax Cannot Be Blank");
			return false;
		}
	}

	$(document)
			.ready(
					function() {
						document.getElementById("txtTotalTax").onfocus = function() {
							var propertydetails = {};
							propertydetails.zoneType = $('#selZoneType').val();
							if (propertydetails.zoneType == 'Select Zone Type:') {
								alert(" Please Select Valid Zone Type");
								return false;
							}
							propertydetails.propertyDescription = $('#selPropertyDescription').val();
							if (propertydetails.propertyDescription == 'Select Property Description:') {
								alert(" Please Select Valid Property Description");
								return false;
							}
							propertydetails.propertyStatus = $('#txtStatus').val();
							if (propertydetails.propertyStatus == 'Select Status:') {
								alert(" Please Select Valid Property Status");
								return false;
							}
							propertydetails.buildingConstructedYear = $('#txtBuildingConstructedYear').val();
							propertydetails.builtupArea = $('#txtBuiltUpArea').val();
							if (propertydetails.builtupArea == '') {
								alert(" Please Enter BuiltUpArea");
								return false;
							}
							if (!$.isNumeric(propertydetails.builtupArea)) {
								alert(" Please Enter Number for BuiltUpArea");
								return false;
							}

							var propertydetailsJSON = JSON.stringify(propertydetails);
							$.ajax({
								        url : 'http://localhost:8080/PropertyTax/zoneValueAndCalculateTax',
										method : 'POST',
										data : propertydetailsJSON,
										contentType : "application/json; charset=utf-8",
										success : function(data) {
											$("#txtTotalTax").val(data);
										},
										error : function(error) {

										}
									})

						}
					});

	$(function() {
		//Reference the DropDownList.
		var txtYearOfAssessment = $("#txtYearOfAssessment");

		//Determine the Current Year.
		var currentYear = (new Date()).getFullYear();

		//Loop and add the Year values to DropDownList.
		for (var i = 1950; i <= currentYear; i++) {
			var option = $("<option />");
			option.html(i);
			option.val(i);
			txtYearOfAssessment.append(option);
		}
	});

	$(function() {
		var noofyears = 100; // Change to whatever you want
		var thisYear = (new Date()).getFullYear();
		for (var i = 0; i <= noofyears; i++) {
			var year = thisYear - i;
			$('<option>', {
				value : year,
				text : year
			}).appendTo("#txtBuildingConstructedYear");
		}
	});

	function cancelAction() {
		document.location.href("propertyregistrationpage.jsp");
	}
</script>
</head>
<body bgcolor="lightgray">
	</br>
	</br>
	</br>

	<table align="center" border="1" style="font-size:17px">

		<td><h1 align="center">Self Assessment of Property Tax Form</h1>

			<form:form name="propertyRegistrationForm" id="propertyRegistrationForm" action="propertyRegistration" modelAttribute="propertyTax" method="post">
				<table align="center" style="font-size:20px">
					<tr>
						<td rowspan="1">Year of Assessment :</td>
						<!--  <td><select id="txtYearOfAssessment" name="yearOfAssessment"></select></td>-->
						<td><input type="text" id="txtYearOfAssessment" name="yearOfAssessment" size="48" /></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td rowspan="2">Name of the Owner :</td>
						<td><input type="text" id="txtownerName" name="ownerName" size="48" /></td>

					</tr>
					<tr>
					</tr>
					<tr>
						<td rowspan="2">Email :</td>
						<td><input type="text" id="txtemail" name="email" size="48" /></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td rowspan="2">Address Of Property :</td>
						<td><textarea rows="4" cols="36" id="txtaddressOfProperty" name="addressOfProperty" form="usrform"></textarea></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td>Zone Classification :</td>
						<td><select name="zoneType" id="selZoneType">
								<option value="Select Zone Type:">Select Zone Type:</option>
								<c:forEach items="${zoneList}" var="zone">
									<option value="${zone}"><c:out value="${zone}" /></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>Description of the Property :</td>
						<td><select name="propertyDescription"
							id="selPropertyDescription">
								<option value="Select Property Description:">Select Property Description:</option>
								<c:forEach items="${propertyList}" var="property">
									<option value="${property}"><c:out value="${property}" /></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td>Status :</td>
						<td><select id="txtStatus" name="propertyStatus">
								<option value="Select Status:">Select Status:</option>
								<c:forEach items="${propertyStatusList}" var="propertyStatus">
									<option value="${propertyStatus}"><c:out
											value="${propertyStatus}" /></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td>Building Constructed Year :</td>
						<!--  <td><select id="txtBuildingConstructedYear"	name="buildingConstructedYear"></select></td>-->
						<td><input type="text" id="txtBuildingConstructedYear" name="buildingConstructedYear" size="48" /></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td>Built up area(Square feet) :</td>
						<td><input type="text" id="txtBuiltUpArea" name="builtupArea" size="48" /></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td>Total Tax Payable :</td>
						<td><input type="text" id="txtTotalTax" name="totalTax" size="48" readonly></td>
					<tr>
					</tr>
					<tr>
						<td><a href="" onclick="javascript:cancelAction();"><input type="button" value="Cancel" name="cancel" style="background-color:blue;color:white;"></a></td>
						<td><input type="button" name="submit" value="Pay Tax"  style="background-color:blue;color:white;" onsubmit="return validateform()"></td>
						</td>
					</tr>
				</table>
	</table>
	</form:form>
</body>
</html>