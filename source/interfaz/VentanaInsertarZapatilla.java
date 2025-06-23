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
    private JComboBox<Parametro> comboTalla, comboGenero, comboTipo, comboMarca;
    private JTextField txtFoto;
    private int idActual;
    private JButton btnGuardar;
    private boolean modoEdicion = false;
    private VentanaListaZapatillas panelLista;

    private int idTalla = 1;
    private int idGenero = 2;
    private int idTipo = 3;
    private int idMarca = 4;


    public VentanaInsertarZapatilla(VentanaListaZapatillas panelLista) {
        this.controlador = new ControladorZapatillas();
        this.panelLista = panelLista;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);	

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
        	    BorderFactory.createLineBorder(Color.GRAY),
        	    "Registro de Zapatilla",
        	    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
        	    javax.swing.border.TitledBorder.ABOVE_TOP,
        	    new Font("Segoe UI", Font.BOLD, 30),
        	    Color.BLACK             
        	));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 18);

        comboTalla = new JComboBox<>();
        comboGenero = new JComboBox<>();
        comboTipo = new JComboBox<>();
        comboMarca = new JComboBox<>();
        txtFoto = new JTextField(25);

        comboTalla.setFont(fuenteCampos);
        comboGenero.setFont(fuenteCampos);
        comboTipo.setFont(fuenteCampos);
        comboMarca.setFont(fuenteCampos);
        txtFoto.setFont(fuenteCampos);

        cargarParametros();

        int y = 0;

        gbc.gridx = 0; gbc.gridy = y; panel.add(crearLabel("Talla:"), gbc);
        gbc.gridx = 1; panel.add(comboTalla, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y; panel.add(crearLabel("Género:"), gbc);
        gbc.gridx = 1; panel.add(comboGenero, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y; panel.add(crearLabel("Tipo:"), gbc);
        gbc.gridx = 1; panel.add(comboTipo, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y; panel.add(crearLabel("Marca:"), gbc);
        gbc.gridx = 1; panel.add(comboMarca, gbc); y++;

        gbc.gridx = 0; gbc.gridy = y; panel.add(crearLabel("Ruta Imagen:"), gbc);
        gbc.gridx = 1; panel.add(txtFoto, gbc); y++;

        JButton btnSeleccionarImagen = new JButton("Seleccionar Imagen");
        btnSeleccionarImagen.setFont(fuenteCampos);
        btnSeleccionarImagen.addActionListener(e -> {
            File carpetaPorDefecto = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "Imagenes");
            JFileChooser fileChooser = new JFileChooser(carpetaPorDefecto);
            int opcion = fileChooser.showOpenDialog(this);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                txtFoto.setText(archivo.getAbsolutePath());
            }
        });

        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; panel.add(btnSeleccionarImagen, gbc); y++;

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnGuardar.setBackground(new Color(76, 175, 80));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new Dimension(180, 40));
        btnGuardar.addActionListener(e -> {
            if (modoEdicion) {
                guardarCambios();
            } else {
                guardarZapatilla();
            }
        });

        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, gbc);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Método auxiliar para crear etiquetas grandes y estilizadas.
     */
    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        return label;
    }

    private void cargarParametros() {
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
            JOptionPane.showMessageDialog(this, "Zapatilla guardada correctamente.");

            prepararParaAgregar();
            panelLista.cargarZapatillas();

            Window window = SwingUtilities.getWindowAncestor(this);
            if (window instanceof JDialog) {
                ((JDialog) window).dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar la zapatilla.");
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
            JOptionPane.showMessageDialog(this, " Zapatilla actualizada correctamente.");
            panelLista.cargarZapatillas();
            SwingUtilities.getWindowAncestor(this).dispose();
        } else {
            JOptionPane.showMessageDialog(this, " Error al actualizar la zapatilla.");
        }
    }

    public void cargarZapatilla(Zapatilla z) {
    	this.idActual = z.getId();
    	this.modoEdicion = true;
    	
    	seleccionarEnCombo(comboTalla, z.getIdTalla());
    	seleccionarEnCombo(comboGenero, z.getIdGenero());
    	seleccionarEnCombo(comboTipo, z.getIdTipo());
    	seleccionarEnCombo(comboMarca, z.getIdMarca());
    	txtFoto.setText(z.getFoto());
    	
    	btnGuardar.setText("Guardar Cambios");
    }
    
    public void prepararParaAgregar() {
    	this.modoEdicion = false;
    	this.idActual = 0;
    	
    	cargarParametros();
    	
    	
    	comboTalla.setSelectedIndex(-1);
    	comboGenero.setSelectedIndex(-1);
    	comboTipo.setSelectedIndex(-1);
    	comboMarca.setSelectedIndex(-1);
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
        return comboTalla.getSelectedItem() != null &&
               comboGenero.getSelectedItem() != null &&
               comboTipo.getSelectedItem() != null &&
               comboMarca.getSelectedItem() != null &&
               !txtFoto.getText().trim().isEmpty();
               
    }
    
    private Zapatilla construirZapatillaDesdeFormulario(int id) {
        return new Zapatilla(
            id,
            ((Parametro) comboTalla.getSelectedItem()).getId(),
            ((Parametro) comboGenero.getSelectedItem()).getId(),
            ((Parametro) comboTipo.getSelectedItem()).getId(),
            ((Parametro) comboMarca.getSelectedItem()).getId(),
            txtFoto.getText()
        );
    }
}
