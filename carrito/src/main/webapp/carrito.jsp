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
		</tr>
	</thead>
	<tbody>
		<c:forEach var="articulo" items="${listaArt}">
		<tr>
			<td><c:out value="${articulo.codigo_articulo}"/></td>
			<td><c:out value="${articulo.nombre}"/></td>
			<td><c:out value="${articulo.descripcion}"/></td>
			<td><c:out value="${articulo.precio}"/></td>
			
			<td>	<form action="CarritoController" method="post">
			
			<select name="cantidad" required>
                <option value="" disabled selected>Elegir cantidad</option>
                <c:forEach begin="1" end="${articulo.stock}" var="cantidad">
                    <option value="${cantidad}">${cantidad}</option>
                </c:forEach>
            </select>
            
		
			<input type="hidden" name="accion" value="agregarAlCarrito">
			<input type="hidden" name="cantidad" value="${cantidad}">
			<input type="hidden" name="cod_articulo" value="${articulo.codigo_articulo}">
			<input type="submit" value="Agregar al carrito">
			</form></td>
		</tr>		
		</c:forEach>	
	</tbody>	
	</table><br>
	<a href="index.jsp">Inicio</a><br>
	<a href="CarritoController?accion=verCarritos">Ver carrito</a><br>
</body>
</html>