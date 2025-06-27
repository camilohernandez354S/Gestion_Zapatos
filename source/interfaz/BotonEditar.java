package interfaz;

import controlador.ControladorZapatillas;
import controlador.ControladorTema;
import controlador.ControladorParametro;
import mundo.Zapatilla;
import mundo.Tema;
import mundo.Parametro;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class BotonEditar extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private static final long serialVersionUID = 1L;

    private JTable tabla;
    private String tipoEntidad;
    private JButton boton;

    private ControladorZapatillas controladorZapatillas;
    private ControladorTema controladorTema;
    private ControladorParametro controladorParametro;

    private VentanaInsertarZapatilla formulario;
    private VentanaInsertarTemaParametro ventanaInsertarTemaParametro;

    public BotonEditar(Object controlador, JTable tabla, String tipoEntidad, VentanaInsertarZapatilla formulario, VentanaInsertarTemaParametro ventanaInsertarTemaParametro) {
        this.tabla = tabla;
        this.tipoEntidad = tipoEntidad;
        this.formulario = formulario;
        this.ventanaInsertarTemaParametro = ventanaInsertarTemaParametro;

        // Inicializar los controladores
        if (tipoEntidad.equals("Zapatilla")) {
            this.controladorZapatillas = (ControladorZapatillas) controlador;
        } else if (tipoEntidad.equals("Tema")) {
            this.controladorTema = (ControladorTema) controlador;
        } else if (tipoEntidad.equals("Parámetro")) {
            this.controladorParametro = (ControladorParametro) controlador;
        }

        // Crear el botón "Editar"
        boton = new JButton("Editar");
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(66, 165, 245));

        boton.addActionListener(e -> {
            fireEditingStopped();
            // Llamar al método correspondiente según la entidad que se está editando
            switch (tipoEntidad) {
                case "Zapatilla":
                    editarZapatilla();
                    break;
                case "Tema":
                    editarTema();
                    break;
                case "Parámetro":
                    editarParametro();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Entidad desconocida");
            }
        });
    }

    // Método para editar Zapatilla
    private void editarZapatilla() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            int id = (int) tabla.getValueAt(filaSeleccionada, 1);  // Obtener el ID de la tabla

            Zapatilla zapatilla = controladorZapatillas.obtenerZapatillaPorId(id);
            if (zapatilla != null) {
                formulario.cargarZapatilla(zapatilla);

                JDialog dialogo = new JDialog();
                dialogo.setTitle("Editar Zapatilla");
                dialogo.setModal(true);
                dialogo.getContentPane().add(formulario);
                dialogo.pack();
                dialogo.setLocationRelativeTo(null);
                dialogo.setVisible(true);
                
                // Refrescar la lista de zapatillas después de la edición
                // Si tienes una ventana que lista las zapatillas, asegúrate de actualizarla
            } else {
                JOptionPane.showMessageDialog(null, "Zapatilla no encontrada para ID: " + id);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al seleccionar una zapatilla.");
        }
    }

    // Método para editar Tema
    private void editarTema() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            int id = (int) tabla.getValueAt(filaSeleccionada, 0);  // El ID está en la primera columna

            Tema tema = controladorTema.obtenerTemaPorId(id);
            if (tema != null) {
                String nuevoNombre = JOptionPane.showInputDialog("Editar nombre del tema", tema.getNombre());
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    controladorTema.actualizarTema(id, nuevoNombre);
                    JOptionPane.showMessageDialog(null, "Tema actualizado correctamente");

                    // Refrescar la tabla de temas
                    ventanaInsertarTemaParametro.cargarDatos();  // Llama a cargarDatos para actualizar la tabla
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tema no encontrado para ID: " + id);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al seleccionar un tema.");
        }
    }

    // Método para editar Parámetro
    private void editarParametro() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            int id = (int) tabla.getValueAt(filaSeleccionada, 0);  // El ID está en la primera columna

            Parametro parametro = controladorParametro.obtenerParametroPorId(id);
            if (parametro != null) {
                String nuevoNombre = JOptionPane.showInputDialog("Editar nombre del parámetro", parametro.getNombre());
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    controladorParametro.actualizarParametro(id, nuevoNombre);
                    JOptionPane.showMessageDialog(null, "Parámetro actualizado correctamente");

                    // Refrescar la tabla de parámetros
                    ventanaInsertarTemaParametro.cargarDatos();  // Llama a cargarDatos para actualizar la tabla
                }
            } else {
                JOptionPane.showMessageDialog(null, "Parámetro no encontrado para ID: " + id);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al seleccionar un parámetro.");
        }
    }

    // Método para renderizar la celda con el botón "Editar"
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JButton botonRender = new JButton("Editar");
        botonRender.setForeground(Color.WHITE);
        botonRender.setBackground(new Color(33, 150, 243));
        return botonRender;
    }

    // Método para editar la celda (el botón de editar)
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return boton;
    }

    // Método que devuelve el valor de la celda, en este caso "Editar"
    public Object getCellEditorValue() {
        return "Editar";
    }
}
