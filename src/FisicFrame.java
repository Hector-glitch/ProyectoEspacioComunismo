import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FisicFrame {
    public static void main(String[] args) {
        FisicFrame f = new FisicFrame();
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
    JPanel AccioPanel;
    JLabel TitolAcademicL;
    JComboBox<String> planetasComboBox;
    JTextArea distanciaArea;
    JTextArea tempsArea; // Nou JTextArea per mostrar el temps calculat
    JTextArea areaTextArea;
    JTextArea costArea;
    JTextArea investigacioArea;


    // Mapa per a les distàncies dels planetes
    HashMap<String, String> distancias;

    FisicFrame() {
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
        frame.setTitle("Físic");
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

        TitolAcademicL = new JLabel("Titol academic: ");
        TitolAcademicL.setBounds(20, 150, 100, 20);
        GreyPanel.add(TitolAcademicL);

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



        //----------------------Altre panell---------------------------

        AccioPanel = new JPanel();
        AccioPanel.setBackground(Color.lightGray);
        AccioPanel.setLayout(null);
        AccioPanel.setBounds(240, 40, 200, 300);
        frame.add(AccioPanel);

        // Crear el JComboBox per als planetes
        String[] planetas = {"Triar Planeta", "Mercuri", "Venus", "Mart", "Júpiter", "Saturn", "Urà", "Neptú"};
        planetasComboBox = new JComboBox<>(planetas);
        planetasComboBox.setBounds(20, 10, 160, 30);
        AccioPanel.add(planetasComboBox);

        // Cambiar JLabel a JTextArea per mostrar la distància
        distanciaArea = new JTextArea("Distància des de la terra: ");
        distanciaArea.setBounds(20, 50, 160, 30);
        distanciaArea.setLineWrap(true);
        distanciaArea.setWrapStyleWord(true);
        distanciaArea.setEditable(false);
        distanciaArea.setOpaque(false);
        distanciaArea.setBorder(null);
        AccioPanel.add(distanciaArea);

        // Crear un JTextArea per mostrar el temps calculat
        tempsArea = new JTextArea("Temps de viatge:" + "          ");
        tempsArea.setBounds(20, 90, 100, 30); // Ajustar la posició i mida
        tempsArea.setLineWrap(true); // Permetre salt de línia
        tempsArea.setWrapStyleWord(true); // Ajustar per paraules senceres
        tempsArea.setEditable(false); // Fer que no sigui editable
        tempsArea.setOpaque(false); // Fer transparent
        tempsArea.setBorder(null); // Treure el borde
        AccioPanel.add(tempsArea);

        // Crear una nova JTextArea per mostrar l'àrea del planeta
        areaTextArea = new JTextArea("Àrea del planeta: ");
        areaTextArea.setBounds(20, 130, 120, 30); // Ajustar la posició i mida
        areaTextArea.setLineWrap(true); // Permetre salt de línia
        areaTextArea.setWrapStyleWord(true); // Ajustar per paraules senceres
        areaTextArea.setEditable(false); // Fer que no sigui editable
        areaTextArea.setOpaque(false); // Fer transparent
        areaTextArea.setBorder(null); // Treure el borde
        AccioPanel.add(areaTextArea);

        // Crear JTextArea per mostrar el cost
        costArea = new JTextArea("Cost per recórrer: ");
        costArea.setBounds(20, 170, 200, 60);
        costArea.setLineWrap(true);
        costArea.setWrapStyleWord(true);
        costArea.setEditable(false);
        costArea.setOpaque(false);
        costArea.setBorder(null);
        AccioPanel.add(costArea);

        // TextArea per mostrar la classificació d'investigació econòmica
        investigacioArea = new JTextArea();
        investigacioArea.setBounds(20, 210, 130, 60);
        investigacioArea.setLineWrap(true);
        investigacioArea.setWrapStyleWord(true);
        investigacioArea.setEditable(false);
        investigacioArea.setOpaque(false);
        investigacioArea.setBorder(null);
        AccioPanel.add(investigacioArea);



        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy ");


        planetasComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String planetaSeleccionado = (String) planetasComboBox.getSelectedItem();
                String distancia = distancias.get(planetaSeleccionado);
                distanciaArea.setText("Distància des de la terra: " + distancia);

                // Calcular el temps
                double temps = Fisic.CalcTemps(planetaSeleccionado);
                if (temps != -1) {
                    tempsArea.setText("Temps de viatge: " + decimalFormat.format(temps) + " anys");
                } else {
                    tempsArea.setText("Temps de viatge: N/A");
                }

                // Calcular i mostrar l'àrea del planeta
                double area = Fisic.CalcArea(planetaSeleccionado);
                if (area != -1) {
                    areaTextArea.setText("Àrea del planeta: " + decimalFormat.format(area) + " km²");
                } else {
                    areaTextArea.setText("Àrea del planeta: N/A");
                }

                // Calcular i mostrar el cost per recórrer l'àrea del planeta
                double cost = Fisic.CalcCostRecorre(area);
                if (cost != -1) {
                    costArea.setText("Cost per recórrer: " + decimalFormat.format(cost) + " rubles");
                } else {
                    costArea.setText("Cost per recórrer: N/A");
                }

                // Determinar si és el planeta més econòmic d'investigar
                String planetaEconomico = Fisic.CompararCostos();
                if (planetaSeleccionado.equals(planetaEconomico)) {
                    investigacioArea.setText("Inversio en l'investigacio: Es el mes economic");
                } else {
                    investigacioArea.setText("Inversio en l'investigacio: No és el més econòmic");
                }
            }
        });

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
