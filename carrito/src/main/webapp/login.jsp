<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Login</title>
</head>
<body>
	<form action="UsuariosController" method="post" class="formulario"> 
		<h1>Iniciar sesión</h1>
		<input name="accion" value="login" type="hidden">
		
		<label>Email:</label><br>
		<input type="email" name="email" required>
		<br>
		
		<label>Contraseña:</label><br>
		<input type="password" name="password" required>
		<br>
		<br>
		<input type="submit" value="Iniciar">
	</form>
<a href="crear_usuario.jsp" class="btn">Registrarse</a>
</body>
</html> 