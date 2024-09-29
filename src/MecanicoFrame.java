import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MecanicoFrame {
    public static void main(String[] args) {
        MecanicoFrame f = new MecanicoFrame();
    }

    JFrame frame;
    JLabel nomL;
    JLabel salariL;
    JLabel edatL;
    JLabel adreçaL;
    JLabel expL;
    JLabel ciutatL;
    JLabel sexeL;
    JPanel GreyPanel;
    JComboBox<String> planetasComboBox;
    JTextArea distanciaArea;
    JTextArea tempsArea; // Nou JTextArea per mostrar el temps calculat
    JTextArea areaTextArea;
    JTextArea costArea;
    JTextArea investigacioArea;


    // Mapa per a les distàncies dels planetes
    HashMap<String, String> distancias;

    MecanicoFrame() {
        // Inicialitzar el mapa de distàncies
        distancias = new HashMap<>();
        distancias.put("Triar Planeta", " ");
        distancias.put("Mercuri", "101.000.000 Km");
        distancias.put("Venus", "40.000.000 Km");
        distancias.put("Mart", "58.000.000 Km");
        distancias.put("Júpiter", "594.000.000 Km");
        distancias.put("Saturn", "1.207.000.000 Km");
        distancias.put("Urà", "2.601.000.000 Km");
        distancias.put("Neptú", "4.306.000.000 Km");

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mecánico");
        frame.setSize(510, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centrar el frame

        GreyPanel = new JPanel();
        GreyPanel.setBackground(Color.gray);
        GreyPanel.setLayout(null);
        GreyPanel.setBounds(20, 40, 200, 300);
        frame.add(GreyPanel);

        nomL = new JLabel("Nom: ");
        nomL.setBounds(20, 10, 80, 20);
        GreyPanel.add(nomL);

        salariL = new JLabel("Salari: ");
        salariL.setBounds(20, 30, 80, 20);
        GreyPanel.add(salariL);

        edatL = new JLabel("Edad: ");
        edatL.setBounds(20, 50, 80, 20);
        GreyPanel.add(edatL);

        adreçaL = new JLabel("Adreça: ");
        adreçaL.setBounds(20, 70, 80, 20);
        GreyPanel.add(adreçaL);

        expL = new JLabel("Anys Exp.: ");
        expL.setBounds(20, 90, 80, 20);
        GreyPanel.add(expL);

        ciutatL = new JLabel("Ciutat d'Ofici: ");
        ciutatL.setBounds(20, 110, 100, 20);
        GreyPanel.add(ciutatL);

        sexeL = new JLabel("Sexe: ");
        sexeL.setBounds(20, 130, 80, 20);
        GreyPanel.add(sexeL);

        // Botó per fitxar hora d'entrada
        JButton entradaButton = new JButton("Fitxar entrada");
        entradaButton.setBounds(20, 180, 120, 30); // Ajustar la posició i mida
        GreyPanel.add(entradaButton);

        // Botó per fitxar hora de sortida
        JButton sortidaButton = new JButton("Fitxar sortida");
        sortidaButton.setBounds(20, 220, 120, 30); // Ajustar la posició i mida
        sortidaButton.setEnabled(false); // Comença desactivat
        GreyPanel.add(sortidaButton);

        // Etiqueta per mostrar l'hora d'entrada
        JLabel entradaHoraLabel = new JLabel("Hora entrada:");
        entradaHoraLabel.setBounds(20, 255, 120, 20); // Posició per la etiqueta
        GreyPanel.add(entradaHoraLabel);

        // Etiqueta que canviarà per mostrar la data i hora d'entrada
        JLabel entradaHoraValor = new JLabel("N/A");
        entradaHoraValor.setBounds(100, 255, 200, 20); // Ajustar la posició i mida
        GreyPanel.add(entradaHoraValor);

        // Etiqueta per mostrar l'hora de sortida
        JLabel sortidaHoraLabel = new JLabel("Hora sortida:");
        sortidaHoraLabel.setBounds(20, 270, 120, 20); // Posició per la etiqueta
        GreyPanel.add(sortidaHoraLabel);

        // Etiqueta que canviarà per mostrar la data i hora de sortida
        JLabel sortidaHoraValor = new JLabel("N/A");
        sortidaHoraValor.setBounds(100, 270, 200, 20); // Ajustar la posició i mida
        GreyPanel.add(sortidaHoraValor);

        // TextArea per mostrar la classificació d'investigació econòmica
        investigacioArea = new JTextArea();
        investigacioArea.setBounds(20, 210, 130, 60);
        investigacioArea.setLineWrap(true);
        investigacioArea.setWrapStyleWord(true);
        investigacioArea.setEditable(false);
        investigacioArea.setOpaque(false);
        investigacioArea.setBorder(null);



        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy ");

        entradaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Capturar la data i hora actual
                LocalDateTime now = LocalDateTime.now();
                String formattedDateTime = now.format(dateTimeFormatter);

                // Actualitzar l'etiqueta amb la data i hora d'entrada
                entradaHoraValor.setText(formattedDateTime);

                // Desactivar el botó d'entrada i activar el botó de sortida
                entradaButton.setEnabled(false);
                sortidaButton.setEnabled(true);
            }
        });

// ActionListener per al botó de sortida
        sortidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Capturar la data i hora actual
                LocalDateTime now = LocalDateTime.now();
                String formattedDateTime = now.format(dateTimeFormatter);

                // Actualitzar l'etiqueta amb la data i hora de sortida
                sortidaHoraValor.setText(formattedDateTime);

                // Desactivar el botó de sortida i activar el botó d'entrada
                sortidaButton.setEnabled(false);
                entradaButton.setEnabled(true);
            }
        });



        frame.revalidate();
        frame.repaint();
    }
}
