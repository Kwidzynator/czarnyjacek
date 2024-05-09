package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrzyciskiGry implements ActionListener {
    protected final JButton hit = new JButton("hit");
    protected final JButton stand = new JButton("stand");
    protected final JButton doubleDown = new JButton("double down");
    protected final JButton surrender = new JButton("surrender");
    protected final JButton bet = new JButton("bet");
    public PrzyciskiGry(JPanel panel) {
        hit.addActionListener(this);
        stand.addActionListener(this);
        doubleDown.addActionListener(this);
        surrender.addActionListener(this);
        bet.addActionListener(this);

        hit.setFocusable(false);
        stand.setFocusable(false);
        doubleDown.setFocusable(false);
        surrender.setFocusable(false);
        bet.setFocusable(false);

        JPanel lewogora = new JPanel();
        lewogora.setOpaque(false);
        lewogora.setLayout(new FlowLayout(FlowLayout.LEFT));
        lewogora.setBackground(new Color(0x333A3A));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(0x333A3A));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Marginesy dla panelu przycisków

        buttonPanel.add(hit);
        buttonPanel.add(stand);
        buttonPanel.add(bet);
        buttonPanel.add(doubleDown);
        buttonPanel.add(surrender);

        lewogora.add(buttonPanel);
        panel.add(lewogora, BorderLayout.NORTH); // Dodajemy panel przycisków do kontenera, ustawiając go na górze

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //implementacja kolejno każdego z przycisków gry, wymaga postawienia serwera tcp więc zostawiam na potem
    }
}
