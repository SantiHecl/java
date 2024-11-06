package controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
    private ArticulosRepo rArticulos ;
	
    public ArticulosController() {
       this.rArticulos = ArticulosRepo.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("");
		
		switch(accion) {
		case "verArticulos" -> getArticulos(request,response);
		case "index" -> getIndex(request,response);
		
		default -> response.sendError(404, "No existe " + accion);
		}		
	}
	
	private void getIndex(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.sendRedirect("index.html");
	}

	private void getArticulos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter escritor = response.getWriter();
		if(rArticulos.getArticulos().isEmpty()) {
			escritor.append("No hay articulos cargados");
		}
		else {
		for (Articulo articulo : rArticulos.getArticulos()) {
			escritor.append(articulo.toString() + '\n');
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("");
		
		switch(accion) {
		case "nuevoArt" -> postArticulos(request,response);
		case "borrarArt" -> postBorrarArticulos(request,response);
		
		default -> response.sendError(404, "No existe " + accion);
		}		
	}

	private void postBorrarArticulos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  Integer codArticulo = Integer.parseInt(request.getParameter("cod_articulo"));
		  
		    Articulo bArticulo = new Articulo();
		    bArticulo.setCodigo_articulo(codArticulo);

		    this.rArticulos.borrar(bArticulo);
		    
		    PrintWriter escritor = response.getWriter();
			escritor.append("Borrado correctamente el articulo con el codigo: " + codArticulo);
	}

	private void postArticulos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer codigo_articulo = Integer.parseInt(request.getParameter("codigo_articulo"));
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		Double precio = Double.parseDouble(request.getParameter("precio"));
		Integer stock = Integer.parseInt(request.getParameter("stock"));
		
		Articulo nuevoArticulo = new Articulo();
		nuevoArticulo.setCodigo_articulo(codigo_articulo);
		nuevoArticulo.setNombre(nombre);
		nuevoArticulo.setDescripcion(descripcion);
		nuevoArticulo.setPrecio(precio);
		nuevoArticulo.setStock(stock);
		
		this.rArticulos.agregar(nuevoArticulo);
		
		PrintWriter escritor = response.getWriter();
		escritor.append("Creado correctamente: " + nuevoArticulo);
	}
}
