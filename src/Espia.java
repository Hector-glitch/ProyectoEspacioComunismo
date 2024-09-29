import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Espia extends Empleado {
    JFrame frame;
    JLabel NomClauL;
    JLabel TelefonL;
    JTextField missatgeField;
    JLabel missatgeEncriptat;
    JLabel EncriptarL;

    private String nombreEnClave;
    private String telefonoContacto;

    Espia(String nombre, int salari, int edad, String direccion, String anosDeExperiencia, String sexo, String nombreClave, String telefono, String ciutatOifici, boolean isAdmin) {
        super(nombre, salari, edad, direccion, anosDeExperiencia, sexo, ciutatOifici, isAdmin);
        nombreEnClave = nombreClave;
        telefonoContacto = telefono;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Espia");
        frame.setSize(375, 420);
        frame.setLocationRelativeTo(null); // Centrar el frame

        // Añadir el panel con la imagen de fondo
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);
        frame.add(backgroundPanel);

        // Crear un panel para los componentes
        JPanel greyPanel = new JPanel();
        greyPanel.setLayout(null);  // Cambiar a null layout para posicionar manualmente los componentes
        greyPanel.setBounds(20, 40, 320, 300); // Ajustar el tamaño y posición
        greyPanel.setOpaque(true); // Hacer el panel semitransparente
        greyPanel.setBackground(new Color(192, 177, 177, 180)); // Color blanco con 200 de opacidad
        backgroundPanel.add(greyPanel);

        // Etiquetas para nombre en clave y teléfono
        NomClauL = new JLabel("Nom en clau: " + nombreEnClave);
        NomClauL.setBounds(20, 10, 200, 20);
        greyPanel.add(NomClauL);

        TelefonL = new JLabel("Telefon de contacte: " + telefonoContacto);
        TelefonL.setBounds(20, 30, 200, 20);
        greyPanel.add(TelefonL);

        EncriptarL = new JLabel("Encriptar Missatge");
        EncriptarL.setBounds(20, 60, 200, 20);
        EncriptarL.setFont(new Font("Arial", Font.BOLD, 12));
        greyPanel.add(EncriptarL);

        // Campo de texto para el mensaje
        missatgeField = new JTextField();
        missatgeField.setBounds(20, 80, 260, 30);
        greyPanel.add(missatgeField);

        // Botón para enviar el mensaje
        JButton enviarButton = new JButton("Encriptar");
        enviarButton.setBounds(20, 120, 100, 30);
        greyPanel.add(enviarButton);

        // Label para mostrar el mensaje encriptado
        missatgeEncriptat = new JLabel("Missatge encriptat: ");
        missatgeEncriptat.setBounds(20, 160, 260, 30);
        greyPanel.add(missatgeEncriptat);

        // Acción al pulsar el botón
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = missatgeField.getText();
                String encriptado = enviarMensajeEncriptado(mensaje);
                missatgeEncriptat.setText("Missatge encriptat: " + encriptado);
            }
        });

        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    private static String enviarMensajeEncriptado(String mensaje) {
        return mensaje.replaceAll("[qwrtypsdfghjklzxcvbnmñQWRTYPSDFGHJKLZXCVBNMÑ]", "");
    }

    // Clase que representa el panel con la imagen de fondo
    class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel() {
            try {
                // Cargar la imagen de fondo
                backgroundImage = ImageIO.read(new File("src/Imatges/EspiaBK.jpg")); // Cambia esto a la ruta de tu imagen
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
