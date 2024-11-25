package models;

public class ArticuloCarrito {
	 private int codArticulo;
	 private String nombre;
	 private int cantidad;
	 private long idCarrito;

	public int getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(int codArticulo) {
		this.codArticulo = codArticulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	public ArticuloCarrito(int codArticulo, String nombre, int cantidad, long idCarrito) {
		super();
		this.codArticulo = codArticulo;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.idCarrito = idCarrito;
	}

	@Override
	public String toString() {
		return "ArticuloCarrito [codArticulo=" + codArticulo + ", nombre=" + nombre + ", cantidad=" + cantidad
				+ ", idCarrito=" + idCarrito + "]";
	}

	public ArticuloCarrito() {
		super();
	}   
    
    
}

