<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	
	ArrayList<Book> list_all_books = (ArrayList<Book>) request.getAttribute("list_all_books");
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
	<a href="index_user.jsp" style="margin-left: 580px;">Back to Home</a>
	<hr>
	<div id="main">
	
	<% if(list_all_books != null) { for(Book book : list_all_books) { %>
	<div id="book-container">
		
		<div class="book-cover">
			<img alt="Book cover" src="<%= book.getImgPath() %>">
		</div>
		
		<div class="book-info">
		
			<div>Title: <b><%= book.getTitle() %></b></div>
			<div><b>Author:</b> <%= book.getAuthor() %></div>
			<div><b>Genre:</b> <%= book.getGenre() %></div>
			<div><b>Publish year:</b> <%= book.getPublishYear() %></div>
			<div><b>In Stock:</b> <%= book.getStock() %></div>
			<!-- provera kopija knjige -->
			<% 
				String book_status = (book.getStock() == 0) ? "Out of stock" : "AVAILABLE";
   				String status_color = (book.getStock() == 0) ? "red" : "green"; 
   			%>
			<div style="color: <%= status_color %>"><%= book_status %></div>

			
		</div>
		
	</div>
	<hr>
	<% } } %>
	
	<hr>
	<a href="index_user.jsp" style="margin-left: 580px;">Back to Home</a>
	
	</div>
	
</body>
</html>
<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp");} %>
