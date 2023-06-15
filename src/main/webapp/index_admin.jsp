<%@page import="model.User"%>
<%@ page import="model.Genre"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
	User admin_session = (User) request.getSession().getAttribute("admin_session");
%>
<% if(admin_session != null) { %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Library</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>

	<header>
		<h1>Book Management</h1>
		<form action="./Login" method="get">
			<input type="submit" name="action" value="Logout">
		</form>
	</header>

	<div id="main">

		<div class="text">
			<h2> Welcome, Administrator</h2>
		</div>

		<hr>
		<br> <br>

		<form action="books" method="post">
			<button type="submit" class="btn" name="action" value="addBook">ADD
				BOOK</button>

			<br>
			<br>

			<button type="submit" class="btn" name="action" value="bookDelete">DELETE
				BOOK</button>

			<br>
			<br>

			<button type="submit" class="btn" name="action" value="stockUpdate">UPDATE
				BOOK COPIES</button>
		</form>

		<br>
		<hr>
		
		<span style="color: red; font-size: 20px;">${requestScope.errorMsg}
			${requestScope.error}</span>
		<hr>

	</div>

</body>
</html>

<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp"); } %>


