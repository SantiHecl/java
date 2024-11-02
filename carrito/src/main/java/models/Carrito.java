package models;

import java.util.List;

public class Carrito {
	private long id_carrito;
	private List<Articulo> articulos_carrito;
	private long id_usuario;
	private Integer precio_total;
	
	
	
	public Carrito(long id_carrito, List<Articulo> articulos_carrito, long id_usuario, Integer precio_total) {
		super();
		this.id_carrito = id_carrito;
		this.articulos_carrito = articulos_carrito;
		this.id_usuario = id_usuario;
		this.precio_total = precio_total;
	}
	@Override
	public String toString() {
		return "Carrito [id_carrito=" + id_carrito + ", articulos_carrito=" + articulos_carrito + ", id_usuario="
				+ id_usuario + ", precio_total=" + precio_total + "]";
	}
	public long getId_carrito() {
		return id_carrito;
	}
	public void setId_carrito(long id_carrito) {
		this.id_carrito = id_carrito;
	}
	public List<Articulo> getArticulos_carrito() {
		return articulos_carrito;
	}
	public void setArticulos_carrito(List<Articulo> articulos_carrito) {
		this.articulos_carrito = articulos_carrito;
	}
	public long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public Integer getPrecio_total() {
		return precio_total;
	}
	public void setPrecio_total(Integer precio_total) {
		this.precio_total = precio_total;
	}
	
	
	
}
