<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Information!</title>
</head>
<body>
	<p>
	Title: <strong> ${film.title } </strong><br>
	Description:  ${film.description } <br>
	Released: ${film.releaseYear } <br>
	Language:	${film.language } <br>
	Rated: ${film.rating }</p>
	Do you want to delete this film?
	<a href="delete.html">Delete</a>
	<br> Do you want to edit it?
	<a href="WEB-INF/edit.jsp">Edit</a>
</body>
</html>