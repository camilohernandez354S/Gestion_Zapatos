package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	
	Connection conectar = null;
	
	String usuario = "root";
	String contrasenia = "";
	String bd = "zapatillas";
	String puerto = "localhost:3306";
	String cadena = "jdbc:mysql://" + puerto + "/" + bd;
	
	public Connection establecerConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
			System.out.println("✅ Conexión exitosa a la base de datos");
		} catch (ClassNotFoundException e) {
			System.out.println("❌ Error: No se encontró el driver JDBC");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("❌ Error al conectar a la base de datos");
			e.printStackTrace();
		}
		return conectar;
	}
}
