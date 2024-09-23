import javax.swing.*;

public class Espia {
    JFrame frame;
    JLabel NomClauL;
    JLabel TelefonL;
    JLabel GreyPanel;


    Espia(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Espia");
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centrar el frame

        NomClauL = new JLabel("Nom en clau: ");
        NomClauL.setBounds(20,10,80,20);
        GreyPanel.add(NomClauL);

        TelefonL = new JLabel("Telefon de contacte: ");
        TelefonL.setBounds(20,30,80,20);
        GreyPanel.add(TelefonL);
    }
}
