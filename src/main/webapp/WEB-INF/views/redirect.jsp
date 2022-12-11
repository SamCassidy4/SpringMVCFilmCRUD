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
	${film.id}
	<br> Title
	<strong>${film.title}</strong>
	<br> Description: ${film.description }
	<br> Released: ${film.releaseYear }
	<br> Language: ${film.language }
	<br> Length: ${film.length }
	<br> Rating: ${film.rating }
	<br> Rental Duration: ${film.rentalDuration }
	<br> Rental Rate: ${film.rentalRate }
	<br> Replacement Cost: ${film.replacementCost }
	<br> Special features: ${film.specialFeatures }
	<br> Cast: ${film.actors}


	<form action="delete.do" method="post">
		<P>
			Would you Like to delete this film? <input type="radio"
				name="deleteFilm" value="yes"> <label>Yes</lable> <input
				type="radio" name="deleteFilm" value="no"> <lable>No</lable><br>
				<input type="text" hidden="true" name="id" value="${film.id}">
				<input type="submit" value="submit">
	</form>


	<form action="edit.do" method="post">
		<p>
			Would you like to edit this film? <label for="title">Title:</label> <input
				type="text" name="title" value="${film.title }" required><br> <label
				for="description">Description:</label> <input type="text"
				name="description" value="${film.description }" required><br> <label
				for="releaseYear">Release Year:</label> <input type="number"
				name="releaseYear" value="${film.releaseYear }" required><br> <label
				for="language">Language</label> <select name="language" required>
				<option selected>${film.language }</option>
				<option value="Enlish">English</option>
				<option value="Italian">Italian</option>
				<option value="Japanese">Japanese</option>
				<option value="Mandarin">Mandarin</option>
				<option value="French">French</option>
				<option value="German">German</option>
			</select> <br>
			<label for="length">Length:</label> <input type="number"
				name="length" value="${film.length }"><br> <label
				for="rating">Rating:</label> <select name="rating" required>
				<option selected>${film.rating }</option>
				<option value="G">G</option>
				<option value="PG">PG</option>
				<option value="PG13">PG13</option>
				<option value="R">R</option>
				<option value="NC17">NC17</option>
			</select>
			<label for="rentalDuration">Rental Duration:</label> <input
				type="number" name="rentalDuration" value="${film.rentalDuration }"required><br>

			<label for="rentalRate">Rental Rate:</label> <input type="number"
				name="rentalRate" value="${film.rentalRate }"required><br> <label
				for="replacementCost">Replacement Cost:</label> <input type="number"
				name="replacementCost" value="${film.replacementCost }"required><br>
			<label for="specialFeatures">Special Features:</label> <input
				type="text" name="specialFeatures" value="${film.specialFeatures }"required><br>
			<button type="submit" class="btn btn-primary">Edit</button>
	</form>
</body>
</html>