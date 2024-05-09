package GUI;

import org.gra.Gra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class Zagraj extends Gui implements ActionListener {
    protected final JButton przyciskZagraj;
    protected JPanel przyciski = new JPanel();
    protected int czyWGrze = 0;
    public Zagraj(JPanel panel){

        this.przyciskZagraj = new JButton("Zagraj");
        this.przyciskZagraj.addActionListener(this);
        this.przyciskZagraj.setFocusable(false); // Usunięcie otoczki wokół napisu
        panel.add(przyciskZagraj);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //tutaj nowe okno które uruchamia nam gre
        if(e.getSource() == przyciskZagraj){
            OknoGry og = new OknoGry();
        }
    }
}
