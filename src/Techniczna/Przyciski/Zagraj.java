package Techniczna.Przyciski;

import Techniczna.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zagraj extends Gui implements ActionListener {
    protected final JButton przyciskZagraj;
    protected final JFrame frame;
    public Zagraj(JPanel panel, JFrame frame){
        this.frame = frame;
        this.przyciskZagraj = new JButton("Zagraj");
        this.przyciskZagraj.addActionListener(this);
        this.przyciskZagraj.setFocusable(false); // Usunięcie otoczki wokół napisu
        //this.przyciskZagraj.setPreferredSize(new Dimension(100,150));
        panel.add(przyciskZagraj);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        uruchomienieGry();
    }
}
