package GUI;

import javax.swing.*;
import java.awt.*;

public class WyswietlanieKarty extends Gui {

    public JPanel panelSrodek = new JPanel();
    public JPanel panelKarty = new JPanel();

    public WyswietlanieKarty() {
        panelSrodek.setLayout(new BorderLayout());
        panelSrodek.setPreferredSize(new Dimension(470, 360));
        panelSrodek.setOpaque(false);

        panelKarty.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelKarty.setPreferredSize(new Dimension(470, 360));
        panelKarty.setOpaque(false);
    }

    public JPanel startGry(String s1, String s2) {
        ImageIcon karta1 = new ImageIcon(getClass().getResource(s1));
        ImageIcon karta2 = new ImageIcon(getClass().getResource(s2));

        JLabel karta1Label = new JLabel(karta1);
        karta1Label.setOpaque(false);
        karta1Label.setPreferredSize(new Dimension(260, 360));

        JLabel karta2Label = new JLabel(karta2);
        karta2Label.setOpaque(false);
        karta2Label.setPreferredSize(new Dimension(260, 360));

        panelKarty.add(karta1Label);
        panelKarty.add(karta2Label);

        panelSrodek.add(panelKarty, BorderLayout.SOUTH);
        panelSrodek.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        return panelSrodek;
    }
}
