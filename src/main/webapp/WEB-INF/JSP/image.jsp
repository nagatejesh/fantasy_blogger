<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<img alt="${image.fileName}" src="data:${image.fileType};base64,${Base64.getEncoder().encodeToString(image.compressedData)}" width="200px" height="200px">
	FileSize : ${image.fileSize}<br/>
	<a href="${image.fileDownloadUri}">Download me</a>	
</body>
</html>