package GUI;

import org.gra.Gra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * implementacja przycisków gry zgodnie z ich założeniami
 * */


public class PrzyciskiGry implements ActionListener {
    private final JButton hit;
    private final JButton stand;
    private final JButton doubleDown;
    private final JButton surrender;
    private final Gra gra;
    private final WyswietlanieKarty wyswietlanieKarty;
    private JFrame oknogry;
    boolean czyDD = false;

    public PrzyciskiGry(JButton hit, JButton stand, JButton doubleDown, JButton surrender,
                        Gra gra, WyswietlanieKarty wyswietlanieKarty, JFrame oknogry) {
        this.gra = gra;
        this.hit = hit;
        this.stand = stand;
        this.doubleDown = doubleDown;
        this.surrender = surrender;
        this.wyswietlanieKarty = wyswietlanieKarty;
        this.oknogry = oknogry;

        System.out.println(this.gra.postawione);

        hit.addActionListener(this);
        stand.addActionListener(this);
        doubleDown.addActionListener(this);
        surrender.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hit) {
            hit();
        } else if (e.getSource() == stand) {
            stand();
        } else if (e.getSource() == doubleDown) {
            doubledown();
        } else if (e.getSource() == surrender) {
            surrender();
        }
    }

    private void hit() {
        if(wyswietlanieKarty.sciezkiKart.size() < 11) {
            if(!czyDD) {
                String sciezkaDodana;
                sciezkaDodana = gra.dodanieDoTalii();
                System.out.println(gra.iloscPkt);
                wyswietlanieKarty.dodanieKarty(sciezkaDodana);
            }
            else{
                JOptionPane.showMessageDialog(oknogry, "użyto już DoubleDown", "Bład", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(oknogry, "przekroczono limit kart", "Bład", JOptionPane.ERROR_MESSAGE);
        }
    }

    //do implementacji gdy już będę miał serwer
    private void stand() {
        System.out.println("stand");
    }

    private void doubledown() {
        if(gra.srodki - 2 * gra.postawione > 0){
            if(!czyDD) {
                gra.postawione *= 2;
                String sciezkaDodana;
                sciezkaDodana = gra.dodanieDoTalii();
                System.out.println(gra.iloscPkt);
                wyswietlanieKarty.dodanieKarty(sciezkaDodana);
                czyDD = true;
            }
            else{
                JOptionPane.showMessageDialog(oknogry, "użyto już DoubleDown", "Bład", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(oknogry, "nie masz kasy :///", "Bład", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void surrender() {

        gra.srodki -= gra.postawione;
        JOptionPane.showMessageDialog(oknogry, "Dlaczego?", "Dlaczego?", JOptionPane.QUESTION_MESSAGE);
        oknogry.dispose();
    }
}
