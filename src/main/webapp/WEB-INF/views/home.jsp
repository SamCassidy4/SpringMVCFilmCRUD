<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC FIlm Site</title>
</head>
<body>

<h1> SD Movie Database</h1>

<a href="CreateMovie.html">Create a movie</a><br>
Search by Movie ID:
<form action="SearchByID.do" method="GET">
<input type="text" name="id"/>
<a href="SearchForMovieByID.html">
<input type="submit" value= "Search"/>
</a>
</form>
<br>
<!-- <a href="SearchForMovieByID.html">
<input type="submit" value="Search"/>
</a> -->
<!-- THIS LINKS TO THE NEXT PAGE, BUT DOESN'T STORE THE INFORMATION. -->
<br>
</body>
</html>