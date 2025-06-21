package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/zapatillas"; // <== nombre correcto
    private static final String USUARIO = "root"; // reemplaza si tu usuario es diferente
    private static final String CONTRASENA = ""; // reemplaza si tu contraseña tiene valor
    
    private static Connection conexion;
    
    // Bandera para imprimir el mensaje solo una vez
    private static boolean conexionExitosa = false;

    public static Connection obtenerConexion() {
        if (conexion == null) { // Solo obtener la conexión si aún no existe
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                if (!conexionExitosa) {
                    System.out.println("✅ Conexión exitosa a la base de datos.");
                    conexionExitosa = true; 
                }
            } catch (SQLException e) {
                System.out.println("❌ Error al conectar a la base de datos: " + e.getMessage());
                return null;  // Devolvemos null si ocurre un error
            }
        }
        return conexion; // Si todo es exitoso, devolvemos la conexión
    }
}
