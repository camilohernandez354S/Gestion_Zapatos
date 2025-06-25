package mundo;

/**
 * La clase {@code Parametro} representa un parámetro en el sistema.
 * Los parámetros están vinculados a un tema y se utilizan para almacenar valores como 
 * colores, marcas, tallas, etc.
 * <p>
 * Ejemplos de parámetros pueden ser valores como "Rojo", "Nike", etc.
 * </p>
 */
public class Parametro {

    // Id del parámetro
    private int id;

    // Nombre del parámetro ("Rojo", "Nike")
    private String nombre;


    /**
     * Constructor de la clase {@code Parametro} con parámetros.
     * 
     * @param id     El identificador único del parámetro.
     * @param nombre El nombre del parámetro, como "Rojo", "Nike".
     */
    public Parametro(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Métodos Getters y Setters

    /**
     * Obtiene el ID del parámetro.
     * 
     * @return El ID del parámetro.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del parámetro.
     * 
     * @param id El nuevo ID del parámetro.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del parámetro.
     * 
     * @return El nombre del parámetro, como "Rojo", "Nike"
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del parámetro.
     * 
     * @param nombre El nuevo nombre del parámetro.
     */
    public void setNombre(String nombre) {
        
    	if (nombre != null && !nombre.trim().isEmpty()) {
    		this.nombre = nombre;
    	} else {
    		throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
    	}
    }

    /**
     * Representa el parámetro como una cadena (utilizado en controles gráficos como combo box).
     * 
     * @return El nombre del parámetro.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
