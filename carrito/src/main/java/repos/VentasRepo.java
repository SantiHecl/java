package repos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import models.Carrito;
import models.Venta;

public class VentasRepo {

	private static VentasRepo singleton;
	
	public static VentasRepo getInstance() {
		if(singleton==null) {
			singleton = new VentasRepo();
		}
		return singleton;
	}
	
	private List<Venta> listaVenta;
	
	private VentasRepo() {
		this.listaVenta = new ArrayList<Venta>();
	}
	
	public void agregarVenta(Venta nVenta) {
		
		long ultId = listaVenta.stream().map(u->u.getId_venta()).max(Long::compare).orElse(0L);
	
		nVenta.setId_venta(ultId+1);
		
		listaVenta.add(nVenta);
	}
	
	public List<Venta> getVentas(long idUsuario) {
		Predicate<Carrito> carrito = c->c.getId_usuario() == idUsuario;
		
		List<Long> idsCarritos = CarritosRepo.getInstance().getCarritos().stream()
	            .filter(carrito)
	            .map(Carrito::getId_carrito)
	            .toList();
		
		return listaVenta.stream()
				.filter(v -> idsCarritos.contains(v.getId_carrito()))
				.toList();
	}
	
	public List<Venta> getVentasTotales(){
		return listaVenta.stream().toList();
	}
	
}
