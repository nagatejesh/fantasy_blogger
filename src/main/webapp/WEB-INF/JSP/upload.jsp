<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data">
	<input type="file" accept="image/jpg, image/jpeg, image/png" name="file" id="file">
	<input type="text" name="title" id="title">
	<input type="submit" value="Submit">
</form>
</body>
</html>