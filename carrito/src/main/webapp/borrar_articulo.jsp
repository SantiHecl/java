<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Borrar Articulo</title>
</head>
<body>
<h1 align="center">Borrar articulo</h1>
<form align="center" action="ArticulosController" method="post">
	<input name="accion" value="borrarArt" type="hidden">
	
	<label>Codigo articulo:</label>
	<input type="text" name="cod_articulo" required>
	<br>
	
	<input type="submit" value="Borrar" name="boton" required>
	<br>
	
</form>
</body>
</html>