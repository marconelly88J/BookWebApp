<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete book</title>
</head>
<body>

	<h1>Delete book using Book ID</h1>
	<hr>
	
	<form action="bookDelete" method="post">
		<b>Book id</b> <br>
		<input type="text" name="id">
		<button type="submit" name="action" value="bookDelete">DELETE BOOK</button>
	</form>
	
	<br>
	<span style="color: green; font-size: 20px;">${requestScope.delSuccess}</span>
	<span style="color: red; font-size: 20px;">${requestScope.delError}</span>
	<br>
	<hr>
	<a href="index.jsp">Back to Home</a>

</body>
</html>