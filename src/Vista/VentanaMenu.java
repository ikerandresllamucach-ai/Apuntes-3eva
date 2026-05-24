package Vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import Controlador.controlador;
import Modelo.Curso;
import Modelo.CursoPresencial;
import Modelo.CursoOnline;

public class VentanaMenu extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private controlador controlador;
	private GestorVista gVista;
	
	// Componentes de la interfaz
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtHoras;
	private JTextField txtPrecio;
	private JTextField txtTipo;
	private JTextField txtEspecifico;
	private JButton mostrarCursos;
	
	private JButton btnVolver;
	
	public VentanaMenu(controlador controlador) {
		this.controlador = controlador;
		
		setTitle("Mi Perfil de Usuario - Datos del Curso");
		setBounds(100, 100, 510, 420);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null); // Centra la ventana en la pantalla
		
		// Título de la ventana
		JLabel lblTitulo = new JLabel("INFORMACIÓN DE TU CURSO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(30, 20, 300, 25);
		getContentPane().add(lblTitulo);
		
		// ID Curso
		JLabel lblId = new JLabel("ID Curso:");
		lblId.setBounds(30, 70, 100, 20);
		getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(150, 70, 300, 22);
		txtId.setEditable(false); // Solo lectura
		getContentPane().add(txtId);
		
		// Nombre Curso
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 110, 100, 20);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(150, 110, 300, 22);
		txtNombre.setEditable(false);
		getContentPane().add(txtNombre);
		
		// Horas
		JLabel lblHoras = new JLabel("Horas Totales:");
		lblHoras.setBounds(30, 150, 100, 20);
		getContentPane().add(lblHoras);
		
		txtHoras = new JTextField();
		txtHoras.setBounds(150, 150, 300, 22);
		txtHoras.setEditable(false);
		getContentPane().add(txtHoras);
		
		// Precio
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(30, 190, 100, 20);
		getContentPane().add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(150, 190, 300, 22);
		txtPrecio.setEditable(false);
		getContentPane().add(txtPrecio);
		
		// Tipo (Presencial / Online)
		JLabel lblTipo = new JLabel("Modalidad:");
		lblTipo.setBounds(30, 230, 100, 20);
		getContentPane().add(lblTipo);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(150, 230, 300, 22);
		txtTipo.setEditable(false);
		getContentPane().add(txtTipo);
		
		// Campo específico (Aula o Plataforma)
		JLabel lblEspecifico = new JLabel("Lugar / Enlace:");
		lblEspecifico.setBounds(30, 270, 120, 20);
		getContentPane().add(lblEspecifico);
		
		txtEspecifico = new JTextField();
		txtEspecifico.setBounds(150, 270, 300, 22);
		txtEspecifico.setEditable(false);
		// Le damos un toque de color diferente para destacar que es dinámico
		txtEspecifico.setBackground(new Color(240, 248, 255)); 
		getContentPane().add(txtEspecifico);
		
		mostrarCursos = new JButton("Mostrar Mas Cursos");
		mostrarCursos.setBounds(197, 323, 169, 25);
		mostrarCursos.addActionListener(this);
		getContentPane().add(mostrarCursos);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(30, 324, 89, 23);
		btnVolver.addActionListener(this);
		getContentPane().add(btnVolver);
		
		// 🚨 PASO CLAVE: Ejecutamos la carga automática al abrir la ventana
		cargarDatosCurso();
	}
	
	private void cargarDatosCurso() {
		// 1. Le pedimos al controlador el curso del usuario logueado
		Curso curso = controlador.obtenerCursoUsuario();
		
		// 2. Si el usuario tiene un curso asignado, pintamos los campos
		if (curso != null) {
			txtId.setText(String.valueOf(curso.getId()));
			txtNombre.setText(curso.getNombre());
			txtHoras.setText(String.valueOf(curso.getHoras()));
			txtPrecio.setText(curso.getPrecio() + " €");
			
			// 3. Comprobamos de qué tipo de clase es para rellenar el campo específico
			if (curso instanceof CursoPresencial) {
				txtTipo.setText("Presencial");
				// Casteamos a CursoPresencial para acceder a su método específico
				txtEspecifico.setText(((CursoPresencial) curso).getAula()); 
			} 
			else if (curso instanceof CursoOnline) {
				txtTipo.setText("Online");
				// Casteamos a CursoOnline para acceder a su método específico
				txtEspecifico.setText(((CursoOnline) curso).getPlataforma());
			}
		} else {
			// En caso de que el usuario no tenga ningún curso vinculado en la BD (id_curso sea NULL)
			txtNombre.setText("No estás matriculado en ningún curso actualmente.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mostrarCursos) {
			controlador.abrirListaCursos();
		}
		
		if (e.getSource() == btnVolver) {
			controlador.mostrarLogin();
		}
		
	}
}