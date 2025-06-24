package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La clase {@code ConexionDB} se encarga de establecer la conexión con la base de datos MySQL.
 * Utiliza los parámetros de URL, usuario y contraseña para crear la conexión.
 * La clase también maneja la impresión de un mensaje de éxito al realizar la conexión.
 */
public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/zapatillas";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";
    
    private static boolean conexionExitosa = false;

    /**
     * Establece y devuelve una conexión con la base de datos MySQL.
     * Si la conexión es exitosa, imprime un mensaje indicando que la conexión fue exitosa.
     * Si ocurre un error durante la conexión, se captura la excepción y se imprime un mensaje de error.
     * 
     * @return Una conexión a la base de datos, o {@code null} si ocurrió un error durante la conexión.
     */
    public static Connection obtenerConexion() {
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
