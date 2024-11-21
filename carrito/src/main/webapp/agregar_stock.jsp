<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar stock</title>
</head>
<body>
<h1 align="center">Agregar stock</h1><br>
	
<form align="center" action="ArticulosController" method="post">
	<input name="accion" value="agregarStock" type="hidden">
	
	<label>Codigo articulo:</label>
	<input name="codigo" required>
	<br>
	
	<label>Cantidad a sumar:</label>
	<input name="sumaStock" required>
	<br>
	
	<input type="submit" value="Guardar" name="boton" required>
	<br>	
</form>
</body>
</html>