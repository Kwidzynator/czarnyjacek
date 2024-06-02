package GUI;

import javax.swing.*;
import java.awt.*;

public class OknoLogowaniaRejestracji {
    public OknoLogowaniaRejestracji() {

    }

    public void lr(Runnable callback){
        UtworzenieOkna utworzenieOkna = new UtworzenieOkna();
        JFrame oknoLogowania = utworzenieOkna.okno();
        oknoLogowania.setResizable(false);
        oknoLogowania.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());


        JTextArea textArea = new JTextArea("podaj login i haslo");
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(panel.getBackground());
        Font customFont = new Font("Gotham", Font.PLAIN, 15);
        textArea.setFont(customFont);


        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(textArea, BorderLayout.NORTH);

        //adres
        JTextField textFieldLogin = new JTextField("login");
        textFieldLogin.setPreferredSize(new Dimension(100, 25));
        panel.add(textFieldLogin);

        //port
        JPasswordField textFieldHaslo = new JPasswordField("haslo");
        textFieldHaslo.setPreferredSize(new Dimension(100, 25));
        panel.add(textFieldHaslo);

        JButton przyciskLogowania = new JButton("zaloguj");
        panel.add(przyciskLogowania);

        JButton przyciskRejestracji = new JButton("zarejestruj");
        panel.add(przyciskRejestracji);

        PrzyciskLogowania pl = new PrzyciskLogowania(przyciskLogowania, przyciskRejestracji, oknoLogowania, textFieldLogin, textFieldHaslo, callback);
        oknoLogowania.add(panel1, BorderLayout.NORTH);
        oknoLogowania.add(panel, BorderLayout.SOUTH);
        oknoLogowania.pack();
        oknoLogowania.setVisible(true);
    }
}
