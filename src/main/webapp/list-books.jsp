<%@page import="model.User"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//User user = (User) request.getAttribute("user");
	ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");
	User user_session = (User) request.getSession().getAttribute("user_session");
%>

<% if(user_session != null) { %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Books</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>
	<header>
		<h1>Book List</h1>
	</header>
	
	<div id="main">
	
	<div id="book-container">	
		<div class="book-cover">
			<img alt="Book cover" src="img/1984.jpg">
		</div>
		
		<div class="book-info">
			<div>Title: 1984</div>
			<div>Author: G. Orwell</div>
			<div>Publish year: 1984</div>
		</div>
	</div>
	
	<hr>
	<a href="index_user.jsp" style="margin-left: 580px;">Back to Home</a>
	</div>
</body>
</html>
<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp");} %>
