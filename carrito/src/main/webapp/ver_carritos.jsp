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
	<h1 align="center">Ver carritos</h1>
	
<c:choose>
	<c:when test="${empty listaCarr}">
       <p>No hay carritos registrados</p>
   </c:when>
   
    <c:otherwise>
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
	</c:otherwise>
</c:choose>

<a href="ArticulosController?accion=index">Inicio</a><br>
</body>
</html>