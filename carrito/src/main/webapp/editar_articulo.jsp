<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Editar Articulos</title>
</head>
<body>
<h1 align="center">Editar Articulo</h1><br>
	
<form align="center" action="ArticulosController" method="post" class="formulario">
	<input name="accion" value="updateArt" type="hidden">
	<input type="hidden" name="codigo_articulo" value="${articulo.codigo_articulo}"  >
	
	<label>Codigo articulo:</label>
	<input name="codigo" value="${articulo.codigo_articulo}" disabled required>
	<br>
	
	<label>Nombre:</label>
	<input name="nombre" value="${articulo.nombre}" required>
	<br>
	
	<label>Descripcion:</label>
	<input type="text" name="descripcion" value="${articulo.descripcion}" required>
	<br>
	
	<label>Precio:</label>
	<input type="number" name="precio" min="1" value="${articulo.precio}" required>
	<br>
	
	<label>Stock:</label>
	<input type="number" name="stock" min="1" value="${articulo.stock}" required>
	<br>
	
	<input type="submit" value="Guardar" name="boton" required>
	<br>	
</form>
</body>
</html>