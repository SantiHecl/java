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

import models.Usuario;

@WebFilter(urlPatterns = {"/*"})
public class filtroIndex extends HttpFilter implements Filter {
       
   
    public filtroIndex() {
        super();
       
    }

	
	public void destroy() {
		
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
	        if (uri.endsWith("login.jsp") || uri.endsWith("UsuariosController")) {
	            chain.doFilter(request, response); // Permitir el acceso sin filtrado
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
       // httpResp.sendRedirect("login.jsp");
        chain.doFilter(request, response); 
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	
	}
}
