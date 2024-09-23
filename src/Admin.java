import javax.swing.*;

public class Admin {
    JFrame frame;
    JMenuBar menuBar;
    JMenu mecanicMenu;

    Admin(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin");
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centrar el frame

        menuBar = new JMenuBar();
        mecanicMenu = new JMenu("Mecànic");


        frame.setJMenuBar(menuBar);
        menuBar.add(mecanicMenu);

    }
}
