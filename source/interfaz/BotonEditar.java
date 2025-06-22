package interfaz;

import controlador.ControladorZapatillas;
import mundo.Zapatilla;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonEditar extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private ControladorZapatillas controlador;
    private JTable tabla;
    private VentanaInsertarZapatilla formulario;
    private JButton boton;

    public BotonEditar(ControladorZapatillas controlador, JTable tabla, VentanaInsertarZapatilla formulario) {
        this.controlador = controlador;
        this.tabla = tabla;
        this.formulario = formulario;

        
        boton = new JButton("Editar");
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(30, 136, 229));

        boton.addActionListener(e -> {
            fireEditingStopped();
            abrirFormularioEdicion();
        });
    }

    
    private void abrirFormularioEdicion() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            int id = (int) tabla.getValueAt(filaSeleccionada, 0);
            int talla = (int) tabla.getValueAt(filaSeleccionada, 1);
            int genero = (int) tabla.getValueAt(filaSeleccionada, 2);
            int tipo = (int) tabla.getValueAt(filaSeleccionada, 3);
            int marca = (int) tabla.getValueAt(filaSeleccionada, 4);
            String foto = tabla.getValueAt(filaSeleccionada, 5).toString();

            Zapatilla z = new Zapatilla(id, talla, genero, tipo, marca, foto);
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

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JButton botonRender = new JButton("Editar");
        botonRender.setForeground(Color.WHITE);
        botonRender.setBackground(new Color(33, 150, 243));
        return botonRender;
    }


    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return boton;
    }


    public Object getCellEditorValue() {
        return "Editar";
    }
}
