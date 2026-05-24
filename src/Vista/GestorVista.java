package Vista;

import Controlador.controlador;
import Modelo.Curso;

public class GestorVista {

    private VentanaMenu ventanaMenu;
    private VentanaAlta ventanaAlta;
    private MenuAdmin menuAdmin;
    private vLogin login;
    private listaCursos listaCursos;
    private detalleCurso detalleCurso;
    


    public GestorVista() {
    }

    public void mostrarMenu(controlador control) {
        cerrarTodo(); 
        ventanaMenu = new VentanaMenu(control); 
        ventanaMenu.setVisible(true);
    }

    public void mostrarAlta(controlador control) {
        cerrarTodo(); 
        ventanaAlta = new VentanaAlta(control); 
        ventanaAlta.setVisible(true);
    }
   
    public void mostrarMenuAdmin(controlador controlador) {
    	cerrarTodo();
    	menuAdmin = new MenuAdmin(controlador);
    	menuAdmin.setVisible(true);
    	
    }
    
    public void mostrarLogin(controlador controlador) {
    	cerrarTodo();
    	login = new vLogin(controlador);
    	login.setVisible(true);
    	
    }
    
    	public void mostrarListaCursos(controlador controlador) {
    	cerrarTodo();
    	listaCursos = new listaCursos(controlador);
    	listaCursos.setVisible(true);
	}
    	
    public void mostrarDetalleCurso(controlador controlador, Curso cursoSeleccionado) {
    	cerrarTodo();
    	detalleCurso = new detalleCurso(controlador, cursoSeleccionado);
    	detalleCurso.setVisible(true);
		
	}
    
    // 4. El limpiador automático que destruye las ventanas en segundo plano
    private void cerrarTodo() {
        if (ventanaMenu != null) { 
            ventanaMenu.dispose(); 
            ventanaMenu = null; 
        }
        if (ventanaAlta != null) { 
            ventanaAlta.dispose(); 
            ventanaAlta = null; 
        }
        
        if (menuAdmin != null) { 
        	menuAdmin.dispose(); 
        	menuAdmin = null; 
        }
        
        if (login != null) { login.dispose(); login = null; }
        if (detalleCurso != null) { detalleCurso.dispose(); detalleCurso = null; }
    }

	


}