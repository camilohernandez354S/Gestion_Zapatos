package interfaz;

import controlador.ControladorZapatillas;
import controlador.ControladorParametro;
import controlador.ControladorTema;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class BotonEliminar extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private static final long serialVersionUID = 1L;
    private JButton boton;
    private ControladorZapatillas controladorZapatillas;
    private ControladorParametro controladorParametro;
    private ControladorTema controladorTema;
    private VentanaListaZapatillas ventanaListaZapatillas;  // Referencia a la ventana de lista
    private VentanaInsertarTemaParametro ventanaInsertarTemaParametro;  // Referencia a la ventana de temas y parámetros

    public BotonEliminar(Object controlador, JTable tabla, String tipoEntidad, 
                         VentanaListaZapatillas ventanaListaZapatillas, 
                         VentanaInsertarTemaParametro ventanaInsertarTemaParametro) {
        this.ventanaListaZapatillas = ventanaListaZapatillas;  // Guardamos la referencia
        this.ventanaInsertarTemaParametro = ventanaInsertarTemaParametro;

        boton = new JButton("Eliminar");
        boton.setBackground(new Color(239, 83, 80));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(100, 30));
        boton.setFont(new Font("Arial", Font.BOLD, 12));

        // Verificar la asignación del controlador correctamente según el tipo de entidad
        if (controlador instanceof ControladorZapatillas) {
            this.controladorZapatillas = (ControladorZapatillas) controlador;
        } else if (controlador instanceof ControladorParametro) {
            this.controladorParametro = (ControladorParametro) controlador;
        } else if (controlador instanceof ControladorTema) {
            this.controladorTema = (ControladorTema) controlador;
        }

        boton.addActionListener(e -> {
            fireEditingStopped();

            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int id = 0;  // Inicializamos el ID

                // Verificar el tipo de entidad y extraer el ID de la columna correcta
                switch (tipoEntidad) {
                    case "Zapatilla":
                        // En la tabla de zapatillas, el ID está en la segunda columna (índice 1)
                        id = (int) tabla.getValueAt(fila, 1);  // Segunda columna, que tiene el ID real de la zapatilla
                        break;
                    case "Parámetro":
                    case "Tema":
                        // En la tabla de parámetros y temas, el ID está en la primera columna (índice 0)
                        id = (int) tabla.getValueAt(fila, 0);  // Primera columna, que tiene el ID real de parámetro o tema
                        break;
                }

                int confirmacion = JOptionPane.showConfirmDialog(
                        tabla,
                        "¿Está seguro de que desea eliminar este " + tipoEntidad + "?",
                        "Confirmar Eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (confirmacion == JOptionPane.YES_OPTION) {
                    boolean exito = false;
                    switch (tipoEntidad) {
                        case "Zapatilla":
                            exito = eliminarZapatilla(id);
                            if (exito) {
                                ventanaListaZapatillas.cargarZapatillas(); // Recargar la lista de zapatillas
                            }
                            break;
                        case "Parámetro":
                            exito = eliminarParametro(id);
                            if (exito) {
                                ventanaInsertarTemaParametro.cargarDatos(); // Recargar la lista de parámetros
                            }
                            break;
                        case "Tema":
                            exito = eliminarTema(id);
                            if (exito) {
                            	ventanaInsertarTemaParametro.cargarDatos(); // Recargar la lista de parámetros
                            }
                            break;
                    }

                    if (exito) {
                        JOptionPane.showMessageDialog(null, tipoEntidad + " eliminado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar " + tipoEntidad);
                    }
                }
            }
        });
    }

    private boolean eliminarZapatilla(int id) {
        return controladorZapatillas.eliminarZapatilla(id);
    }

    private boolean eliminarParametro(int id) {
        return controladorParametro.eliminarParametro(id);
    }

    private boolean eliminarTema(int id) {
        return controladorTema.eliminarTema(id);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return boton;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return boton;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
