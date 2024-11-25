package repos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

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
	
	
	private CarritosRepo() {
		this.listaCarr = new ArrayList<Carrito>();
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
	     
		if (articuloExistente != null) {
			//si el articulo existe en carrito le sumo la cantidad
	        articuloExistente.setCantidad(articuloExistente.getCantidad() + sArticulo.getCantidad());
	    } else {
	    	//si no existe lo agrego
	        carrito.getArticulos_carrito().add(new ArticuloCarrito(sArticulo.getCodArticulo(),sArticulo.getNombre(), sArticulo.getCantidad(), sArticulo.getIdCarrito()));
	    }
		
		//resto la cantidad al stock del articulo
		articuloStock.setStock(articuloStock.getStock() - sArticulo.getCantidad());
		
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
		long ultCarrito = listaCarr.stream().map(a->a.getId_carrito()).max(Long::compare).orElse(0L);
		
		if (ultCarrito != 0) {
	        Carrito carritoActivo = listaCarr.stream()
                 .filter(c -> c.getId_carrito() == ultCarrito && c.isActivo())
                 .findFirst()
                 .orElse(null);
	        
	        //al crear un carrito nuevo se da de baja el anterior 
	        if (carritoActivo != null) {
	            carritoActivo.setActivo(false);
	            
	            ArticulosRepo articulosRepo = ArticulosRepo.getInstance();
	            
	            for (ArticuloCarrito articuloCarrito : carritoActivo.getArticulos_carrito()) {
	                Articulo articulo = articulosRepo.getArticulos().stream()
                          .filter(a -> a.getCodigo_articulo() == articuloCarrito.getCodArticulo())
                          .findFirst()
                          .orElseThrow(() -> new IllegalArgumentException("Artículo no encontrado: " + articuloCarrito.getCodArticulo()));
	                // devuelve stock a los articulos
	                articulo.setStock(articulo.getStock() + articuloCarrito.getCantidad());
	            }
	        }
	    }
					
		Carrito nCarrito = new Carrito();
		
		nCarrito.setId_carrito(ultCarrito+1);
		nCarrito.setId_usuario(idUsuario);
		nCarrito.setActivo(true);
		nCarrito.setArticulos_carrito(new ArrayList<>());
		nCarrito.setPrecio_total(0.0);
		
		listaCarr.add(nCarrito);
		
		return nCarrito;
	}
	
	public List<Carrito> getCarritos(){
		return listaCarr.stream().toList();
	}
	
	public List<Carrito> getCarritosPorUsuario(long idUsuario){
		long ultCarrito = listaCarr.stream().map(a->a.getId_carrito()).max(Long::compare).orElse(0L);
		
		if(ultCarrito == 0L) {
			new IllegalArgumentException("No existen carritos");
		}
		return listaCarr.stream()
				.filter(c->c.getId_carrito()==ultCarrito)
				.toList();
	}
	
	public void finalizarCarrito(long idCarrito, long idUsuario, double saldo, Date fecha) {
		
		 Predicate<Carrito> carrExiste = a-> a.getId_carrito() == idCarrito;
		 Carrito carrito = listaCarr.stream()
	             .filter(carrExiste)
	             .findFirst()
	             .orElseThrow(() -> new IllegalArgumentException("No existe el carrito"));
		
		if(carrito.getPrecio_total()<=saldo) {	
			//desactivar carrito
				carrito.setActivo(false);
		
				UsuariosRepo usuariosRepo = UsuariosRepo.getInstance();
				List<Usuario> listaUsr = usuariosRepo.getUsuarios();
				 
				Predicate<Usuario> user = u -> u.getId_usuario() == idUsuario ;
				Usuario usuar = listaUsr.stream()
						.filter(user)
						.findFirst()
						.orElse(null);
				//descontar el precio al saldo del usuario
				usuar.setSaldo(usuar.getSaldo() - carrito.getPrecio_total());
				
				//guardar la venta
				Venta nVenta = new Venta();
				nVenta.setFecha_compra(fecha);
				nVenta.setId_carrito(idCarrito);
			
				VentasRepo.getInstance().agregarVenta(nVenta);
	
		}
		else {
			throw new IllegalArgumentException("Saldo insuficiente");
		}		
		
		
		
	}
}



