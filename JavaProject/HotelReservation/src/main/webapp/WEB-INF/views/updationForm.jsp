<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>New Student Information</title>
<link rel="stylesheet" type="text/css" href="../resources/css/nav.css" />

</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
<center>
	<h1><font  color="green">Please Enter Your Information</font></h1>
<font  color="#FF1493">
	<form name="updationForm" action="./successForm.html"
		method="GET">
		<table>
			<tr>
				<input type="hidden" name="hotelId" value=${hotel.hotelId}>
			</tr>
			<tr>
				<td><input type="hidden" name="roomId" value=${room.roomId}></td>
			</tr>
			<tr>
				<td><input type="hidden" name="customerId" value=${customer.customerId}></td>
			</tr>
			<tr>
				<td>Hotel Name</td>
				<td>${hotel.hotelName}</td>
			</tr>
			<tr>
				<td>City</td>
				<td>${hotel.hotelCity}</td>
			</tr>
			<tr>
				<td>Room Type:</td>
				<td>${room.roomType}</td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="TEXT" name="firstName" value=${customer.custFirstName} ></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="TEXT" name="lastName" value=${customer.custLastName} ></td>
			</tr>
			<tr>
				<td>Number of Nights :</td>
				<td><input type="TEXT" name="nightNo" value=${customer.noOfNights}></td>
			</tr>
			<tr><td></td>
				<td><input type="submit" value="Update"></td>
			</tr>
		</table>
		</font>
		</center>
	</form>

</body>
</html>
