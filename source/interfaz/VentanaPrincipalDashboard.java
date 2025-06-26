package interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del sistema con un estilo de dashboard moderno.
 * Contiene un menú lateral con opciones para registrar, ver y salir del sistema,
 * y un panel central que cambia su contenido según la opción seleccionada en el menú.
 */
public class VentanaPrincipalDashboard extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panelCentral;
    private VentanaInsertarZapatilla formulario;
    private VentanaListaZapatillas panelLista;

    /**
     * Constructor de la ventana principal del sistema.
     * Inicializa los componentes gráficos y asigna las acciones a los botones del menú.
     */
    public VentanaPrincipalDashboard() {
        setTitle("Gestión de Zapatillas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de encabezado
        JPanel encabezado = new JPanel();
        encabezado.setBackground(new Color(33, 150, 243));
        encabezado.setPreferredSize(new Dimension(900, 80));

        JLabel titulo = new JLabel(" Sistema de Gestión de Zapatillas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        encabezado.add(titulo);

        // Panel lateral (menú)
        JPanel menuLateral = new JPanel();
        menuLateral.setLayout(new GridLayout(3, 1, 10, 10));
        menuLateral.setBackground(new Color(240, 240, 240));
        menuLateral.setPreferredSize(new Dimension(250, 0));
        menuLateral.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JButton btnRegistrar = crearBoton(" Registrar Zapatilla", new Color(76, 175, 80));
        JButton btnVer = crearBoton(" Ver Zapatillas", new Color(255, 152, 0));
        JButton btnSalir = crearBoton(" Salir", new Color(244, 67, 54));
        JButton btnTemaParametro = crearBoton(" Agregar Tema-Parametro", new Color(103, 58, 183));

        menuLateral.add(btnRegistrar);
        menuLateral.add(btnVer);
        menuLateral.add(btnSalir);
        menuLateral.add(btnTemaParametro);
        // Panel central 
        panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);

        panelLista = new VentanaListaZapatillas();

        VentanaInsertarZapatilla formulario = new VentanaInsertarZapatilla(panelLista);
        formulario.prepararParaAgregar(); // 🔁 CORRECCIÓN: limpia el formulario al iniciar

        // Acciones de los botones
        btnRegistrar.addActionListener(e -> mostrarRegistrar());
        btnVer.addActionListener(e -> mostrarLista());
        btnSalir.addActionListener(e -> System.exit(0));

        // Añadir todo al frame
        add(encabezado, BorderLayout.NORTH);
        add(menuLateral, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);

        mostrarLogoPrincipal();

        setVisible(true);
    }

    /**
     * Crea un botón estilizado con fondo y texto blanco.
     * 
     * @param texto      El texto que se mostrará en el botón.
     * @param colorFondo El color de fondo del botón.
     * @return El botón creado con estilo.
     */
    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return boton;
    }

    /**
     * Carga la vista de registrar una nueva zapatilla.
     * Se reemplaza el contenido del panel central con el formulario para agregar zapatillas.
     */
    private void mostrarRegistrar() {
        panelCentral.removeAll();
        formulario = new VentanaInsertarZapatilla(panelLista);
        formulario.prepararParaAgregar();
        panelCentral.add(formulario, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    /**
     * Carga la vista de ver las zapatillas registradas.
     * Se reemplaza el contenido del panel central con la lista de zapatillas.
     */
    private void mostrarLista() {
        panelCentral.removeAll();
        panelLista.cargarZapatillas();
        panelCentral.add(panelLista, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    /**
     * Método principal para ejecutar la ventana principal del sistema.
     * Llama al constructor para mostrar la interfaz gráfica.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipalDashboard());
    }

    /**
     * Muestra el logo principal en el panel central cuando no se ha seleccionado ninguna opción.
     * Se establece una imagen en el centro de la ventana.
     */
    private void mostrarLogoPrincipal() {
        panelCentral.removeAll();

        JLabel labelImagen = new JLabel();
        labelImagen.setHorizontalAlignment(JLabel.CENTER);
        labelImagen.setVerticalAlignment(JLabel.CENTER);

        // Ruta relativa al proyecto (asegúrate de que el archivo esté en /data/Imagenes/)

        ImageIcon icono = new ImageIcon("data\\Imagenes\\LogoPrincipal.png");

        Image imagen = icono.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        labelImagen.setIcon(new ImageIcon(imagen));

        panelCentral.add(labelImagen, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

}
