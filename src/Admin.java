import javax.swing.*;

public class Admin {
    public static void main(String[] args) {
        Admin f = new Admin();
    }
    JFrame frame;

    Admin(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin");
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centrar el frame


    }
}
