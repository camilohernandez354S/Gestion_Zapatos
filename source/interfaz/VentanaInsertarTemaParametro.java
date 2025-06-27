package interfaz;

import controlador.ControladorTemaParametro;
import controlador.ControladorTema;
import controlador.ControladorParametro;
import mundo.Tema;
import mundo.Parametro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaInsertarTemaParametro extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTable tablaTemas;
    private JTable tablaParametros;
    private JTable tablaTemaParametros;

    private ControladorTemaParametro controladorTemaParametro;
    private ControladorTema controladorTema;
    private ControladorParametro controladorParametro;
    private ArrayList<Tema> temas;
    private ArrayList<Parametro> parametros;
    
        public VentanaInsertarTemaParametro() {
            controladorTemaParametro = new ControladorTemaParametro();
            controladorTema = new ControladorTema();
            controladorParametro = new ControladorParametro();
            
            // Usar BorderLayout para mejor disposición
            setLayout(new BorderLayout());
            setBackground(new Color(245, 245, 245)); // Fondo claro

            // Panel de título para cada columna
            JPanel panelTitulo = new JPanel();
            panelTitulo.setLayout(new GridLayout(1, 3, 10, 10));

            JLabel lblTemas = new JLabel("Gestión de Temas", JLabel.CENTER);
            JLabel lblParametros = new JLabel("Gestión de Parámetros", JLabel.CENTER);
            JLabel lblRelaciones = new JLabel("Relaciones Tema-Parametro", JLabel.CENTER);

            panelTitulo.add(lblTemas);
            panelTitulo.add(lblParametros);
            panelTitulo.add(lblRelaciones);
            add(panelTitulo, BorderLayout.NORTH);

            // Panel para las tablas
            JPanel panelTablas = new JPanel();
            panelTablas.setLayout(new GridLayout(1, 3, 10, 10));

            tablaTemas = new JTable();
            JScrollPane scrollTemas = new JScrollPane(tablaTemas);
            panelTablas.add(scrollTemas);

            tablaParametros = new JTable();
            JScrollPane scrollParametros = new JScrollPane(tablaParametros);
            panelTablas.add(scrollParametros);

            tablaTemaParametros = new JTable();
            JScrollPane scrollTemaParametros = new JScrollPane(tablaTemaParametros);
            panelTablas.add(scrollTemaParametros);

            add(panelTablas, BorderLayout.CENTER);

            // Panel lateral para los botones
            JPanel panelBotones = new JPanel();
            panelBotones.setLayout(new GridLayout(5, 1, 5, 5));

            JButton btnAgregarTema = new JButton("Agregar Tema");
            JButton btnAgregarParametro = new JButton("Agregar Parámetro");
            JButton btnAgregarTemaParametro = new JButton("Agregar Tema-Parametro");
            JButton btnEliminar = new JButton("Eliminar");

            // Botones organizados en el panel lateral
            panelBotones.add(btnAgregarTema);
            panelBotones.add(btnAgregarParametro);
            panelBotones.add(btnAgregarTemaParametro);
            panelBotones.add(btnEliminar);

            add(panelBotones, BorderLayout.EAST);

         // Acción para el botón de eliminar
            btnEliminar.addActionListener(e -> {
                // Preguntar qué tipo de elemento desea eliminar (Tema, Parámetro, o Relación)
                String[] options = {"Tema", "Parámetro", "Relación"};
                String seleccion = (String) JOptionPane.showInputDialog(this, 
                    "¿Qué deseas eliminar?", 
                    "Seleccionar tipo", 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, options, options[0]);

                if (seleccion != null) {
                    // Si se seleccionó uno de los tipos, se pide confirmación
                    int confirmacion = JOptionPane.showConfirmDialog(this, 
                        "¿Estás seguro de eliminar el " + seleccion + "?", 
                        "Confirmación de eliminación", 
                        JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        // En función de la selección, llamar al método de eliminación correspondiente
                        if ("Tema".equals(seleccion)) {
                            eliminarTema();
                        } else if ("Parámetro".equals(seleccion)) {
                            eliminarParametro();
                        } else if ("Relación".equals(seleccion)) {
                            eliminarRelacion();
                        }
                    }
                }
            });


            // Acción para los botones de agregar
            btnAgregarTema.addActionListener(e -> agregarTema());
            btnAgregarParametro.addActionListener(e -> agregarParametro());
            btnAgregarTemaParametro.addActionListener(e -> asociarTemaParametro());

            cargarDatos(); // Cargar los datos desde la base de datos
        }

        // Método para eliminar los diferentes tipos de objetos
     // Método para eliminar un Tema
        private void eliminarTema() {
            int filaTema = tablaTemas.getSelectedRow();
            if (filaTema != -1) {
                int idTema = (int) tablaTemas.getValueAt(filaTema, 0);
                int confirmacion = JOptionPane.showConfirmDialog(this, 
                    "¿Estás seguro de eliminar el tema seleccionado?", 
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    boolean exito = controladorTema.eliminarTema(idTema);
                    if (exito) {
                        JOptionPane.showMessageDialog(this, "Tema eliminado correctamente");
                        cargarDatos();  // Recargar los datos
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar el tema");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un tema para eliminar");
            }
        }


    public void cargarDatos() {
        temas = controladorTema.obtenerTodosLosTemas();
        parametros = controladorParametro.obtenerTodosLosParametros();

        mostrarTemasEnTabla(temas);
        mostrarParametrosEnTabla(parametros);
        mostrarRelacionesEnTabla();  // Mostrar las relaciones entre Tema y Parámetro

    }

    private void mostrarTemasEnTabla(ArrayList<Tema> temas) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");

        for (Tema tema : temas) {
            modelo.addRow(new Object[] {tema.getId(), tema.getNombre()});
        }

        tablaTemas.setModel(modelo);
    }

    private void mostrarParametrosEnTabla(ArrayList<Parametro> parametros) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        

        for (Parametro parametro : parametros) {
            modelo.addRow(new Object[] {parametro.getId(), parametro.getNombre()});
        }

        tablaParametros.setModel(modelo);
    }

    private void mostrarRelacionesEnTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Tema");
        modelo.addColumn("Parametro");

        ArrayList<Object[]> relaciones = controladorTemaParametro.obtenerRelacionesPorTema();  // Obtener relaciones

        for (Object[] relacion : relaciones) {
            modelo.addRow(relacion);  // Cada fila es un array con el nombre del Tema y el nombre del Parámetro
        }

        tablaTemaParametros.setModel(modelo);
    }

    public void agregarTema() {
        String nombreTema = JOptionPane.showInputDialog("Ingrese el nombre del tema");

        if (nombreTema != null && !nombreTema.trim().isEmpty()) {
            if (nombreTema.matches(".\\d.")) {
                JOptionPane.showMessageDialog(this, "El nombre del tema no puede contener números");
            } else {
                Tema nuevoTema = new Tema(0, nombreTema);
                boolean exito = controladorTema.insertarTema(nuevoTema);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Tema agregado correctamente");
                    cargarDatos();
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

 

    // Método para eliminar un Parámetro
    private void eliminarParametro() {
        int filaParametro = tablaParametros.getSelectedRow();
        if (filaParametro != -1) {
            int idParametro = (int) tablaParametros.getValueAt(filaParametro, 0);
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de eliminar el parámetro seleccionado?", 
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = controladorParametro.eliminarParametro(idParametro);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Parámetro eliminado correctamente");
                    cargarDatos();  // Recargar los datos
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el parámetro");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un parámetro para eliminar");
        }
    }

    // Método para eliminar una Relación entre Tema y Parámetro
    private void eliminarRelacion() {
        int filaRelacion = tablaTemaParametros.getSelectedRow();
        if (filaRelacion != -1) {
            int idTema = (int) tablaTemaParametros.getValueAt(filaRelacion, 0);
            int idParametro = (int) tablaTemaParametros.getValueAt(filaRelacion, 1);

            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de eliminar esta relación?", 
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = controladorTemaParametro.eliminarRelacion(idTema, idParametro);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Relación eliminada correctamente");
                    cargarDatos();  // Recargar los datos
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la relación");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una relación para eliminar");
        }
    }
}