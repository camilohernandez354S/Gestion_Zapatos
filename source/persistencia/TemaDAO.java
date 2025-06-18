package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mundo.Tema;

/**
 * Clase encargada de acceder a la tabla 'tema' en la base de datos.
 * Contiene métodos para consultar todos los temas.
 */
public class TemaDAO {

    /**
     * Consulta todos los temas registrados en la tabla 'tema'
     * 
     * @return una lista de objetos Tema
     */
    public ArrayList<Tema> obtenerTodosLosTemas() {
        ArrayList<Tema> lista = new ArrayList<>();

        // Consulta SQL
        String sql = "SELECT * FROM tema";

        try (
            // Establecer conexión y preparar consulta
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            // Recorremos los resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                Tema tema = new Tema(id, nombre);
                lista.add(tema);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al consultar los temas: " + e.getMessage());
        }

        return lista;
    }
}
