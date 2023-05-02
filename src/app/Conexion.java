package app;
import java.sql.*;

import javax.swing.JOptionPane;

//import javax.swing.JOptionPane;

public class Conexion {
	
	//URL PARA CONECTAR LA DB
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	static String user ="system";
	static String pass ="umg123";
	
	
	
	Connection conexion;
	
	public Conexion()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			// TODO: handle exception
			
			//System.out.println(e.getMessage());
			 JOptionPane.showMessageDialog(null,"no se pudo conectar)"+e);
			
		}
		try {
			conexion = DriverManager.getConnection(url,user,pass);
			
		} catch (Exception e) { 
			// TODO: handle exception 
			//Sino es posible conectar la DB
			System.out.println("Error en el Driver de Conexion "+e.getMessage());
		}
		
	}
	
	public Connection getConexion()
			{
		return conexion;
			}

}
