package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Modelo.Conexion;


public class UsuarioDAO {
	
	public int validarLogin(String nombre, String contraseña, String rol) {
	    String sql = "SELECT * FROM usuario WHERE username = ? AND password = ? AND rol = ?";
	    
	    try (Connection con  = Conexion.getConexion(); 
	         PreparedStatement ps = con.prepareStatement(sql)) { 
	        
	        ps.setString(1, nombre);
	        ps.setString(2, contraseña);
	        ps.setString(3, rol);
	        
	        try (ResultSet rs = ps.executeQuery()){
	            if (rs.next()) {   
	                return rs.getInt("id_curso");
	            }
	        } 				
	        
	    } catch (Exception e) {
	        System.out.println("Error en el DAO: " + e.getMessage());
	        return -1;
	    }
	    
	    return -1; 
	}
	
}
