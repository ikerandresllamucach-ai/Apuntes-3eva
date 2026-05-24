package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;

import Modelo.Conexion;

public class CursoDAO {

	public Curso filtrarCurso(int id) {
	    String sql = "Select * from Curso where id_curso = ?";
	    
	    try (Connection con = Conexion.getConexion();
	            PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        ps.setInt(1, id);
	        
	        try (ResultSet rs = ps.executeQuery()){
	            
	        	if (rs.next()) {
	        	    int idCurso = rs.getInt("id_curso");
	        	    String nombre = rs.getString("nombre");
	        	    int horas = rs.getInt("horas");
	        	    double precio = rs.getDouble("precio");
	        	    String tipo = rs.getString("tipo");
	        	    
	        	    // 🌟 1. Sacamos los 3 campos nuevos directamente del ResultSet
	        	    String urlImg = rs.getString("url_imagen");
	        	    String desc = rs.getString("descripcion");
	        	    int alumnos = rs.getInt("alumnos_apuntados");
	        	    
	        	    if (tipo.equalsIgnoreCase("Presencial")) {
	        	        String lugar = rs.getString("aula"); 
	        	        
	        	        // 🌟 2. Le pasamos los 8 parámetros en el orden exacto de tu constructor
	        	        return new CursoPresencial(idCurso, nombre, horas, precio, urlImg, desc, alumnos, lugar);
	        	        
	        	    } else {
	        	        String plataforma = rs.getString("plataforma"); 
	        	        
	        	        // 🌟 3. Hacemos lo mismo para el Online (asumiendo que su constructor es igual)
	        	        return new CursoOnline(idCurso, nombre, horas, precio, urlImg, desc, alumnos, plataforma);
	        	    }
	        	}
	        }
	        
	    } catch (Exception e) {
	        System.out.println("Error en la busqueda JDBC: " + e.getMessage());
	        return null;
	    }
	    
	    return null;
	}
    
    
	public boolean insertarCurso(Curso c) {
	    // 1. Ampliamos la consulta SQL con las 3 nuevas columnas
	    String sql = "INSERT INTO curso (nombre, horas, precio, tipo, aula, plataforma, url_imagen, descripcion, alumnos_apuntados) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try (Connection con = Conexion.getConexion(); 
	         PreparedStatement pst = con.prepareStatement(sql)) {
	        
	        // Parámetros comunes de la clase padre
	        pst.setString(1, c.getNombre());
	        pst.setInt(2, c.getHoras());
	        pst.setDouble(3, c.getPrecio());
	        
	        // Bloque condicional según el tipo de curso
	        if (c instanceof CursoPresencial) {
	            CursoPresencial presencial = (CursoPresencial) c;
	            
	            pst.setString(4, "Presencial");
	            pst.setString(5, presencial.getAula());
	            pst.setNull(6, java.sql.Types.VARCHAR); // No tiene plataforma
	            
	        } else if (c instanceof CursoOnline) {
	            CursoOnline online = (CursoOnline) c;
	            
	            pst.setString(4, "Online");
	            pst.setNull(5, java.sql.Types.VARCHAR); // No tiene aula
	            pst.setString(6, online.getPlataforma());
	        }
	        
	        // 2. 🌟 AÑADIMOS LAS NUEVAS COLUMNAS (Se ejecutan para ambos tipos por igual porque están en la clase padre)
	        pst.setString(7, c.getUrlImagen());
	        pst.setString(8, c.getDescripcion());
	        pst.setInt(9, c.getAlumnosApuntados());
	        
	        // 3. Ejecutamos la inserción en la base de datos
	        int filasAfectadas = pst.executeUpdate();
	        
	        return filasAfectadas > 0;
	        
	    } catch (SQLException e) {
	        System.out.println("Error en la inserción JDBC: " + e.getMessage());
	        return false;
	    }
	}
	
	
	public DefaultListModel<Curso> obtenerTodosLosCursos() {
		DefaultListModel<Curso> cursos =  new DefaultListModel<Curso>();
		
		String sql = "Select * FROM curso";
		
		
		try (Connection con = Conexion.getConexion();
				PreparedStatement ps  = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {
				int idCurso = rs.getInt("id_curso");
	            String nombre = rs.getString("nombre");
	            int horas = rs.getInt("horas");
	            double precio = rs.getDouble("precio");
	            String tipo = rs.getString("tipo");
	            String urlImg = rs.getString("url_imagen");
	            String desc = rs.getString("descripcion");
	            int alumnos = rs.getInt("alumnos_apuntados");
				
	            Curso c = null;
	            
	            if (tipo.equalsIgnoreCase("Presnecial")) {
					String lugar  = rs.getString("aula");
					c = new CursoPresencial(idCurso, nombre,horas,precio,urlImg,desc, alumnos, lugar);
				}
	            else {
	            	String plataforma = rs.getString("plataforma"); 
	                c = new CursoOnline(idCurso, nombre, horas, precio, urlImg, desc, alumnos, plataforma);
	            }
	            
	            cursos.addElement(c);
			}
			

		} catch (Exception e) {
			System.out.println("Error al obtener todos los cursos JDBC: " + e.getMessage());
	        return null; // Si algo rompe en la BD, devolvemos null para controlarlo
		}
		
		return cursos;		
	}
	
	
	public boolean actualizarCurso(Curso c) {
	   
	    String sql = "UPDATE curso SET nombre = ?, horas = ?, precio = ?, descripcion = ?, tipo = ? WHERE id_curso = ?";
	    
	    try (Connection con = Conexion.getConexion();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        ps.setString(1, c.getNombre());
	        ps.setInt(2, c.getHoras());
	        ps.setDouble(3, c.getPrecio());
	        ps.setString(4, c.getDescripcion());
	        
	        if (c instanceof Modelo.CursoPresencial) {
	            ps.setString(5, "Presencial");
	        } else {
	            ps.setString(5, "Online");
	        }
	        
	      
	        ps.setInt(6, c.getId()); 
	        
	        int filasAfectadas = ps.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (Exception e) {
	        System.out.println("Error al actualizar el curso en el DAO: " + e.getMessage());
	        return false; 
	    } 
	}
		
}
	
