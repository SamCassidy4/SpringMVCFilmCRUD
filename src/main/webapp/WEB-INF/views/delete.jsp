<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete</title>
</head>
<body>
<h3>Delete</h3>
<form action="delete.do" method="POST">

<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="deleteFilm">
  <label class="form-check-label" for="inlineRadio1">Yes</label>
  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="NO">
  <label class="form-check-label" for="inlineRadio2">No</label>
</div>
	<button type="submit" class="btn btn-primary">Submit</button>

</form>
</body>
</html>