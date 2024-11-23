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
import javax.servlet.http.HttpSession;

import models.Articulo;
import models.ArticuloCarrito;
import models.Carrito;
import models.Usuario;
import repos.ArticulosRepo;
import repos.CarritosRepo;

@WebServlet("/CarritoController")
public class CarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private CarritosRepo repoCarrito;
	
    public CarritoController() {
        this.repoCarrito = CarritosRepo.getInstance();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("");
		
		switch(accion) {
		case "verArticulos" -> getArticulos(request,response);
		case "verCarritos" -> getCarritos(request,response);
		case "index" -> getIndex(request,response);
		
		default -> response.sendError(404, "No existe " + accion);
		}		
	}


	private void getCarritos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CarritosRepo repo = CarritosRepo.getInstance();
		
		List<Carrito> listCarritos = repo.getCarritos();
		
		request.setAttribute("listaCarr", listCarritos);
		
		PrintWriter escritor = response.getWriter();
		escritor.append("carrito: " + listCarritos);
	}


	private void getIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("index.jsp");
	}


	private void getArticulos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
ArticulosRepo repo = ArticulosRepo.getInstance();
		
		List<Articulo> listArticulos = repo.getArticulos();
		
		request.setAttribute("listaArt", listArticulos);
		
		request.getRequestDispatcher("carrito.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("");
		
		switch(accion) {
		case "agregarAlCarrito" -> postSumaArticulo(request,response);
		case "nuevoCarrito" -> postNuevoCarrito(request,response);
		
		default -> response.sendError(404, "No existe " + accion);
		}		
	}


	public void postNuevoCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		
		Usuario idUser = (Usuario) session.getAttribute("usuarioLogueado");
		long idUsuario = idUser.getId_usuario();
		Carrito nCarrito = this.repoCarrito.nuevoCarrito(idUsuario);
		
		session.setAttribute("idCarrito", nCarrito.getId_carrito());
		response.sendRedirect("index.jsp");
	}


	private void postSumaArticulo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer codigo_articulo = Integer.parseInt(request.getParameter("cod_articulo"));
		Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
		
		ArticuloCarrito agregarArticulo = new ArticuloCarrito();
		agregarArticulo.setCodArticulo(codigo_articulo);
		agregarArticulo.setCantidad(cantidad);
		
		HttpSession session = request.getSession();
		long idCarrito = (long) session.getAttribute("idCarrito");
		
		agregarArticulo.setIdCarrito(idCarrito);
	
		repoCarrito.agregarArt(agregarArticulo);
		response.sendRedirect("CarritoController?accion=verArticulos");
		
	}

}
