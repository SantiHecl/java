package models;

import java.util.Date;

public class Venta {
	private long id_venta;
	private long id_carrito;
	private Date fecha_venta;
	
	
	public Venta(long id_venta, long id_carrito, Date fecha_venta) {
		super();
		this.id_venta = id_venta;
		this.id_carrito = id_carrito;
		this.fecha_venta = fecha_venta;
	}
	
	public Venta() {
		super();
	}

	@Override
	public String toString() {
		return "Venta [id_venta=" + id_venta + ", id_carrito=" + id_carrito + ", fecha_venta=" + fecha_venta + "]\n";
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
	public Date getFecha_venta() {
		return fecha_venta;
	}
	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}
	
	
}
