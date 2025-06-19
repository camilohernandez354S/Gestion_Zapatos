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

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Zapatillas Registradas", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(33, 150, 243));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"ID", "Color", "Talla", "Género", "Tipo", "Marca", "Foto"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
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
                    String ruta = tabla.getValueAt(fila, 6).toString();
                    ImageIcon icono = new ImageIcon(ruta);
                    Image imagen = icono.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    imagenLabel.setIcon(new ImageIcon(imagen));
                }
            }
        });

        cargarZapatillas();
    }

    private void cargarZapatillas() {
        ArrayList<Zapatilla> lista = controlador.obtenerZapatillas();
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        for (Zapatilla z : lista) {
        	
        	JButton botonEliminar = new JButton("Eliminar");
        	botonEliminar.addActionListener(new ActionListener() {
        		
        		public void actionPerformed(ActionEvent e) {
        			
        			int idZapatilla = z.getId();
        			boolean eliminado = controlador.eliminarZapatilla(idZapatilla);
        			if (eliminado) {
        				JOptionPane.showMessageDialog(null, "Zapatilla eliminada correctamente");
        				cargarZapatillas();
        			} else {
        				JOptionPane.showMessageDialog(null, "Error al eliminar la zapatilla");
        			}
        		}
        		
        	});
        	
            modelo.addRow(new Object[]{
                z.getId(),
                z.getIdColor(),
                z.getIdTalla(),
                z.getIdGenero(),
                z.getIdTipo(),
                z.getIdMarca(),
                z.getFoto(),
                botonEliminar
            });
        }
        ButtonColumn.setButtonColumn(tabla, 7);
    }
}
