import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Astronauta extends Empleado {
    JFrame frame;
    JLabel nomL;
    JLabel edatL;
    JLabel adreçaL;
    JLabel sexeL;
    JLabel rangL;
    JLabel missionsOKL;
    JLabel missionsKOL;
    JPanel GreyPanel;
    JPanel AccioPanel;
    JTextField mensajeField;
    JTextArea mensajeEncriptadoArea; // Cambiado a JTextArea
    JLabel coordenadasLabel;

    private String fechaPrimerVuelo;

    // Constructor modificado: quitar apellido
    public Astronauta(String nombre, int salari, int edad, String direccion, String anosDeExperiencia, String sexo, String fechaPrimerVuelo) {
        super(nombre, edad, salari, direccion, anosDeExperiencia, sexo);
        this.fechaPrimerVuelo = fechaPrimerVuelo;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Astronauta");
        frame.setSize(600, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centrar el frame

        GreyPanel = new JPanel();
        GreyPanel.setBackground(Color.gray);
        GreyPanel.setLayout(null);
        GreyPanel.setBounds(20,40,200,300);
        frame.add(GreyPanel);

        nomL = new JLabel("Nom: ");
        nomL.setBounds(20,10,80,20);
        GreyPanel.add(nomL);

        edatL = new JLabel("Edad: ");
        edatL.setBounds(20,30,80,20);
        GreyPanel.add(edatL);

        adreçaL = new JLabel("Adreça: ");
        adreçaL.setBounds(20,50,80,20);
        GreyPanel.add(adreçaL);

        sexeL = new JLabel("Sexe: ");
        sexeL.setBounds(20,70,80,20);
        GreyPanel.add(sexeL);

        rangL = new JLabel ("Rang Militar: ");
        rangL.setBounds(20,90,80,20);
        GreyPanel.add(rangL);

        missionsOKL = new JLabel ("Missions OK: ");
        missionsOKL.setBounds(20,110,80,20);
        GreyPanel.add(missionsOKL);

        missionsKOL = new JLabel ("Missions KO: ");
        missionsKOL.setBounds(20,130,80,20);
        GreyPanel.add(missionsKOL);

        AccioPanel = new JPanel();
        AccioPanel.setBackground(Color.lightGray);
        AccioPanel.setLayout(null);
        AccioPanel.setBounds(240, 40, 300, 300);
        frame.add(AccioPanel);

        mensajeField = new JTextField();
        mensajeField.setBounds(20, 20, 260, 30);
        AccioPanel.add(mensajeField);

        JButton enviarButton = new JButton("Enviar");
        enviarButton.setBounds(20, 60, 100, 30);
        AccioPanel.add(enviarButton);

        mensajeEncriptadoArea = new JTextArea();
        mensajeEncriptadoArea.setBounds(20, 100, 260, 100); // Se ajusta el tamaño para mostrar varias líneas
        mensajeEncriptadoArea.setLineWrap(true); // Habilitar el ajuste de línea
        mensajeEncriptadoArea.setWrapStyleWord(true); // Ajustar líneas completas
        mensajeEncriptadoArea.setEditable(false); // Hacer que el área de texto sea solo de lectura
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
        generarCoordenadasButton.setBounds(20, 210, 160, 30);
        AccioPanel.add(generarCoordenadasButton);

        coordenadasLabel = new JLabel("Coordenadas: ");
        coordenadasLabel.setBounds(20, 250, 260, 30);
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

    public static void main(String[] args) {
        new Astronauta("Nombre", 1234, 25, "Direccion", "Anos de Experiencia", "Sexo", "Primer Vuelo");
    }
}
