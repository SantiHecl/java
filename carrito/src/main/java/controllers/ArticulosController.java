package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Articulo;
import repos.ArticulosRepo;

@WebServlet("/ArticulosController")
public class ArticulosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArticulosRepo repoArticulos ;
	
    public ArticulosController() {
       this.repoArticulos = ArticulosRepo.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("");
		
		switch(accion) {
		case "verArticulos" -> getArticulos(request,response);
		case "index" -> getIndex(request,response);
		case "edit" -> getEdit(request,response);
		
		
		default -> response.sendError(404, "No existe " + accion);
		}		
	}

	private void getEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sCodArticulo = request.getParameter("codArticulo");
		int cod = Integer.parseInt(sCodArticulo);
		
		ArticulosRepo repo = ArticulosRepo.getInstance();
		
		Articulo artic = repo.getByCodigo(cod);
		
		request.setAttribute("articulo", artic);
		
		request.getRequestDispatcher("editar_articulo.jsp").forward(request, response);		
	}

	private void getIndex(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.sendRedirect("index.jsp");
	}

	private void getArticulos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ArticulosRepo repo = ArticulosRepo.getInstance();
		
		List<Articulo> listArticulos = repo.getArticulos();
		
		request.setAttribute("listaArt", listArticulos);
		
		request.getRequestDispatcher("ver_articulos.jsp").forward(request, response);
	}
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("");
		
		switch(accion) {
		case "nuevoArt" -> postArticulos(request,response);
		case "borrarArt" -> postBorrarArticulos(request,response);
		case "updateArt" -> postUpdateArticulos(request,response);
		case "agregarStock" -> postAgregarStock(request,response);
		
		default -> response.sendError(404, "No existe " + accion);
		}		
	}

	
	private void postAgregarStock(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer codigo_articulo = Integer.parseInt(request.getParameter("codigo"));
		Integer suma_stock = Integer.parseInt(request.getParameter("sumaStock"));
		
		if(suma_stock<=0) {
			response.sendError(400,"Numero inválido: "+ suma_stock);
			return;
		}
		
		Articulo updateArticulo = repoArticulos.getByCodigo(codigo_articulo);
		
		int stock = updateArticulo.getStock();
		updateArticulo.setStock(suma_stock+stock);
		
		repoArticulos.update(updateArticulo);
		response.sendRedirect("ArticulosController?accion=verArticulos");
	}

	private void postUpdateArticulos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer codigo_articulo = Integer.parseInt(request.getParameter("codigo_articulo"));
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		Double precio = Double.parseDouble(request.getParameter("precio"));
		Integer stock = Integer.parseInt(request.getParameter("stock"));
		
		if(codigo_articulo<=0) {
			response.sendError(400,"El codigo articulo es  inválido");
			return;
		}
		if(precio<=0 ) {
			response.sendError(400,"Precio inválido");
			return;
		}
		if(stock<=0) {
			response.sendError(400,"Numero stock inválido");
			return;
		}
		
		Articulo updateArticulo = repoArticulos.getByCodigo(codigo_articulo);
		
		updateArticulo.setCodigo_articulo(codigo_articulo);
		updateArticulo.setNombre(nombre);
		updateArticulo.setDescripcion(descripcion);
		updateArticulo.setPrecio(precio);
		updateArticulo.setStock(stock);
		
		repoArticulos.update(updateArticulo);
		
		response.sendRedirect("ArticulosController?accion=verArticulos");
		
	}

	private void postBorrarArticulos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  Integer codArticulo = Integer.parseInt(request.getParameter("codigo_articulo"));
		  
		    Articulo bArticulo = new Articulo();
		    bArticulo.setCodigo_articulo(codArticulo);

		    this.repoArticulos.borrar(bArticulo);
		    
		    response.sendRedirect("ArticulosController?accion=verArticulos");
	}

	private void postArticulos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Integer codigo_articulo = Integer.parseInt(request.getParameter("codigo_articulo"));
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		Double precio = Double.parseDouble(request.getParameter("precio"));
		Integer stock = Integer.parseInt(request.getParameter("stock"));
		
		if(codigo_articulo<=0) {
			response.sendError(400,"El codigo articulo es  inválido");
			return;
		}
		if(precio<=0 ) {
			response.sendError(400,"Precio inválido");
			return;
		}
		if(stock<=0) {
			response.sendError(400,"Numero stock inválido");
			return;
		}
		
		Articulo nuevoArticulo = new Articulo();
		nuevoArticulo.setCodigo_articulo(codigo_articulo);
		nuevoArticulo.setNombre(nombre);
		nuevoArticulo.setDescripcion(descripcion);
		nuevoArticulo.setPrecio(precio);
		nuevoArticulo.setStock(stock);
		
		this.repoArticulos.agregar(nuevoArticulo);
		
		PrintWriter escritor = response.getWriter();
		escritor.append("Creado correctamente: " + nuevoArticulo);
	}
}
