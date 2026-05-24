package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter; // 🌟 Cambiado por MouseAdapter para reducir código
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import Controlador.controlador;
import Modelo.Curso;

public class listaCursos extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private controlador controlador;
	
	
	private JList<Curso> jlCursos;
	private JScrollPane scrollLista;
	private JButton btnVerDetalle;
	private JLabel lblInstrucciones;
	private JButton btnVolver;
	

	
	public listaCursos(controlador controlador) {
		this.controlador = controlador;
		
		
		setTitle("Catálogo - Lista de Cursos Disponibles");
		setBounds(100, 100, 510, 420);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
	
		lblInstrucciones = new JLabel("Selecciona un curso de la lista para la Informacion:");
		lblInstrucciones.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInstrucciones.setBounds(30, 20, 430, 25);
		getContentPane().add(lblInstrucciones);
		
		
		jlCursos = new JList<Curso>();
		
		jlCursos.addMouseListener(new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					abrirDetalleCurso(); 
				}
			}
		});
		
		
		scrollLista = new JScrollPane(jlCursos);
		scrollLista.setBounds(30, 60, 434, 240); 
		getContentPane().add(scrollLista);
		
	
		btnVerDetalle = new JButton("Ver Ficha del Curso");
		btnVerDetalle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerDetalle.setBounds(160, 320, 180, 30);
		btnVerDetalle.addActionListener(this); 
		getContentPane().add(btnVerDetalle);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(30, 325, 89, 23);
		btnVolver.addActionListener(this);
		getContentPane().add(btnVolver);
		
		// 🚨 PASO CLAVE: Método para que cargue los datos de la BD nada más abrirse
		inyectarCursosEnLista();
	}
	
	
	private void inyectarCursosEnLista() {
		// Le pedimos al controlador el DefaultListModel que creamos en el DAO
		
		jlCursos.setModel(controlador.obtenerModeloTodosLosCursos());
	}
	
	
	private void abrirDetalleCurso() {
		Curso cursoSeleccionado = jlCursos.getSelectedValue();
		
		if (cursoSeleccionado != null) {
			controlador.abrirDetalleCurso(cursoSeleccionado);
			dispose();
		}
		
		else {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un curso de la lista", "Aviso", JOptionPane.WARNING_MESSAGE);		}
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVerDetalle) {
			abrirDetalleCurso();
		}
		
		if (e.getSource() ==  btnVolver) {
			dispose();
			
			String rol = controlador.getRolUser();
			
			if (rol.equals("Admin")) {
				controlador.mostrarMenuAdmin();
			}
			else { controlador.mostrarMenu();}
			
			
		}
	}
}