package repos;

import java.util.ArrayList;
import java.util.List;

import models.ArticuloCarrito;
import models.Usuario;

public class ArticuloCarritoRepo {
	private static ArticuloCarritoRepo singleton;
	
	public static ArticuloCarritoRepo getInstance() {
	if (singleton == null) {
		singleton = new ArticuloCarritoRepo();
	}
	return singleton;
	}
	
	private List<ArticuloCarrito> listaArtCarr;
	
	
	private ArticuloCarritoRepo() {
		this.listaArtCarr = new ArrayList<ArticuloCarrito>();
	}
	
	public List<ArticuloCarrito> getArtCarr(){
		return listaArtCarr.stream().toList();
	}
}
