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

        String sql = "SELECT * FROM tema";

        try (
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                Tema tema = new Tema(id, nombre);
                lista.add(tema);
            }

        } catch (SQLException e) {
            System.out.println(" Error al consultar los temas: " + e.getMessage());
        }

        return lista;
    }
    
    public boolean insertarTema(Tema tema) {
    	String sql = """
    			INSERT INTO tema (nombre)
    			VALUES (?)
    			""";
    	
    	try (
    		Connection conn = ConexionDB.obtenerConexion();
    		PreparedStatement stmt = conn.prepareStatement(sql);
    	){
    		stmt.setString(1, tema.getNombre());
    		
    		int filas = stmt.executeUpdate();
    		
    		return filas > 0;
    	} catch (SQLException e) {
    		System.out.println("Error al insertar un tema: " + e.getMessage());
    		return false;
    	}
    	
    }
    
    public static boolean eliminarTema(int id) {
    	String sql = "DELETE FROM tema WHERE id = ?";
    	
    	try (
    		Connection conn = ConexionDB.obtenerConexion();
    		PreparedStatement stmt = conn.prepareStatement(sql);
    	) {
    		stmt.setInt(1, id);
    		
    		int filas = stmt.executeUpdate();
    		return filas > 0;
    		
    	} catch (SQLException e) {
    		System.out.println("Error al eliminar el tema: " + e.getMessage());
    		return false;
    	}
    	
    }
    
    public static boolean actualizarTema(Tema t) {
    	String sql = """
    			UPDATE tema
    			SET nombre = ?
    			WHERE id = ?
    			""";
    	
    	try (
    		Connection conn = ConexionDB.obtenerConexion();
    		PreparedStatement stmt = conn.prepareStatement(sql);
    	) {
    		stmt.setString(1, t.getNombre());
    		stmt.setInt(2, t.getId());
    		
    		int filas = stmt.executeUpdate();
    		return filas > 0;
    	} catch (SQLException e) {
    		System.out.println("Error al actualizar el tema: " + e.getMessage());
    		return false;
    	}
    }
}

