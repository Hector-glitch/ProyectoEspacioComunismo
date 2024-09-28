import javax.swing.*;
import java.awt.*;


public class Espia extends Empleado {
    JFrame frame;
    JLabel NomClauL;
    JLabel TelefonL;
    JPanel GreyPanel;

    private String nombreEnClave;
    private String telefonoContacto;

    Espia (String nombre, int salari, int edad, String direccion, String anosDeExperiencia, String sexo, String nombreClave, String telefono){
        super(nombre, salari, edad, direccion, anosDeExperiencia, sexo);
        nombreEnClave = nombreClave;
        telefonoContacto = telefono;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Espia");
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centrar el frame

        GreyPanel = new JPanel();
        GreyPanel.setBackground(Color.gray);
        GreyPanel.setLayout(null);
        GreyPanel.setBounds(20, 40, 200, 300);
        frame.add(GreyPanel);


        NomClauL = new JLabel("Nom en clau: " + nombreEnClave);
        NomClauL.setBounds(20,10,80,20);
        GreyPanel.add(NomClauL);

        TelefonL = new JLabel("Telefon de contacte: " + telefonoContacto);
        TelefonL.setBounds(20,30,80,20);
        GreyPanel.add(TelefonL);

        frame.revalidate();
        frame.repaint();
    }
}
