package mundo;

/**
 * Representa un tema en el sistema, como "Colores", "Marcas", "Tallas", etc.
 * Cada tema agrupa varios parámetros relacionados.
 */
public class Tema {

    // Identificador único del tema (clave primaria)
    private int id;

    // Nombre del tema (ejemplo: "Colores", "Marcas")
    private String nombre;

    /**
     * Constructor vacío (útil para frameworks y librerías de persistencia)
     */
    public Tema() {
    }

    /**
     * Constructor con todos los campos
     * 
     * @param id     Identificador del tema
     * @param nombre Nombre del tema
     */
    public Tema(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters

    /**
     * Obtiene el ID del tema
     * 
     * @return id del tema
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del tema
     * 
     * @param id nuevo ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del tema
     * 
     * @return nombre del tema
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del tema
     * 
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre como representación del objeto
     */
    @Override
    public String toString() {
        return nombre;
    }
}
