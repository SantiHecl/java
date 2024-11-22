package repos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.el.stream.Optional;

import models.Articulo;
import models.Carrito;

public class CarritosRepo {
	private static CarritosRepo singleton;
	
	public static CarritosRepo getInstance() {
		if (singleton == null) {
			singleton = new CarritosRepo();
		}
		return singleton;
	}
	
	private List<Carrito> listaCarr;

	private CarritosRepo() {
		this.listaCarr = new ArrayList<Carrito>();
	}
	
	
	public void agregarArt(long id_carrito, Articulo nArticulo) {
		/*	Predicate<Carrito> carrExiste = a-> a.getId_carrito() == id_carrito;
		
		 Carrito carrito = listaCarr.stream()
                 .filter(carrExiste)
                 .findFirst()
                 .orElseThrow(() -> new IllegalArgumentException("No existe el carrito"));
		
		if (carrito != null) {
		Predicate<Articulo> art = a-> a.getCodigo_articulo().equals(nArticulo);
	
		
		  Articulo artic = listaArt.stream()
                  .filter(art) 
                  .findFirst() 
                  .orElse(null);
		  
		carrito.getArticulos_carrito().add(nArticulo);
		} 
		else {
		
		throw new IllegalArgumentException("No existe el carrito con ID: " + id_carrito);
		} */
	} 
	
	public Carrito nuevoCarrito(long idUsuario) {
		long ultCarrito = listaCarr.stream().map(a->a.getId_carrito()).max(Long::compare).orElse(0L);
		Carrito nCarrito = new Carrito();
		
		nCarrito.setId_carrito(ultCarrito+1);
		nCarrito.setId_usuario(idUsuario);
		
		listaCarr.add(nCarrito);
		return nCarrito;
	}
	
	public List<Carrito> getCarritos(){
		return listaCarr.stream().toList();
	}
}



