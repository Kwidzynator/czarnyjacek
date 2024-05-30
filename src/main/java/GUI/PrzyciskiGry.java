package GUI;

import org.gra.Gra;

import javax.swing.*;
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
    private final JFrame oknogry;
    private final int srodki;
    private int postawione;
    boolean czyDD = false;

    public PrzyciskiGry(JButton hit, JButton stand, JButton doubleDown, JButton surrender,
                        Gra gra, WyswietlanieKarty wyswietlanieKarty, JFrame oknogry, int postawione, int srodki) {
        this.srodki = srodki;
        this.postawione = postawione;
        this.gra = gra;
        this.hit = hit;
        this.stand = stand;
        this.doubleDown = doubleDown;
        this.surrender = surrender;
        this.wyswietlanieKarty = wyswietlanieKarty;
        this.oknogry = oknogry;

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
                sciezkaDodana = gra.uzyskanieLinka();
                System.out.println(gra.pktKlienta);
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
        if(this.srodki - 2 * this.postawione > 0){
            if(!czyDD) {
                this.postawione *= 2;
                String sciezkaDodana;
                sciezkaDodana = gra.uzyskanieLinka();
                System.out.println(this.postawione);
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
