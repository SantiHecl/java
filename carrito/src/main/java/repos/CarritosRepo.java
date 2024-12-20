package repos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.el.stream.Optional;

import models.Articulo;
import models.ArticuloCarrito;
import models.Carrito;
import models.Usuario;
import models.Venta;
import repos.ArticulosRepo;

public class CarritosRepo {
	private static CarritosRepo singleton;
	
		public static CarritosRepo getInstance() {
		if (singleton == null) {
			singleton = new CarritosRepo();
		}
		return singleton;
	}
			
	private List<Carrito> listaCarr;
	
	ArticulosRepo articulosRepo = ArticulosRepo.getInstance();
    List<Articulo> listaArt = articulosRepo.getArticulos();
	
	private CarritosRepo() {
		this.listaCarr = new ArrayList<Carrito>();
	}
	
	
	public boolean eliminarArticulo(long idCarrito, int codArticulo) {
		Predicate<Carrito> carr = c -> c.getId_carrito() == idCarrito;
	    Carrito carrito = listaCarr.stream()
	        .filter(carr)
	        .findFirst()
	        .orElse(null);

	    if (carrito != null) {
	    	Predicate<Articulo> art = c -> c.getCodigo_articulo()==codArticulo;
	    	Articulo articulo = listaArt.stream()
	    			.filter(art)
	    			.findFirst()
	    			.orElse(null);
	    	
	    	Predicate<ArticuloCarrito> artCarr = (a -> a.getCodArticulo() == codArticulo);
	    	ArticuloCarrito articuloEliminado = carrito.getArticulos_carrito().stream()
	                .filter(artCarr)
	                .findFirst()
	                .orElse(null);

	            if (articuloEliminado != null) {
	            	Double precio = articulo.getPrecio() * articuloEliminado.getCantidad();
	            	
	                //restar el precio del artículo
	                carrito.setPrecio_total(carrito.getPrecio_total() - precio);

	                //eliminar el artículo del carrito
	                return carrito.getArticulos_carrito().remove(articuloEliminado);
	            }
	        }
	    return false;
	}
	
	
	public void agregarArt(ArticuloCarrito sArticulo) {
		
		//busca el carrito
		 Predicate<Carrito> carrExiste = a-> a.getId_carrito() == sArticulo.getIdCarrito();
		 Carrito carrito = listaCarr.stream()
	             .filter(carrExiste)
	             .findFirst()
	             .orElseThrow(() -> new IllegalArgumentException("No existe el carrito"));
		
		 //busca el articulo si ya esta agregado
		 Predicate<ArticuloCarrito> artExiste = a -> a.getCodArticulo() == sArticulo.getCodArticulo();
		 ArticuloCarrito articuloExistente = carrito.getArticulos_carrito().stream()
		            .filter(artExiste)
		            .findFirst()
		            .orElse(null);
		 
		//verifica el stock del articulo
		 Predicate<Articulo> stockArt = a-> a.getCodigo_articulo() == sArticulo.getCodArticulo();
		 ArticulosRepo articulosRepo = ArticulosRepo.getInstance();
		 List<Articulo> listaArt = articulosRepo.getArticulos();
	     
		 Articulo articuloStock = listaArt.stream()
	    		.filter(stockArt)
	    		.findFirst()
	            .orElseThrow(() -> new IllegalArgumentException("No existe el articulo"));
		 
	 	int cantidadSolicitada = (articuloExistente != null)
	            ? articuloExistente.getCantidad() + sArticulo.getCantidad()
	            : sArticulo.getCantidad();

	    if (cantidadSolicitada > articuloStock.getStock()) {
	        throw new IllegalArgumentException("Stock insuficiente para el artículo: " + sArticulo.getCodArticulo());
	    }
		    
	     
		if (articuloExistente != null && articuloExistente.getCantidad() <= (articuloExistente.getCantidad() + sArticulo.getCantidad())) {
			//si el articulo existe en carrito y hay stock disponible sumo la cantidad
	        articuloExistente.setCantidad(articuloExistente.getCantidad() + sArticulo.getCantidad());
	    } 
		else{
	    	//si no existe lo agrego
	        carrito.getArticulos_carrito().add(new ArticuloCarrito(sArticulo.getCodArticulo(),sArticulo.getNombre(), sArticulo.getCantidad(), sArticulo.getIdCarrito()));
	    }
		
		
		
		//calcular total 
		double total = 0.0;
	    for (ArticuloCarrito articulo : carrito.getArticulos_carrito()) {
	    	
	    	Articulo articuloRepo = listaArt.stream()
	                .filter(a -> a.getCodigo_articulo() == articulo.getCodArticulo())
	                .findFirst()
	                .orElseThrow(() -> new IllegalArgumentException("No existe el artículo"));
	        
	        // multiplicar la cantidad por el precio y sumar al total
	        total += articulo.getCantidad() * articuloRepo.getPrecio(); 
	    }
		carrito.setPrecio_total(total);
	} 
	
	
	public Carrito nuevoCarrito(long idUsuario) {
		long ultCarrito = listaCarr.stream()
				.map(a->a.getId_carrito())
				.max(Long::compare)
				.orElse(0L);
					
		Carrito nCarrito = new Carrito();
		
		nCarrito.setId_carrito(ultCarrito+1);
		nCarrito.setId_usuario(idUsuario);
		nCarrito.setArticulos_carrito(new ArrayList<>());
		nCarrito.setPrecio_total(0.0);
		
		listaCarr.add(nCarrito);
		
		return nCarrito;
	}
	
	public List<Carrito> getCarritos(){
		return listaCarr.stream().toList();
	}
	
	public List<Carrito> getCarritosPorUsuario(long idUsuario, long idCarrito){
		List<Carrito> lista = listaCarr.stream()
	            .filter(c -> c.getId_usuario() == idUsuario)
	            .toList();
	           
	            
	            return lista.stream()
	            		.filter(a->a.getId_carrito()==idCarrito)
	            		.toList();
	}
	
	public void finalizarCarrito(long idCarrito, long idUsuario, double saldo, Date fecha) {
		
		 Predicate<Carrito> carrExiste = a-> a.getId_carrito() == idCarrito;
		 Carrito carrito = listaCarr.stream()
	             .filter(carrExiste)
	             .findFirst()
	             .orElseThrow(() -> new IllegalArgumentException("No existe el carrito"));
		
		 //verifica si el carrito esta vacio
		 if(carrito.getArticulos_carrito().isEmpty()) {
			 throw new IllegalArgumentException("Carrito vacio");
		 }
		 
		 //verifica el saldo del cliente
		if(carrito.getPrecio_total()>saldo) {		
			 throw new IllegalArgumentException("Saldo insuficiente");
	    }
		
		UsuariosRepo usuariosRepo = UsuariosRepo.getInstance();
		List<Usuario> listaUsr = usuariosRepo.getUsuarios();
		 
		Predicate<Usuario> user = u -> u.getId_usuario() == idUsuario ;
		Usuario usuar = listaUsr.stream()
				.filter(user)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No existe el usuario"));
		
		
		 ArticulosRepo articulosRepo = ArticulosRepo.getInstance();
		    List<Articulo> listaArt = articulosRepo.getArticulos();

	    // validar stock para todos los artículos en el carrito
	    for (ArticuloCarrito articuloCarrito : carrito.getArticulos_carrito()) {
	        Articulo articuloStock = listaArt.stream()
	                .filter(a -> a.getCodigo_articulo() == articuloCarrito.getCodArticulo())
	                .findFirst()
	                .orElseThrow(() -> new IllegalArgumentException(
	                        "El artículo no existe " + articuloCarrito.getCodArticulo()));

	        // verifica si hay stock disponible
	        if (articuloCarrito.getCantidad() > articuloStock.getStock()) {
	            throw new IllegalArgumentException(
	                    "Stock insuficiente para el artículo: " + articuloCarrito.getCodArticulo());
	        }
	    }
	      //descontar el precio al saldo del usuario
		usuar.setSaldo(usuar.getSaldo() - carrito.getPrecio_total());   
		 
		//resta la cantidad al stock
		for (ArticuloCarrito articuloCarrito : carrito.getArticulos_carrito()) {
	        Articulo articuloStock = listaArt.stream()
	                .filter(a -> a.getCodigo_articulo() == articuloCarrito.getCodArticulo())
	                .findFirst()
	                .orElseThrow(() -> new IllegalArgumentException(
	                        "El artículo no existe " + articuloCarrito.getCodArticulo()));

	        articuloStock.setStock(articuloStock.getStock() - articuloCarrito.getCantidad());
	    }
		     
		//guardar la venta
		Venta nVenta = new Venta();
		nVenta.setFecha_venta(fecha);
		nVenta.setId_carrito(idCarrito);
	
		VentasRepo.getInstance().agregarVenta(nVenta);
	}
	
	public Carrito verificarCarrito(long idUsuario) {
		Predicate<Carrito> perteneceAlUsuario = c -> c.getId_usuario() == idUsuario;
	    List<Carrito> carritosUsuario = listaCarr.stream()
	            .filter(perteneceAlUsuario)
	            .toList();

	    // verificar cuál no está asociado a una venta
	    VentasRepo ventaRepo = VentasRepo.getInstance();
	    for (Carrito carrito : carritosUsuario) {
	        boolean estaEnVenta = ventaRepo.getVentasTotales().stream()
	                .anyMatch(v -> v.getId_carrito() == carrito.getId_carrito());

	        if (!estaEnVenta) {
	            return carrito; // reutilizar este carrito si no está asociado a una venta
	        }
	    }

	    return null;
	}
}



