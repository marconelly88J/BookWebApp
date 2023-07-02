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
<link rel="stylesheet" href="css/book_service.css">
</head>
<body>

	<h1>ADMIN Book Service Library Database </h1>	
	<input type="text" placeholder="search for book..." name="search">
	<button type="submit" name="action" value="searchBook">search</button> 
	
	<hr>
	
	<div id="main">
	
	<% 	
		if (list_all_books != null) {
    	for (Book book : list_all_books) {
	%>
    <div id="book-container">
        <div class="book-cover">
            <img alt="Book cover" src="<%= book.getImgPath() %>">
        </div>
        <br>
        <div class="book-info">
            <div>Title: <b><%= book.getTitle() %></b></div>
            <div><b>Author:</b> <%= book.getAuthor() %></div>
            <div><b>Genre:</b> <%= book.getGenre() %></div>
            <div><b>Publish year:</b> <%= book.getPublishYear() %></div>
            <div><b>In Stock:</b> <%= book.getStock() %></div>
            <% 
				String book_status = (book.getStock() == 0) ? "Out of stock" : "AVAILABLE";
   				String status_color = (book.getStock() == 0) ? "red" : "green"; 
   			%>
			<div style="color: <%= status_color %>"><%= book_status %></div>
            <div><b>Book ID:</b> <%= book.getId() %></div><br>
            
            <form action="BookService" method="post">
                <input style="width:105px;" type="text" name="newNumberOfCopies" placeholder="number of copies">
                <input type="hidden" name="action" value="updateCopies">
                <input type="hidden" name="id" value="<%= book.getId() %>">
                <button type="submit">Update book copies</button>
            </form>
            
            <br><br>
            
            <a href="BookService?action=deleteBook&id=<%= book.getId()%>">REMOVE BOOK</a>
            
            <hr>
        </div>
    </div>
		<% }} %>
	
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
