package Techniczna.Przyciski;

import javax.swing.*;

public class UtworzenieOkna {
    public UtworzenieOkna() {
    }
    public JFrame okno(){
        JFrame okno = new JFrame();
        okno.setSize(1200, 1200);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
        return okno;
    }
}
