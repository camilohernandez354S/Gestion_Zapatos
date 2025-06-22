package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/zapatillas";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";
    
    // Bandera para imprimir el mensaje solo una vez
    private static boolean conexionExitosa = false;

    public static Connection obtenerConexion() {
<<<<<<< HEAD
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            if (!conexionExitosa) {
                System.out.println("Conexión exitosa a la base de datos.");
                conexionExitosa = true; 
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}
=======
        try {
            // Si no hay conexión o si está cerrada, se crea una nueva
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                if (!conexionExitosa) {
                    System.out.println("✅ Conexión exitosa a la base de datos.");
                    conexionExitosa = true; 
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
        return conexion;
    }

}
>>>>>>> f0181e3152478700c6ea6b5f37fe6e207e9ca4da
