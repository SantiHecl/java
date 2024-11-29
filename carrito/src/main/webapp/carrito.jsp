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
	      <li><a href="CarritoController?accion=verArticulos">Artículos</a></li>
	      <li><a href="CarritoController?accion=verCompras">Ver Compras</a></li>
	      <li><a href="saldo.jsp">Saldo</a></li>
	      <li><a href="UsuariosController?accion=cerrarSession">Cerrar Sesión</a></li>
	    </ul>
	  </nav>
</header>

<body>
<div class="tablas-contenedor">

<c:choose>
	<c:when test="${empty listaArt}">
       <p>No hay articulos registrados</p>
   </c:when>
   
    <c:otherwise>
	<table border="1" align="center" class="tabla-articulos">
	<caption>Articulos</caption>
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
			<input type="hidden" name="cod_articulo" value="${articulo.codigo_articulo}"><br>
			<input type="submit" value="Agregar al carrito">
			</form></td>
		</tr>		
		</c:forEach>	
	</tbody>	
	</table><br>
	</c:otherwise>
</c:choose>


	<div class="tabla-carrito-contenedor">
	<table border="1" align="center" class="tabla-carrito">
	<caption>Mi carrito</caption>
	<thead> 
		<tr>
		<th>Articulos carrito</th> 
		<th></th>
		<th>Precio total</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="carrito" items="${listaCarr}">
		<tr>
			<td>
				<c:forEach var="articulo" items="${carrito.articulos_carrito}">
		        Código: <c:out value="${articulo.codArticulo}" />, 
		        Nombre: <c:out value="${articulo.nombre}" />,
		        Cantidad: <c:out value="${articulo.cantidad}" /><br />
		    	</c:forEach>
	    	</td>
	    	
			<td>
				<c:forEach var="articulo" items="${carrito.articulos_carrito}">
				<form method="post" action="CarritoController">
					<input type="hidden" name="accion" value="borrarArtList" >
					<input type="hidden" name="codigo" value="${articulo.codArticulo}">
					
					<input type="submit" value="Eliminar" class="eliminar">
				</form>	
				</c:forEach>		
			</td>
			
			<td><c:out value="${carrito.precio_total}"/></td>
		</tr>
		</c:forEach>	
	</tbody>	
	</table>
	
		<form method="post" action="CarritoController" class="form-finalizar"> 
		<input type="hidden" name="accion" value="finCarrito">
		
		<input type="submit" value="Finalizar compra">
	</form>
	</div>
	
</div>
</body>
</html>