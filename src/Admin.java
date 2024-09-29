import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Admin {
    JFrame frame;
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JButton addButton, editButton, deleteButton;

    public static void main(String[] args) {
        new Admin();
    }

    Admin() {
        // Crear el frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin - Gestión de Espías");
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        // Tabla para mostrar espías
        String[] columnNames = {"Número Empleado", "Usuario", "Nombre Clave", "Teléfono Contacto"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 540, 200);
        frame.add(scrollPane);

        // Botón para añadir
        addButton = new JButton("Añadir Espía");
        addButton.setBounds(20, 240, 150, 30);
        frame.add(addButton);

        // Botón para editar
        editButton = new JButton("Editar Espía");
        editButton.setBounds(190, 240, 150, 30);
        frame.add(editButton);

        // Botón para borrar
        deleteButton = new JButton("Borrar Espía");
        deleteButton.setBounds(360, 240, 150, 30);
        frame.add(deleteButton);

        // Mostrar espías en la tabla
        cargarEspias();

        // Añadir acción a los botones
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEspia();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarEspia();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrarEspia();
            }
        });

        frame.setVisible(true);
    }

    // Método para cargar espías desde la base de datos a la tabla
    private void cargarEspias() {
        Connection conn = conexio.getConnection();
        String query = "SELECT empleados.num_empleado, empleados.username, espia.nombre_clave, espia.telefono_contacto " +
                "FROM empleados INNER JOIN espia ON empleados.num_empleado = espia.num_empleado";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Limpiar la tabla antes de cargar
            tableModel.setRowCount(0);

            // Cargar los datos en el modelo de la tabla
            while (rs.next()) {
                int numEmpleado = rs.getInt("num_empleado");
                String username = rs.getString("username");
                String nombreClave = rs.getString("nombre_clave");
                String telefono = rs.getString("telefono_contacto");

                // Añadir fila a la tabla
                tableModel.addRow(new Object[]{numEmpleado, username, nombreClave, telefono});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para agregar un nuevo espía
    private void agregarEspia() {
        // Crear un cuadro de diálogo para añadir un espía
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField nombreClaveField = new JTextField();
        JTextField telefonoField = new JTextField();

        Object[] message = {
                "Usuario:", usernameField,
                "Contraseña:", passwordField,
                "Nombre Clave:", nombreClaveField,
                "Teléfono Contacto:", telefonoField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Añadir Espía", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String nombreClave = nombreClaveField.getText();
            String telefono = telefonoField.getText();

            // Insertar en la base de datos
            try {
                Connection conn = conexio.getConnection();
                String insertEmpleadoQuery = "INSERT INTO empleados (username, password) VALUES (?, ?)";
                PreparedStatement psEmpleado = conn.prepareStatement(insertEmpleadoQuery, Statement.RETURN_GENERATED_KEYS);
                psEmpleado.setString(1, username);
                psEmpleado.setString(2, password);
                psEmpleado.executeUpdate();

                // Obtener el num_empleado generado
                ResultSet rs = psEmpleado.getGeneratedKeys();
                int numEmpleado = 0;
                if (rs.next()) {
                    numEmpleado = rs.getInt(1);
                }

                // Insertar en la tabla espía
                String insertEspiaQuery = "INSERT INTO espia (num_empleado, nombre_clave, telefono_contacto) VALUES (?, ?, ?)";
                PreparedStatement psEspia = conn.prepareStatement(insertEspiaQuery);
                psEspia.setInt(1, numEmpleado);
                psEspia.setString(2, nombreClave);
                psEspia.setString(3, telefono);
                psEspia.executeUpdate();

                // Recargar la tabla
                cargarEspias();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para editar un espía existente
    private void editarEspia() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un espía para editar.");
            return;
        }

        // Obtener datos del espía seleccionado
        int numEmpleado = (int) tableModel.getValueAt(selectedRow, 0);
        String username = (String) tableModel.getValueAt(selectedRow, 1);
        String nombreClave = (String) tableModel.getValueAt(selectedRow, 2);
        String telefono = (String) tableModel.getValueAt(selectedRow, 3);

        // Crear un cuadro de diálogo para editar el espía
        JTextField usernameField = new JTextField(username);
        JTextField passwordField = new JTextField(); // Nueva contraseña si se quiere cambiar
        JTextField nombreClaveField = new JTextField(nombreClave);
        JTextField telefonoField = new JTextField(telefono);

        Object[] message = {
                "Usuario:", usernameField,
                "Contraseña (dejar en blanco si no se cambia):", passwordField,
                "Nombre Clave:", nombreClaveField,
                "Teléfono Contacto:", telefonoField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Editar Espía", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String newUsername = usernameField.getText();
            String newPassword = passwordField.getText();
            String newNombreClave = nombreClaveField.getText();
            String newTelefono = telefonoField.getText();

            try {
                Connection conn = conexio.getConnection();
                // Actualizar tabla empleados
                String updateEmpleadoQuery = "UPDATE empleados SET username = ? " + (newPassword.isEmpty() ? "" : ", password = ?") + " WHERE num_empleado = ?";
                PreparedStatement psEmpleado = conn.prepareStatement(updateEmpleadoQuery);
                psEmpleado.setString(1, newUsername);
                if (!newPassword.isEmpty()) {
                    psEmpleado.setString(2, newPassword);
                    psEmpleado.setInt(3, numEmpleado);
                } else {
                    psEmpleado.setInt(2, numEmpleado);
                }
                psEmpleado.executeUpdate();

                // Actualizar tabla espía
                String updateEspiaQuery = "UPDATE espia SET nombre_clave = ?, telefono_contacto = ? WHERE num_empleado = ?";
                PreparedStatement psEspia = conn.prepareStatement(updateEspiaQuery);
                psEspia.setString(1, newNombreClave);
                psEspia.setString(2, newTelefono);
                psEspia.setInt(3, numEmpleado);
                psEspia.executeUpdate();

                // Recargar la tabla
                cargarEspias();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para borrar un espía
    private void borrarEspia() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un espía para borrar.");
            return;
        }

        int numEmpleado = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este espía?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection conn = conexio.getConnection();
                String deleteEspiaQuery = "DELETE FROM espia WHERE num_empleado = ?";
                PreparedStatement psEspia = conn.prepareStatement(deleteEspiaQuery);
                psEspia.setInt(1, numEmpleado);
                psEspia.executeUpdate();

                String deleteEmpleadoQuery = "DELETE FROM empleados WHERE num_empleado = ?";
                PreparedStatement psEmpleado = conn.prepareStatement(deleteEmpleadoQuery);
                psEmpleado.setInt(1, numEmpleado);
                psEmpleado.executeUpdate();

                // Recargar la tabla
                cargarEspias();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

