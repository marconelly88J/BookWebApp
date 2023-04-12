<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Book</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>

	<header>
		<h1>Book Search</h1>
	</header>

	<table border="1" style="margin: auto; margin-top: 40px;">
	<caption><b>Found book:</b></caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Genre</th>
				<th>Publish Year</th>
				<th>Stock</th>
				<th>Status</th>
			</tr>
		</thead>
		
		<tbody>
			<%
			String errorMsg = (String)request.getAttribute("errorMsg");
			ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("bookSearch");	
			if (books != null) { 
				for (Book book : books) {
			%>
			<tr>
				<td><%= book.getId() %></td>
				<td><%= book.getTitle() %></td>
				<td><%= book.getAuthor() %></td>
				<td><%= book.getGenre() %></td>
				<td><%= book.getPublishYear() %></td>
				<td><%= book.getStock() %></td>
				<td><%= book.getStock() == 0 ? "<span style='color: red'>Unavailable</span>":"<span style='color: green'>Available</span>" %> </td>
				
			</tr>
			<% } } %>
			
		</tbody>
		
	</table>
		
	${requestScope.errorMsg}
	<br><hr>
	<a href="index.jsp" style="margin-left: 580px;">Back to Home</a>
	

</body>
</html>