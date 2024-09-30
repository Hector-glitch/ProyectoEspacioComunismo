import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Mecanico {
    private String numeroDeTaller;

    public Mecanico(String numeroDeTaller) {
        this.numeroDeTaller = numeroDeTaller;
    }

    public void listarVehiculos() {
        ArrayList<String> vehiculos = new ArrayList<>();
        Connection conn = Conexio.getConnection();
        try {
            // Ajustar consulta según tus necesidades
            String query = "SELECT * FROM vehiculos WHERE taller = 1"; // Puedes ajustar esto si tienes un campo de taller
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int numeroSerie = rs.getInt("numero_serie");
                String revision = rs.getString("revision");
                String tipoVehiculo = rs.getString("tipo_viniculo");
                int maxPasajeros = rs.getInt("max_pasajeros");
                int taller = rs.getInt("taller");
                String enTaller = taller == 1 ? "Sí" : "No";

                vehiculos.add("Serie: " + numeroSerie + ", Revisión: " + revision + ", Tipo: " + tipoVehiculo +
                        ", Máx. Pasajeros: " + maxPasajeros + ", En Taller: " + enTaller);
            }

            if (vehiculos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay vehículos registrados en este taller.");
            } else {
                // Escribir en el archivo
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehiculos.txt"))) {
                    for (String vehiculo : vehiculos) {
                        writer.write(vehiculo);
                        writer.newLine(); // Nueva línea después de cada vehículo
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al guardar el archivo.");
                }

                JOptionPane.showMessageDialog(null, "Vehículos guardados en vehiculos.txt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al listar los vehículos");
        }
    }



    public void agregarVehiculo(String numeroSerie, String revision, String tipoViniculo, String maxPasajeros, boolean enTaller) {
        Connection conn = Conexio.getConnection();
        try {
            String query = "INSERT INTO vehiculos (numero_serie, revision, tipo_vehiculo, max_pasajeros, taller) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(numeroSerie));
            stmt.setString(2, revision);
            stmt.setString(3, tipoViniculo);
            stmt.setInt(4, Integer.parseInt(maxPasajeros));
            stmt.setInt(5, enTaller ? 1 : 0);

            int result = stmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Vehículo agregado correctamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar vehículo");
        }
    }

    public void cambiarEstadoTaller(String numeroSerie, boolean enTaller) {
        Connection conn = Conexio.getConnection();
        try {
            if (numeroSerie.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar el número de serie");
                return;
            }
            String query = "UPDATE vehiculos SET taller = ? WHERE numero_serie = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, enTaller ? 1 : 0);
            stmt.setInt(2, Integer.parseInt(numeroSerie));
            int result = stmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Estado del vehículo actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún vehículo con ese número de serie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cambiar el estado");
        }
    }
}
