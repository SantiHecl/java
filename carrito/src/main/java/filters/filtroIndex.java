package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.CarritoController;
import models.Articulo;
import models.Usuario;
import repos.ArticulosRepo;
import repos.UsuariosRepo;

@WebFilter(urlPatterns = {"/*"})
public class filtroIndex extends HttpFilter implements Filter {

    public filtroIndex() {
        super();
    }

	public void destroy() {
		
	}
	
	private static UsuariosRepo rUsuarios;
	private static ArticulosRepo repoArticulos ;
		static {		
		rUsuarios = UsuariosRepo.getInstance();
        repoArticulos = ArticulosRepo.getInstance();
        
	//usuarios y articulos creado para probar 
			Usuario usuarioPrueba = new Usuario();
			usuarioPrueba.setNombre("Santiago");
			usuarioPrueba.setApellido("Hecl");
			usuarioPrueba.setEmail("santiagoemanuelhecl@gmail.com");
			usuarioPrueba.setPassword("123");
			usuarioPrueba.setPuesto("empleado");
			rUsuarios.agregarUsuario(usuarioPrueba);
			
			Usuario usuarioPrueba2 = new Usuario();
			usuarioPrueba2.setNombre("Santi");
			usuarioPrueba2.setApellido("Hecl");
			usuarioPrueba2.setEmail("santi@gmail.com");
			usuarioPrueba2.setPassword("123");
			usuarioPrueba2.setPuesto("cliente");
			usuarioPrueba2.setSaldo(10000.0);
			rUsuarios.agregarUsuario(usuarioPrueba2);
			
			Articulo nuevoArticulo1 = new Articulo();
			nuevoArticulo1.setCodigo_articulo(111);
			nuevoArticulo1.setNombre("fideos");
			nuevoArticulo1.setDescripcion("fideos tallarines");
			nuevoArticulo1.setPrecio(1000.0);
			nuevoArticulo1.setStock(50);
			repoArticulos.agregar(nuevoArticulo1);
			
			Articulo nuevoArticulo2 = new Articulo();
			nuevoArticulo2.setCodigo_articulo(222);
			nuevoArticulo2.setNombre("pan");
			nuevoArticulo2.setDescripcion("pan de panaderia");
			nuevoArticulo2.setPrecio(500.0);
			nuevoArticulo2.setStock(15);
			repoArticulos.agregar(nuevoArticulo2);
			
			Articulo nuevoArticulo3 = new Articulo();
			nuevoArticulo3.setCodigo_articulo(333);
			nuevoArticulo3.setNombre("carne");
			nuevoArticulo3.setDescripcion("carne para asado");
			nuevoArticulo3.setPrecio(1500.0);
			nuevoArticulo3.setStock(10);
			repoArticulos.agregar(nuevoArticulo3);
	}
	
	@SuppressWarnings("null")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession session = null;
		HttpServletResponse httpResp = null;
		
		if(request instanceof HttpServletRequest) {
			HttpServletRequest httpReq;
			httpReq = (HttpServletRequest) request;
			session = httpReq.getSession();	
			
			String uri = httpReq.getRequestURI();
	        if (uri.endsWith("login.jsp") || uri.endsWith("UsuariosController") || uri.endsWith("crear_usuario.jsp") || uri.endsWith("style.css")) {
	            chain.doFilter(request, response); // permitir el acceso sin filtrado
	            return;
	        }
			
			if (session != null) {
	            Object usuarioLogueado = session.getAttribute("usuarioLogueado");
	            
	            if (usuarioLogueado != null && usuarioLogueado instanceof Usuario) {
	                Usuario usuario = (Usuario) usuarioLogueado;
	                String puesto = usuario.getPuesto();
	               
	                request.setAttribute("puesto", puesto);
	                chain.doFilter(request, response);
	                return;
	            }
	            else {
	            	httpResp = (HttpServletResponse) response;
	            	httpResp.sendError(401);
	            	return;
	            }
			}
		}
        chain.doFilter(request, response); 
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	
	}
}
