package models;

import java.sql.Date;

public class Venta {
	private long id_venta;
	private long id_carrito;
	private Date fecha_venta;
	
	
	public Venta(long id_venta, long id_carrito, Date fecha_compra) {
		super();
		this.id_venta = id_venta;
		this.id_carrito = id_carrito;
		this.fecha_venta = fecha_compra;
	}
	@Override
	public String toString() {
		return "Venta [id_venta=" + id_venta + ", id_carrito=" + id_carrito + ", fecha_compra=" + fecha_venta + "]\n";
	}
	public long getId_venta() {
		return id_venta;
	}
	public void setId_venta(long id_venta) {
		this.id_venta = id_venta;
	}
	public long getId_carrito() {
		return id_carrito;
	}
	public void setId_carrito(long id_carrito) {
		this.id_carrito = id_carrito;
	}
	public Date getFecha_compra() {
		return fecha_venta;
	}
	public void setFecha_compra(Date fecha_compra) {
		this.fecha_venta = fecha_compra;
	}
	
	
}
