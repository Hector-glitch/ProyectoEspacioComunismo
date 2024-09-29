import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Mecanico extends Empleado {
    private String numTaller;
    private JFrame frame;
    private JTextField txtNumeroSerie, txtRevision, txtTipoViniculo, txtMaxPasajeros;
    private JCheckBox chkEnTaller;
    private JButton btnListarVehiculos, btnAgregarVehiculo, btnCambiarEstadoTaller;

    public Mecanico(String nombre, int salario, int edad, String direccion, String anosExp, String sexo, String numTaller, boolean isAdmin) {
        super(nombre, salario, edad, direccion, anosExp, sexo, isAdmin);
        this.numTaller = numTaller;
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Mecánico - Taller " + numTaller);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(10, 2));

        JLabel lblNumeroSerie = new JLabel("Número de Serie:");
        JLabel lblRevision = new JLabel("Revisión:");
        JLabel lblTipoViniculo = new JLabel("Tipo de Viniculo:");
        JLabel lblMaxPasajeros = new JLabel("Máx. Pasajeros:");

        txtNumeroSerie = new JTextField();
        txtRevision = new JTextField();
        txtTipoViniculo = new JTextField();
        txtMaxPasajeros = new JTextField();

        chkEnTaller = new JCheckBox("En Taller");

        btnListarVehiculos = new JButton("Listar Vehículos");
        btnAgregarVehiculo = new JButton("Agregar Vehículo");
        btnCambiarEstadoTaller = new JButton("Cambiar Estado Taller");

        frame.add(lblNumeroSerie);
        frame.add(txtNumeroSerie);
        frame.add(lblRevision);
        frame.add(txtRevision);
        frame.add(lblTipoViniculo);
        frame.add(txtTipoViniculo);
        frame.add(lblMaxPasajeros);
        frame.add(txtMaxPasajeros);

        frame.add(chkEnTaller);
        frame.add(btnCambiarEstadoTaller);

        frame.add(btnListarVehiculos);
        frame.add(btnAgregarVehiculo);

        btnListarVehiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarVehiculos();
            }
        });

        btnAgregarVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVehiculo();
            }
        });

        btnCambiarEstadoTaller.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarEstadoTaller();
            }
        });

        frame.setVisible(true);
    }

    private void listarVehiculos() {
        ArrayList<String> vehiculos = new ArrayList<>();
        Connection conn = conexio.getConnection();
        try {
            String query = "SELECT * FROM viniculos WHERE taller = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, numTaller);
            ResultSet rs = stmt.executeQuery();

            // Crear lista de vehículos
            while (rs.next()) {
                int numeroSerie = rs.getInt("numero_serie");
                String revision = rs.getString("revision");
                String tipoViniculo = rs.getString("tipo_viniculo");
                int maxPasajeros = rs.getInt("max_pasajeros");
                int taller = rs.getInt("taller");
                String enTaller = taller == 1 ? "Sí" : "No";

                vehiculos.add("Serie: " + numeroSerie + ", Revisión: " + revision + ", Tipo: " + tipoViniculo +
                        ", Máx. Pasajeros: " + maxPasajeros + ", En Taller: " + enTaller);
            }

            // Escribir en archivo .txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehiculos.txt"))) {
                for (String vehiculo : vehiculos) {
                    writer.write(vehiculo);
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(frame, "Vehículos guardados en 'vehiculos.txt'");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al listar los vehículos");
        }
    }

    private void agregarVehiculo() {
        Connection conn = conexio.getConnection();
        try {
            String query = "INSERT INTO viniculos (numero_serie, revision, tipo_viniculo, max_pasajeros, taller) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(txtNumeroSerie.getText()));
            stmt.setString(2, txtRevision.getText());
            stmt.setString(3, txtTipoViniculo.getText());
            stmt.setInt(4, Integer.parseInt(txtMaxPasajeros.getText()));
            stmt.setInt(5, 0);

            int result = stmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(frame, "Vehículo agregado correctamente");
                txtNumeroSerie.setText("");
                txtRevision.setText("");
                txtTipoViniculo.setText("");
                txtMaxPasajeros.setText("");
                chkEnTaller.setSelected(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al agregar vehículo");
        }
    }

    private void cambiarEstadoTaller() {
        Connection conn = conexio.getConnection();
        try {
            if (txtNumeroSerie.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Debe ingresar el número de serie");
                return;
            }

            String query = "UPDATE viniculos SET taller = ? WHERE numero_serie = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, chkEnTaller.isSelected() ? 1 : 0);
            stmt.setInt(2, Integer.parseInt(txtNumeroSerie.getText()));

            int result = stmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(frame, "Estado del vehículo actualizado correctamente");
                txtNumeroSerie.setText("");
                chkEnTaller.setSelected(false);
            } else {
                JOptionPane.showMessageDialog(frame, "No se ha encontrado ningún vehículo con ese número de serie");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error al cambiar el estado");
        }
    }
}
