package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mundo.Parametro;

/**
 * Clase encargada de acceder a la tabla 'parametros' en la base de datos.
 * Contiene métodos para consultar todos los parámetros registrados.
 */
public class ParametroDAO {

    /**
     * Obtiene todos los parámetros registrados en la tabla 'parametros'
     * 
     * @return una lista de objetos Parametro
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
            System.out.println(" Error al consultar los parámetros: " + e.getMessage());
        }

        return lista;
    }
    
    
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
