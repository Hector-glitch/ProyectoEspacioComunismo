import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mecanico extends Empleado implements ActionListener {
    JFrame frame;
    JLabel nomL, salariL, edatL, adreçaL, expL, ciutatL, sexeL, numTallerL;
    JPanel GreyPanel;
    private String numeroDeTaller;

    // Constructor modificado: eliminar apellido
    public Mecanico(String nombre, int salari, int edad, String direccion, String anosDeExperiencia, String sexo, String numeroDeTaller) {
        super(nombre, salari, edad, direccion, anosDeExperiencia, sexo); // Llama al constructor de Empleado correctamente
        this.numeroDeTaller = numeroDeTaller;

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

        salariL = new JLabel("Salari: " + salari); // Aquí puedes obtener y mostrar el salario desde la base de datos si es necesario
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

        frame.revalidate();
        frame.repaint();
    }

    public void actionPerformed(ActionEvent e) {

    }
}
