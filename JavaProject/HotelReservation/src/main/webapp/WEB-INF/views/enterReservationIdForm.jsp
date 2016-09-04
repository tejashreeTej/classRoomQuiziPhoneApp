<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Find Hotels for you</title>
<link rel="stylesheet" type="text/css" href="../resources/css/nav.css" />

</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
 <center>
<h1><font  color="#FF1493">Enter the Your Reservation Id</font></h1>
<form name="GetReservationIdForm" action="./checkreservationForm.html" method="GET">
<font  color="blue">
   Reservation Id:  <input type="TEXT" name="reservationId">
   <br>
   <br>
   <br>
    <input type="submit" value="Check">
      <h3>${message}</h3>
      </font>
</form>
</body>
</html>