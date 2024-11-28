package models;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private long id_carrito;
	private List<ArticuloCarrito> articulos_carrito;
	private long id_usuario;
	private Double precio_total;
	
	public Carrito() {
		 this.articulos_carrito = new ArrayList<>();
	}
	
	public Carrito(long id_carrito, List<ArticuloCarrito> articulos_carrito, long id_usuario, Double precio_total) {
		super();
		this.id_carrito = id_carrito;
		this.articulos_carrito = articulos_carrito;
		this.id_usuario = id_usuario;
		this.precio_total = precio_total;
	}
	@Override
	public String toString() {
		return "Carrito [id_carrito=" + id_carrito + ", \n articulos_carrito=" + articulos_carrito + ", id_usuario="
				+ id_usuario + ", precio_total=" + precio_total +"]\n";
	}

	public long getId_carrito() {
		return id_carrito;
	}

	public void setId_carrito(long id_carrito) {
		this.id_carrito = id_carrito;
	}

	public List<ArticuloCarrito> getArticulos_carrito() {
		return articulos_carrito;
	}

	public void setArticulos_carrito(List<ArticuloCarrito> articulos_carrito) {
		this.articulos_carrito = articulos_carrito;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Double getPrecio_total() {
		return precio_total;
	}

	public void setPrecio_total(Double precio_total) {
		this.precio_total = precio_total;
	}
}
