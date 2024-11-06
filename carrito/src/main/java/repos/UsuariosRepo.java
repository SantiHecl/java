package repos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.ServletResponse;

import models.Usuario;

public class UsuariosRepo {
	private static UsuariosRepo singleton;
	
	public static UsuariosRepo getInstance() {
		if(singleton==null) {
			singleton= new UsuariosRepo();
		}
		return singleton;
	}
	
	private List<Usuario> listaUsuario;
	
	private UsuariosRepo() {
		this.listaUsuario = new ArrayList<Usuario>();
	}
	
	public void agregarUsuario(Usuario nUsuario) {
		Predicate<Usuario> usrExiste = u-> u.getEmail().equals(nUsuario.getEmail());
		boolean existe = listaUsuario.stream().anyMatch(usrExiste);
		
		if(existe) {
			
			return;
		}
		else {
		long ultId = listaUsuario.stream().map(u->u.getId_usuario()).max(Long::compare).orElse(0L);
		
		nUsuario.setId_usuario(ultId+1);
		nUsuario.setSaldo(0D);
		listaUsuario.add(nUsuario);
		}
	}
	
	public long getSize() {
		return listaUsuario.size();
	}
	
	public List<Usuario> getUsuarios(){
		return listaUsuario.stream().toList();
	}
}
