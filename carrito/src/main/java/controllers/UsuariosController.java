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
import models.Usuario;
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
		response.sendRedirect("index.html");
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
		
		default -> response.sendError(404, "No existe " + accion);
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
