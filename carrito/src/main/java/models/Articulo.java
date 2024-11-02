package models;

public class Articulo {
	
	private long id_articulo;
	private Integer codigo_articulo;
	private String nombre;
	private String descripcion;
	private Double precio;
	private Integer stock;
	
	
	
	public Articulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Articulo(long id_articulo, Integer codigo_articulo, String nombre, String descripcion, Double precio,
			Integer stock) {
		super();
		this.id_articulo = id_articulo;
		this.codigo_articulo = codigo_articulo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}



	@Override
	public String toString() {
		return "Articulo [id_articulo=" + id_articulo + ", codigo_articulo=" + codigo_articulo + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", precio=" + precio + ", stock=" + stock + "] ";
	}
	public long getId_articulo() {
		return id_articulo;
	}
	public void setId_articulo(long id_articulo) {
		this.id_articulo = id_articulo;
	}
	public Integer getCodigo_articulo() {
		return codigo_articulo;
	}
	public void setCodigo_articulo(Integer codigo_articulo) {
		this.codigo_articulo = codigo_articulo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}
