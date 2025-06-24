package mundo;

/**
 * Representa la relación entre un Tema y un Parametro.
 * Es una entidad intermedia usada para agrupar parámetros según su tema.
 * 
 * Ejemplo: Tema = "Color", Parametro = "Rojo"
 */
public class TemaParametro {

    private int id;
    private int idTema;
    private int idParametro;


    /**
     * Constructor con todos los campos
     * 
     * @param id           ID de la relación
     * @param idTema       ID del tema (clave foránea a tema)
     * @param idParametro  ID del parámetro (clave foránea a parametros)
     */
    public TemaParametro(int id, int idTema, int idParametro) {
        this.id = id;
        this.idTema = idTema;
        this.idParametro = idParametro;
    }

    // Getters y Setters

    /**
     * Obtiene el ID de la relación
     * 
     * @return id de la fila en tema_parametros
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la relación
     * 
     * @param id nuevo ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del tema
     * 
     * @return id del tema
     */
    public int getIdTema() {
        return idTema;
    }

    /**
     * Establece el ID del tema
     * 
     * @param idTema nuevo id del tema
     */
    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    /**
     * Obtiene el ID del parámetro
     * 
     * @return id del parámetro
     */
    public int getIdParametro() {
        return idParametro;
    }

    /**
     * Establece el ID del parámetro
     * 
     * @param idParametro nuevo id del parámetro
     */
    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }
}
