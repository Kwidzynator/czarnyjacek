package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class PrzyciskPolaczenia implements ActionListener {
    private final JButton button;
    private final JFrame frame;
    private final JTextField jTextFieldIP;
    private final JTextField jTextFieldPORT;


    public PrzyciskPolaczenia(JButton button, JFrame frame, JTextField tIP, JTextField tPORT) {
        this.button = button;
        this.frame = frame;
        this.jTextFieldIP = tIP;
        this.jTextFieldPORT = tPORT;
        this.button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            int port = Integer.parseInt(jTextFieldPORT.getText());
            String ip = jTextFieldIP.getText();
            int srodki = 5000;
            try {
                Socket socket = new Socket(ip, port);
                if (socket.isConnected()) {
                    Oknozakladu oknozakladu = new Oknozakladu();
                    oknozakladu.zaklad(srodki, socket);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "wystapil problem polaczenia", "Bład", JOptionPane.ERROR_MESSAGE);
                    jTextFieldIP.setText("adres");
                    jTextFieldPORT.setText("port");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
