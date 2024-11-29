<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Ver Articulos</title>
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
<h1>Todos los articulos</h1>

<c:choose>
	<c:when test="${empty listaArt}">
        <p>No hay articulos registrados</p>
    </c:when>
    
    <c:otherwise>
	<div>
	<table border="1" align="center">
	<thead> 
		<tr>
		<th>Id</th>
		<th>Codigo Articulo</th> 
		<th>Nombre</th>
		<th>Descripcion</th>
		<th>Precio</th>
		<th>Stock</th>
		<th></th>
		<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="articulo" items="${listaArt}">
		<tr>
			<td><c:out value="${articulo.id_articulo}"/></td>
			<td><c:out value="${articulo.codigo_articulo}"/></td>
			<td><c:out value="${articulo.nombre}"/></td>
			<td><c:out value="${articulo.descripcion}"/></td>
			<td><c:out value="${articulo.precio}"/></td>
			<td><c:out value="${articulo.stock}"/></td>
			<td><a href="ArticulosController?accion=edit&codArticulo=${articulo.codigo_articulo}">Editar</a></td>
			
			<td><form action="ArticulosController" method="post">
			<input type="hidden" name="accion" value="borrarArt">
			<input type="hidden" name="codigo_articulo" value="${articulo.codigo_articulo}">
			<input type="submit" value="Borrar">
			</form>
			</td>
		</tr>		
		</c:forEach>	
	</tbody>	
	</table>
	</div>
	</c:otherwise>
</c:choose>
</body>
</html>