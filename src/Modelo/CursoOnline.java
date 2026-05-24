package Modelo;

public class CursoOnline extends Curso{
	
	private String plataforma;
		
	public CursoOnline(int id, String nombre, int horas, double precio, String urlImagen, String descripcion,
			int alumnosApuntados, String plataforma) {
		super(id, nombre, horas, precio, urlImagen, descripcion, alumnosApuntados);
		this.plataforma = plataforma;
	}

	
	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	
}
