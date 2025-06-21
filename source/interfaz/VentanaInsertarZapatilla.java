package interfaz;

import controlador.ControladorZapatillas;
import mundo.Parametro;
import mundo.Zapatilla;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class VentanaInsertarZapatilla extends JPanel {
	
	private static final long serialVersionUID = 1L;

    private ControladorZapatillas controlador;
    private JComboBox<Parametro> comboColor, comboTalla, comboGenero, comboTipo, comboMarca;
    private JTextField txtFoto;
    private Zapatilla zapatillaActual = null;
    private int idActual;
    private JButton btnGuardar;
    private boolean modoEdicion = false;
    private VentanaListaZapatillas panelLista;

    private int idColor = 1, idTalla = 2, idGenero = 3, idTipo = 4, idMarca = 5;

    public VentanaInsertarZapatilla(VentanaListaZapatillas panelLista) {
        this.controlador = new ControladorZapatillas();
        this.panelLista = panelLista;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);	

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(250, 250, 250));
        panel.setBorder(BorderFactory.createTitledBorder("Registro de Zapatilla"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        comboColor = new JComboBox<>();
        comboTalla = new JComboBox<>();
        comboGenero = new JComboBox<>();
        comboTipo = new JComboBox<>();
        comboMarca = new JComboBox<>();
        txtFoto = new JTextField(20);

        cargarParametros();

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Color:"), gbc);
        gbc.gridx = 1; panel.add(comboColor, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Talla:"), gbc);
        gbc.gridx = 1; panel.add(comboTalla, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Género:"), gbc);
        gbc.gridx = 1; panel.add(comboGenero, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1; panel.add(comboTipo, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Marca:"), gbc);
        gbc.gridx = 1; panel.add(comboMarca, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y; panel.add(new JLabel("Ruta Imagen:"), gbc);
        gbc.gridx = 1; panel.add(txtFoto, gbc); y++;

        JButton btnSeleccionarImagen = new JButton("Seleccionar Imagen");
        btnSeleccionarImagen.addActionListener(e -> {
        	File carpetaPorDefecto = new File(System.getProperty("user.dir") + File.separator + "data");
        	JFileChooser fileChooser = new JFileChooser(carpetaPorDefecto);
            int opcion = fileChooser.showOpenDialog(this);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                txtFoto.setText(archivo.getAbsolutePath());
            }
        });

        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; panel.add(btnSeleccionarImagen, gbc); y++;
        
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color (76, 175, 80));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> {
        	if (modoEdicion) {
        		guardarCambios();
        	} else {
        		guardarZapatilla();
        	}
        });
        

        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void cargarParametros() {
        cargarCombo(comboColor, idColor);
        cargarCombo(comboTalla, idTalla);
        cargarCombo(comboGenero, idGenero);
        cargarCombo(comboTipo, idTipo);
        cargarCombo(comboMarca, idMarca);
    }

    private void cargarCombo(JComboBox<Parametro> combo, int idTema) {
        ArrayList<Parametro> parametros = controlador.obtenerParametrosPorTema(idTema);
        combo.removeAllItems();
        for (Parametro p : parametros) {
            combo.addItem(p);
        }
    }

    private void guardarZapatilla() {
        if (!validarCampos()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }

        Zapatilla z = construirZapatillaDesdeFormulario(0);

        boolean exito = controlador.insertarZapatilla(z);
        if (exito) {
            JOptionPane.showMessageDialog(this, "✅ Zapatilla guardada correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al guardar la zapatilla.");
        }
    }
    
    private void guardarCambios() {
        if (!validarCampos()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }

        Zapatilla z = construirZapatillaDesdeFormulario(idActual);

        boolean actualizado = controlador.actualizarZapatilla(z);
        if (actualizado) {
            JOptionPane.showMessageDialog(this, "✅ Zapatilla actualizada correctamente.");
            panelLista.cargarZapatillas();
            SwingUtilities.getWindowAncestor(this).dispose();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al actualizar la zapatilla.");
        }
    }


    
    public void cargarZapatilla(Zapatilla z) {
        
    	this.idActual = z.getId();
    	this.modoEdicion = true;
    	this.zapatillaActual = z;
    	
    	seleccionarEnCombo(comboColor, z.getIdColor());
    	seleccionarEnCombo(comboTalla, z.getIdTalla());
    	seleccionarEnCombo(comboGenero, z.getIdGenero());
    	seleccionarEnCombo(comboTipo, z.getIdTipo());
    	seleccionarEnCombo(comboMarca, z.getIdMarca());
    	txtFoto.setText(z.getFoto());
    	
    	btnGuardar.setText("Guardar Cambios");
    }
    
    public void prepararParaAgregar() {
    	this.modoEdicion = false;
    	this.zapatillaActual = null;
    	this.idActual = 0;
    	
    	cargarParametros();
    	
    	comboColor.setSelectedIndex(0);
    	comboTalla.setSelectedIndex(0);
    	comboGenero.setSelectedIndex(0);
    	comboTipo.setSelectedIndex(0);
    	comboMarca.setSelectedIndex(0);
    	txtFoto.setText("");
    	
    	btnGuardar.setText("Guardar");
    	
    }


    private void seleccionarEnCombo(JComboBox<Parametro> combo, int id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Parametro p = combo.getItemAt(i);
            if (p.getId() == id) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private boolean validarCampos() {
        return comboColor.getSelectedItem() != null &&
               comboTalla.getSelectedItem() != null &&
               comboGenero.getSelectedItem() != null &&
               comboTipo.getSelectedItem() != null &&
               comboMarca.getSelectedItem() != null &&
               !txtFoto.getText().trim().isEmpty();
    }
    
    private Zapatilla construirZapatillaDesdeFormulario(int id) {
        return new Zapatilla(
            id,
            ((Parametro) comboColor.getSelectedItem()).getId(),
            ((Parametro) comboTalla.getSelectedItem()).getId(),
            ((Parametro) comboGenero.getSelectedItem()).getId(),
            ((Parametro) comboTipo.getSelectedItem()).getId(),
            ((Parametro) comboMarca.getSelectedItem()).getId(),
            txtFoto.getText()
        );
    }
}
