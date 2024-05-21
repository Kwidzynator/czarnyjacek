package GUI;

import org.gra.Gra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class Zagraj extends Gra implements ActionListener {
    protected final JButton przyciskZagraj;
    protected JPanel przyciski = new JPanel();
    protected int czyWGrze = 0;
    public Zagraj(JButton przyciskZagraj){
        this.przyciskZagraj = przyciskZagraj;
        this.przyciskZagraj.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //tutaj nowe okno które uruchamia nam gre
        if(e.getSource() == przyciskZagraj){
            Oknozakladu oknozakladu = new Oknozakladu();
            oknozakladu.zaklad(srodki);
        }
    }
}
