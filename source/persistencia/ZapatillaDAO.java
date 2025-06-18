package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mundo.Zapatilla;

/**
 * Clase DAO para manejar operaciones con la tabla 'zapatillas'.
 */
public class ZapatillaDAO {

    /**
     * Inserta una nueva zapatilla en la base de datos.
     * 
     * @param zapatilla Objeto Zapatilla con los datos a guardar
     * @return true si se insertó correctamente, false si ocurrió un error
     */
    public boolean insertarZapatilla(Zapatilla zapatilla) {
        String sql = """
            INSERT INTO zapatillas (id_color, id_talla, id_genero, id_tipo, id_marca, Foto)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, zapatilla.getIdColor());
            stmt.setInt(2, zapatilla.getIdTalla());
            stmt.setInt(3, zapatilla.getIdGenero());
            stmt.setInt(4, zapatilla.getIdTipo());
            stmt.setInt(5, zapatilla.getIdMarca());
            stmt.setString(6, zapatilla.getFoto());

            int filas = stmt.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar zapatilla: " + e.getMessage());
            return false;
        }
    }

    /**
     * Consulta todas las zapatillas registradas en la base de datos.
     * 
     * @return lista de objetos Zapatilla
     */
    public ArrayList<Zapatilla> obtenerTodasLasZapatillas() {
        ArrayList<Zapatilla> lista = new ArrayList<>();

        String sql = "SELECT * FROM zapatillas";

        try (
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idColor = rs.getInt("id_color");
                int idTalla = rs.getInt("id_talla");
                int idGenero = rs.getInt("id_genero");
                int idTipo = rs.getInt("id_tipo");
                int idMarca = rs.getInt("id_marca");
                String foto = rs.getString("Foto");

                Zapatilla z = new Zapatilla(id, idColor, idTalla, idGenero, idTipo, idMarca, foto);
                lista.add(z);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al consultar zapatillas: " + e.getMessage());
        }

        return lista;
    }
}
