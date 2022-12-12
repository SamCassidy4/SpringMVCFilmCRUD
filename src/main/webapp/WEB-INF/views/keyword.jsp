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
			<P>
		<form action="delete.do" method="post">

			<p>Would you Like to delete this film?</p>
			 <input type="radio"name="deleteFilm" value="yes"> <label>Yes</label> 
				<input type="text" hidden="true" name="id" value="${film.id}">
				<input type="submit" value="Submit">
	</form>


	<form action="edit.do" method="post">
		<p>Would you like to edit this film? </p>
		
		<input type="radio" name="editFilm" value="YES"> <label>Yes</label>
			<input type="text" hidden="true" name="id" value="${film.id}">
			<input type="submit" value="Submit">

	</form>
	
	
	<br>
	</c:forEach>
	

</body>
</html>