package interfaz;

import controlador.ControladorZapatillas;
import mundo.Zapatilla;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;

/**
 * Clase que representa el botón "Editar" en una celda de la tabla.
 * Permite editar los datos de una zapatilla, cargando su información en un formulario.
 * Implementa TableCellRenderer y TableCellEditor para integrarse en la tabla de datos.
 */
public class BotonEditar extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
    
    private static final long serialVersionUID = 1L;

    // Atributos
    private ControladorZapatillas controlador;
    private JTable tabla;
    private VentanaInsertarZapatilla formulario;
    private JButton boton;

    /**
     * Constructor de la clase BotonEditar.
     * Inicializa el controlador, la tabla y el formulario para la edición.
     * También crea el botón "Editar" y establece su apariencia y comportamiento.
     *
     * @param controlador El controlador que maneja las operaciones relacionadas con las zapatillas.
     * @param tabla La tabla donde se muestran las zapatillas.
     * @param formulario El formulario donde se edita la información de las zapatillas.
     */
    public BotonEditar(ControladorZapatillas controlador, JTable tabla, VentanaInsertarZapatilla formulario) {
        this.controlador = controlador;
        this.tabla = tabla;
        this.formulario = formulario;
        
        
        boton = new JButton("Editar");
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(66, 165, 245));

        
        boton.addActionListener(e -> {
            fireEditingStopped();
            abrirFormularioEdicion();
        });
    }

    /**
     * Método que abre el formulario de edición para la zapatilla seleccionada.
     * Obtiene la ID de la zapatilla seleccionada en la tabla, carga los datos en el formulario
     * y muestra un cuadro de diálogo para editar la información de esa zapatilla.
     */
    private void abrirFormularioEdicion() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            int id = (int) tabla.getValueAt(filaSeleccionada, 1);

            
            Zapatilla z = controlador.obtenerZapatillaPorId(id);

            
            formulario.cargarZapatilla(z);

            
            JDialog dialogo = new JDialog();
            dialogo.setTitle("Editar Zapatilla");
            dialogo.setModal(true);
            dialogo.getContentPane().add(formulario);
            dialogo.pack();
            dialogo.setLocationRelativeTo(null);
            dialogo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una fila para editar.");
        }
    }

    /**
     * Método de la interfaz TableCellRenderer que renderiza la celda del botón "Editar".
     * Este método define cómo se ve el botón en la tabla.
     *
     * @param table La tabla que contiene la celda.
     * @param value El valor de la celda (no se utiliza en este caso).
     * @param isSelected Si la celda está seleccionada.
     * @param hasFocus Si la celda tiene el foco.
     * @param row El número de fila de la celda.
     * @param column El número de columna de la celda.
     * @return El componente que renderiza la celda, en este caso un botón "Editar".
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JButton botonRender = new JButton("Editar");
        botonRender.setForeground(Color.WHITE);
        botonRender.setBackground(new Color(33, 150, 243));
        return botonRender;
    }

    /**
     * Método de la interfaz TableCellEditor que retorna el componente para editar la celda.
     * En este caso, el componente de la celda es un botón "Editar".
     *
     * @param table La tabla que contiene la celda.
     * @param value El valor de la celda (no se utiliza en este caso).
     * @param isSelected Si la celda está seleccionada.
     * @param row El número de fila de la celda.
     * @param column El número de columna de la celda.
     * @return El botón "Editar" que se usará para editar la celda.
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return boton;
    }

    /**
     * Método que devuelve el valor de la celda. En este caso, siempre retorna "Editar",
     * ya que el valor de la celda no es relevante cuando el componente es un botón.
     *
     * @return El valor de la celda (en este caso, siempre "Editar").
     */
    public Object getCellEditorValue() {
        return "Editar";
    }
}
