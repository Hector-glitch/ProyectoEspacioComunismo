import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Admin {
    public static void main(String[] args) {
        Admin f = new Admin();
        getConnection();
    }

    JFrame frame;
    JTabbedPane tabbedPane;
    private static String url = "jdbc:mysql://localhost:3306/comunismo";
    private static String user = "root";
    private static String pass = "admin";
    private static Connection conn;

    Admin(){
        // Crear el frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin");
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); // Centrar el frame

        // Crear el JTabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 0, 420, 420);

        // Crear paneles para cada pestaña
        JPanel gestionarPanel = new JPanel();
        gestionarPanel.add(new JLabel("Contenido de Gestionar"));

        JPanel editarPanel = new JPanel();
        editarPanel.add(new JLabel("Contenido de Editar"));

        // Añadir los paneles al tabbedPane con sus títulos
        tabbedPane.add("Gestionar", gestionarPanel);
        tabbedPane.add("Editar", editarPanel);

        // Añadir el tabbedPane al frame
        frame.add(tabbedPane);
        frame.setVisible(true);

    }


    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            System.out.println("Not Connected" + e);
        }
        return conn;
    }

}
