<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result by Keyword</title>
</head>
<body>


	<c:forEach items="${films}" var ="film">
	Film ID:${film.id } <br>
	Title: <strong> ${film.title } </strong><br>
	Description: ${film.description } <br>
	Release Year: ${film.releaseYear } <br>
	Language: ${film.language } <br>
	Rental Duration: ${film.rentalDuration } <br>
	Rental Rate: ${film.rentalRate } <br>
	Length: ${film.length } <br>
	Replacement Cost: ${film.replacementCost } <br>
	Rating: ${film.rating }<br>
	Cast: ${film.actors}<br>
	<br>
	</c:forEach>
	

</body>
</html>