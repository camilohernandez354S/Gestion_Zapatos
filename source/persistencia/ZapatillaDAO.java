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
            INSERT INTO zapatillas (id_talla, id_genero, id_tipo, id_marca, Foto)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(2, zapatilla.getIdTalla());
            stmt.setInt(3, zapatilla.getIdGenero());
            stmt.setInt(4, zapatilla.getIdTipo());
            stmt.setInt(5, zapatilla.getIdMarca());
            stmt.setString(6, zapatilla.getFoto());

            int filas = stmt.executeUpdate();

            return filas > 0;
            
        } catch (SQLException e) {
            System.out.println(" Error al insertar zapatilla: " + e.getMessage());
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
                int idTalla = rs.getInt("id_talla");
                int idGenero = rs.getInt("id_genero");
                int idTipo = rs.getInt("id_tipo");
                int idMarca = rs.getInt("id_marca");
                String foto = rs.getString("Foto");

                Zapatilla z = new Zapatilla(id, idTalla, idGenero, idTipo, idMarca, foto);
                lista.add(z);
            }

        } catch (SQLException e) {
            System.out.println(" Error al consultar zapatillas: " + e.getMessage());
        }

        return lista;
    }
    
    
    /**
     * Elimina una zapatilla de la base de datos según su ID.
     * <p>
     * Ejecuta una sentencia SQL DELETE en la tabla 'zapatillas' para eliminar
     * el registro correspondiente al ID proporcionado.
     * </p>
     *
     * @param id El ID de la zapatilla que se desea eliminar.
     * @return true si la eliminación fue exitosa (al menos una fila afectada), false si no se eliminó ninguna fila o ocurrió un error.
     */
    
    public static boolean eliminarZapatilla(int id) {
    	String sql = "DELETE FROM zapatillas WHERE id = ?";
    	
    	try (
    		Connection conn = ConexionDB.obtenerConexion();
    		PreparedStatement stmt = conn.prepareStatement(sql);
    			
    	) {
    		stmt.setInt(1, id);
    		
    		int filas = stmt.executeUpdate();
    		return filas > 0;
 
    	} catch (SQLException e){
			System.out.println("❌ Error al eliminar zapatilla: " + e.getMessage());
    		return false;
    	}
    
    }
    
    /**
     * Actualiza una zapatilla existente en la base de datos.
     *
     * @param z Objeto Zapatilla con los datos actualizados.
     * @return true si la actualización fue exitosa, false si ocurrió un error.
     */
    public static boolean actualizarZapatilla(Zapatilla z) {
        String sql = """
            UPDATE zapatillas
            SET id_talla = ?, id_genero = ?, id_tipo = ?, id_marca = ?, Foto = ?
            WHERE id = ?
        """;

        try (
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, z.getIdTalla());
            stmt.setInt(2, z.getIdGenero());
            stmt.setInt(3, z.getIdTipo());
            stmt.setInt(4, z.getIdMarca());
            stmt.setString(5, z.getFoto());
            stmt.setInt(6, z.getId());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar zapatilla: " + e.getMessage());
            return false;
        }
    }
    
}
