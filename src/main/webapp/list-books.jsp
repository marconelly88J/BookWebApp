<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<table border="1" style="margin: auto; margin-top: 40px;">
	<caption><b>List of all books:</b></caption>
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
			ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");
			if (books != null) { for (Book book : books) {
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
	<br><br>
	<%
	String error = (String) request.getAttribute("error");
	if (error != null && !error.equals("")) {
	%>
	<div class="container">
		<p><%= error %></p>
	</div>
	<% } %>
	<hr>
	<a href="index.jsp" style="margin-left: 580px;">Back to Home</a>
	

</body>
</html>
