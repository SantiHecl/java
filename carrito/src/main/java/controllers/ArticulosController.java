package controllers;

import java.io.IOException;
import java.io.PrintWriter;

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
		PrintWriter escritor = response.getWriter();
		for (Articulo articulo : rArticulos.getArticulos()) {
			escritor.append(articulo.toString() + '\n');
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
