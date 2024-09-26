import javax.swing.*;
import java.awt.*;

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

    private String fechaPrimerVuelo;

    Astronauta(String fecha, String nombre, String apellido, int edad, String direccion, String anosDeExperiencia, String sexo) {
        super(nombre, apellido, edad, direccion, anosDeExperiencia, sexo);
        this.fechaPrimerVuelo = fecha;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Astronauta");
        frame.setSize(420, 420);
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

        frame.revalidate();
        frame.repaint();

    }
}
