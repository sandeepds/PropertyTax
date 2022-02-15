<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>ZONE WISE REPORT</title>
        </head>
        <body style="background-color:rgb(239, 188, 143)">
        </br>
        </br>
        <table border="2" align="center">
        <tr><td style="font-size:35px">ZONE WISE REPORT</td></tr>
        </table>
        </br>
        </br>
     
        <table border="2" align="center" style="font-size:25px">
    <tr>
        <th>Zone Name</th>
        <th>Property Type</th>
        <th>Amount Collected</th>
    </tr>
   
	
	
	 <c:forEach var="zone" items="${zoneMap}">
	   <tr>
        <td rowspan="2" align="center">${zone.key}</td>
	   <c:forEach var="status" items="${zone.value}">
    <!--  <tr>
        <td rowspan="2">${zone.key}</td>-->
        <td align="center">${status.key}</td>
        <td>${status.value}</td>
    </tr>
  
    </c:forEach>
    </c:forEach>

</table>
</br>
</br>
<table border="2" align="center" style="font-size:25px">
<tr>
<td>
<a href="index.jsp">Click Here For Main Menu</a>
</td>
</tr>  

        </table>
        </body>
        </html>
        