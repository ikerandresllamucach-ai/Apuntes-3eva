package Controlador;

import javax.swing.ListModel;

import Modelo.Curso;
import Modelo.CursoDAO;
import Modelo.CursoOnline;
import Modelo.CursoPresencial;
import Modelo.UsuarioDAO;
import Vista.GestorVista;


public class controlador {
	
	private CursoDAO cursoDao;
	private UsuarioDAO usuarioDao;
	private GestorVista gestorV;
	
	private int idCursoLogueado;
	

	private String nombreUserLog;
	private String rolUser;
	
	public controlador() {
		this.cursoDao = new CursoDAO();
		this.usuarioDao = new UsuarioDAO();
		this.gestorV = new GestorVista();
	}
	
	public void iniciarAplicacion() {
		mostrarLogin();
	}

	public boolean procesarAltaCurso(String nombre, String horaStr, String precioStr, String tipo, String especifico) {
	    
	    int horasNum = 0;
	    double PrecioNum = 0.0;
	    
	    try {
	        horasNum = Integer.parseInt(horaStr);
	        PrecioNum = Double.parseDouble(precioStr);
	        
	        Curso curso = null;
	        
	        // 🌟 VALORES POR DEFECTO PARA EL ALTA
	        String urlImagenPorDefecto = "https://via.placeholder.com/440x200.png?text=Curso+Sin+Imagen"; 
	        String descripcionPorDefecto = "Sin descripción disponible.";
	        int alumnosIniciales = 0;
	        
	        if (tipo.equals("Presencial")) {
	            // 🌟 Pasamos los 8 parámetros en orden:
	            // id, nombre, horas, precio, urlImagen, descripcion, alumnosApuntados, aula
	            curso = new CursoPresencial(0, nombre, horasNum, PrecioNum, urlImagenPorDefecto, descripcionPorDefecto, alumnosIniciales, especifico);
	        }
	        else { 
	            // 🌟 Hacemos lo mismo para el Online (asumiendo que pide el mismo orden y al final la plataforma)
	            curso = new CursoOnline(0, nombre, horasNum, PrecioNum, urlImagenPorDefecto, descripcionPorDefecto, alumnosIniciales, especifico);
	        }
	        
	        return cursoDao.insertarCurso(curso);
	        
	    } catch (Exception e) {
	        System.out.println("Error en el controlador: " + e.getMessage());
	        return false;
	    }
	}
	
	
	public void validacionLogin (String user, String pass, String rol) {
	    int resultadoLogin = usuarioDao.validarLogin(user, pass, rol);
	    
	  
	    if (resultadoLogin == -1) {
	        javax.swing.JOptionPane.showMessageDialog(null, 
	            "Usuario, contraseña o rol incorrectos", 
	            "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
	    } 
	  
	    else {  
	        this.idCursoLogueado = resultadoLogin; 
	        this.nombreUserLog = user;
	        this.rolUser = rol;
	      
	        if (rol.equals("Admin")) {
	        	mostrarMenuAdmin();
	        } else {
	        	mostrarMenu();
	        }
	    }
	}
	
	public Curso obtenerCursoUsuario() {
		return cursoDao.filtrarCurso(this.idCursoLogueado);
	}
	
	
	public ListModel<Curso> obtenerModeloTodosLosCursos() {
		return cursoDao.obtenerTodosLosCursos();
	}

	

	public boolean procesarModificacionCurso(int idCurso, String nombre, String horaStr, String precioStr, String tipoStr, String descripcion, String alumnosStr) {
	    try {
	        int horasNum = Integer.parseInt(horaStr.trim());
	        
	        String precioLimpio = precioStr.replaceAll("[^0-9.]", ""); 
	        double precioNum = Double.parseDouble(precioLimpio);
	        
	        String tipoLimpio = tipoStr.trim();
	        Curso cursoEditado;

	        if ("Presencial".equalsIgnoreCase(tipoLimpio)) {
	            cursoEditado = new Modelo.CursoPresencial(idCurso, nombre, horasNum, precioNum, "", descripcion, 0, "");
	        } else if ("Online".equalsIgnoreCase(tipoLimpio)) {
	            cursoEditado = new Modelo.CursoOnline(idCurso, nombre, horasNum, precioNum, "", descripcion, 0, "");
	        } else {
	            System.out.println("Error: El tipo introducido no es válido.");
	            return false;
	        }

	        return cursoDao.actualizarCurso(cursoEditado);
	        
	    } catch (NumberFormatException e) {
	        System.out.println("Error de formato numérico en el controlador: " + e.getMessage());
	        return false;
	    } catch (Exception e) {
	        System.out.println("Error inesperado: " + e.getMessage());
	        return false;
	    }
	}
	
	
	
	// ABRIR VENTANAS
	
	public void mostrarLogin() {
		gestorV.mostrarLogin(this);
	}
	
	public void mostrarMenuAdmin() {
		gestorV.mostrarMenuAdmin(this);
	}
	
	public void mostrarMenu() {
		gestorV.mostrarMenu(this);
	}
	
	
	public void mostrarVentanAlta() {
		gestorV.mostrarAlta(this);
		
	}
	public void abrirListaCursos() {
		gestorV.mostrarListaCursos(this);
		
	}

	public void abrirDetalleCurso(Curso cursoSeleccionado) {
		gestorV.mostrarDetalleCurso(this, cursoSeleccionado);
		
	}

	
	// GETTERS Y SETTERS
	public String getNombreUserLog() {
		return this.nombreUserLog;
	}

	public String getRolUser() {
		return rolUser;
	}

	public int getIdCursoLogueado() {
		return idCursoLogueado;
	}
	
	
} 
	
