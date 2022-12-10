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
${films.title }
${films.description }
${films.releaseYear }
${films.language }
${films.rating }
</c:forEach>

	<br>
	<!-- <a href="searchForFilmByID.html">
<input type="submit" value="Search"/>
</a> -->
	<!-- THIS LINKS TO THE NEXT PAGE, BUT DOESN'T STORE THE INFORMATION. -->
</body>
</html>