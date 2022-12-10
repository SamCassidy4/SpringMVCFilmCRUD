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
	ID: ${film.id }
	Title: <strong> ${film.title } </strong><br>
	Description:  ${film.description } <br>
	Released: ${film.releaseYear } <br>
	Language:	${film.language } <br>
	Rated: ${film.rating }</p>
	Do you want to delete this film?
	<form action="delete.do" method="POST" >
	<input type="hidden" name="id" value="${film.id}">
	 <button>Delete</button>
	
	</form>
	
	<br> Do you want to edit it?
	<a href="edit.html">Edit</a>
</body>
</html>