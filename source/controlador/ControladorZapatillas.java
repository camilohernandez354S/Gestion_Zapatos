package controlador;

import java.util.ArrayList;

import mundo.Parametro;
import mundo.Tema;
import mundo.Zapatilla;
import persistencia.ParametroDAO;
import persistencia.TemaDAO;
import persistencia.TemaParametroDAO;
import persistencia.ZapatillaDAO;

/**
 * Clase que actúa como intermediario entre la interfaz gráfica
 * y la capa de persistencia.
 */
public class ControladorZapatillas {

    private TemaDAO temaDAO;
    private TemaParametroDAO temaParametroDAO;
    private ZapatillaDAO zapatillaDAO;

    
    /**
     * Constructor: inicializa todos los DAOs usados por el sistema
     */
    public ControladorZapatillas() {
        temaDAO = new TemaDAO();
        new ParametroDAO();
        temaParametroDAO = new TemaParametroDAO();
        zapatillaDAO = new ZapatillaDAO();
    }

    /**
     * Obtiene todos los temas del sistema (como Color, Talla, etc.)
     * 
     * @return lista de temas
     */
    public ArrayList<Tema> obtenerTemas() {
        return temaDAO.obtenerTodosLosTemas();
    }

    /**
     * Obtiene los parámetros asociados a un tema (por ejemplo, colores de tema "Color")
     * 
     * @param idTema ID del tema
     * @return lista de parámetros del tema
     */
    public ArrayList<Parametro> obtenerParametrosPorTema(int idTema) {
        return temaParametroDAO.obtenerParametrosPorTema(idTema);
    }

    /**
     * Registra una nueva zapatilla en la base de datos
     * 
     * @param z Objeto Zapatilla con todos los datos
     * @return true si fue insertada correctamente
     */
    public boolean insertarZapatilla(Zapatilla z) {
        return zapatillaDAO.insertarZapatilla(z);
    }

    /**
     * Consulta todas las zapatillas registradas en el sistema
     * 
     * @return lista de zapatillas
     */
    public ArrayList<Zapatilla> obtenerZapatillas() {
        return zapatillaDAO.obtenerTodasLasZapatillas();
    }
    
    /**
     * Eliminar una zapatilla de la lista
     * 
     * @return lista de zapatillas
     */
    
    public boolean eliminarZapatilla(int id) {
    	return ZapatillaDAO.eliminarZapatilla(id);
    }
}
