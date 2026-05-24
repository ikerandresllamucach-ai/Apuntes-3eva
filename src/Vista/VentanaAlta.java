package Vista;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Controlador.controlador;


public class VentanaAlta extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JTextField txtNombre;
	private JTextField txtHoras;
	private JTextField txtPrecio;
	private JComboBox<String> tipoCurso;
	private JTextField txtEspecifico;
	private JLabel lblEspecifico;
	private JButton btnGuardar;
	
	private JLabel lblNombre;
	private JLabel lblHoras;
	private JLabel lblPrecio;
	
	private JButton btnVolver;

	
	private controlador controlador;
	
	public VentanaAlta(controlador controlador) {
		this.controlador = controlador;
		
		setTitle("Alta de Cursos");
		setBounds(100, 100, 510, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(178, 75, 132, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtHoras = new JTextField();
		txtHoras.setColumns(10);
		txtHoras.setBounds(98, 146, 132, 20);
		getContentPane().add(txtHoras);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(261, 146, 132, 20);
		getContentPane().add(txtPrecio);
		
		tipoCurso = new JComboBox<String>();
		tipoCurso.setBounds(166, 203, 132, 25);
		tipoCurso.addItem("Presencial");
		tipoCurso.addItem("Online");
		tipoCurso.addActionListener(this);
		getContentPane().add(tipoCurso);
		
		txtEspecifico = new JTextField();
        txtEspecifico.setBounds(319, 204, 132, 25); 
        getContentPane().add(txtEspecifico);
        txtEspecifico.setColumns(10);
		
		lblEspecifico = new JLabel("Aula:");
		lblEspecifico.setBounds(78, 203, 100, 25);
		getContentPane().add(lblEspecifico);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(166, 282, 132, 23);
		btnGuardar.addActionListener(this);
		getContentPane().add(btnGuardar);
		
		lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(221, 50, 76, 14);
		getContentPane().add(lblNombre);
		
		lblHoras = new JLabel("Horas: ");
		lblHoras.setBounds(142, 121, 46, 14);
		getContentPane().add(lblHoras);
		
		lblPrecio = new JLabel("Precio: ");
		lblPrecio.setBounds(302, 121, 46, 14);
		getContentPane().add(lblPrecio);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(34, 46, 89, 23);
		btnVolver.addActionListener(this);
		getContentPane().add(btnVolver);
		
	} 

	// 🚨 EL MÉTODO ACTIONPERFORMED VA AQUÍ AFUERA
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// 1. Si el usuario interactúa con el ComboBox
		if (e.getSource() == tipoCurso) {
			String seleccion = tipoCurso.getSelectedItem().toString();
			
			if (seleccion.equals("Online")) {
				lblEspecifico.setText("Plataforma: ");
			} else {
				lblEspecifico.setText("Aula: ");
			}
		}
		
		// 2. Si el usuario hace clic en el botón Guardar
		if (e.getSource() == btnGuardar) {
			String nombre = txtNombre.getText();
			String hora = txtHoras.getText();
			String precio = txtPrecio.getText();
			String tipo = tipoCurso.getSelectedItem().toString(); 
			String especifico = txtEspecifico.getText();
			
			boolean exito = controlador.procesarAltaCurso(nombre,hora,precio, tipo, especifico);
			
			if (exito) {
				JOptionPane.showMessageDialog(btnGuardar, "Alta Confirmada");
				dispose();
			}else {JOptionPane.showMessageDialog(btnGuardar, "Por favor, revise los datos.");}
		}
		
		if (e.getSource() == btnVolver) {
			controlador.mostrarMenuAdmin();
		}
		
		
	}
}