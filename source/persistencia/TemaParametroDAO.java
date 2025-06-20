package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mundo.Parametro;

/**
 * Clase encargada de obtener los parámetros que pertenecen a un tema específico,
 * a través de la tabla intermedia 'tema_parametros'.
 */
public class TemaParametroDAO {

    /**
     * Consulta todos los parámetros asociados a un tema específico
     * 
     * @param idTema ID del tema (por ejemplo, el tema "Color" o "Marca")
     * @return lista de parámetros asociados a ese tema
     */
    public ArrayList<Parametro> obtenerParametrosPorTema(int idTema) {
        ArrayList<Parametro> lista = new ArrayList<>();

        String sql = """
            SELECT p.id, p.nombre 
            FROM tema_parametros tp
            JOIN parametros p ON tp.id_parametro = p.id
            WHERE tp.id_tema = ?
        """;

        try (
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, idTema);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                Parametro p = new Parametro(id, nombre);
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println(" Error al consultar parámetros del tema: " + e.getMessage());
        }

        return lista;
    }
}
