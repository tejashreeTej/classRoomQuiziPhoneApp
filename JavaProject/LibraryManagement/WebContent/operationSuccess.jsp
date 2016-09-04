<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NPU Library</title>
<link type="text/html" rel="stylesheet" href="../Library.css">
</head>
<body>
	<header>
	<center>
		<h2>My Library</h2>
	</center>
	</header>
	<div id=main>
		</h1>
		Congratulations &nbsp;
		</h1>
		<h2>${sessionScope.operation}&nbsp;&nbsp;isSuccessfull.</h2>
		<br> <br>
		<c:choose>
			<c:when test="${sessionScope.operation == 'Registration'}">
				<c:out value="${param.studentId}" /> :  <c:out
					value="${param.studentName}" />
				</h2>
				<br>
				<br>
				<a href=/LibraryManagement/library.html>continue </a>
			</c:when>
			<c:otherwise>
				<br>
				<br>
				<a href=/LibraryManagement/booktransaction.html>continue </a>
			</c:otherwise>
		</c:choose>
	</div>
	<footer>
	<center>
		<h4>
			Privacy Policy · Terms of Use · Contact Us<br> Copyright © 2015
			PublicLibraries.com
		</h4>
	</center>
	</footer>

</body>
</html>