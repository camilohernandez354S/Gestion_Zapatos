package interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal con estilo dashboard moderno.
 * Contiene menú lateral y panel central que cambia según la opción.
 */
public class VentanaPrincipalDashboard extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private JPanel panelCentral;
    private VentanaInsertarZapatilla formulario;
    private VentanaListaZapatillas panelLista;

    public VentanaPrincipalDashboard() {
        setTitle("Gestión de Zapatillas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔵 Panel de encabezado
        JPanel encabezado = new JPanel();
        encabezado.setBackground(new Color(33, 150, 243));
        encabezado.setPreferredSize(new Dimension(900, 80));

        JLabel titulo = new JLabel(" Sistema de Gestión de Zapatillas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        encabezado.add(titulo);

        // 🟢 Panel lateral (menú)
        JPanel menuLateral = new JPanel();
        menuLateral.setLayout(new GridLayout(3, 1, 10, 10));
        menuLateral.setBackground(new Color(240, 240, 240));
        menuLateral.setPreferredSize(new Dimension(250, 0));
        menuLateral.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JButton btnRegistrar = crearBoton(" Registrar Zapatilla", new Color(76, 175, 80));
        JButton btnVer = crearBoton(" Ver Zapatillas", new Color(255, 152, 0));
        JButton btnSalir = crearBoton(" Salir", new Color(244, 67, 54));

        menuLateral.add(btnRegistrar);
        menuLateral.add(btnVer);
        menuLateral.add(btnSalir);

        // ⚪ Panel central (donde cambia el contenido)
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
     * Carga la vista de registrar zapatilla.
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
     * Carga la vista de ver zapatillas.
     */
    private void mostrarLista() {
        panelCentral.removeAll();
        panelLista.cargarZapatillas();
        panelCentral.add(panelLista, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipalDashboard());
    }
    
    private void mostrarLogoPrincipal() {
        panelCentral.removeAll();

        JLabel labelImagen = new JLabel();
        labelImagen.setHorizontalAlignment(JLabel.CENTER);
        labelImagen.setVerticalAlignment(JLabel.CENTER);

        // Ruta relativa al proyecto (asegúrate de que el archivo esté en /data/Imagenes/)
        ImageIcon icono = new ImageIcon("data\\Imagenes\\Principal2.jpg");

        // Escalar imagen si es necesario
        Image imagen = icono.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        labelImagen.setIcon(new ImageIcon(imagen));

        panelCentral.add(labelImagen, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

}
