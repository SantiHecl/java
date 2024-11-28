<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Compras</title>
</head>
<body align="center">

<c:if test="${puesto == 'empleado'}">
	<h1 align="center">Todas las ventas</h1>
</c:if>

<c:if test="${puesto == 'cliente'}">
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
<a href="ArticulosController?accion=index">Inicio</a><br>
</body>
</html>