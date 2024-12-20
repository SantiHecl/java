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
<body align="center">
	<h1 align="center">Ver carritos</h1>
	
<c:choose>
	<c:when test="${empty listaCarr}">
       <p>No hay carritos registrados</p>
   </c:when>
   
    <c:otherwise>
	<table border="1" align="center">
	<thead> 
		<tr>
		<th>Id carrito</th>
		<th>Articulos carrito</th> 
		<th>Precio total</th>
		

		</tr>
	</thead>
	<tbody>
		<c:forEach var="carrito" items="${listaCarr}">
		<tr>
			<td><c:out value="${carrito.id_carrito}"/></td>
			<td><c:forEach var="articulo" items="${carrito.articulos_carrito}">
	        Código: <c:out value="${articulo.codArticulo}" />, 
	        Nombre: <c:out value="${articulo.nombre}" />,
	        Cantidad: <c:out value="${articulo.cantidad}" /><br />
	    	</c:forEach>
	    	</td>
			<td><c:out value="${carrito.precio_total}"/></td>
			
		</tr>		
		</c:forEach>	
	</tbody>	
	</table>
	</c:otherwise>
</c:choose>
</body>
</html>