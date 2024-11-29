<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Agregar stock</title>
</head>
 <header>
	  <nav>
	    <ul class="menu">
	      <li><a href="index.jsp">Inicio</a></li>
	      <li><a href="crear_articulo.html">Crear Artículos</a></li>
	      <li><a href="crear_usuario.jsp">Crear Usuario</a></li>
	      <li class="dropdown">
	        <a href="#">Ver Registros</a>
	       		<ul class="dropdown-menu">
	       		  <li><a href="ArticulosController?accion=verArticulos">Artículos</a></li>
		          <li><a href="UsuariosController?accion=verUsuarios">Usuarios</a></li>
		          <li><a href="CarritoController?accion=verTodasVentas">Ventas</a></li>
		          <li><a href="CarritoController?accion=verTodosCarritos">Carritos</a></li>
		        </ul>
	      </li>
	      <li><a href="UsuariosController?accion=cerrarSession">Cerrar Sesión</a></li>
	    </ul>
	  </nav>
  </header>
<body>
<h1 align="center">Agregar stock</h1><br>
	
<form align="center" action="ArticulosController" method="post" class="formulario">
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