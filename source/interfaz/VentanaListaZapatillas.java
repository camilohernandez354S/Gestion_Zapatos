package interfaz;

import controlador.ControladorZapatillas;
import mundo.Zapatilla;

import javax.swing.*;
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

        String[] columnas = {"ID", "Talla", "Género", "Tipo", "Marca", "Foto", "Eliminar" , "Editar"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6 || column == 7; 
            }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(24);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setSelectionBackground(new Color(200, 230, 201));

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
                    String ruta = tabla.getValueAt(fila, 5).toString();
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

        for (Zapatilla z : lista) {
            modelo.addRow(new Object[]{
                    z.getId(),
                    z.getIdTalla(),
                    z.getIdGenero(),
                    z.getIdTipo(),
                    z.getIdMarca(),
                    z.getFoto(),
                    "Eliminar",
                    "Editar"
            });
        }
    }
}
