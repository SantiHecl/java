<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar usuario</title>
</head>

<body align="center">
<h1 align="center">Registrar usuario</h1>
<form action="UsuariosController" method="post">
	<input name="accion" value="nuevoUser" type="hidden">


	<label>Nombre:</label>
	<input type="text" name="nombre" required>
	<br>
	
	<label>Apellido:</label>
	<input type="text" name="apellido" required>
	<br>
	
	<label>Email:</label>
	<input type="email" name="email" required>
	<br>
	
	<label>Contraseña:</label>
	<input type="password" name="password" required>
	<br>
	
	<c:if test="${puesto == 'empleado'}">
	<label>Puesto:</label>
	<select name="puesto" required>
	<option disabled selected>Elegir rol</option>
	<option value="empleado">Empleado</option>
	<option value="cliente">Cliente</option>
	</select>
	<br>
	</c:if>
	

	<input type="hidden" name="puesto" value="cliente" required>
	<br>
	
	
	<input type="submit" value="Guardar" name="boton">
</form>

</body>
</html>