<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unos glumca</title>
</head>
<body>
	
	<form action="/PozoristeWeb/SaveGlumacS" method="post">
	
		Unesite ime glumca: <input type="text" name="ime"> <br><br>
		
		Unesite prezime glumca: <input type="text" name="prez"> <br><br>
		
		Unesite JMBG glumca: <input type="text" name="jmbg"> <br><br>
		
		<input type="submit" value="Sacuvaj"> <br><br>
	
	</form>
</body>
</html>