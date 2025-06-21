package persistencia;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Connection;
import org.junit.jupiter.api.Test;

public class TestConexion {

    @Test
    public void conexionNoDebeSerNula() {
        Connection conn = ConexionDB.obtenerConexion();
        assertNotNull(conn, "La conexión debería ser exitosa y no nula");
    }
}
