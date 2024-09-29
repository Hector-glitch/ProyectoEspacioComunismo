import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mecanico extends Empleado implements ActionListener {
    JFrame frame;
    JLabel nomL, salariL, edatL, adreçaL, expL, ciutatL, sexeL, numTallerL;
    JPanel GreyPanel;
    private String numeroDeTaller;

    // Constructor modificado: eliminar apellido
    public Mecanico(String nombre, int salari, int edad, String direccion, String anosDeExperiencia, String sexo, String numTaller, String ciutatOfici, boolean isAdmin) {
        super(nombre, salari, edad, direccion, anosDeExperiencia, sexo, ciutatOfici, isAdmin); // Llama al constructor de Empleado correctamente
        this.numeroDeTaller = numTaller;

        // Configuración de la ventana
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mecànic");
        frame.setSize(520, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centrar el frame

        GreyPanel = new JPanel();
        GreyPanel.setBackground(Color.gray);
        GreyPanel.setLayout(null);
        GreyPanel.setBounds(20, 40, 200, 300);
        frame.add(GreyPanel);

        // Mostrar los datos en la interfaz
        nomL = new JLabel("Nom: " + nombre);
        nomL.setBounds(20, 10, 200, 20);
        GreyPanel.add(nomL);

        salariL = new JLabel("Salari: " + salari + "K"); // Aquí puedes obtener y mostrar el salario desde la base de datos si es necesario
        salariL.setBounds(20, 30, 200, 20);
        GreyPanel.add(salariL);

        edatL = new JLabel("Edat: " + edad);
        edatL.setBounds(20, 50, 200, 20);
        GreyPanel.add(edatL);

        adreçaL = new JLabel("Adreça: " + direccion);
        adreçaL.setBounds(20, 70, 200, 20);
        GreyPanel.add(adreçaL);

        expL = new JLabel("Anys Exp.: " + anosDeExperiencia);
        expL.setBounds(20, 90, 200, 20);
        GreyPanel.add(expL);

        ciutatL = new JLabel("Ciutat d'Ofici: ");
        ciutatL.setBounds(20, 110, 200, 20);
        GreyPanel.add(ciutatL);

        sexeL = new JLabel("Sexe: " + sexo);
        sexeL.setBounds(20, 130, 200, 20);
        GreyPanel.add(sexeL);

        numTallerL = new JLabel("Num. Taller: " + numeroDeTaller);
        numTallerL.setBounds(20, 150, 200, 20);
        GreyPanel.add(numTallerL);

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
        JLabel entradaHoraValor = new JLabel(" ");
        entradaHoraValor.setBounds(100, 255, 200, 20); // Ajustar la posició i mida
        GreyPanel.add(entradaHoraValor);

        // Etiqueta per mostrar l'hora de sortida
        JLabel sortidaHoraLabel = new JLabel("Hora sortida:");
        sortidaHoraLabel.setBounds(20, 270, 120, 20); // Posició per la etiqueta
        GreyPanel.add(sortidaHoraLabel);

        // Etiqueta que canviarà per mostrar la data i hora de sortida
        JLabel sortidaHoraValor = new JLabel(" ");
        sortidaHoraValor.setBounds(100, 270, 200, 20); // Ajustar la posició i mida
        GreyPanel.add(sortidaHoraValor);

        frame.revalidate();
        frame.repaint();

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
    }

    public void actionPerformed(ActionEvent e) {

    }
}
