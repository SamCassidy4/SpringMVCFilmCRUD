<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Information</title>
</head>
<body>

	<h1>SD Film Database</h1>
	<c:forEach var="films" items="${film }">
${film.title }
${film.description }
${film.releaseYear }
${film.language }
${film.rating }
	<form action="delete.do" method="post">
		<P>
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
</c:forEach>

	<br>
	<!-- <a href="searchForFilmByID.html">
<input type="submit" value="Search"/>
</a> -->
	<!-- THIS LINKS TO THE NEXT PAGE, BUT DOESN'T STORE THE INFORMATION. -->
</body>
</html>