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
<h1>Bienvenido <c:out value="${puesto} ${sessionScope.usuarioLogueado.nombre}" /></h1>

  <c:if test="${puesto == 'empleado'}">
        <a href="crear_articulo.html">Crear Articulo</a><br>
		<a href="ArticulosController?accion=verArticulos">Ver Articulos</a><br>
		<a href="borrar_articulo.jsp">Borrar Articulos</a><br>
		<br>
		<a href="agregar_stock.jsp">Agregar stock</a><br>
		<br>
		<a href="crear_usuario.html">Crear Usuario</a><br>
		<a href="UsuariosController?accion=verUsuarios">Ver Usuarios</a><br>
  </c:if>
  
   <c:if test="${puesto == 'cliente'}">
   		<h2>hola cliente</h2>     
   </c:if>
</body>
</html>