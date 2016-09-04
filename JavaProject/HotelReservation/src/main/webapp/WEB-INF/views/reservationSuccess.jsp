<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />

<html>
<head>
<title>Reservation done</title>
<link rel="stylesheet" type="text/css" href="../resources/css/nav.css" />

</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
<center>
<h2>Congratulations !</h2>
<h3>Reservation is Done check details</h3>
<h3>Your Reservation Id :  <font color="blue"/>${customer.customerId} </font></h3>
<h3>Customer Name :  <font color="blue"/>${customer.custFirstName} ${customer.custLastName}  </font></h3>
<h3>Total Cost :  <font color="blue"/>${customer.totalCost} </font></h3>
    </center>  
<br>
<h2>
<center>
<a href="${context}">Home</a>
</center>
</h2>
      
</body>
</html>
