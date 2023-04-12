<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book</title>
</head>
<body>

	<h1>Add new book</h1>
	<hr>

	<form action="addNewBook" method="post">
		
		Book id num <br>
		<input type="text" name="id" > <br><br>
		
		Title <br>
		<input type="text" name="title" > <br><br>
		
		Author <br>
		<input type="text" name="author" > <br><br>
		
		Genre <br>
		<select name="genre">
			<option value="DRAMA" selected>Drama</option>
  			<option value="HORROR" >Horror</option>
  			<option value="FANTASY">Fantasy</option>
  			<option value="ROMANCE">Romance</option>
		</select> <br><br>
		
		Publish year <br>
		<input type="text" name="publishYear" > <br><br>
		
		Copies <br>
		<input type="text" name="stock"> <br><br>
		
		<button type="submit" name="action" value="addNewBook">ADD BOOK</button> 
	</form>
	<br><br>
	<span style="color: green; font-size: 20px;">${requestScope.successMsg}</span>
	<span style="color: red; font-size: 20px;">${requestScope.errorMsg}</span>
	<hr>
	<a href="index.jsp">Back to Home</a>
	
	
</body>
</html>