package interfaz;

import controlador.ControladorTemaParametro;
import controlador.ControladorTema;
import controlador.ControladorParametro;
import mundo.Tema;
import mundo.Parametro;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaInsertarTemaParametro extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTable tablaTemas;
    private JTable tablaParametros;
<<<<<<< HEAD
    private JTable tablaTemaParametros;  // Tabla para mostrar las relaciones
=======
    private JTable tablaTemaParametros;
>>>>>>> feature/CRUDT-P

    private ControladorTemaParametro controladorTemaParametro;
    private ControladorTema controladorTema;
    private ControladorParametro controladorParametro;
    private ArrayList<Tema> temas;
    private ArrayList<Parametro> parametros;

    public VentanaInsertarTemaParametro() {
        controladorTemaParametro = new ControladorTemaParametro();
        controladorTema = new ControladorTema();
        controladorParametro = new ControladorParametro();
        setLayout(new BorderLayout());
<<<<<<< HEAD
        setBackground(new Color(245, 245, 245));

        // Panel de Título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new GridLayout(1, 3, 20, 20));  // Aumento del espaciado horizontal entre títulos
        panelTitulo.setBackground(new Color(33, 150, 243));
=======
        setBackground(Color.WHITE);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new GridLayout(1, 3, 10, 10));
>>>>>>> feature/CRUDT-P

        JLabel lblTemas = new JLabel("Temas", JLabel.CENTER);
        JLabel lblParametros = new JLabel("Parámetros", JLabel.CENTER);
        JLabel lblRelaciones = new JLabel("Tema-Parametro", JLabel.CENTER);

<<<<<<< HEAD
        lblTemas.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblParametros.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblRelaciones.setFont(new Font("Segoe UI", Font.BOLD, 18));

        lblTemas.setForeground(Color.WHITE);
        lblParametros.setForeground(Color.WHITE);
        lblRelaciones.setForeground(Color.WHITE);

=======
>>>>>>> feature/CRUDT-P
        panelTitulo.add(lblTemas);
        panelTitulo.add(lblParametros);
        panelTitulo.add(lblRelaciones);
        add(panelTitulo, BorderLayout.NORTH);

<<<<<<< HEAD
        // Panel de Tablas
        JPanel panelTablas = new JPanel();
        panelTablas.setLayout(new GridLayout(1, 3, 30, 30));  // Aumento del espaciado horizontal y vertical

        // Panel de Temas
        tablaTemas = new JTable();
        customizeTable(tablaTemas);
        JScrollPane scrollTemas = new JScrollPane(tablaTemas);
        panelTablas.add(scrollTemas);

        // Panel de Parámetros
        tablaParametros = new JTable();
        customizeTable(tablaParametros);
        JScrollPane scrollParametros = new JScrollPane(tablaParametros);
        panelTablas.add(scrollParametros);

        // Panel de Tema-Parametro (Para mostrar las relaciones)
        tablaTemaParametros = new JTable();
        customizeTable(tablaTemaParametros);
=======
        JPanel panelTablas = new JPanel();
        panelTablas.setLayout(new GridLayout(1, 3, 10, 10));

        tablaTemas = new JTable();
        JScrollPane scrollTemas = new JScrollPane(tablaTemas);
        panelTablas.add(scrollTemas);

        tablaParametros = new JTable();
        JScrollPane scrollParametros = new JScrollPane(tablaParametros);
        panelTablas.add(scrollParametros);

        tablaTemaParametros = new JTable();
>>>>>>> feature/CRUDT-P
        JScrollPane scrollTemaParametros = new JScrollPane(tablaTemaParametros);
        panelTablas.add(scrollTemaParametros);

        add(panelTablas, BorderLayout.CENTER);

<<<<<<< HEAD
        // Panel de Botones (Lado Derecho)
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.setBackground(new Color(245, 245, 245));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));  // Aumento del espaciado superior e inferior

        JButton btnAgregarTema = crearBoton("Agregar Tema", new Color(76, 175, 80));
        JButton btnAgregarParametro = crearBoton("Agregar Parámetro", new Color(255, 152, 0));
        JButton btnAgregarTemaParametro = crearBoton("Agregar Tema-Parametro", new Color(103, 58, 183));
        JButton btnEliminarRelacion = crearBoton("Eliminar Relación", new Color(244, 67, 54));

        // Ajuste de tamaños de botones
        btnAgregarTema.setPreferredSize(new Dimension(250, 60));  // Botones más grandes
        btnAgregarParametro.setPreferredSize(new Dimension(250, 60));
        btnAgregarTemaParametro.setPreferredSize(new Dimension(250, 60));
        btnEliminarRelacion.setPreferredSize(new Dimension(250, 60));

        panelBotones.add(btnAgregarTema);
        panelBotones.add(Box.createVerticalStrut(60));  // Espaciado entre los botones
        panelBotones.add(btnAgregarParametro);
        panelBotones.add(Box.createVerticalStrut(60));  // Espaciado entre los botones
        panelBotones.add(btnAgregarTemaParametro);
        panelBotones.add(Box.createVerticalStrut(60));  // Espaciado entre los botones
=======
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 5, 5));

        JButton btnAgregarTema = new JButton("Agregar Tema");
        JButton btnAgregarParametro = new JButton("Agregar Parámetro");
        JButton btnAgregarTemaParametro = new JButton("Agregar Tema-Parametro");
        JButton btnEliminarRelacion = new JButton("Eliminar Relación");

        panelBotones.add(btnAgregarTema);
        panelBotones.add(btnAgregarParametro);
        panelBotones.add(btnAgregarTemaParametro);
>>>>>>> feature/CRUDT-P
        panelBotones.add(btnEliminarRelacion);

        add(panelBotones, BorderLayout.EAST);

<<<<<<< HEAD
        // Acciones de los botones
=======
>>>>>>> feature/CRUDT-P
        btnAgregarTema.addActionListener(e -> agregarTema());
        btnAgregarParametro.addActionListener(e -> agregarParametro());
        btnAgregarTemaParametro.addActionListener(e -> asociarTemaParametro());
        btnEliminarRelacion.addActionListener(e -> eliminarRelacion());

        cargarDatos();
    }

<<<<<<< HEAD
    private void customizeTable(JTable table) {
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(0, 123, 255));
        table.setSelectionForeground(Color.BLACK);
        table.setDefaultEditor(Object.class, null);  // Deshabilitar edición directa en la tabla

        // Cambiar los bordes de la tabla para hacerlo más moderno
        table.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Alternar colores de las filas
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer());
    }

    private class MyTableCellRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            // Alterna el color de fondo de las filas
            if (row % 2 == 0) {
                c.setBackground(new Color(240, 240, 240));  // Color gris claro para las filas pares
            } else {
                c.setBackground(Color.WHITE);  // Color blanco para las filas impares
            }
            return c;
        }
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));  // Espaciado interior de los botones
        boton.setAlignmentX(CENTER_ALIGNMENT);  // Alineación al centro del contenedor
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Cambiar el cursor cuando el mouse pasa sobre el botón
        return boton;
    }

=======
>>>>>>> feature/CRUDT-P
    public void cargarDatos() {
        temas = controladorTema.obtenerTodosLosTemas();
        parametros = controladorParametro.obtenerTodosLosParametros();

        mostrarTemasEnTabla(temas);
        mostrarParametrosEnTabla(parametros);

<<<<<<< HEAD
        // Cargar las relaciones entre tema y parámetro
        mostrarRelacionesEnTabla();
=======
        // Llamando a BotonEditar con la instancia actual
        
        tablaTemas.getColumn("Eliminar").setCellRenderer(new BotonEliminar(controladorTema, tablaTemas, "Tema", null, this));
        tablaTemas.getColumn("Eliminar").setCellEditor(new BotonEliminar(controladorTema, tablaTemas, "Tema", null, this));

        tablaParametros.getColumn("Eliminar").setCellRenderer(new BotonEliminar(controladorParametro, tablaParametros, "Parámetro", null, this));
        tablaParametros.getColumn("Eliminar").setCellEditor(new BotonEliminar(controladorParametro, tablaParametros, "Parámetro", null, this));
        
        tablaTemas.getColumn("Editar").setCellRenderer(new BotonEditar(controladorTema, tablaTemas, "Tema", null, this));
        tablaTemas.getColumn("Editar").setCellEditor(new BotonEditar(controladorTema, tablaTemas, "Tema", null, this));

        tablaParametros.getColumn("Editar").setCellRenderer(new BotonEditar(controladorParametro, tablaParametros, "Parámetro", null, this));
        tablaParametros.getColumn("Editar").setCellEditor(new BotonEditar(controladorParametro, tablaParametros, "Parámetro", null, this));
>>>>>>> feature/CRUDT-P
    }

    private void mostrarTemasEnTabla(ArrayList<Tema> temas) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
<<<<<<< HEAD

        for (Tema tema : temas) {
            modelo.addRow(new Object[]{tema.getId(), tema.getNombre()});
=======
        modelo.addColumn("Eliminar");
        modelo.addColumn("Editar");

        for (Tema tema : temas) {
            modelo.addRow(new Object[] {tema.getId(), tema.getNombre()});
>>>>>>> feature/CRUDT-P
        }

        tablaTemas.setModel(modelo);
    }

    private void mostrarParametrosEnTabla(ArrayList<Parametro> parametros) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
<<<<<<< HEAD

        for (Parametro parametro : parametros) {
            modelo.addRow(new Object[]{parametro.getId(), parametro.getNombre()});
=======
        modelo.addColumn("Eliminar");
        modelo.addColumn("Editar");

        for (Parametro parametro : parametros) {
            modelo.addRow(new Object[] {parametro.getId(), parametro.getNombre()});
>>>>>>> feature/CRUDT-P
        }

        tablaParametros.setModel(modelo);
    }

<<<<<<< HEAD
    // Mostrar las relaciones Tema-Parametro en la tabla
    private void mostrarRelacionesEnTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Tema");
        modelo.addColumn("ID Parámetro");

        // Obtén las relaciones desde el controlador
        ArrayList<Object[]> relaciones = controladorTemaParametro.obtenerRelacionesPorTema();

        for (Object[] relacion : relaciones) {
            modelo.addRow(relacion);
        }

        tablaTemaParametros.setModel(modelo);
    }

=======
>>>>>>> feature/CRUDT-P
    public void agregarTema() {
        String nombreTema = JOptionPane.showInputDialog("Ingrese el nombre del tema");

        if (nombreTema != null && !nombreTema.trim().isEmpty()) {
            if (nombreTema.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "El nombre del tema no puede contener números");
            } else {
                Tema nuevoTema = new Tema(0, nombreTema);
                boolean exito = controladorTema.insertarTema(nuevoTema);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Tema agregado correctamente");
<<<<<<< HEAD
                    cargarDatos();  // Recargar los datos en la tabla
=======
                    cargarDatos();
>>>>>>> feature/CRUDT-P
                } else {
                    JOptionPane.showMessageDialog(this, "Error al agregar el tema");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "El nombre del tema no puede estar vacío");
        }
    }

    public void agregarParametro() {
        String nombreParametro = JOptionPane.showInputDialog("Ingrese el nombre del parámetro");

        if (nombreParametro != null && !nombreParametro.trim().isEmpty()) {
            Parametro nuevoParametro = new Parametro(0, nombreParametro);
            boolean exito = controladorParametro.insertarParametro(nuevoParametro);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Parámetro agregado correctamente");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el parámetro");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El nombre del parámetro no puede estar vacío");
        }
    }

    public void asociarTemaParametro() {
        int filaTema = tablaTemas.getSelectedRow();
        int filaParametro = tablaParametros.getSelectedRow();

        if (filaTema != -1 && filaParametro != -1) {
            int idTema = (int) tablaTemas.getValueAt(filaTema, 0);
            int idParametro = (int) tablaParametros.getValueAt(filaParametro, 0);

            boolean exito = controladorTemaParametro.asociarTemaParametro(idTema, idParametro);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Tema y parámetro asociados correctamente");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al asociar el tema y parámetro");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un tema y un parámetro");
        }
    }

<<<<<<< HEAD
    public void eliminarRelacion() { 
        int filaRelacion = tablaTemaParametros.getSelectedRow();

        if (filaRelacion != -1) {
            int idParametro = (int) tablaTemaParametros.getValueAt(filaRelacion, 1);

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar esta relación?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
=======
    public void eliminarRelacion() {
        int filaRelacion = tablaTemaParametros.getSelectedRow();

        if (filaRelacion != -1) {
            int idTema = (int) tablaTemaParametros.getValueAt(filaRelacion, 0);
            int idParametro = (int) tablaTemaParametros.getValueAt(filaRelacion, 1);

            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar esta relación", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
>>>>>>> feature/CRUDT-P

            if (confirmacion == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Relación eliminada correctamente");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la relación");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una relación");
        }
    }
}
