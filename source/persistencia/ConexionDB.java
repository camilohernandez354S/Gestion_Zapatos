package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

<<<<<<< HEAD
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
            }
=======
    private static final String URL = "jdbc:mysql://localhost:3306/zapatillas";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println(" Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println(" Error al conectar a la base de datos: " + e.getMessage());
>>>>>>> 7f2f9d2059ff1410eff5522e843eed646d1bd302
        }
        return conexion;
    }

    // Método para cerrar la conexión
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();  // Cerrar la conexión
                conexion = null;  // Marcar la conexión como cerrada
                System.out.println("✅ Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
