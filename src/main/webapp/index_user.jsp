<%@page import="model.User"%>
<%@ page import="model.Genre"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//User user = (User) request.getAttribute("user");
	User user_session = (User) request.getSession().getAttribute("user_session");
%>

<% if(user_session != null) { %>
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
			<h2><%= user_session.getEmail() %>, Welcome to Book Management App!
				Choose an option below to get started.
			</h2>
		</div>

		<hr>
		<br> 

		<!--------------- FIND BOOK ----------------->
		<h2 style="text-decoration: underline;">Find Book</h2>

		<form action="findBooks" method="get" id="find-book">


			<button type="submit" class="btn" name="action" value="listBooks">LIST
				OF ALL BOOKS</button> <br>

			<br> By author: <br> <input type="text" name="author">
			<button type="submit" class="btn" name="action" value="authorSearch">Search</button>

			<br>
			<br> By title: <br> <input type="text" name="title">
			<button type="submit" class="btn" name="action" value="titleSearch">Search</button>

			<br>
			<br> 
			By genre: <br> 
			<select name="genre">
				<option value="DRAMA" selected>Drama</option>
				<option value="HORROR">Horror</option>
				<option value="FANTASY">Fantasy</option>
				<option value="ROMANCE">Romance</option>
			</select> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" class="btn" name="action" value="genreSearch">Search</button>
			
		</form>

		<br>
		<hr>

		<span style="color: red; font-size: 20px;">${requestScope.errorMsg}
			${requestScope.error}</span>
		<hr>



	</div>



</body>
</html>
<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp");} %>
