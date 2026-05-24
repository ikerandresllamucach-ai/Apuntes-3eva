package Modelo;

public class CursoPresencial extends Curso {
	
	private String aula;
	
	public CursoPresencial(int id, String nombre, int horas, double precio, String urlImagen, String descripcion,
			int alumnosApuntados, String aula) {
		super(id, nombre, horas, precio, urlImagen, descripcion, alumnosApuntados);
		this.aula = aula;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}
	
	
	
}
