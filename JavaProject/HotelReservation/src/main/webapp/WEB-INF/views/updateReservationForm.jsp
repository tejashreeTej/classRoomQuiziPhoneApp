<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Update Reservation</title>
<link rel="stylesheet" type="text/css" href="../resources/css/nav.css" />
</head>
<body> 
<%@ include file="./homeNavbar.jsp"%>
<center>
<h1><font  color="#FF1493">Update your Reservation </font></h1>
<form name="GetCourseForm" action="./updationForm.html" method="GET">
<font  color="blue">
    Enter reservation Id: <input type="TEXT" name="custId">
     <br>
     <br>
     <br>
    <input type="submit" value="Search">
    <h3>${message}</h3>
    </font>
</form>
</body>
</html>