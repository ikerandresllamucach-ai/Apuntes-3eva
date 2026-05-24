package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import Controlador.controlador;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class vLogin extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	private controlador controlador; 
    private GestorVista gVista;
	
	private JLabel lblUser;
	private JLabel lblPass;

	private JComboBox<String> rol;
	
	private JTextField txtUser;
	private JPasswordField txtPass;
	
	private JButton btnEntrar;
	
	public vLogin(controlador controlador ) {
		this.controlador= controlador;
		
		setTitle("Login");
		setBounds(100, 100, 510, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblUser = new JLabel("Usuario: ");
		lblUser.setBounds(105, 89, 75, 14);
		getContentPane().add(lblUser);
		
		lblPass = new JLabel("Contraseña: ");
		lblPass.setBounds(105, 143, 75, 14);
		getContentPane().add(lblPass);
		
		txtUser = new JTextField();
		txtUser.setBounds(193, 86, 126, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setColumns(10);
		txtPass.setBounds(193, 140, 126, 20);
		getContentPane().add(txtPass);
		
		rol = new JComboBox<String>();
		rol.setBounds(192, 189, 127, 22);
		rol.addItem("Usuario");
		rol.addItem("Admin");
		rol.addActionListener(this);
		getContentPane().add(rol);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(193, 240, 126, 23);
		btnEntrar.addActionListener(this);
		getContentPane().add(btnEntrar);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    
	    if (e.getSource() == btnEntrar) {
	        
	        String user = txtUser.getText();
	        String pass = new String(txtPass.getPassword());
	        String rolSeleccionado = rol.getSelectedItem().toString();
	        
	        controlador.validacionLogin(user, pass, rolSeleccionado);  
	      
	    }
	
	}
	
}
