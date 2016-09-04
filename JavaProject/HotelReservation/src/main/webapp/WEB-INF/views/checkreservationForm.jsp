
<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />

<html>
<head>
   <title>Check Reservations</title>
   <link rel="stylesheet" type="text/css" href="../resources/css/nav.css" />
   
</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
  <h1> <font  color="#8A2BE2">Reservation Details </font></h1>

   <form action="./viewRoomsList" method="GET" >
   <font face="verdana" color="green">
<h3>Reservation Id :  ${customer.customerId}</h3>
<h3>Customer Name :  ${customer.custFirstName} ${customer.custLastName} </h3>
<h3>Hotel Name :  ${hotel.hotelName}</h3>
<h3>City Name :  ${hotel.hotelCity} </h3>
<h3>Room Type :  ${room.roomType}</h3>
<h3>Total Cost :  ${customer.totalCost}</h3>
</font>
     


</form>
<br><br><h2>
<a href="${context}">Home</a>
</h2>

</body>
</html>