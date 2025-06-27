package controlador;

import persistencia.TemaParametroDAO;
import mundo.Parametro;
import java.util.ArrayList;

/**
 * Controlador para la lógica de negocio de la relación entre temas y parámetros.
 * Este controlador maneja las operaciones de CRUD para la tabla intermedia 'tema_parametros'.
 */
public class ControladorTemaParametro {

    private TemaParametroDAO temaParametroDAO;

    public ControladorTemaParametro() {
        temaParametroDAO = new TemaParametroDAO();  // Instanciamos el DAO para interactuar con la base de datos
    }

    public ArrayList<Parametro> obtenerParametrosPorTema(int idTema) {
    	return temaParametroDAO.obtenerParametrosPorTema(idTema);
    }
    
    public boolean insertarParametroPorTema(int idTema, int idParametro) {
    	return temaParametroDAO.insertarParametroPorTema(idTema, idParametro);
    }
    
    public boolean eliminarParametroPorTema(int idTema, int idParametro) {
    	return temaParametroDAO.eliminarParametroPorTema(idTema, idParametro);
    }
    
    public boolean actualizarParametroPorTema(int idTema, int idParametroNuevo, int idParametroViejo) {
    	return temaParametroDAO.actualizarParametroPorTema(idTema, idParametroNuevo, idParametroViejo);
    }
    
    public boolean asociarTemaParametro(int idTema, int idParametro) {
    	return temaParametroDAO.insertarParametroPorTema(idTema, idParametro);
    }
    
    public ArrayList<Object[]> obtenerRelacionesPorTema() {
        return temaParametroDAO.obtenerRelacionesPorTema();
    }
    
    public boolean eliminarRelacion(int idTema, int idParametro) {
        // Llamamos al DAO para eliminar la relación
        return temaParametroDAO.eliminarRelacion(idTema, idParametro);
    }
}
