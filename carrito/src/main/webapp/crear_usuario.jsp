<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Registrar usuario</title>
</head>
<c:if test="${sessionScope.usuarioLogueado.puesto == 'empleado'}">
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
 </c:if>
<body>
<form action="UsuariosController" method="post" class="formulario">
	<h1>Registrar usuario</h1>
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
	
	<c:if test="${sessionScope.usuarioLogueado.puesto == 'empleado'}">
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