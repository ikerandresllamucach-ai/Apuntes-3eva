package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Controlador.controlador;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MenuAdmin extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	private controlador controlador;
	
	private JLabel lblTitulo;
	private JButton btnAñadirCurso;
	private JButton btnModifcarCurso;
	private JButton btnBorrarCurso;
	private JButton btnSalir;


	
	
	public MenuAdmin(controlador controlador) {
		this.controlador = controlador;
		
		setTitle("MenuAdmin");
		setBounds(100, 100, 510, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		lblTitulo = new JLabel("Bienvenido, " + controlador.getNombreUserLog().toUpperCase());
		lblTitulo.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
		lblTitulo.setBounds(138, 36, 193, 24);
		getContentPane().add(lblTitulo);
		
		btnAñadirCurso = new JButton("Añadir Curso");
		btnAñadirCurso.setBounds(138, 119, 193, 23);
		btnAñadirCurso.addActionListener(this);
		getContentPane().add(btnAñadirCurso);
		
		btnModifcarCurso = new JButton("Modifcar Curso");
		btnModifcarCurso.setBounds(138, 181, 193, 23);
		btnModifcarCurso.addActionListener(this);
		getContentPane().add(btnModifcarCurso);
		
		btnBorrarCurso = new JButton("Eliminar Curso");
		btnBorrarCurso.setBounds(138, 236, 193, 23);
		btnBorrarCurso.addActionListener(this);
		getContentPane().add(btnBorrarCurso);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(23, 299, 89, 23);
		btnSalir.addActionListener(this);
		getContentPane().add(btnSalir);

	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalir) {
			controlador.mostrarLogin();
		}
		
		if (e.getSource() ==  btnAñadirCurso) {
			controlador.mostrarVentanAlta();
		}
		
		if (e.getSource() == btnModifcarCurso) {
			controlador.abrirListaCursos();
		}
		
	}

}
