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

        myButton = new JButton("New Window");
        myButton.setBounds(100, 200, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        frame.add(myButton);

        frame.revalidate();
        frame.repaint();

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton) {
            if (UserTextF.getText().equals("Admin") & ContraTextF.getText().equals("Patata")) {
                Admin myWindow = new Admin();
                frame.dispose(); //frame.setVisible(false);
            } else if (UserTextF.getText().equals("Mecanic") & ContraTextF.getText().equals("Patata")) {
                MecanicFrame myWindow = new MecanicFrame();
                frame.dispose();
            } else if (UserTextF.getText().equals("Fisic") & ContraTextF.getText().equals("Patata")){
                FisicFrame myWindow = new FisicFrame();
                frame.dispose();
            } else if (UserTextF.getText().equals("AstronautaFrame") & ContraTextF.getText().equals("Patata")) {
                AstronautaFrame myWindow = new AstronautaFrame();
                frame.dispose();
            } else if (UserTextF.getText().equals("EspiaFrame") & ContraTextF.getText().equals("Patata")) {
                EspiaFrame myWindow = new EspiaFrame();
                frame.dispose();
            } else {
                frame.dispose();
            }
        }
    }
}
