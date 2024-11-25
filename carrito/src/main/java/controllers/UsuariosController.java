package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Articulo;
import models.Carrito;
import models.Usuario;
import repos.ArticulosRepo;
import repos.UsuariosRepo;

@WebServlet("/UsuariosController")
public class UsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuariosRepo rUsuarios;
	
    public UsuariosController() {
        this.rUsuarios = UsuariosRepo.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("");
		
		switch(accion) {
		case "verUsuarios" -> getUsuarios(request,response);
		case "index" -> getIndex(request,response);
		
		default -> response.sendError(404, "No existe " + accion);
		}			
	}
	
	private void getIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("index.jsp");
	}

	private void getUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter escritor = response.getWriter();
		if(rUsuarios.getUsuarios().isEmpty()) {
			escritor.append("No hay usuarios cargados");
		}
		else {
			for (Usuario usuario : rUsuarios.getUsuarios()) {
				escritor.append(usuario.toString() + '\n');
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		accion = Optional.ofNullable(accion).orElse("");
		
		switch(accion) {
		case "nuevoUser" -> postUsuario(request,response);
		case "login" -> postLogin(request,response);
		case "cargarSaldo" -> postCargarSaldo(request,response);
		case "transferirSaldo" -> postTransferirSaldo(request,response);
		
		default -> response.sendError(404, "No existe " + accion);
		}		
	}
			


	private void postTransferirSaldo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		double saldo = Double.parseDouble(request.getParameter("saldo"));
		String email = request.getParameter("email");
		
		HttpSession session = request.getSession();
		Usuario idUser = (Usuario) session.getAttribute("usuarioLogueado");
		long idUsuario = idUser.getId_usuario();
		
		rUsuarios.transferirSaldo(saldo,email,idUsuario);
		
		response.sendRedirect("saldo.jsp");
	}

	private void postCargarSaldo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		double saldo = Double.parseDouble(request.getParameter("saldo"));
		
		HttpSession session = request.getSession();
		Usuario idUser = (Usuario) session.getAttribute("usuarioLogueado");
		long idUsuario = idUser.getId_usuario();
		
		rUsuarios.agregarSaldo(idUsuario, saldo);
		response.sendRedirect("saldo.jsp");
	}

	private void postLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String email = request.getParameter("email");
		String contra = request.getParameter("password");
		
		Usuario logUsuario = new Usuario();
		logUsuario.setEmail(email);
		logUsuario.setPassword(contra);
		
		Usuario usuarioLogueado = this.rUsuarios.login(logUsuario);
		if(usuarioLogueado != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("usuarioLogueado", usuarioLogueado);
	        
	        response.sendRedirect("index.jsp");
	    } 
		else {
	        response.sendRedirect("login.jsp");
		}	
	}

	private void postUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String puesto = request.getParameter("puesto");
		
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre(nombre);
		nuevoUsuario.setApellido(apellido);
		nuevoUsuario.setEmail(email);
		nuevoUsuario.setPassword(password);
		nuevoUsuario.setPuesto(puesto);
		
		this.rUsuarios.agregarUsuario(nuevoUsuario);
		
		PrintWriter escritor = response.getWriter();
		escritor.append("Creado correctamente: " + nuevoUsuario);
	}
}
