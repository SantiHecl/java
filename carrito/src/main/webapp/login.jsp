<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<h1 align="center">Iniciar sesión</h1>
<form align="center" action="UsuariosController" method="post"> 
	<input name="accion" value="login" type="hidden">
	
	<label>Email:</label>
	<input type="email" name="email" required>
	<br>
	
	<label>Contraseña:</label>
	<input type="password" name="password" required>
	<br>

	<input type="submit" value="Iniciar">
</form>
</body>
</html> 