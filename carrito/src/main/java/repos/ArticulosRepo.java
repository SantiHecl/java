package repos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import models.Articulo;

public class ArticulosRepo {
	
	private static ArticulosRepo singleton;
	
	public static ArticulosRepo getInstance() {
		if(singleton==null) {
			singleton= new ArticulosRepo();
		}
		return singleton;
	}
	
	private List<Articulo> listaArt;
	
	private ArticulosRepo() {
		this.listaArt = new ArrayList<Articulo>();
	}
	
	public void agregar(Articulo nArticulo) {
		
		Predicate<Articulo> artExiste = a-> a.getCodigo_articulo().equals(nArticulo.getCodigo_articulo());
		
		boolean existe = listaArt.stream().anyMatch(artExiste);
		
		if (existe) {
		   throw new IllegalArgumentException("El artículo ya existe en la lista.");
		}
		
		long ultId = listaArt.stream().map(a->a.getId_articulo()).max(Long::compare).orElse(0L);
		
		nArticulo.setId_articulo(ultId+1);
		
		listaArt.add(nArticulo);
	}
	
	public void borrar(Articulo bArticulo) {
		Predicate<Articulo> artExiste = a-> a.getCodigo_articulo() == bArticulo.getCodigo_articulo();
		
		boolean existe = listaArt.stream().anyMatch(artExiste);
		if (existe) {
			listaArt.removeIf(artExiste);
		}
		else {
		 throw new IllegalArgumentException("El codigo artículo no existe.");
		}
	}
	
	public long getSize() {
		return listaArt.size();
	}
	
	public List<Articulo> getArticulos(){
		return listaArt.stream().toList();
	}
}
