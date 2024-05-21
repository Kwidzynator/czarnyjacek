package GUI;

import org.gra.Gra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Oknozakladu{
    private JTextField textField;
    private JButton button;
    private JFrame oknozakladu;


    public void zaklad(int srodki) {


        UtworzenieOkna utworzenieOkna = new UtworzenieOkna();
        oknozakladu = utworzenieOkna.okno();
        oknozakladu.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        oknozakladu.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        button = new JButton("zakład");


        JTextArea textArea = new JTextArea(" Postaw zakład.\n Twoje obecne środki to: " + srodki);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(panel.getBackground());
        Font customFont = new Font("Gotham", Font.PLAIN, 15);
        textArea.setFont(customFont);


        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(textArea, BorderLayout.NORTH);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 25));
        PrzyciskZakladu przyciskZakladu = new PrzyciskZakladu(button, oknozakladu, textField, srodki);
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
