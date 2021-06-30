<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Base64" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="file.txt"%>
<title>Fantasy Blogger</title>
<style type="text/css">
	body{
		padding-top:7%;
		background: gray;
	}
	.container-fluid{
		padding: 0 1rem;
	}
	.material-icons{
		vertical-align: middle;
	}
	img:hover{
		opacity: 80%;
	}
	.card-img-overlay{
		top: auto;
		left: auto;
		padding:0 1rem;
	}
</style>
</head>
<body>

		<nav class="navbar navbar-inverse bg-dark fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/">fantasyBlogger</a>
				</div>
					<a href="#" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
						<span class="material-icons">menu</span>
					</a>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li class="active"><a href="/">Home</a></li>
						<li><a href="#">About Me</a></li>
						<li><a href="#">Contact</a></li>
					</ul>
					<!--       <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul> -->
				</div>
			</div>
		</nav>
	<div class="container-fluid" id="body-start">
		<div class="card-columns col-sm-12">
			<c:forEach items="${images}" var="img">
				<div class="card">
					<img class="card-img" alt="${img.fileName}" src="data:${img.fileType};base64,${Base64.getEncoder().encodeToString(img.compressedData)}">
					<div class="card-img-overlay">
					<div>
						<div class="row">
							<p class="col"><a href="${img.fileDownloadUri}">${img.fileSize}<span class="material-icons">download</span></a></p>
						</div>
					</div>
					</div>
				</div>
				<div>
					<div id="model${img.id}">
						
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>