<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Saldo</title>
</head>

<header>
	  <nav>
	    <ul class="menu">
	      <li><a href="index.jsp">Inicio</a></li>
	      <li><a href="CarritoController?accion=verArticulos">Artículos</a></li>
	      <li><a href="CarritoController?accion=verCompras">Ver Compras</a></li>
	      <li><a href="saldo.jsp">Saldo</a></li>
	      <li><a href="UsuariosController?accion=cerrarSession">Cerrar Sesión</a></li>
	    </ul>
	  </nav>
</header>

<body align="center">
<table border="1" align="center">
<tr>
	<td>
		<h3>Saldo disponible:</h3>
		<c:out value="${usuarioLogueado.saldo}"/>
	</td>

	<td>
	<h1>Cargar Saldo</h1>
		<form action="UsuariosController" method="post" class="formularioS">
		<input name="accion" value="cargarSaldo" type="hidden">
			
			<label>Cantidad a sumar:</label>
			<input name="saldo" required>
			<br>
			
			<input type="submit" value="Cargar" name="boton" required>
			<br>	
		</form><br>
	</td>

	<td>
	<h1>Transferir Saldo</h1>
		<form action="UsuariosController" method="post" class="formularioS">
		<input name="accion" value="transferirSaldo" type="hidden">
			
			<label>Cantidad a transferir:</label>
			<input name="saldo" required>
			<br>
			
			<label>Email del usuario a transferir:</label>
			<input type="email" name="email" required>
			<br>
			
			<input type="submit" value="Transferir" name="boton" required>
			<br>	
		
		</form>
	</td>
</tr>
</table>
</body>
</html>