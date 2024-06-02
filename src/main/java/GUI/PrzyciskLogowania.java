package GUI;

import bazadanych.ZarzadzanieDanymi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrzyciskLogowania implements ActionListener {
    private final JButton logowanie;
    private final JButton rejestracja;
    private JFrame okno;
    private final JTextField login;
    private final JTextField haslo;
    private Runnable callback;

    public PrzyciskLogowania(JButton logowanie, JButton rejestracja, JFrame okno, JTextField login, JPasswordField haslo, Runnable callback) {
        this.logowanie = logowanie;
        this.rejestracja = rejestracja;
        this.okno = okno;
        this.login = login;
        this.haslo = haslo;
        this.callback = callback;
        logowanie.addActionListener(this);
        rejestracja.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ZarzadzanieDanymi zd = new ZarzadzanieDanymi();
        if(e.getSource() == logowanie){
            logowanie(zd);
        }
        else if(e.getSource() == rejestracja){
            zd.dodanie(login.getText(), haslo.getText());
        }
    }
    private void logowanie(ZarzadzanieDanymi zd){
        if(zd.sprawdzenie(login.getText(), haslo.getText())){
            callback.run();
            okno.dispose();
        }
        else{
            JOptionPane.showMessageDialog(okno, "wystapil błąd przy logowaniu :/", "Bład", JOptionPane.ERROR_MESSAGE);
        }
    }
}
