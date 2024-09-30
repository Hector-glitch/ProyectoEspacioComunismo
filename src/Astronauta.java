import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Astronauta extends Empleado {
    JFrame frame;
    JLabel nomL;
    JLabel edatL;
    JLabel adreçaL;
    JLabel sexeL;
    JLabel rangL;
    JLabel missionsOKL;
    JLabel missionsKOL;
    JTextField mensajeField;
    JTextArea mensajeEncriptadoArea; // Cambiado a JTextArea
    JLabel coordenadasLabel;

    private String fechaPrimerVuelo;
    private int missionsOK;
    private int missionsKO;
    private String rangMilitar;

    // Constructor modificado: quitar apellido
    public Astronauta(String nombre, int salari, int edad, String direccion, String anosDeExperiencia, String sexo, String fechaPrimerVuelo,
                      String rangMili, int missionsOK, int missionsKO, String ciutatOifici, boolean isAdmin) {
        super(nombre, edad, salari, direccion, anosDeExperiencia, sexo, ciutatOifici, isAdmin);
        this.fechaPrimerVuelo = fechaPrimerVuelo;
        this.missionsOK = missionsOK;
        this.missionsKO = missionsKO;
        this.rangMilitar = rangMili;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Astronauta");
        frame.setSize(600, 420);
        frame.setLocationRelativeTo(null); // Centrar el frame
        frame.setResizable(false);

        // Añadir el panel con la imagen de fondo
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);
        frame.add(backgroundPanel);

        // Crear un panel gris para los datos del astronauta
        JPanel greyPanel = new JPanel();
        greyPanel.setLayout(null);
        greyPanel.setBounds(20, 40, 200, 300);
        greyPanel.setOpaque(true); // Hacer el panel semitransparente
        greyPanel.setBackground(new Color(255, 255, 255, 200)); // Color blanco con 200 de opacidad
        backgroundPanel.add(greyPanel);

        ImageIcon icon = new ImageIcon("src/Imatges/CCCP.png");
        Image scaledIcon = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        frame.setIconImage(icon.getImage());

        // Etiquetas para la información del astronauta
        nomL = new JLabel("Nom: " + nombre);
        nomL.setBounds(20, 10, 100, 20);
        greyPanel.add(nomL);

        edatL = new JLabel("Edad: " + edad);
        edatL.setBounds(20, 30, 100, 20);
        greyPanel.add(edatL);

        adreçaL = new JLabel("Adreça: " + direccion);
        adreçaL.setBounds(20, 50, 100, 20);
        greyPanel.add(adreçaL);

        sexeL = new JLabel("Sexe: " + sexo);
        sexeL.setBounds(20, 70, 100, 20);
        greyPanel.add(sexeL);

        rangL = new JLabel("Rang Militar: " + rangMili);
        rangL.setBounds(20, 90, 100, 20);
        greyPanel.add(rangL);

        missionsOKL = new JLabel("Missions OK: " + missionsOK);
        missionsOKL.setBounds(20, 110, 100, 20);
        greyPanel.add(missionsOKL);

        missionsKOL = new JLabel("Missions KO: " + missionsKO);
        missionsKOL.setBounds(20, 130, 100, 20);
        greyPanel.add(missionsKOL);

        // Crear el panel de acción
        JPanel AccioPanel = new JPanel();
        AccioPanel.setLayout(null);
        AccioPanel.setBounds(240, 40, 300, 300);
        AccioPanel.setOpaque(true); // Hacer el panel semitransparente
        backgroundPanel.add(AccioPanel);

        mensajeField = new JTextField();
        mensajeField.setBounds(20, 20, 260, 30);
        AccioPanel.add(mensajeField);

        JButton enviarButton = new JButton("Enviar");
        enviarButton.setBounds(20, 60, 100, 30);
        AccioPanel.add(enviarButton);

        mensajeEncriptadoArea = new JTextArea();
        mensajeEncriptadoArea.setBounds(20, 100, 260, 50);
        mensajeEncriptadoArea.setLineWrap(true);
        mensajeEncriptadoArea.setWrapStyleWord(true);
        mensajeEncriptadoArea.setEditable(false);
        mensajeEncriptadoArea.setOpaque(false);
        mensajeEncriptadoArea.setBorder(null);
        AccioPanel.add(mensajeEncriptadoArea);

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = mensajeField.getText();
                String encriptado = enviarMensajeEncriptado(mensaje);
                mensajeEncriptadoArea.setText(encriptado);
            }
        });

        JButton generarCoordenadasButton = new JButton("Generar Coordenadas");
        generarCoordenadasButton.setBounds(20, 150, 160, 30);
        AccioPanel.add(generarCoordenadasButton);

        coordenadasLabel = new JLabel("Coordenadas: ");
        coordenadasLabel.setBounds(20, 180, 260, 30);
        AccioPanel.add(coordenadasLabel);

        generarCoordenadasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                String latitud = generarCoordenadasDMS(random);
                String longitud = generarCoordenadasDMS(random);
                coordenadasLabel.setText("Coordenadas: " + latitud + " , " + longitud);
            }
        });

        frame.setVisible(true);
    }

    private static String generarCoordenadasDMS(Random random) {
        int grados = random.nextInt(180);
        int minutos = random.nextInt(60);
        int segundos = random.nextInt(60);
        return grados + "° " + minutos + "' " + segundos + "\"";
    }

    private static String enviarMensajeEncriptado(String mensaje) {
        return mensaje.replaceAll("[aeiouAEIOUäëïöüÄËÏÖÜáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙ]", "");
    }

    // Clase que representa el panel con la imagen de fondo
    class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel() {
            try {
                // Cargar la imagen de fondo
                backgroundImage = ImageIO.read(new File("src/Imatges/AstroBK.jpg")); // Cambia esto a la ruta de tu imagen
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
