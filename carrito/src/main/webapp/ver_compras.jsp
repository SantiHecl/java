<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Compras</title>
</head>

<body align="center">

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
	<h1 align="center">Todas las ventas</h1>
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
  
	<h1 align="center">Mis compras</h1>
</c:if>
	
<c:choose>
	<c:when test="${empty listaVentas}">
        <p>No hay compras registradas</p>
    </c:when>
    
    <c:otherwise>
		<table border="1" align="center">
		<thead> 
			<tr>
			<th>Código Venta</th> 
			<th>Código Carrito</th>
			<th>Fecha</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="ventas" items="${listaVentas}">
			<tr>
				<td><c:out value="${ventas.id_venta}" /></td>
		       	<td><c:out value="${ventas.id_carrito}" /></td>
		      	<td><c:out value="${ventas.fecha_venta}" /></td>			
			</tr>		
			</c:forEach>	
		</tbody>	
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>