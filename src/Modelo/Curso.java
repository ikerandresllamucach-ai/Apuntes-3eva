package Modelo;

public class Curso {
	
	protected int id;
	protected String nombre;
	protected int horas;
	protected double precio;
	
	private String urlImagen;
	private String descripcion;
	private int alumnosApuntados;
	
	public Curso(int id, String nombre, int horas, double precio, String urlImagen, String descripcion,
			int alumnosApuntados) {

		this.id = id;
		this.nombre = nombre;
		this.horas = horas;
		this.precio = precio;
		this.urlImagen = urlImagen;
		this.descripcion = descripcion;
		this.alumnosApuntados = alumnosApuntados;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getAlumnosApuntados() {
		return alumnosApuntados;
	}

	public void setAlumnosApuntados(int alumnosApuntados) {
		this.alumnosApuntados = alumnosApuntados;
	}
	
	
	
	@Override
	public String toString() {
	    return this.nombre; // 🌟 Cambia 'nombre' por la variable exacta de tu clase donde guardas el nombre del curso
	}
	
	
}
