<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>Ver Articulos</title>
</head>
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

<a href="ArticulosController?accion=index">Inicio</a><br>

</body>
</html>