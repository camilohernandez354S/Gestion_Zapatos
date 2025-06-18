package mundo;

/**
 * Representa un parámetro en el sistema, como un color, marca, talla, etc.
 * Este parámetro está vinculado a un tema.
 * Ejemplo: nombre = "Rojo", "Nike", etc.
 */
public class Parametro {

    // Identificador único del parámetro (clave primaria)
    private int id;

    // Nombre del parámetro (ejemplo: "Rojo", "Nike", etc.)
    private String nombre;

    /**
     * Constructor vacío (recomendado para usar con JDBC)
     */
    public Parametro() {
    }

    /**
     * Constructor con todos los campos
     * 
     * @param id     Identificador del parámetro
     * @param nombre Nombre del parámetro
     */
    public Parametro(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Métodos Getters y Setters

    /**
     * Obtiene el ID del parámetro
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del parámetro
     * 
     * @param id nuevo ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del parámetro
     * 
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del parámetro
     * 
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre como representación del objeto (útil para combos)
     */
    @Override
    public String toString() {
        return nombre;
    }
}
