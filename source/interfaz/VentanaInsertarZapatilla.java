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

    private int idColor = 1, idTalla = 2, idGenero = 3, idTipo = 4, idMarca = 5;

    public VentanaInsertarZapatilla() {
        controlador = new ControladorZapatillas();
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
            JFileChooser fileChooser = new JFileChooser();
            int opcion = fileChooser.showOpenDialog(this);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                txtFoto.setText(archivo.getAbsolutePath());
            }
        });

        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; panel.add(btnSeleccionarImagen, gbc); y++;

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(30, 136, 229));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> guardarZapatilla());

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
        Parametro color = (Parametro) comboColor.getSelectedItem();
        Parametro talla = (Parametro) comboTalla.getSelectedItem();
        Parametro genero = (Parametro) comboGenero.getSelectedItem();
        Parametro tipo = (Parametro) comboTipo.getSelectedItem();
        Parametro marca = (Parametro) comboMarca.getSelectedItem();
        String foto = txtFoto.getText();

        if (color == null || talla == null || genero == null || tipo == null || marca == null) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            return;
        }

        Zapatilla z = new Zapatilla(0, color.getId(), talla.getId(), genero.getId(), tipo.getId(), marca.getId(), foto);
        boolean exito = controlador.insertarZapatilla(z);

        if (exito) {
            JOptionPane.showMessageDialog(this, "✅ Zapatilla registrada correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al registrar la zapatilla.");
        }
    }
}
