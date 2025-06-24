package mundo;

/**
 * La clase {@code Zapatilla} representa una zapatilla dentro del sistema.
 * Cada zapatilla puede tener asignados varios atributos, como el color, talla, tipo, género, marca, y la imagen.
 * Estos atributos son almacenados como claves foráneas hacia la tabla {@code tema_parametros}.
 */
public class Zapatilla {

    // ID único de la zapatilla
    private int id;

    // Claves foráneas hacia tema_parametros
    private int idTalla;
    private int idGenero;
    private int idTipo;
    private int idMarca;

    // Ruta de la imagen asociada
    private String foto;


    /**
     * Constructor con todos los campos necesarios para crear una nueva zapatilla.
     * Este constructor es utilizado cuando se crean nuevas zapatillas en el sistema.
     * 
     * @param id        ID de la zapatilla
     * @param idTalla   ID de la talla asociada, clave foránea hacia la tabla {@code tema_parametros}
     * @param idGenero  ID del género asociado, clave foránea hacia la tabla {@code tema_parametros}
     * @param idTipo    ID del tipo de la zapatilla, clave foránea hacia la tabla {@code tema_parametros}
     * @param idMarca   ID de la marca asociada, clave foránea hacia la tabla {@code tema_parametros}
     * @param foto      Ruta de la imagen asociada a la zapatilla
     */
    public Zapatilla(int id, int idTalla, int idGenero, int idTipo, int idMarca, String foto) {
        this.id = id;
        this.idTalla = idTalla;
        this.idGenero = idGenero;
        this.idTipo = idTipo;
        this.idMarca = idMarca;
        this.foto = foto;
    }

    // Métodos Getters y Setters

    /**
     * Obtiene el ID de la zapatilla.
     * 
     * @return El ID de la zapatilla.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la zapatilla.
     * 
     * @param id El nuevo ID de la zapatilla.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID de la talla asociada a la zapatilla.
     * 
     * @return El ID de la talla asociada.
     */
    public int getIdTalla() {
        return idTalla;
    }

    /**
     * Establece el ID de la talla asociada a la zapatilla.
     * 
     * @param idTalla El nuevo ID de la talla.
     */
    public void setIdTalla(int idTalla) {
        this.idTalla = idTalla;
    }

    /**
     * Obtiene el ID del género asociado a la zapatilla.
     * 
     * @return El ID del género.
     */
    public int getIdGenero() {
        return idGenero;
    }

    /**
     * Establece el ID del género asociado a la zapatilla.
     * 
     * @param idGenero El nuevo ID del género.
     */
    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    /**
     * Obtiene el ID del tipo de la zapatilla.
     * 
     * @return El ID del tipo de la zapatilla.
     */
    public int getIdTipo() {
        return idTipo;
    }

    /**
     * Establece el ID del tipo de la zapatilla.
     * 
     * @param idTipo El nuevo ID del tipo.
     */
    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * Obtiene el ID de la marca asociada a la zapatilla.
     * 
     * @return El ID de la marca asociada.
     */
    public int getIdMarca() {
        return idMarca;
    }

    /**
     * Establece el ID de la marca asociada a la zapatilla.
     * 
     * @param idMarca El nuevo ID de la marca.
     */
    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    /**
     * Obtiene la ruta de la imagen asociada a la zapatilla.
     * 
     * @return La ruta de la imagen.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Establece la ruta de la imagen asociada a la zapatilla.
     * 
     * @param foto La nueva ruta de la imagen.
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Devuelve una representación sencilla de la zapatilla.
     * Esta representación es utilizada en la mayoría de los casos para mostrar 
     * información breve de la zapatilla, como en listas o tablas.
     * 
     * @return Una cadena representando el ID de la zapatilla.
     */
    @Override
    public String toString() {
        return "Zapatilla #" + id;
    }
}
