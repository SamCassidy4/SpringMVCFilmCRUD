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
	${film}<br>
	Cast: ${film.actors}
	
	
	<form action = "delete.do" method="post">
		<P> Would you Like to delete this film?
		<input type ="radio" name="deleteFilm" value="yes"> <label>Yes</lable>
		<input type = "radio" name="deleteFilm" value="no"> <lable>No</lable><br>
		<input type ="text" hidden ="true" name="id" value="${film.id}">
		<input type ="submit" value="submit">
		</form>
		
		
		<form action="edit.do" method="post">
		<p> Would you like to edit this film?
		<input type="radio" name="editFilm" value="yes"><label>Yes</label>
		<input type="radio" name="editFilm" value="no"><label>No</label>
		<input type="text" hidden="true" name="id" value="${film.id}">
		<input type="submit" value="submit">
		
		
		</form>
</body>
</html>