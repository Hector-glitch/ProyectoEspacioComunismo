import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MecanicoFrame extends Empleado {
    private JFrame frame;
    private JLabel nomL, salariL, edatL, adreçaL, expL, ciutatL, sexeL, numTallerL;
    private JPanel GreyPanel, VehiculosPanel, backgroundPanel;
    private JTextField txtNumeroSerie, txtRevision, txtTipoViniculo, txtMaxPasajeros;
    private JCheckBox chkEnTaller;
    private JButton btnListarVehiculos, btnAgregarVehiculo, btnCambiarEstadoTaller;
    private String numeroDeTaller;
    private Mecanico mecanico;

    public MecanicoFrame(String nombre, int salari, int edad, String direccion, String anosDeExperiencia, String sexo, String numTaller, String ciutatOfici, boolean isAdmin) {
        super(nombre, salari, edad, direccion, anosDeExperiencia, sexo, ciutatOfici, isAdmin);
        this.numeroDeTaller = numTaller;
        this.mecanico = new Mecanico(numTaller);

        // Configuración de la ventana
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mecànic");
        frame.setSize(510, 420);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        // Panel de fondo
        backgroundPanel = new BackgroundPanel(); // Creación del panel de fondo
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.add(backgroundPanel);

        // Panel gris para la información del mecánico
        GreyPanel = new JPanel();
        GreyPanel.setBackground(new Color(255, 255, 255, 200)); // Color blanco con transparencia
        GreyPanel.setLayout(null);
        GreyPanel.setBounds(20, 20, 200, 350);
        backgroundPanel.add(GreyPanel);

        // Mostrar los datos en la interfaz
        nomL = new JLabel("Nom: " + nombre);
        nomL.setBounds(20, 10, 160, 20);
        GreyPanel.add(nomL);

        salariL = new JLabel("Salari: " + salari + "K");
        salariL.setBounds(20, 30, 160, 20);
        GreyPanel.add(salariL);

        edatL = new JLabel("Edat: " + edad);
        edatL.setBounds(20, 50, 160, 20);
        GreyPanel.add(edatL);

        adreçaL = new JLabel("Adreça: " + direccion);
        adreçaL.setBounds(20, 70, 160, 20);
        GreyPanel.add(adreçaL);

        expL = new JLabel("Anys Exp.: " + anosDeExperiencia);
        expL.setBounds(20, 90, 160, 20);
        GreyPanel.add(expL);

        ciutatL = new JLabel("Ciutat d'Ofici: " + ciutatOfici);
        ciutatL.setBounds(20, 110, 160, 20);
        GreyPanel.add(ciutatL);

        sexeL = new JLabel("Sexe: " + sexo);
        sexeL.setBounds(20, 130, 160, 20);
        GreyPanel.add(sexeL);

        numTallerL = new JLabel("Num. Taller: " + numeroDeTaller);
        numTallerL.setBounds(20, 150, 160, 20);
        GreyPanel.add(numTallerL);

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

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy ");

        entradaButton.addActionListener(e -> {
            LocalDateTime now = LocalDateTime.now();
            entradaHoraValor.setText(now.format(dateTimeFormatter));
            entradaButton.setEnabled(false);
            sortidaButton.setEnabled(true);
        });

        sortidaButton.addActionListener(e -> {
            LocalDateTime now = LocalDateTime.now();
            sortidaHoraValor.setText(now.format(dateTimeFormatter));
            sortidaButton.setEnabled(false);
            entradaButton.setEnabled(true);
        });

        // Panel para manejar los vehículos
        VehiculosPanel = new JPanel();
        VehiculosPanel.setBackground(new Color(255, 255, 255, 200)); // Color blanco con transparencia
        VehiculosPanel.setLayout(new GridLayout(10, 2));
        VehiculosPanel.setBounds(240, 20, 240, 350);
        backgroundPanel.add(VehiculosPanel);

        // Agregar los campos de texto y botones del panel de Vehículos
        JLabel lblNumeroSerie = new JLabel("Número de Serie:");
        txtNumeroSerie = new JTextField();
        JLabel lblRevision = new JLabel("Revisión:");
        txtRevision = new JTextField();
        JLabel lblTipoViniculo = new JLabel("Tipo de Viniculo:");
        txtTipoViniculo = new JTextField();
        JLabel lblMaxPasajeros = new JLabel("Máx. Pasajeros:");
        txtMaxPasajeros = new JTextField();
        chkEnTaller = new JCheckBox("En Taller");

        btnListarVehiculos = new JButton("Listar Vehículos");
        btnAgregarVehiculo = new JButton("Agregar Vehículo");
        btnCambiarEstadoTaller = new JButton("Cambiar Estado Taller");

        // Agregar componentes al panel de Vehículos
        VehiculosPanel.add(lblNumeroSerie);
        VehiculosPanel.add(txtNumeroSerie);
        VehiculosPanel.add(lblRevision);
        VehiculosPanel.add(txtRevision);
        VehiculosPanel.add(lblTipoViniculo);
        VehiculosPanel.add(txtTipoViniculo);
        VehiculosPanel.add(lblMaxPasajeros);
        VehiculosPanel.add(txtMaxPasajeros);
        VehiculosPanel.add(chkEnTaller);
        VehiculosPanel.add(btnListarVehiculos);
        VehiculosPanel.add(btnAgregarVehiculo);
        VehiculosPanel.add(btnCambiarEstadoTaller);

        // Mostrar el marco
        frame.setVisible(true);
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = ImageIO.read(new File("src/Imatges/MecaBK.jpg")); // Cambia esta ruta por la ruta de tu imagen
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
