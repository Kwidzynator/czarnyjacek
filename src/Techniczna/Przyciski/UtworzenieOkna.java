package Techniczna.Przyciski;

import javax.swing.*;

public class UtworzenieOkna {
    public UtworzenieOkna() {
    }
    public JFrame okno(){
        JFrame okno = new JFrame();    //tworzy okno
        okno.setSize(1200, 1200);        //ustala rozdzielczość
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //pozwala na zamknięcie po kliknięciu "x"
        okno.setVisible(true);        //opcja aby było widoczne dla użytkownika
        return okno;
    }
}
