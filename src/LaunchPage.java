import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener {
    JFrame frame;
    JButton myButton;
    JLabel UserLabel;
    JTextField UserTextF;
    JLabel ContraLabel;
    JTextField ContraTextF;
    JComboBox TreballsCB;
    JPanel GreyPanel;

    LaunchPage() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Programa Espacial URSS");
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centrar el frame

        UserLabel = new JLabel("User");
        UserLabel.setBounds(100, 80, 200, 40);
        frame.add(UserLabel);

        UserTextF = new JTextField();
        UserTextF.setBounds(100, 110, 200, 30);
        frame.add(UserTextF);

        ContraLabel = new JLabel("Contrasenya");
        ContraLabel.setBounds(100,130,200,40);
        frame.add(ContraLabel);

        ContraTextF = new JTextField();
        ContraTextF.setBounds(100, 160, 200, 30);
        frame.add(ContraTextF);

        String[] choices = { "Mecànic","Astronauta", "Físic","Espia","Admin"};
        TreballsCB = new JComboBox(choices);
        TreballsCB.setBounds(100,200,200,40);
        frame.add(TreballsCB);

        myButton = new JButton("New Window");
        myButton.setBounds(100, 250, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        frame.add(myButton);

        frame.revalidate();
        frame.repaint();

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton) {
            if (ContraTextF.getText().equals("Patata") & TreballsCB.getSelectedItem().equals("Mecànic")) {
                Mecanico myWindow = new Mecanico();
                frame.dispose(); //frame.setVisible(false);
            } else if (ContraTextF.getText().equals("Patata") & TreballsCB.getSelectedItem().equals("Astronauta")) {
                Astronauta myWindow = new Astronauta();
                frame.dispose();
            } else if (ContraTextF.getText().equals("Patata") & TreballsCB.getSelectedItem().equals("Físic")){
                Fisico myWindow = new Fisico();
                frame.dispose();
            } else if (ContraTextF.getText().equals("Patata") & TreballsCB.getSelectedItem().equals("Espia")) {
                Espia myWindow = new Espia();
                frame.dispose();
            } else {
                Admin myWindow = new Admin();
                frame.dispose();
            }
        }
    }
}
