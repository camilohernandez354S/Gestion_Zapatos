package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mundo.Parametro;

/**
 * Clase encargada de acceder a la tabla 'parametros' en la base de datos.
 * Esta clase contiene métodos para consultar los parámetros registrados en la base de datos,
 * como la obtención de todos los parámetros y la obtención de un parámetro específico por su ID.
 */
public class ParametroDAO {

    /**
     * Obtiene todos los parámetros registrados en la tabla 'parametros' de la base de datos.
     * Este método ejecuta una consulta SQL para obtener todos los registros de la tabla 'parametros',
     * y luego crea una lista de objetos {@link Parametro} con la información recuperada.
     * 
     * @return Una lista de objetos {@link Parametro}, representando todos los parámetros registrados.
     */
    public ArrayList<Parametro> obtenerTodosLosParametros() {
        ArrayList<Parametro> lista = new ArrayList<>();

        String sql = "SELECT * FROM parametros";

        try (
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                Parametro parametro = new Parametro(id, nombre);
                lista.add(parametro);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar los parámetros: " + e.getMessage());
        }

        return lista;
    }
    
    public boolean insertarParametro(Parametro parametro) {
    	String sql = """
    			INSERT INTO parametros (nombre)
    			VALUES (?)
    			""";
    	try(
    		Connection conn = ConexionDB.obtenerConexion();
    		PreparedStatement stmt = conn.prepareStatement(sql);
    	) {
    		stmt.setString(1, parametro.getNombre());
    		
    		int filas = stmt.executeUpdate();
    		
    		return filas > 0;
    		
    	} catch (SQLException e) {
    		System.out.println("Error al insertar un parametro");
    		return false;
    	}
    }
    
    public boolean eliminarParametro(int id) {
    	String sql = "DELETE FROM parametros WHERE id = ?";
    	
    	try (
    		Connection conn = ConexionDB.obtenerConexion();
        	PreparedStatement stmt = conn.prepareStatement(sql);
    	) {
    		stmt.setInt(1, id);
    		
    		int filas = stmt.executeUpdate();
    		return filas > 0;
    		
    	} catch(SQLException e) {
    		System.out.println("Error al eliminar un parametro");
    		return false;
    	}
    	
    }
    
    public boolean actualizarParametro(Parametro p) {
    	String sql = """
    			UPDATE parametros
    			SET nombre = ?
    			WHERE id = ?
    			""";
    	
    	try (
    		Connection conn = ConexionDB.obtenerConexion();
        	PreparedStatement stmt = conn.prepareStatement(sql);
    	) {
    		stmt.setString(1, p.getNombre());
    		stmt.setInt(2, p.getId());
    		
    		int filas = stmt.executeUpdate();
    		return filas > 0;
    	} catch (SQLException e) {
    		System.out.println("Error al actualizar el parametro: " + e.getMessage());
    		return false;
    	}
    }
    
    /**
     * Obtiene un parámetro de la base de datos por su ID.
     * Este método ejecuta una consulta SQL con un parámetro dinámico (ID) para obtener el parámetro correspondiente.
     * 
     * @param id El ID del parámetro a recuperar de la base de datos.
     * @return Un objeto {@link Parametro} con la información del parámetro correspondiente al ID.
     */
    public Parametro obtenerParametroPorId(int id) {
        Parametro parametro = null;
        String sql = "SELECT * FROM parametros WHERE id = ?";

        try (
            Connection conn = ConexionDB.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int idParametro = rs.getInt("id");
                String nombre = rs.getString("nombre");
                parametro = new Parametro(idParametro, nombre);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener parámetro por ID: " + e.getMessage());
        }

        return parametro;
    }
}
