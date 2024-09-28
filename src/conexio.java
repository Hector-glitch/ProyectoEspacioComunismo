import java.sql.*;

public class conexio {
    private static String url = "jdbc:mysql://localhost:3306/comunismo";
    private static String user = "root";
    private static String pass = "admin";
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Connected to the database");
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
            }
        }
        return conn;
    }

    // Método para verificar las credenciales de usuario
    public static Empleado autenticarUsuario(String username, String password) {
        Connection conn = getConnection();
        Empleado empleado = null;

        try {
            String query = "SELECT * FROM empleados WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int salari = rs.getInt("salario");
                int edad = rs.getInt("edad");
                String direccion = rs.getString("direccion");
                String anosExp = rs.getString("anos_experiencia");
                String sexo = rs.getString("sexo");
                int isAdmin = rs.getInt("isAdmin");
                String role = determinarRolEmpleado(rs.getInt("num_empleado"));

                // Dependiendo del rol, creamos el tipo de empleado
                switch (role) {
                    case "mecanico":
                        String numTaller = obtenerTaller(rs.getInt("num_empleado"));
                        empleado = new Mecanico(nombre, salari, edad, direccion, anosExp, sexo, numTaller);
                        break;
                    case "astronauta":
                        String primerVuelo = obtenerFechaPrimerVuelo(rs.getInt("num_empleado"));
                        empleado = new Astronauta(nombre, salari, edad, direccion, anosExp, sexo, primerVuelo);
                        break;
                    case "espia":
                        String nombreClave = obtenerNombreClave(rs.getInt("num_empleado"));
                        String telefono = obtenerTelefono(rs.getInt("num_empleado"));
                        empleado = new Espia(nombre, salari, edad, direccion, anosExp, sexo, nombreClave, telefono);
                        break;
                    case "fisico":  // Nueva condición para Fisico
                        String titolAcademic = obtenerTitulacionAcademica(rs.getInt("num_empleado"));
                        empleado = new FisicFrame(nombre, salari, edad, direccion, anosExp, sexo, titolAcademic);
                        break;

                    // Añadir más casos según los roles
                    default:
                        System.out.println("Rol no encontrado");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al autenticar usuario: " + e.getMessage());
        }

        return empleado;
    }

    // Método auxiliar para determinar el rol del empleado (ejemplo para obtener rol por tabla)
    private static String determinarRolEmpleado(int numEmpleado) throws SQLException {
        // Consultamos las otras tablas para ver a qué rol pertenece
        Connection conn = getConnection();
        String query = "SELECT 'mecanico' AS rol FROM mecanico WHERE num_empleado = ? " +
                "UNION ALL " +
                "SELECT 'astronauta' AS rol FROM astronauta WHERE num_empleado = ? " +
                "UNION ALL " +
                "SELECT 'espia' AS rol FROM espia WHERE num_empleado = ? " + // Cambiado a num_empleado
                "UNION ALL " +
                "SELECT 'fisico' AS rol FROM fisico WHERE num_empleado = ? ";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, numEmpleado);
        stmt.setInt(2, numEmpleado);
        stmt.setInt(3, numEmpleado); // Cambiado a num_empleado
        stmt.setInt(4, numEmpleado);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("rol");
        }
        return "desconocido";
    }

    // Método para obtener el taller del mecánico
    private static String obtenerTaller(int numEmpleado) throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT num_taller FROM mecanico WHERE num_empleado = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, numEmpleado);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("num_taller");
        }
        return null;
    }

    private static String obtenerTitulacionAcademica(int numEmpleado) throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT titulacion FROM fisico WHERE num_empleado = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, numEmpleado);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("titulacion");
        }
        return null;
    }

    // Método para obtener la fecha del primer vuelo del astronauta
    private static String obtenerFechaPrimerVuelo(int numEmpleado) throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT primer_vuelo FROM astronauta WHERE num_empleado = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, numEmpleado);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("primer_vuelo"); // Devuelve la fecha en formato String
        }
        return null; // Retorna null si no se encuentra la fecha
    }

    private static String obtenerNombreClave(int numEmpleado) throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT nombre_clave FROM espia WHERE num_empleado = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, numEmpleado);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("nombre_clave");
        }
        return null;
    }

    // Método para obtener el teléfono del espía
    private static String obtenerTelefono(int numEmpleado) throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT telefono_contacto FROM espia WHERE num_empleado = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, numEmpleado);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("telefono_contacto");
        }
        return null;
    }

}
