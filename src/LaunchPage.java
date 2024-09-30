import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class LaunchPage implements ActionListener {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LaunchPage();
        });
    }

    JFrame frame;
    JButton LoginButton;
    JLabel UserLabel;
    JTextField UserTextF;
    JLabel ContraLabel;
    JPasswordField ContraTextF;

    LaunchPage() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Programa Espacial URSS");
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null); // Centrar el frame
        frame.setResizable(false);

        ImageIcon icon = new ImageIcon("src/Imatges/CCCP.png");
        Image scaledIcon = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        frame.setIconImage(icon.getImage());

        // Añadir el panel con la imagen de fondo
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);
        frame.add(backgroundPanel);

        // Crear un panel para los componentes del login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(50, 60, 320, 280); // Cambia el tamaño y la posición según sea necesario
        loginPanel.setOpaque(true); // Hacer el panel semitransparente
        // Establecer un color de fondo semitransparente
        loginPanel.setBackground(new Color(255, 255, 255, 200)); // Color blanco con 200 de opacidad

        // Crear y añadir componentes al panel de login
        UserLabel = new JLabel("Usuari");
        UserLabel.setBounds(20, 20, 200, 40);
        loginPanel.add(UserLabel);

        UserTextF = new JTextField();
        UserTextF.setBounds(20, 60, 280, 30);
        loginPanel.add(UserTextF);

        ContraLabel = new JLabel("Contrasenya");
        ContraLabel.setBounds(20, 100, 200, 40);
        loginPanel.add(ContraLabel);

        ContraTextF = new JPasswordField();
        ContraTextF.setBounds(20, 140, 280, 30);
        loginPanel.add(ContraTextF);

        LoginButton = new JButton("Login");
        LoginButton.setBounds(20, 180, 280, 40);
        LoginButton.setFocusable(false);
        LoginButton.addActionListener(this);
        loginPanel.add(LoginButton);

        // Añadir el panel de login al panel de fondo
        backgroundPanel.add(loginPanel);

        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LoginButton) {
            String username = UserTextF.getText();
            String password = ContraTextF.getText();

            Empleado empleado = Conexio.autenticarUsuario(username, password);

            if (empleado != null) {
                // Manejar el login del usuario
                if (empleado.isAdmin()) {
                    new Admin(); // Abre el frame de administrador
                    frame.dispose(); // Cierra el frame de login
                } else {
                    // Rediriges según el tipo de empleado
                    if (empleado instanceof MecanicoFrame) {
                        MecanicoFrame MecanicoFrame = (MecanicoFrame) empleado;
                        frame.dispose();
                    } else if (empleado instanceof FisicFrame) {
                        FisicFrame FisicFrame = (FisicFrame) empleado;
                        frame.dispose();
                    } else if (empleado instanceof Astronauta) {
                        Astronauta AstronautaFrame = (Astronauta) empleado;
                        frame.dispose();
                    } else if (empleado instanceof Espia) {
                        Espia EspiaFrame = (Espia) empleado;
                        frame.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Credencials Incorrectes");
            }
        }
    }

    // Clase que representa el panel con la imagen de fondo
    static class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = ImageIO.read(new File("src/Imatges/LoginBK.jpg")); // Cambia esto a la ruta de tu imagen
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        }
    }
}
