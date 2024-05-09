package GUI;

import org.gra.Gra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OknoGry extends Gui{
    protected GridBagConstraints gbc = new GridBagConstraints();
    protected JPanel calosc = new JPanel();
    protected JPanel przyciski = new JPanel();
    public OknoGry(){
        calosc.setLayout(new GridBagLayout());
        calosc.setOpaque(false);

        UtworzenieOkna utworzenieOkna = new UtworzenieOkna();
        JFrame oknogry = utworzenieOkna.okno();

        przyciski.setOpaque(false);
        przyciski.setLayout(new BoxLayout(przyciski, BoxLayout.LINE_AXIS));
        przyciski.setPreferredSize(new Dimension(200, 200));
        PrzyciskiGry przyciskiGry = new PrzyciskiGry(przyciski);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; // Wypełnienie komórki siatki w obu kierunkach
        gbc.insets = new Insets(10, 10, 10, 10); // Marginesy

        calosc.add(przyciski, gbc);


        String sciezkaKarty1, sciezkakarty2;
        Gra gra = new Gra();
        sciezkaKarty1 = gra.dodanieDoTalii();
        sciezkakarty2 = gra.dodanieDoTalii();
        System.out.println(gra.getIloscPkt());
        WyswietlanieKarty wyswietlanieKarty = new WyswietlanieKarty();
        JPanel panelKart = wyswietlanieKarty.startGry(sciezkaKarty1, sciezkakarty2);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.anchor |= GridBagConstraints.SOUTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; // Wypełnienie komórki siatki w obu kierunkach

        calosc.add(panelKart, gbc);


        oknogry.add(calosc);







        /*objaśnienie: tutaj nasłuchujemy czy nasze okno jest już otwarte gdyż nie chcemy, umożliwić użytkownikowi
            otwarcia dwóch okien z grami, oraz pozbywamy się przycisków w przypadku "schowania okna"*/
        oknogry.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                przyciski.removeAll();
            }
        });
    }

}
