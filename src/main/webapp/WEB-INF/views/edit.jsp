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
			value="${film.title }" maxlength="10" required><br> <label
			for="description">Description:</label> <input type="text"
			name="description" value="${film.description }" maxlength="255" required> <br>

		<label for="releaseYear">Release Year:</label> <input type="number"
			name="releaseYear" value="${film.releaseYear }" min="1970" max="2022" required> <br>

		<label for="languageId">Language:</label> 
		<select name="languageId" required>
				<option value="1">English</option>
				<option value="2">Italian</option>
				<option value="3">Japanese</option>
				<option value="4">Mandarin</option>
				<option value="5">French</option>
				<option value="6">German</option>
		</select>
		 <label
			for="rentalDuration">Rental Duration</label> <input type="text"
			name="rentalDuration" value="${film.rentalDuration}" min="1" max="7" required> 
			<label
			for="rentalRate">Rental Rate</label> <input type="number"
			name="rentalRate" value="${ film.rentalRate}" step=0.01 min="0.01" max="8"
				name="rentalRate" required> <label
			for="length">Length</label><input type="number" name="length"
			value="${film.length}" min="1" max="185" required> 
			<label for="replacementCost">Replacement
			Cost:</label> <input type="number" step= 0.01 name="replacementCost"
			value="${film.replacementCost}" min="0" max="30" required>
	<label
				for="rating">Rating:</label> <select name="rating" required>
				<option selected>${film.rating }</option>
				<option value="G">G</option>
				<option value="PG">PG</option>
				<option value="PG13">PG13</option>
				<option value="R">R</option>
				<option value="NC17">NC17</option>
			</select>
		
		 <label
			for="specialFeatures">Special Features</label> 
				<select name="specialFeatures" required>
				<option selected>${film.specialFeatures}</option>
				<option></option>
				<option value="Trailers">Trailers</option>
				<option value="Behind the Scenes">Behind the Scenes</option>
				<option value="Commentaries">Commentaries</option>

		</select>
			 <input
			type="submit" name="Submit">


	</form>
</body>
</html>