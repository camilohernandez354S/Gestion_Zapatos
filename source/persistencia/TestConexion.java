package persistencia;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        Connection conn = ConexionDB.obtenerConexion();

        if (conn != null) {
            System.out.println("✅ Conexión comprobada exitosamente.");
        } else {
            System.out.println("❌ Falló la conexión.");
        }
    }
}