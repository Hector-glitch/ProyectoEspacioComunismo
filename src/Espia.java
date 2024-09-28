import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Espia extends Empleado {
    JFrame frame;
    JLabel NomClauL;
    JLabel TelefonL;
    JPanel GreyPanel;
    JPanel AccioPanel;
    JTextField mensajeField;
    JLabel mensajeEncriptadoLabel;

    private String nombreEnClave;
    private String telefonoContacto;

    Espia (String nombre, int salari, int edad, String direccion, String anosDeExperiencia, String sexo, String nombreClave, String telefono){
        super(nombre, salari, edad, direccion, anosDeExperiencia, sexo);
        nombreEnClave = nombreClave;
        telefonoContacto = telefono;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Espia");
        frame.setSize(600, 420); // Ajusta el tamaño para acomodar el nuevo panel
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centrar el frame

        // Panel gris
        GreyPanel = new JPanel();
        GreyPanel.setBackground(Color.gray);
        GreyPanel.setLayout(null);
        GreyPanel.setBounds(20, 40, 200, 300);
        frame.add(GreyPanel);

        NomClauL = new JLabel("Nom en clau: " + nombreEnClave);
        NomClauL.setBounds(20, 10, 160, 20);
        GreyPanel = new JPanel();
        GreyPanel.setBackground(Color.gray);
        GreyPanel.setLayout(null);
        GreyPanel.setBounds(20, 40, 200, 300);
        frame.add(GreyPanel);


        NomClauL = new JLabel("Nom en clau: " + nombreEnClave);
        NomClauL.setBounds(20,10,80,20);
        GreyPanel.add(NomClauL);

        TelefonL = new JLabel("Telefon de contacte: " + telefonoContacto);
        TelefonL.setBounds(20, 40, 160, 20);
        GreyPanel.add(TelefonL);

        frame.revalidate();
        frame.repaint();

        // Panel de acción
        AccioPanel = new JPanel();
        AccioPanel.setBackground(Color.lightGray);
        AccioPanel.setLayout(null);
        AccioPanel.setBounds(240, 40, 300, 300);
        frame.add(AccioPanel);

        // Campo de texto para el mensaje
        mensajeField = new JTextField();
        mensajeField.setBounds(20, 20, 260, 30);
        AccioPanel.add(mensajeField);

        // Botón para enviar el mensaje
        JButton enviarButton = new JButton("Enviar");
        enviarButton.setBounds(20, 60, 100, 30);
        AccioPanel.add(enviarButton);

        // Label para mostrar el mensaje encriptado
        mensajeEncriptadoLabel = new JLabel("Mensaje encriptado: ");
        mensajeEncriptadoLabel.setBounds(20, 100, 260, 30);
        AccioPanel.add(mensajeEncriptadoLabel);

        // Acción al pulsar el botón
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = mensajeField.getText();
                String encriptado = enviarMensajeEncriptado(mensaje);
                mensajeEncriptadoLabel.setText("Mensaje encriptado: " + encriptado);
            }
        });

        frame.setVisible(true); // Mover setVisible al final
    }

    private static String enviarMensajeEncriptado(String mensaje) {
        return mensaje.replaceAll("[qwrtypsdfghjklzxcvbnmñQWRTYPSDFGHJKLZXCVBNMÑ]", "");
    }

    public static void main(String[] args) {
    }
}
