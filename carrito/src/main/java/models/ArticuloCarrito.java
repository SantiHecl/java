package models;

public class ArticuloCarrito {
	 private int codArticulo;
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

	@Override
	public String toString() {
		return "ArticuloCarrito [codArticulo=" + codArticulo + ", cantidad=" + cantidad + ", idCarrito=" + idCarrito
				+ "]\n";
	}

	public ArticuloCarrito(int codArticulo, int cantidad, long idCarrito) {
		super();
		this.codArticulo = codArticulo;
		this.cantidad = cantidad;
		this.idCarrito = idCarrito;
	}

	public ArticuloCarrito() {
		super();
	}   
    
    
}

