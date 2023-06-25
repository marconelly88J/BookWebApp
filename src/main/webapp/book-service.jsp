<%@page import="model.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	Admin admin_session = (Admin) request.getSession().getAttribute("admin_session");
	ArrayList<Book> list_all_books = (ArrayList<Book>) request.getAttribute("list_all_books");
 	if(admin_session != null) { 
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Service</title>
<link rel="stylesheet" href="css/book_delete.css">
</head>
<body>

	<h1>ADMIN Book Service Library Database </h1>	
	<input type="text" placeholder="search for book..." name="search">
	<button type="submit" name="action" value="searchBook">search</button> 
	
	<hr>
	
	<div id="main">
	
	<% if(list_all_books != null) { for(Book book : list_all_books) { %>
	<div id="book-container">
		
		<div class="book-cover">
			<img alt="Book cover" src="<%= book.getImgPath() %>">
		</div>
		<br>
		<div class="book-info">
		
			<div>Title: <b><%= book.getTitle() %></b></div>
			<div>Author: <%= book.getAuthor() %></div>
			<div>Genre: <%= book.getGenre() %></div>
			<div>Publish year: <%= book.getPublishYear() %></div>
			<div>In Stock: <%= book.getStock() %></div>
			<!-- provera kopija knjige -->
			<% String book_status = (book.getStock() == 0 ) ? "Out of stock" : "AVAILABLE"; %>
			<div>Status: <%= book_status %> </div>
			<div>Book ID: <%= book.getId() %></div><br>
			
			<input style="width:105px;" type="text" placeholder="number of copies"> 
			<button type="submit" name="action" value="updateCopies">Update book copies</button>
			
			<br> <br>
			
			<a href="BookService?action=deleteBook&id=<%= book.getId()%>">REMOVE BOOK</a>
			
			<hr>
		</div>
	
	</div>
	
	<% } } %>
	
	</div>
	
	<br>
	<span style="color: green; font-size: 20px;">${requestScope.delSuccess}</span>
	<span style="color: red; font-size: 20px;">${requestScope.delError}</span>
	<br>
	<hr>
	<a href="index_admin.jsp">Back to Home</a>

</body>
</html>
<% } else { request.getSession().invalidate(); response.sendRedirect("login.jsp"); } %>
