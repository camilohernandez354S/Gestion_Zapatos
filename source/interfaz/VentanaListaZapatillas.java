package interfaz;

import controlador.ControladorZapatillas;
import controlador.ControladorParametro;
import mundo.Zapatilla;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Ventana que permite mostrar la lista de zapatillas registradas.
 * La ventana permite ordenar las zapatillas por diferentes parámetros como talla, marca o tipo.
 * También ofrece la opción de eliminar o editar las zapatillas directamente desde la tabla.
 */
public class VentanaListaZapatillas extends JPanel {

    private static final long serialVersionUID = 1L;

    private ControladorZapatillas controladorZapatillas;
    private ControladorParametro controladorParametro;
    
    private JTable tabla;
    private JLabel imagenLabel;
    private ArrayList<Zapatilla> zapatillasOriginal;

    /**
     * Constructor de la clase, inicializa la ventana de la lista de zapatillas y configura los componentes gráficos.
     */
    public VentanaListaZapatillas() {
        controladorZapatillas = new ControladorZapatillas();
        controladorParametro = new ControladorParametro();
        VentanaInsertarZapatilla formulario = new VentanaInsertarZapatilla(this);

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Título de la ventana
        JLabel titulo = new JLabel("Zapatillas Registradas", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(33, 150, 243));
        add(titulo, BorderLayout.NORTH);

        // Panel para los botones de ordenar
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(Color.WHITE);

        JButton btnOrdenarTalla = new JButton("Ordenar por Talla");
        JButton btnOrdenarMarca = new JButton("Ordenar por Marca");
        JButton btnOrdenarTipo = new JButton("Ordenar por Tipo");

        panelBotones.add(btnOrdenarTalla);
        panelBotones.add(btnOrdenarMarca);
        panelBotones.add(btnOrdenarTipo);

        // Definición de la tabla y su modelo
        String[] columnas = {"Numero", "ID real", "Talla", "Género", "Tipo", "Marca", "Foto", "Eliminar", "Editar"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7 || column == 8;
            }
        };
        tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setSelectionBackground(new Color(197, 225, 165));
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setGridColor(new Color(224, 224, 224));
        tabla.setShowGrid(true);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tabla.getTableHeader().setBackground(new Color(33, 150, 243));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setReorderingAllowed(false);

        // Establecimiento de colores de las filas
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : new Color(230, 240, 255));
                } else {
                    c.setBackground(new Color(197, 225, 165));
                }
                return c;
            }
        });

        // Crear el panel central que contiene los botones de ordenación y la tabla
        JScrollPane scroll = new JScrollPane(tabla);
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(panelBotones, BorderLayout.NORTH);
        panelCentral.add(scroll, BorderLayout.CENTER);

        // Añadir el panel central al centro de la ventana
        add(panelCentral, BorderLayout.CENTER);

        // Etiqueta para mostrar la imagen seleccionada de la tabla
        imagenLabel = new JLabel();
        imagenLabel.setPreferredSize(new Dimension(250, 250));
        imagenLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(imagenLabel, BorderLayout.EAST);

        // Acción de clic en una fila para mostrar la imagen de la zapatilla
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    String ruta = tabla.getValueAt(fila, 6).toString();
                    ImageIcon icono = new ImageIcon(ruta);
                    Image imagen = icono.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    imagenLabel.setIcon(new ImageIcon(imagen));
                }
            }
        });

        // Configuración de los botones "Eliminar" y "Editar" para cada fila
        tabla.getColumn("Eliminar").setCellRenderer(new BotonEliminar(controladorZapatillas, tabla, this));
        tabla.getColumn("Eliminar").setCellEditor(new BotonEliminar(controladorZapatillas, tabla, this));

        tabla.getColumn("Editar").setCellRenderer(new BotonEditar(controladorZapatillas, tabla, formulario));
        tabla.getColumn("Editar").setCellEditor(new BotonEditar(controladorZapatillas, tabla, formulario));

        // Cargar las zapatillas al iniciar la ventana
        cargarZapatillas();

        // Acciones de los botones de ordenar
        btnOrdenarTalla.addActionListener(e -> {
            ArrayList<Zapatilla> ordenadas = new ArrayList<>(zapatillasOriginal);
            ordenadas.sort(Comparator.comparing(a -> controladorParametro.obtenerParametroPorId(a.getIdTalla()).getNombre()));
            mostrarZapatillasEnTabla(ordenadas);
        });

        btnOrdenarMarca.addActionListener(e -> {
            ArrayList<Zapatilla> ordenadas = new ArrayList<>(zapatillasOriginal);
            ordenadas.sort(Comparator.comparing(a -> controladorParametro.obtenerParametroPorId(a.getIdMarca()).getNombre()));
            mostrarZapatillasEnTabla(ordenadas);
        });

        btnOrdenarTipo.addActionListener(e -> {
            ArrayList<Zapatilla> ordenadas = new ArrayList<>(zapatillasOriginal);
            ordenadas.sort(Comparator.comparing(a -> controladorParametro.obtenerParametroPorId(a.getIdTipo()).getNombre()));
            mostrarZapatillasEnTabla(ordenadas);
        });
    }

    /**
     * Carga las zapatillas en la tabla al iniciar la ventana.
     * Obtiene las zapatillas del controlador y las muestra en la tabla.
     */
    public void cargarZapatillas() {
        zapatillasOriginal = controladorZapatillas.obtenerZapatillas();
        mostrarZapatillasEnTabla(zapatillasOriginal);
    }

    /**
     * Muestra las zapatillas en la tabla.
     * Recibe una lista de zapatillas y las agrega a la tabla.
     *
     * @param lista Lista de zapatillas a mostrar en la tabla.
     */
    public void mostrarZapatillasEnTabla(ArrayList<Zapatilla> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < lista.size(); i++) {
            Zapatilla z = lista.get(i);
            String talla = controladorParametro.obtenerParametroPorId(z.getIdTalla()).getNombre();
            String genero = controladorParametro.obtenerParametroPorId(z.getIdGenero()).getNombre();
            String tipo = controladorParametro.obtenerParametroPorId(z.getIdTipo()).getNombre();
            String marca = controladorParametro.obtenerParametroPorId(z.getIdMarca()).getNombre();

            modelo.addRow(new Object[]{
                    (i + 1),             // Número de fila
                    z.getId(),           // ID real de la zapatilla
                    talla,               // Talla de la zapatilla
                    genero,              // Género de la zapatilla
                    tipo,                // Tipo de la zapatilla
                    marca,               // Marca de la zapatilla
                    z.getFoto(),        // Ruta de la imagen de la zapatilla
                    "Eliminar",          // Botón de eliminar
                    "Editar"             // Botón de editar
            });
        }
    }
}
