package GUI;

import javax.swing.*;
import java.awt.*;

public class OknoPolaczenia {
    public OknoPolaczenia() {
    }

    public void polaczenie(){

        UtworzenieOkna utworzenieOkna = new UtworzenieOkna();
        JFrame oknopolaczenia = utworzenieOkna.okno();
        oknopolaczenia.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        oknopolaczenia.setResizable(false);
        oknopolaczenia.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());



        JTextArea textArea = new JTextArea("podaj adres serwera oraz port");
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(panel.getBackground());
        Font customFont = new Font("Gotham", Font.PLAIN, 15);
        textArea.setFont(customFont);


        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(textArea, BorderLayout.NORTH);

        //adres
        JTextField textFieldIp = new JTextField("localhost");
        textFieldIp.setPreferredSize(new Dimension(100, 25));
        panel.add(textFieldIp);

        //port
        JTextField textFieldPort = new JTextField("7777");
        textFieldPort.setPreferredSize(new Dimension(100, 25));
        panel.add(textFieldPort);

        JButton button = new JButton("zatwierdź");
        PrzyciskPolaczenia przyciskPolaczenia = new PrzyciskPolaczenia(button, oknopolaczenia,
                                                                        textFieldIp, textFieldPort);
        panel.add(button);

        oknopolaczenia.add(panel1, BorderLayout.NORTH);
        oknopolaczenia.add(panel, BorderLayout.SOUTH);
        oknopolaczenia.pack();
        oknopolaczenia.setVisible(true);
    }
}
