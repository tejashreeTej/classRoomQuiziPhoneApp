<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../Library.css">
<%@ page import="java.util.*" import="edu.npu.library.domain.*"%>

</head>
<body>
	<header>
	<center>
		<h2>NPU Library</h2>
	</center>
	</header>
	<div id="main">
		<center>
			<br>
			<h2>Books in NPU Library</h2>
			<br>
			<c:choose>
				<c:when test="${empty requestScope.bookList}">
					No Books in Library at this time
				</c:when>
				<c:otherwise>
					<table border='solid'>
						<tr>
							<th>Book ID</th>
							<th>Book Name</th>
						</tr>
						<c:forEach items="${requestScope.bookList}" var="book">
							<tr>
								<td>${book.bookId}</td>
								<td>${book.bookName}</td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>
	</div>
	<a href=/LibraryManagement/booktransaction.html>continue </a>
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