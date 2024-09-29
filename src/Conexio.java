import java.sql.*;

public class Conexio {
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
                String ciutatOifici = rs.getString("ciudad_trabaja");
                String sexo = rs.getString("sexo");
                boolean isAdmin = rs.getInt("isAdmin") == 1; // Verifica si es admin
                String role = determinarRolEmpleado(rs.getInt("num_empleado"), isAdmin);

                // Dependiendo del rol, creamos el tipo de empleado
                switch (role) {
                    case "admin":
                        // No necesitas crear un objeto específico para el admin, solo verifica si es admin
                        empleado = new Empleado(nombre, salari, edad, direccion, anosExp, sexo, ciutatOifici, isAdmin);
                        break;
                    case "mecanico":
                        String numTaller = obtenerTaller(rs.getInt("num_empleado"));
                        empleado = new MecanicoFrame(nombre, salari, edad, direccion, anosExp, sexo, numTaller, ciutatOifici, isAdmin);
                        break;
                    case "astronauta":
                        String primerVuelo = obtenerFechaPrimerVuelo(rs.getInt("num_empleado"));
                        String[] misionesYRango = obtenerMisionesAstronauta(rs.getInt("num_empleado"));
                        if (misionesYRango != null) {
                            int missionsOK = Integer.parseInt(misionesYRango[0]);
                            int missionsKO = Integer.parseInt(misionesYRango[1]);
                            String rangMilitar = misionesYRango[2];
                        empleado = new Astronauta(nombre, salari, edad, direccion, anosExp, sexo, primerVuelo, rangMilitar, missionsKO, missionsOK, ciutatOifici, isAdmin);
                        }
                        break;
                    case "espia":
                        String nombreClave = obtenerNombreClave(rs.getInt("num_empleado"));
                        String telefono = obtenerTelefono(rs.getInt("num_empleado"));
                        empleado = new Espia(nombre, salari, edad, direccion, anosExp, sexo, nombreClave, telefono, ciutatOifici, isAdmin);
                        break;
                    case "fisico":
                        String titolAcademic = obtenerTitulacionAcademica(rs.getInt("num_empleado"));
                        empleado = new FisicFrame(nombre, salari, edad, direccion, anosExp, sexo, titolAcademic, ciutatOifici, isAdmin);
                        break;

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
    private static String determinarRolEmpleado(int numEmpleado, boolean isAdmin) throws SQLException {
        // Si el empleado es administrador, retornar "admin"
        if (isAdmin) {
            return "admin";
        }

        // Consultamos las otras tablas para ver a qué rol pertenece si no es admin
        Connection conn = getConnection();
        String query = "SELECT 'mecanico' AS rol FROM mecanico WHERE num_empleado = ? " +
                "UNION ALL " +
                "SELECT 'astronauta' AS rol FROM astronauta WHERE num_empleado = ? " +
                "UNION ALL " +
                "SELECT 'espia' AS rol FROM espia WHERE num_empleado = ? " +
                "UNION ALL " +
                "SELECT 'fisico' AS rol FROM fisico WHERE num_empleado = ? ";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, numEmpleado);
        stmt.setInt(2, numEmpleado);
        stmt.setInt(3, numEmpleado);
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

    private static String[] obtenerMisionesAstronauta(int numEmpleado) throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT misiones_ok, misiones_ko, rang_militar FROM astronauta WHERE num_empleado = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, numEmpleado);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int misionesOk = rs.getInt("misiones_ok");
            int misionesKo = rs.getInt("misiones_ko");
            String rangMilitar = rs.getString("rang_militar");

            // Retornamos los valores en un array de String
            return new String[]{String.valueOf(misionesOk), String.valueOf(misionesKo), rangMilitar};
        }
        return null; // Si no se encuentran datos
    }


}
