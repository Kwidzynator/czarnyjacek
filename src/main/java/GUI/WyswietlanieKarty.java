package GUI;

import org.gra.Karty;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Flow;

/**
 * jak sama nazwa wskazuje klasa odpowiedzialna za wyswietlenie naszych kart
 * */
public class WyswietlanieKarty{
    ArrayList<String> sciezkiKart = new ArrayList<>();
    public JPanel panelSrodek = new JPanel();
    public JPanel panelKarty = new JPanel();

    public WyswietlanieKarty() {
    }
    private void nowePole(){
        panelSrodek.setLayout(new BorderLayout());
        panelSrodek.setPreferredSize(new Dimension(470, 360));
        panelSrodek.setOpaque(false);

        panelKarty.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelKarty.setPreferredSize(new Dimension(470, 360));
        panelKarty.setOpaque(false);

        panelSrodek.add(panelKarty, BorderLayout.SOUTH);
        panelSrodek.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
    }
    public JPanel startGry(String s1, String s2) {
        nowePole();

        dodanieKarty(s1);
        dodanieKarty(s2);

        return panelSrodek;
    }

    public void dodanieKarty(String s) {
        sciezkiKart.add(s);
        ImageIcon oryginalnaKarta = new ImageIcon(Objects.requireNonNull(getClass().getResource(s)));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;


        JLabel nowaKartaLabel = getjLabel(screenWidth, screenHeight, oryginalnaKarta);

        panelKarty.add(nowaKartaLabel);
        panelKarty.revalidate();
        panelKarty.repaint();
    }

    /** karty skalujące się z rozmiarem monitora*/
    private JLabel getjLabel(int screenWidth, int screenHeight, ImageIcon oryginalnaKarta) {
        int cardWidth = screenWidth / 9;
        int cardHeight = screenHeight / 4;

        // Skalowanie obrazu karty
        Image oryginalnyObraz = oryginalnaKarta.getImage();
        Image skalowanyObraz = oryginalnyObraz.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
        ImageIcon nowaKarta = new ImageIcon(skalowanyObraz);

        JLabel nowaKartaLabel = new JLabel(nowaKarta);
        nowaKartaLabel.setOpaque(false);
        nowaKartaLabel.setPreferredSize(new Dimension(cardWidth, cardHeight));
        return nowaKartaLabel;
    }
}