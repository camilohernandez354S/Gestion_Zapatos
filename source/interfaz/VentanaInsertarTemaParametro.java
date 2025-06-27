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
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
	
	JPanel panelTitulo = new JPanel();
	panelTitulo.setLayout(new GridLayout(1, 3, 10, 10));
	
	JLabel lblTemas = new JLabel("Temas", JLabel.CENTER);
	JLabel lblParametros = new JLabel("Parámetros", JLabel.CENTER);
	JLabel lblRelaciones = new JLabel("Tema-Parametro", JLabel.CENTER);
	
	panelTitulo.add(lblTemas);
	panelTitulo.add(lblParametros);
	panelTitulo.add(lblRelaciones);
	add(panelTitulo, BorderLayout.NORTH);
	
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
	
	JPanel panelBotones = new JPanel();
	panelBotones.setLayout(new GridLayout(4, 1, 5, 5));
	
	JButton btnAgregarTema = new JButton("Agregar Tema");
	JButton btnAgregarParametro = new JButton("Agregar Parámetro");
	JButton btnAgregarTemaParametro = new JButton("Agregar Tema-Parametro");
	JButton btnEliminarRelacion = new JButton("Elimnar Relación");
	
	panelBotones.add(btnAgregarTema);
    panelBotones.add(btnAgregarParametro);
    panelBotones.add(btnAgregarTemaParametro);
    panelBotones.add(btnEliminarRelacion);
	
	add(panelBotones, BorderLayout.EAST);
	
	btnAgregarTema.addActionListener(e -> agregarTema());
	btnAgregarParametro.addActionListener(e -> agregarParametro());
	btnAgregarTemaParametro.addActionListener(e -> asociarTemaParametro());
	btnEliminarRelacion.addActionListener(e -> eliminarRelacion());
	
	cargarDatos();
	}
	
	public void cargarDatos() {
		temas = controladorTema.obtenerTodosLosTemas();
		parametros = controladorParametro.obtenerTodosLosParametros();
		
		mostrarTemasEnTabla(temas);
		mostrarParametrosEnTabla(parametros);
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
	
	public void agregarTema() {
		String nombreTema = JOptionPane.showInputDialog("Ingrese el nombre del tema");
		
		if (nombreTema != null && !nombreTema.trim().isEmpty()) {
			if (nombreTema.matches(".*\\d.*")) {
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
	
	public void eliminarRelacion() {
		int filaRelacion = tablaTemaParametros.getSelectedRow();
		
		if (filaRelacion != -1) {
			int idTema = (int) tablaTemaParametros.getValueAt(filaRelacion, 0);
			int idParametro = (int) tablaTemaParametros.getValueAt(filaRelacion, 1);
			
			int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar esta relación", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
			
			if (confirmacion == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(this, "Relación eliminada correctamente");
				cargarDatos();
			} else {
				JOptionPane.showMessageDialog(this, "Error al eliminar la relación");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecciones una relación");
		}
	}
}