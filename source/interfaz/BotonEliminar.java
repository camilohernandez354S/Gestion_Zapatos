package interfaz;

import controlador.ControladorZapatillas;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

/**
 * Clase que representa un botón de eliminación dentro de una tabla de Zapatillas.
 * Esta clase implementa las interfaces TableCellRenderer y TableCellEditor para
 * permitir que el botón se renderice y edite dentro de las celdas de una tabla de datos.
 * Este botón permite al usuario eliminar una zapatilla del sistema.
 */
public class BotonEliminar extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    
    private static final long serialVersionUID = 1L;

    private JButton boton;

    /**
     * Constructor que inicializa el botón de eliminar y le asigna los estilos
     * y acciones necesarias para que funcione correctamente dentro de la tabla.
     * 
     * @param controlador Objeto que maneja la lógica de la aplicación y la base de datos
     * @param tabla JTable donde se mostrará el botón
     * @param panelLista Ventana que contiene la lista de zapatillas
     */
    public BotonEliminar(ControladorZapatillas controlador, JTable tabla, VentanaListaZapatillas panelLista) {

    	boton = new JButton("Eliminar");
        boton.setBackground(new Color(239, 83, 80));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(100, 30));
        boton.setFont(new Font("Arial", Font.BOLD, 12));

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	fireEditingStopped();

            	int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    int id = (int) tabla.getValueAt(fila, 1);

                    int confirmacion = JOptionPane.showConfirmDialog(
                            tabla,
                            "¿Está seguro de que desea eliminar esta zapatilla?",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        boolean eliminado = controlador.eliminarZapatilla(id);
                        if (eliminado) {
                            JOptionPane.showMessageDialog(null, "Zapatilla eliminada correctamente");
                            panelLista.cargarZapatillas();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al eliminar la zapatilla");
                        }
                    }
                }
            }
        });
    }

    /**
     * Método que devuelve el componente de celda renderizada (el botón de eliminar)
     * para ser mostrado en la tabla.
     *
     * @param table Tabla donde se mostrará el botón
     * @param value Valor de la celda (no utilizado aquí)
     * @param isSelected Indica si la celda está seleccionada
     * @param hasFocus Indica si la celda tiene el foco
     * @param row Fila de la celda
     * @param column Columna de la celda
     * @return Componente (botón) que se va a mostrar en la celda
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return boton;
    }

    /**
     * Método que devuelve el componente de celda editado (el botón de eliminar)
     * cuando la celda está en modo de edición.
     *
     * @param table Tabla donde se mostrará el botón
     * @param value Valor de la celda (no utilizado aquí)
     * @param isSelected Indica si la celda está seleccionada
     * @param row Fila de la celda
     * @param column Columna de la celda
     * @return Componente (botón) que se va a mostrar en la celda
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return boton;
    }

    /**
     * Método que indica si la celda es editable o no.
     *
     * @param e Evento que indica si la celda es editable (siempre es verdadero en este caso)
     * @return true Si la celda es editable
     */
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    /**
     * Método que devuelve el valor editado de la celda. En este caso, no tiene un valor
     * editable asociado, por lo que se devuelve null.
     *
     * @return null Siempre, porque el botón no tiene un valor editable
     */
    public Object getCellEditorValue() {
        return null;
    }
}
