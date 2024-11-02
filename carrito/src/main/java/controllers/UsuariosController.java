package controllers;

import java.io.IOException;
import java.io.PrintWriter;

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
		PrintWriter escritor = response.getWriter();
		for (Usuario usuario : rUsuarios.getUsuarios()) {
			escritor.append(usuario.toString() + '\n');
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
