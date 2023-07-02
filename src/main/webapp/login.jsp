<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library | Login</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>

	<div id="login-id">
	<h1>Check all the books from your local library.</h1>
	<h2>Leave us your email so we can notify you when bestsellers are released.</h2>
	
	<br>
	
	<form action="./Login" method="post">
		<span style="font-size: 20px;">Email:</span> <br>
		<input type="text" style="width: 300px; height: 30px;" required name="email"><br>
		* We will never share your information.
		<br><br>
		<input class="btn" type="submit" name="action" value="Login">
	</form>
	<br><br>
	<span style="color: red; font-size: 20px;">${requestScope.msg}</span>
	</div>
	

</body>
</html>