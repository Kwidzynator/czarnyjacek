package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class Oknozakladu{

    public void zaklad(int srodki, Socket socket) {


        UtworzenieOkna utworzenieOkna = new UtworzenieOkna();
        JFrame oknozakladu = utworzenieOkna.okno();
        oknozakladu.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        oknozakladu.setResizable(false);
        oknozakladu.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton button = new JButton("zakład");


        JTextArea textArea = new JTextArea(" Postaw zakład.\n Twoje obecne środki to: " + srodki);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(panel.getBackground());
        Font customFont = new Font("Gotham", Font.PLAIN, 15);
        textArea.setFont(customFont);


        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(textArea, BorderLayout.NORTH);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 25));

        PrzyciskZakladu przyciskZakladu = new PrzyciskZakladu(button, oknozakladu, textField, srodki, socket);
        panel.add(textField);
        panel.add(button);

        oknozakladu.add(panel1, BorderLayout.NORTH);
        oknozakladu.add(panel, BorderLayout.SOUTH);
        oknozakladu.pack();
        oknozakladu.setVisible(true);
    }


    public Oknozakladu() {
    }

}
