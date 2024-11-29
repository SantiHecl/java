<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Carrito</title>
</head>
<body>
  <c:if test="${puesto == 'empleado'}">
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
  <h1>Bienvenido <c:out value="${puesto} ${sessionScope.usuarioLogueado.nombre}" /></h1>
  <div class="contenedor">
	  <div class="fila">
	    <a href="crear_articulo.html" class="btn">Crear Artículo</a>
	    <a href="ArticulosController?accion=verArticulos" class="btn">Ver Artículos</a>
	    <a href="agregar_stock.jsp" class="btn">Agregar stock</a>
	  </div>
  	  <div class="fila">
	    <a href="crear_usuario.jsp" class="btn">Crear Usuario</a>
	    <a href="UsuariosController?accion=verUsuarios" class="btn">Ver Usuarios</a>
	  </div>
  	  <div class="fila">
	    <a href="CarritoController?accion=verTodasVentas" class="btn">Ver todas las ventas</a>
	    <a href="CarritoController?accion=verTodosCarritos" class="btn">Ver todos los carritos</a>
	  </div>
  </div>
  </c:if>
  
  <c:if test="${puesto == 'cliente'}">
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
  
   <h1>Bienvenido <c:out value="${puesto} ${sessionScope.usuarioLogueado.nombre}" /></h1>   
   		<br>
   		<a href="CarritoController?accion=verArticulos" class="btn">Articulos</a><br> 
   		<br>
   		<a href="saldo.jsp" class="btn">Cargar o transferir saldo</a><br>
   		<br>
   		<a href="CarritoController?accion=verCompras" class="btn">Ver compras</a><br>
   		<br>
   		<a href="UsuariosController?accion=cerrarSession" class="btn">Cerrar sesion</a><br>
   </c:if>
</body>
</html>