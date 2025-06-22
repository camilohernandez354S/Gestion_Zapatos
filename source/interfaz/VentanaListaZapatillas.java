package interfaz;

import controlador.ControladorZapatillas;
import mundo.Zapatilla;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VentanaListaZapatillas extends JPanel {

    private static final long serialVersionUID = 1L;

    private ControladorZapatillas controlador;
    private JTable tabla;
    private JLabel imagenLabel;

    public VentanaListaZapatillas() {
        controlador = new ControladorZapatillas();
        VentanaInsertarZapatilla formulario = new VentanaInsertarZapatilla(this);

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Zapatillas Registradas", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(33, 150, 243));
        add(titulo, BorderLayout.NORTH);

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

        // Cabecera moderna
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tabla.getTableHeader().setBackground(new Color(33, 150, 243));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setReorderingAllowed(false);

        // Estilo de filas alternas
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : new Color(230, 240, 255));
                } else {
                    c.setBackground(new Color(197, 225, 165));
                }
                return c;
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        imagenLabel = new JLabel();
        imagenLabel.setPreferredSize(new Dimension(250, 250));
        imagenLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(imagenLabel, BorderLayout.EAST);

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

        tabla.getColumn("Eliminar").setCellRenderer(new BotonEliminar(controlador, tabla, this));
        tabla.getColumn("Eliminar").setCellEditor(new BotonEliminar(controlador, tabla, this));

        tabla.getColumn("Editar").setCellRenderer(new BotonEditar(controlador, tabla, formulario));
        tabla.getColumn("Editar").setCellEditor(new BotonEditar(controlador, tabla, formulario));

        cargarZapatillas();
    }

    public void cargarZapatillas() {
        ArrayList<Zapatilla> lista = controlador.obtenerZapatillas();
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        for (int i = 0; i < lista.size(); i++) {
            Zapatilla z = lista.get(i);

            String talla = controlador.obtenerParametroPorId(z.getIdTalla()).getNombre();
            String genero = controlador.obtenerParametroPorId(z.getIdGenero()).getNombre();
            String tipo = controlador.obtenerParametroPorId(z.getIdTipo()).getNombre();
            String marca = controlador.obtenerParametroPorId(z.getIdMarca()).getNombre();

            // Aquí seteamos el número visible, pero en otra columna guardamos el ID real oculto (opcional)
            modelo.addRow(new Object[]{
            	    (i + 1),              // #
            	    z.getId(),            // ID real
            	    talla,
            	    genero,
            	    tipo,
            	    marca,
            	    z.getFoto(),
            	    "Eliminar",
            	    "Editar"
            	});
        }
    }
}
