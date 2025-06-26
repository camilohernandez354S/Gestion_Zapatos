package controlador;

import java.util.ArrayList;


import mundo.Zapatilla;
import persistencia.ZapatillaDAO;

/**
 * Clase ControladorZapatillas que actúa como intermediario entre la interfaz gráfica y la capa de persistencia.
 * Proporciona los métodos necesarios para interactuar con las clases de la capa de persistencia, obteniendo, insertando, 
 * actualizando y eliminando datos relacionados con las zapatillas, los temas y los parámetros.
 */
public class ControladorZapatillas {

    private ZapatillaDAO zapatillaDAO;

    /**
     * Constructor de la clase ControladorZapatillas. Inicializa los DAOs utilizados en el sistema.
     * El controlador se encarga de interactuar con los DAOs y facilita las operaciones sobre los datos.
     */
    public ControladorZapatillas() {
        zapatillaDAO = new ZapatillaDAO();
    }

    /**
     * Registra una nueva zapatilla en la base de datos.
     * Este método recibe un objeto Zapatilla con todos los datos necesarios y lo inserta en la base de datos
     * utilizando el DAO correspondiente.
     * 
     * @param z Objeto Zapatilla que contiene todos los datos necesarios para su inserción.
     * @return true si la zapatilla fue insertada correctamente, false si hubo un error.
     */
    public boolean insertarZapatilla(Zapatilla z) {
        return zapatillaDAO.insertarZapatilla(z);
    }

    /**
     * Consulta todas las zapatillas registradas en el sistema.
     * Utiliza el DAO de zapatillas para obtener la lista de todas las zapatillas almacenadas en la base de datos.
     * 
     * @return Lista de todas las zapatillas registradas en la base de datos.
     */
    public ArrayList<Zapatilla> obtenerZapatillas() {
        return zapatillaDAO.obtenerTodasLasZapatillas();
    }

    /**
     * Elimina una zapatilla de la base de datos, identificada por su ID.
     * Utiliza el DAO de zapatillas para realizar la operación de eliminación.
     * 
     * @param id ID de la zapatilla que se desea eliminar.
     * @return true si la zapatilla fue eliminada correctamente, false si hubo un error.
     */
    public boolean eliminarZapatilla(int id) {
        return ZapatillaDAO.eliminarZapatilla(id);
    }

    /**
     * Actualiza los datos de una zapatilla existente en la base de datos.
     * Recibe un objeto Zapatilla con los nuevos datos y lo actualiza en la base de datos.
     * 
     * @param z Objeto Zapatilla con los nuevos datos que se desean actualizar.
     * @return true si la actualización fue exitosa, false si hubo un error.
     */
    public boolean actualizarZapatilla(Zapatilla z) {
        return ZapatillaDAO.actualizarZapatilla(z);
    }


    /**
     * Busca y devuelve una zapatilla por su ID.
     * Este método recorre todas las zapatillas y devuelve aquella que coincida con el ID proporcionado.
     * 
     * @param id ID de la zapatilla que se desea buscar.
     * @return El objeto Zapatilla correspondiente al ID, o null si no se encuentra.
     */
    public Zapatilla obtenerZapatillaPorId(int id) {
        for (Zapatilla z : obtenerZapatillas()) {
            if (z.getId() == id) {  // Si el ID de la zapatilla coincide con el proporcionado.
                return z;  // Devuelve la zapatilla encontrada.
            }
        }
        return null;
    }

}
