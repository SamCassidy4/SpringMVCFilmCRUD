<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Details</title>
</head>
<body>

<form action= "EditState.do" method="POST">
<label for="title">Title: </label>
<input type="text" name="title" value= ${title }>
<br>
<label for="description">Title: </label>
<input type="text" name="description" value= ${description }>
<br>
<label for="releaseYear">Title: </label>
<input type="number" name="releaseYear" value= ${releaseYear }>
<br>
<label for="language">Title: </label>
<input type="text" name="language" value= ${language }>
<br>
<label for="rating">Title: </label>
<input type="text" name="rating" value= ${ rating }>
<br>

</form>

</body>
</html>