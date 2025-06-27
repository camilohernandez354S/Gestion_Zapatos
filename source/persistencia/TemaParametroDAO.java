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
    
    public boolean insertarParametroPorTema(int idTema, int idParametro) {
    	String sql = "INSERT INTO tema_parametros (id_tema, id_parametro) VALUES (?, ?)";
    	
    	try (
    		Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
    	) {
    		stmt.setInt(1, idTema);
    		stmt.setInt(2, idParametro);
    		
    		int filas = stmt.executeUpdate();
    		return filas > 0;
    	} catch (SQLException e) {
    		System.out.println("Error al insertar el parámetro en el tema: " + e.getMessage());
    		return false;
    	}
    }
    
    public boolean eliminarParametroPorTema(int idTema, int idParametro) {
    	String sql = "DELETE FROM tema_parametros WHERE id_tema = ? AND id_parametro = ?";
    	
    	try (
    		Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
    	) {
    		stmt.setInt(1, idTema);
    		stmt.setInt(2, idParametro);
    		
    		int filas = stmt.executeUpdate();
    		return filas > 0;

    	} catch (SQLException e) {
    		System.out.println("Error al eliminar el parámetro del tema" + e.getMessage());
    		return false;
    	}
    }
    
    public boolean actualizarParametroPorTema(int idTema, int idParametroNuevo, int idParametroViejo) {
    	String sql = "UPDATE tema_parametros SET id_parametro = ? WHERE id_tema = ? AND id_parametro = ?";
    	
    	try (
    		Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
    	) {
    		stmt.setInt(1, idParametroNuevo);
    		stmt.setInt(2, idTema);
    		stmt.setInt(3, idParametroViejo);
    		
    		int filas = stmt.executeUpdate();
    		return filas > 0;
    	} catch (SQLException e) {
    		System.out.println("Error al actualizar el parámetro del tema: " + e.getMessage());
    		return false;
    	}
    	
    }
    
    public ArrayList<Object[]> obtenerRelacionesPorTema() {
        ArrayList<Object[]> relaciones = new ArrayList<>();
        
        // Consulta SQL para obtener las relaciones entre Tema y Parametro con los nombres
        String sql = "SELECT t.nombre AS tema_nombre, p.nombre AS parametro_nombre " +
                     "FROM tema t " +
                     "JOIN tema_parametros tp ON t.id = tp.id_tema " +
                     "JOIN parametros p ON tp.id_parametro = p.id";

        try (
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            // Recorrer el resultado de la consulta
            while (rs.next()) {
                String nombreTema = rs.getString("tema_nombre");
                String nombreParametro = rs.getString("parametro_nombre");
                relaciones.add(new Object[]{nombreTema, nombreParametro});  // Agregar la relación a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las relaciones entre Tema y Parámetro: " + e.getMessage());
        }

        return relaciones;  // Retornar la lista con todas las relaciones
    }
    
    public boolean eliminarRelacion(int idTema, int idParametro) {
        String sql = "DELETE FROM tema_parametro WHERE id_tema = ? AND id_parametro = ?";

        try (
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, idTema);
            stmt.setInt(2, idParametro);

            int filas = stmt.executeUpdate();
            return filas > 0;  // Si se eliminó al menos una fila, la operación fue exitosa
        } catch (SQLException e) {
            System.out.println("Error al eliminar la relación: " + e.getMessage());
            return false;
        }
    }
    
}
