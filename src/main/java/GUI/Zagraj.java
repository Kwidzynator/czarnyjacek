package GUI;

import org.gra.Gra;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zagraj implements ActionListener {
    protected final JButton przyciskZagraj;

    public Zagraj(JButton przyciskZagraj){
        this.przyciskZagraj = przyciskZagraj;
        this.przyciskZagraj.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //tutaj nowe okno które uruchamia nam gre
        if(e.getSource() == przyciskZagraj){
            OknoPolaczenia oknoPolaczenia = new OknoPolaczenia();
            oknoPolaczenia.polaczenie();
        }
    }
}
