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
        ContraLabel.setBounds(100, 130, 200, 40);
        frame.add(ContraLabel);

        ContraTextF = new JTextField();
        ContraTextF.setBounds(100, 160, 200, 30);
        frame.add(ContraTextF);

        myButton = new JButton("Login");
        myButton.setBounds(100, 200, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        frame.add(myButton);

        frame.revalidate();
        frame.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton) {
            String username = UserTextF.getText();
            String password = ContraTextF.getText();

            Empleado empleado = conexio.autenticarUsuario(username, password);

            if (empleado != null) {
                // Si el empleado es administrador, abre el frame de admin
                if (empleado.isAdmin()) {
                    new Admin(); // Abre el frame de administrador
                    frame.dispose(); // Cierra el frame de login
                } else {
                    // Rediriges según el tipo de empleado
                    if (empleado instanceof Mecanico) {
                        Mecanico MecanicoFrame = (Mecanico) empleado;
                        frame.dispose();
                    } else if (empleado instanceof FisicFrame) {
                        FisicFrame FisicFrame = (FisicFrame) empleado;
                        frame.dispose();
                    } else if (empleado instanceof Astronauta) {
                        Astronauta AstronautaFrame = (Astronauta) empleado;
                        frame.dispose();
                    } else if (empleado instanceof Espia) {
                        Espia EspiaFrame = (Espia) empleado;
                        frame.dispose();
                    }
                    // Añadir más tipos de empleados
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Credenciales incorrectas");
            }
        }
    }
}
