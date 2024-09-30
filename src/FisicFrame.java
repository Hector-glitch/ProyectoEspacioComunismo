import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FisicFrame extends Empleado implements ActionListener {

    JFrame frame;
    JLabel nomL, salariL, edatL, adreçaL, expL, ciutatL, sexeL, TitolAcademicL;
    JPanel GreyPanel, AccioPanel, backgroundPanel;
    JComboBox<String> planetasComboBox;
    JTextArea distanciaArea, tempsArea, areaTextArea, costArea, investigacioArea;
    HashMap<String, String> distancias;
    private String titolAcademic;

    public FisicFrame(String nombre, int salari, int edad, String direccion, String anosDeExperiencia, String sexo, String titolAcademic, String ciutatOifici, boolean isAdmin) {
        super(nombre, salari, edad, direccion, anosDeExperiencia, sexo, ciutatOifici, isAdmin);
        this.titolAcademic = titolAcademic;
        distancias = new HashMap<>();
        distancias.put("Triar Planeta", " ");
        distancias.put("Mercuri", "101.000.000 Km");
        distancias.put("Venus", "40.000.000 Km");
        distancias.put("Mart", "58.000.000 Km");
        distancias.put("Júpiter", "594.000.000 Km");
        distancias.put("Saturn", "1.207.000.000 Km");
        distancias.put("Urà", "2.601.000.000 Km");
        distancias.put("Neptú", "4.306.000.000 Km");

        // Configuración de la ventana
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Físic");
        frame.setSize(510, 420);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        ImageIcon icon = new ImageIcon("src/Imatges/CCCP.png");
        Image scaledIcon = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        frame.setIconImage(icon.getImage());

        // Panel de fondo
        backgroundPanel = new BackgroundPanel(); // Crear el panel de fondo
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(backgroundPanel);

        // Panel gris para la información del físico
        GreyPanel = new JPanel();
        GreyPanel.setBackground(new Color(255, 255, 255, 200)); // Fondo blanco con transparencia
        GreyPanel.setLayout(null);
        GreyPanel.setBounds(20, 40, 200, 300);
        backgroundPanel.add(GreyPanel);

        // Etiquetas de información
        nomL = new JLabel("Nom: " + nombre);
        nomL.setBounds(20, 10, 80, 20);
        GreyPanel.add(nomL);

        salariL = new JLabel("Salari: " + salari + "K");
        salariL.setBounds(20, 30, 80, 20);
        GreyPanel.add(salariL);

        edatL = new JLabel("Edat: " + edad);
        edatL.setBounds(20, 50, 80, 20);
        GreyPanel.add(edatL);

        adreçaL = new JLabel("Adreça: " + direccion);
        adreçaL.setBounds(20, 70, 150, 20);
        GreyPanel.add(adreçaL);

        expL = new JLabel("Anys Exp.: " + anosDeExperiencia);
        expL.setBounds(20, 90, 80, 20);
        GreyPanel.add(expL);

        ciutatL = new JLabel("Ciutat d'Ofici: " + ciutatOifici);
        ciutatL.setBounds(20, 110, 120, 20);
        GreyPanel.add(ciutatL);

        sexeL = new JLabel("Sexe: " + sexo);
        sexeL.setBounds(20, 130, 80, 20);
        GreyPanel.add(sexeL);

        TitolAcademicL = new JLabel("Titol academic: " + titolAcademic);
        TitolAcademicL.setBounds(20, 150, 150, 20);
        GreyPanel.add(TitolAcademicL);

        // Botones de fichaje de entrada y salida
        JButton entradaButton = new JButton("Fitxar entrada");
        entradaButton.setBounds(20, 180, 120, 30);
        GreyPanel.add(entradaButton);

        JButton sortidaButton = new JButton("Fitxar sortida");
        sortidaButton.setBounds(20, 220, 120, 30);
        sortidaButton.setEnabled(false);
        GreyPanel.add(sortidaButton);

        JLabel entradaHoraLabel = new JLabel("Hora entrada:");
        entradaHoraLabel.setBounds(20, 255, 120, 20);
        GreyPanel.add(entradaHoraLabel);

        JLabel entradaHoraValor = new JLabel(" ");
        entradaHoraValor.setBounds(100, 255, 200, 20);
        GreyPanel.add(entradaHoraValor);

        JLabel sortidaHoraLabel = new JLabel("Hora sortida:");
        sortidaHoraLabel.setBounds(20, 270, 120, 20);
        GreyPanel.add(sortidaHoraLabel);

        JLabel sortidaHoraValor = new JLabel(" ");
        sortidaHoraValor.setBounds(100, 270, 200, 20);
        GreyPanel.add(sortidaHoraValor);

        // Segundo panel para la acción
        AccioPanel = new JPanel();
        AccioPanel.setBackground(new Color(255, 255, 255, 200)); // Fondo blanco con transparencia
        AccioPanel.setLayout(null);
        AccioPanel.setBounds(240, 40, 200, 300);
        backgroundPanel.add(AccioPanel);

        // ComboBox para seleccionar el planeta
        String[] planetas = {"Triar Planeta", "Mercuri", "Venus", "Mart", "Júpiter", "Saturn", "Urà", "Neptú"};
        planetasComboBox = new JComboBox<>(planetas);
        planetasComboBox.setBounds(20, 10, 160, 30);
        AccioPanel.add(planetasComboBox);

        // TextAreas para la información de distancias, tiempo, área, etc.
        distanciaArea = new JTextArea(" ");
        distanciaArea.setBounds(20, 50, 160, 30);
        distanciaArea.setEditable(false);
        distanciaArea.setOpaque(false);
        distanciaArea.setBorder(null);
        AccioPanel.add(distanciaArea);

        tempsArea = new JTextArea(" ");
        tempsArea.setBounds(20, 90, 100, 30);
        tempsArea.setEditable(false);
        tempsArea.setOpaque(false);
        tempsArea.setBorder(null);
        AccioPanel.add(tempsArea);

        areaTextArea = new JTextArea(" ");
        areaTextArea.setBounds(20, 130, 120, 30);
        areaTextArea.setEditable(false);
        areaTextArea.setOpaque(false);
        areaTextArea.setBorder(null);
        AccioPanel.add(areaTextArea);

        costArea = new JTextArea(" ");
        costArea.setBounds(20, 170, 200, 60);
        costArea.setEditable(false);
        costArea.setOpaque(false);
        costArea.setBorder(null);
        AccioPanel.add(costArea);

        investigacioArea = new JTextArea(" ");
        investigacioArea.setBounds(20, 210, 130, 60);
        investigacioArea.setEditable(false);
        investigacioArea.setOpaque(false);
        investigacioArea.setBorder(null);
        AccioPanel.add(investigacioArea);

        // Acción del ComboBox
        planetasComboBox.addActionListener(e -> {
            String planetaSeleccionado = (String) planetasComboBox.getSelectedItem();
            String distancia = distancias.get(planetaSeleccionado);
            if ("Triar Planeta".equals(planetaSeleccionado)) {
                distanciaArea.setText(" ");
            } else {
                distanciaArea.setText("Distància des de la terra: " + distancia);
            }

            double temps = Fisic.CalcTemps(planetaSeleccionado);
            if (temps != -1) {
                tempsArea.setText("Temps de viatge: " + new DecimalFormat("#,###.00").format(temps) + " anys");
            } else {
                tempsArea.setText(" ");
            }
        });

        // Mostrar el frame
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    // Panel de fondo personalizado
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = ImageIO.read(new File("src/Imatges/FisicBK.jpg")); // Cambia esta ruta por la ruta de tu imagen
            } catch (IOException e) {
                e.printStackTrace(); // Muestra el error en la consola
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
