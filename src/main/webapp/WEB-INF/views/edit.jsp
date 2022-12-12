<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
</head>
<body>
	<h3>Edit Film:</h3>


	<form action="editYes.do" method="POST">

		<input type="text" hidden="true" name="id" value="${film.id}">

		<label for="title">Title:</label> <input type="text" name="title"
			value="${film.title }" required><br> <label
			for="description">Description:</label> <input type="text"
			name="description" value="${film.description }" required> <br>

		<label for="releaseYear">Release Year:</label> <input type="number"
			name="releaseYear" value="${film.releaseYear }" required> <br>

		<label for="languageId">Language ID:</label> <input type="number"
			name="languageId" value="${film.languageId}" required> <label
			for="rentalDuration">Rental Duration</label> <input type="text"
			name="rentalDuration" value="${film.rentalDuration}"> <label
			for="rentalRate">Rental Rate</label> <input type="number"
			name="rentalRate" value="${ film.rentalRate}"> <label
			for="length">Length</label><input type="number" name="length"
			value="${film.length}"> <label for="replacementCost">Replacement
			Cost:</label> <input type="number" name="replacementCost"
			value="${film.replacementCost}"> <label for="rating">Rating:
		</label> <input type="text" name="rating" value="${film.rating}"> <label
			for="specialFeatures">Special Features</label> <input type="text"
			name="specialFeatures" value="${film.specialFeatures}"> <input
			type="submit" name="Submit">


	</form>
</body>
</html>