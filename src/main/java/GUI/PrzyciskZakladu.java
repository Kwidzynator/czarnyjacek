package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrzyciskZakladu implements ActionListener {

    private final JButton button;
    private final int srodki;
    private final JTextField textField;
    private final JFrame oknozakladu;
    public PrzyciskZakladu(JButton button, JFrame oknozakladu, JTextField textField, int srodki) {
        this.srodki = srodki;
        this.textField = textField;
        this.oknozakladu = oknozakladu;
        this.button = button;
        this.button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            try {
                int postawione = Integer.parseInt(textField.getText());
                if(postawione > this.srodki){
                    JOptionPane.showMessageDialog(oknozakladu, "my tutaj nie zezwalamy na kredyty :)", "Bład", JOptionPane.ERROR_MESSAGE);
                    textField.setText("");
                }
                else {
                    OknoGry oknoGry = new OknoGry();
                    oknoGry.poZakladzie();
                    oknozakladu.dispose();
                }
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(oknozakladu, "podaj LICZBE", "Bład", JOptionPane.ERROR_MESSAGE);
                textField.setText("");
            }

        }
    }
}
