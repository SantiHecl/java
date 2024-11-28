package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import models.Venta;
import repos.ArticulosRepo;
import repos.CarritosRepo;
import repos.VentasRepo;

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
		case "verTodosCarritos" -> getTodosCarritos(request,response);
		case "verCompras" -> getCompras(request,response);
		
		case "index" -> getIndex(request,response);
		
		default -> response.sendError(404, "No existe " + accion);
		}		
	}

//ver compras por usuario, ver todas las ventas
	private void getCompras(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		VentasRepo repoVentas = VentasRepo.getInstance();
		
		HttpSession session = request.getSession();
		Usuario idUser = (Usuario) session.getAttribute("usuarioLogueado");
		long idUsuario = idUser.getId_usuario();
		
		List<Venta> listVentas = repoVentas.getVentas(idUsuario);
		
		request.setAttribute("listaVentas", listVentas);
		request.getRequestDispatcher("ver_compras.jsp").forward(request, response);
		
	}


	private void getTodosCarritos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Carrito> listCarritos = repoCarrito.getCarritos();
		
		request.setAttribute("listaCarr", listCarritos);
		
		request.getRequestDispatcher("ver_carritos.jsp").forward(request, response);
	}


	private void getCarritos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		HttpSession session = request.getSession();
		Usuario idUser = (Usuario) session.getAttribute("usuarioLogueado");
		Long idCarrito =  (Long) session.getAttribute("idCarrito");
		long idUsuario = idUser.getId_usuario();
		
		List<Carrito> listCarritos = repoCarrito.getCarritosPorUsuario(idUsuario,idCarrito);
		
		request.setAttribute("listaCarr", listCarritos);
		
		request.getRequestDispatcher("ver_carritos.jsp").forward(request, response);
	}


	private void getIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("index.jsp");
	}


	private void getArticulos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
ArticulosRepo repo = ArticulosRepo.getInstance();
		
		List<Articulo> listArticulos = repo.getArticulos();
		request.setAttribute("listaArt", listArticulos);
		
		HttpSession session = request.getSession();
		Usuario idUser = (Usuario) session.getAttribute("usuarioLogueado");
		Long idCarrito =  (Long) session.getAttribute("idCarrito");
		long idUsuario = idUser.getId_usuario();
		
		List<Carrito> listCarritos = repoCarrito.getCarritosPorUsuario(idUsuario,idCarrito);
		request.setAttribute("listaCarr", listCarritos);		
		
		request.getRequestDispatcher("carrito.jsp").forward(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("");
		
		switch(accion) {
		case "agregarAlCarrito" -> postSumaArticulo(request,response);
		case "nuevoCarrito" -> postNuevoCarrito(request,response);
		case "finCarrito" -> postFinCarrito(request,response);
		
		default -> response.sendError(404, "No existe " + accion);
		}		
	}


	private void postFinCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		
		Usuario idUser = (Usuario) session.getAttribute("usuarioLogueado");
		long idUsuario = idUser.getId_usuario();
		long idCarrito = (long) session.getAttribute("idCarrito");
		Date fecha = new Date();
		double saldo = idUser.getSaldo();
		
		repoCarrito.finalizarCarrito(idCarrito, idUsuario, saldo, fecha);
		
		Carrito nuevoCarrito = repoCarrito.nuevoCarrito(idUsuario);
		session.setAttribute("idCarrito", nuevoCarrito.getId_carrito());
		
		response.sendRedirect("index.jsp");
	}


	public void postNuevoCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("idCarrito");
		
		Usuario idUser = (Usuario) session.getAttribute("usuarioLogueado");
		long idUsuario = idUser.getId_usuario();
		Carrito nCarrito = this.repoCarrito.nuevoCarrito(idUsuario);
		
		session.setAttribute("idCarrito", nCarrito.getId_carrito());
	}


	private void postSumaArticulo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer codigo_articulo = Integer.parseInt(request.getParameter("cod_articulo"));
		Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
		String nombre = request.getParameter("nombre");
		
		ArticuloCarrito agregarArticulo = new ArticuloCarrito();
		agregarArticulo.setCodArticulo(codigo_articulo);
		agregarArticulo.setCantidad(cantidad);
		agregarArticulo.setNombre(nombre);
		
		HttpSession session = request.getSession();
		long idCarrito = (long) session.getAttribute("idCarrito");
		
		agregarArticulo.setIdCarrito(idCarrito);
	
		repoCarrito.agregarArt(agregarArticulo);
		response.sendRedirect("CarritoController?accion=verArticulos");
		
	}

}
