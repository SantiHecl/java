<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrito</title>
</head>
<body align="center">
<div>
<h1>Articulos</h1>

<c:choose>
	<c:when test="${empty listaArt}">
       <p>No hay articulos registrados</p>
   </c:when>
   
    <c:otherwise>
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
			
			<td><form action="CarritoController" method="post">
			
			<select name="cantidad" required>
                <option value="" disabled selected>Elegir cantidad</option>
                <c:forEach begin="1" end="${articulo.stock}" var="cantidad">
                    <option value="${cantidad}">${cantidad}</option>
                </c:forEach>
            </select>
		
			<input type="hidden" name="accion" value="agregarAlCarrito">
			<input type="hidden" name="nombre" value="${articulo.nombre}">
			<input type="hidden" name="cantidad" value="${cantidad}">
			<input type="hidden" name="cod_articulo" value="${articulo.codigo_articulo}">
			<input type="submit" value="Agregar al carrito">
			</form></td>
		</tr>		
		</c:forEach>	
	</tbody>	
	</table><br>
	</c:otherwise>
</c:choose>
</div>
	
	
	
<div>
<h1 align="center">Mi carrito</h1>
	
	<table border="1" align="center">
	<thead> 
		<tr>
		<th>Articulos carrito</th> 
		<th>Precio total</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="carrito" items="${listaCarr}">
		<tr>
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

<form method="post" action="CarritoController">
	<input type="hidden" name="accion" value="finCarrito">
	
	<input type="submit" value="Finalizar compra">
</form><br>
</div>
	<a href="index.jsp">Inicio</a><br>
</body>
</html>