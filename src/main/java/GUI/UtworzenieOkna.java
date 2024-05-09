package GUI;

import javax.swing.*;
import java.awt.*;

public class UtworzenieOkna {
    public UtworzenieOkna() {
    }
    public JFrame okno(){
        JFrame okno = new JFrame();
        okno.setVisible(true);
        okno.setSize(1200, 1200);
        okno.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        okno.getContentPane().setBackground(new Color(0x333A3A));
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Obrazki/catgambling.png"));
        okno.setIconImage(imageIcon.getImage());
        return okno;

    }
}
