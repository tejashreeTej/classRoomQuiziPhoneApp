<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />

<html>
<head>
   <title>Rooms In Hotel</title>
   <link rel="stylesheet" type="text/css" href="../resources/css/nav.css" />
   
</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
<center>
  <h1>Rooms in Hotel:</h1>
</center>
   <form:form action="./makeReservationForm" method="GET">
  <center>
  <table BORDER="8" bgcolor="#DDA0DD" cellspacing="10" cellpadding="15">
  <font  color="#FF1493">
<tr>
	<th>Hotel Id</th>
	<th>Room Id</th>
	<th>Room Type</th>
	<th>Room cost</th>
	<th>Available rooms</th>
	<th>Book Room</th>
</tr>
	<c:forEach var="curRoom" items="${roomsList}" varStatus="theCount">
		<tr>
		
		   <td><input  type="TEXT" name="hotelId" value=${curRoom.hotelId}></td>
	       <td><input  type="TEXT" name="roomId" value=${curRoom.roomId}></td>
		   <td>${curRoom.roomType}</td>
		   <td>${curRoom.roomCost}</td>
		   <td>${curRoom.availableRooms}</td>
		   <td><input type="submit" name="buttonId" value=Book${theCount.count} ></td>
		</tr>
	</c:forEach>
	</font>
</table>
</center>
</form:form>
<center>
<br><br><h2>
<a href="${context}">Home</a>
</h2>
</center>
</body>
</html>