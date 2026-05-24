package Vista;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controlador.controlador;
import Modelo.Curso;
import Modelo.CursoPresencial;

public class detalleCurso extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private controlador controlador;
	
	private JLabel lblCurso;
	private JLabel lblalumnos;
	private JLabel lblprecio;
	private	JLabel lblHoras;
	private	JLabel lblTipo;
	private JTextArea lblDescripcion;
	
	private JTextField txtAlumnos;
	private JTextField txtPrecio;
	private JTextField txtHoras;
	private JTextField txtTipo;
	private JLabel lblImagen;

	private JButton bntVolver;
	private JButton btnGuardar;
	
	private String rolUser;
	private int idCurso;

	public detalleCurso(controlador controlador, Curso cursoSeleccionado) {
		this.controlador = controlador;
		this.rolUser = controlador.getNombreUserLog() != null ? controlador.getRolUser() : "User";
		this.idCurso = cursoSeleccionado.getId();
		
		setTitle(cursoSeleccionado.getNombre());
		setBounds(100, 100, 510, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		lblCurso = new JLabel(cursoSeleccionado.getNombre().toUpperCase());
		lblCurso.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
		lblCurso.setBounds(40, 20, 403, 25);
		getContentPane().add(lblCurso);
		
		lblalumnos = new JLabel("Alumnos: ");
		lblalumnos.setBounds(289, 86, 74, 14);
		getContentPane().add(lblalumnos);
		
		lblprecio = new JLabel("Precio: ");
		lblprecio.setBounds(289, 145, 62, 14);
		getContentPane().add(lblprecio);
		
		lblHoras = new JLabel("Horas: ");
		lblHoras.setBounds(289, 261, 62, 14);
		getContentPane().add(lblHoras);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(289, 203, 62, 14);
		getContentPane().add(lblTipo);
		
		lblImagen = new JLabel("Cargando Imagen...");
		lblImagen.setBounds(40, 56, 206, 147);
		lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY));
		getContentPane().add(lblImagen);
		
		txtAlumnos = new JTextField(String.valueOf(cursoSeleccionado.getAlumnosApuntados()));
		txtAlumnos.setBounds(357, 83, 86, 20);
		txtAlumnos.setEditable(false);
		txtAlumnos.setColumns(10);
		getContentPane().add(txtAlumnos);
		
		txtPrecio = new JTextField(cursoSeleccionado.getPrecio() + " €");
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(357, 142, 86, 20);
		txtPrecio.setEditable(false);
		getContentPane().add(txtPrecio);
		
		txtHoras = new JTextField(String.valueOf(cursoSeleccionado.getHoras()));
		txtHoras.setColumns(10);
		txtHoras.setBounds(357, 258, 86, 20);
		txtHoras.setEditable(false);
		getContentPane().add(txtHoras);
		
		String modalidad = (cursoSeleccionado instanceof CursoPresencial) ? "Presencial" : "Online";
		txtTipo = new JTextField(modalidad);
		txtTipo.setColumns(10);
		txtTipo.setEditable(false);
		txtTipo.setBounds(357, 200, 86, 20);
		getContentPane().add(txtTipo);

		lblDescripcion = new JTextArea(cursoSeleccionado.getDescripcion());
		lblDescripcion.setLineWrap(true);
		lblDescripcion.setWrapStyleWord(true);
		lblDescripcion.setEditable(false);
		lblDescripcion.setBounds(40, 234, 206, 91);
		lblDescripcion.setBackground(getContentPane().getBackground());
		getContentPane().add(lblDescripcion);
		
		bntVolver = new JButton("Volver");
		bntVolver.setBounds(357, 319, 86, 23);
		bntVolver.addActionListener(this);
		getContentPane().add(bntVolver);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(256, 319, 86, 23);
		btnGuardar.addActionListener(this);
		btnGuardar.setVisible(false); 
		getContentPane().add(btnGuardar);
		
		cargarImagen(cursoSeleccionado.getUrlImagen());
		
		if ("Admin".equalsIgnoreCase(this.rolUser)) {
			modificarCurso();
		}
	}

	private void cargarImagen(String urlImagen) {
		try {
			java.net.URL url = java.net.URI.create(urlImagen).toURL();
	        ImageIcon iconoOriginal = new ImageIcon(url);
	        java.awt.Image imgOriginal = iconoOriginal.getImage();
	        java.awt.Image imgEscalada = imgOriginal.getScaledInstance(
	        		lblImagen.getWidth(), 
	        		lblImagen.getHeight(), 
	        		java.awt.Image.SCALE_SMOOTH
	        );
	        lblImagen.setIcon(new ImageIcon(imgEscalada));
	        lblImagen.setText(""); 
		} catch (Exception e) {
			lblImagen.setText("<html><center>No se pudo cargar<br>la imagen de portada</center></html>");
			lblImagen.setHorizontalAlignment(JLabel.CENTER);
		}
	}
	
	private void modificarCurso() {
		txtAlumnos.setEditable(true);
		txtPrecio.setEditable(true);
		txtHoras.setEditable(true);
		txtTipo.setEditable(true);
		lblDescripcion.setEditable(true);
		lblDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GRAY));
		btnGuardar.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bntVolver) {
			dispose();
			controlador.abrirListaCursos();
		}
		
		if (e.getSource() == btnGuardar) {
			int idCurso = this.idCurso;
			String nombre = lblCurso.getText();
			String cantidadAlumnos = txtAlumnos.getText();
			String horas = txtHoras.getText();
			String precio = txtPrecio.getText();
			String tipo = txtTipo.getText(); 
			String descripcion = lblDescripcion.getText();
			
			boolean exito = controlador.procesarModificacionCurso(idCurso, nombre, horas, precio, tipo, descripcion, cantidadAlumnos);
			
			if (exito) {
				javax.swing.JOptionPane.showMessageDialog(
			            this, 
			            "¡Curso actualizado correctamente en la Base de Datos!", 
			            "Operación Exitosa", 
			            javax.swing.JOptionPane.INFORMATION_MESSAGE
			        );
				
				txtAlumnos.setEditable(false);
		        txtPrecio.setEditable(false);
		        txtHoras.setEditable(false);
		        txtTipo.setEditable(false);
		        lblDescripcion.setEditable(false);
		        lblDescripcion.setBorder(null);
		        btnGuardar.setVisible(false);
			} else {
				javax.swing.JOptionPane.showMessageDialog(
			            this, 
			            "Error al actualizar el curso.\nPor favor, comprueba que las Horas, los Alumnos y el Precio sean correctos.", 
			            "Error de Validación", 
			            javax.swing.JOptionPane.ERROR_MESSAGE
			        );
			}
		}
	}
}