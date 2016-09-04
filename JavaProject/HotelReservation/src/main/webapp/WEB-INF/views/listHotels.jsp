
<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />

<html>
<head>
   <title>Hotels</title>
   <link rel="stylesheet" type="text/css" href="../resources/css/nav.css" />
   
</head>
<body>
<%@ include file="./homeNavbar.jsp"%>
<center>
  <h1>Hotels in ${cityName}:</h1>
</center>
   <form action="./viewRoomsList" method="GET" >
<center>
  <table BORDER="8" bgcolor="#DDA0DD" cellspacing="10" cellpadding="15">
   <font  color="#FF1493">
<tr>
    <th>Hotel Id</th>
	<th>Hotel Name</th>
	<th>Book Hotel</th>
</tr>
	<c:forEach var="curHotel" items="${hotelList}" varStatus="theCount">
		<tr>
		   <td><input type="TEXT" name="hotelId" value=${curHotel.hotelId}></td>
		   <td>${curHotel.hotelName}</td>
		   <td><input type="submit"  name="buttonId" value=Book${theCount.count}></td>
		</tr>
	</c:forEach>
	</font>
</table>

</form>
<br><br><h2>
<a href="${context}">Home</a>
</h2>
</center>
</body>
</html>