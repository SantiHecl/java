package repos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
		Predicate<Usuario> userExiste = u-> u.getEmail().equals(nUsuario.getEmail());
		boolean existe = listaUsuario.stream().anyMatch(userExiste);
		
		if(existe) {
			
			return;
		}
		else {
		long ultId = listaUsuario.stream()
				.map(u->u.getId_usuario())
				.max(Long::compare)
				.orElse(0L);
		
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
	
	public Usuario login(Usuario loginUsuario) {
		 return listaUsuario.stream()
		            .filter(u -> u.getEmail().equals(loginUsuario.getEmail()) 
		                      && u.getPassword().equals(loginUsuario.getPassword()))
		            .findFirst()
		            .orElse(null);
	}
	
	public void agregarSaldo(long idUser, double saldo) {
		Predicate<Usuario> userExiste = u-> u.getId_usuario() == idUser;
		
		Usuario user = listaUsuario.stream()
				.filter(userExiste)
				.findFirst()
				.orElse(null);
		
		user.setSaldo(user.getSaldo() + saldo);
	}
	
	public void transferirSaldo(double saldo, String email, long idUsuario) {
		Predicate<Usuario> userExiste = u-> u.getId_usuario() == idUsuario;
		
		Usuario user = listaUsuario.stream()
				.filter(userExiste)
				.findFirst()
				.orElse(null);
	
		Predicate<Usuario> userEmail = u-> u.getEmail().equals(email);
		Usuario user2 = listaUsuario.stream()
				.filter(userEmail)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No existe el email"));
		
		if(user.getSaldo() >= saldo && saldo>0) {
				user.setSaldo(user.getSaldo() - saldo);
				user2.setSaldo(user2.getSaldo() + saldo);				
		}
		else {
			throw new IllegalArgumentException("Saldo insuficiente");
		}
	}
}
