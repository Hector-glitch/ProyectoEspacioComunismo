import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mecanico extends Empleado implements ActionListener {
    JFrame frame;
    JLabel nomL;
    JLabel salariL;
    JLabel edatL;
    JLabel adreçaL;
    JLabel expL;
    JLabel ciutatL;
    JLabel sexeL;
    JLabel numTallerL;
    JPanel GreyPanel;
    private String numeroDeTaller;

    public Mecanico(String nombre, String apellido, int edad, String direccion, String anosDeExperiencia, String sexo, String numeroDeTaller) {
        super(nombre, apellido, edad, direccion, anosDeExperiencia, sexo);
        this.numeroDeTaller = numeroDeTaller;


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
        GreyPanel.setBounds(20,40,200,300);
        frame.add(GreyPanel);

        nomL = new JLabel("Nom: ");
        nomL.setBounds(20,10,80,20);
        GreyPanel.add(nomL);

        salariL = new JLabel("Salari: ");
        salariL.setBounds(20,30,80,20);
        GreyPanel.add(salariL);

        edatL = new JLabel("Edat: ");
        edatL.setBounds(20,50,80,20);
        GreyPanel.add(edatL);

        adreçaL = new JLabel("Adreça: ");
        adreçaL.setBounds(20,70,80,20);
        GreyPanel.add(adreçaL);

        expL = new JLabel("Anys Exp.: ");
        expL.setBounds(20,90,80,20);
        GreyPanel.add(expL);

        ciutatL = new JLabel("Ciutat d'Ofici: ");
        ciutatL.setBounds(20,110,80,20);
        GreyPanel.add(ciutatL);

        sexeL = new JLabel("Sexe: ");
        sexeL.setBounds(20,130,80,20);
        GreyPanel.add(sexeL);

        numTallerL = new JLabel("Num. Taller: ");
        numTallerL.setBounds(20,150,80,20);
        GreyPanel.add(numTallerL);

        frame.revalidate();
        frame.repaint();

    }
    public void actionPerformed(ActionEvent e) {

    }
}
