package mundo;

/**
 * Representa una zapatilla dentro del sistema.
 * Cada zapatilla puede tener asignado un color, talla, tipo, género, marca e imagen.
 * Todos esos valores están almacenados como claves foráneas hacia tema_parametros.
 */
public class Zapatilla {

    // ID único de la zapatilla
    private int id;

    // Claves foráneas hacia tema_parametros
    private int idTalla;
    private int idGenero;
    private int idTipo;
    private int idMarca;

    // Ruta de la imagen asociada (opcional)
    private String foto;

    /**
     * Constructor vacío
     */
    public Zapatilla() {
    }

    /**
     * Constructor con todos los campos
     * 
     * @param id        ID de la zapatilla
     * @param idColor   ID del color (tema_parametros)
     * @param idTalla   ID de la talla (tema_parametros)
     * @param idGenero  ID del género (tema_parametros)
     * @param idTipo    ID del tipo (tema_parametros)
     * @param idMarca   ID de la marca (tema_parametros)
     * @param foto      Ruta de la imagen de la zapatilla
     */
    public Zapatilla(int id, int idTalla, int idGenero, int idTipo, int idMarca, String foto) {
        this.id = id;
        this.idTalla = idTalla;
        this.idGenero = idGenero;
        this.idTipo = idTipo;
        this.idMarca = idMarca;
        this.foto = foto;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(int idTalla) {
        this.idTalla = idTalla;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Devuelve una representación sencilla de la zapatilla (puede ajustarse)
     */
    @Override
    public String toString() {
        return "Zapatilla #" + id;
    }
}
