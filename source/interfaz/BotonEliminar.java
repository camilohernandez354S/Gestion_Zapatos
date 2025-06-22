package interfaz;

import controlador.ControladorZapatillas;

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
    private JTable tabla;
    private ControladorZapatillas controlador;
    private VentanaListaZapatillas panelLista;


    public BotonEliminar(ControladorZapatillas controlador, JTable tabla, VentanaListaZapatillas panelLista) {
        this.controlador = controlador;
        this.tabla = tabla;
        this.panelLista = panelLista;

        boton = new JButton("Eliminar");
        boton.setBackground(Color.RED);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(100, 30));
        boton.setFont(new Font("Arial", Font.BOLD, 12));

        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	fireEditingStopped();
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    int id = (int) tabla.getValueAt(fila, 0);

                    
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

    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return boton;
    }

    
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return boton;
    }

    
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    
    public Object getCellEditorValue() {
        return null;
    }
}
