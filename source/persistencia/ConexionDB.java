package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/zapatillas"; // <== nombre correcto
    private static final String USUARIO = "root"; // reemplaza si tu usuario es diferente
    private static final String CONTRASENA = ""; // reemplaza si tu contraseña tiene valor

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}