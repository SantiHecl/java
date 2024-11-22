<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrito</title>
</head>
<body>
<h1 align="center">Articulos</h1>
	
	<table border="1" align="center">
	<thead> 
		<tr>
		<th>Codigo Articulo</th> 
		<th>Nombre</th>
		<th>Descripcion</th>
		<th>Precio</th>
		<th>Cantidad</th>
		<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="articulo" items="${listaArt}">
		<tr>
			<td><c:out value="${articulo.codigo_articulo}"/></td>
			<td><c:out value="${articulo.nombre}"/></td>
			<td><c:out value="${articulo.descripcion}"/></td>
			<td><c:out value="${articulo.precio}"/></td>
			
			<td><select name="cantidad_${articulo.codigo_articulo}">
                <option value="" disabled selected>Elegir cantidad</option>
                <c:forEach begin="1" end="${articulo.stock}" var="cantidad">
                    <option value="${cantidad}">${cantidad}</option>
                </c:forEach>
            </select></td>
            
			<td><form action="CarritosController" method="post">
			<input type="hidden" name="accion" value="agregarAlCarrito">
			<input type="hidden" name="cod_articulo" value="${articulo.codigo_articulo}">
			<input type="submit" value="Agregar al carrito">
			</form></td>
		</tr>		
		</c:forEach>	
	</tbody>	
	</table>
</body>
</html>