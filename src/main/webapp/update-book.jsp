<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Book</title>
</head>
<body>

	<h1>Update book copies</h1>
	<hr>

	<form action="stockUpdate" method="post">
		
		<strong>Book id</strong> <br>
		<input type="text" name="id" value="${param.id}"> <br><br>
		
		<strong>Num of copies</strong> <br>
		<input type="text" name="stock" value="${param.stock}"> <br><br>
		
		<button type="submit" name="action" value="stockUpdate">Update copies</button> 
		
	</form>
	<br><br>
	
	<span style="color: green; font-size: 20px;">${requestScope.successMsg}</span>
	<span style="color: red; font-size: 20px;">${requestScope.errorMsg}</span>
	<hr>
	<a href="index_admin.jsp">Back to Home</a>
	

</body>
</html>