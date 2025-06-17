package persistencia;

import java.sql.Connection;

public class TestConexion {
	public static void main(String[] args) {
        ConexionDB conexion = new ConexionDB();
        Connection conn = conexion.establecerConexion();

        if (conn != null) {
            System.out.println("✅ Conexión comprobada exitosamente.");
        } else {
            System.out.println("❌ Falló la conexión.");
        }
    }
}
	