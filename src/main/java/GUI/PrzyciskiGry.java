package GUI;

import org.gra.Gra;
import org.gra.Karty;
import org.gra.Talia;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * implementacja przycisków gry zgodnie z ich założeniami, oraz poszczególnych technicznych rzeczy związanych z oknemgry
 * generalnie ta część kodu to backend gry
 * */


public class PrzyciskiGry implements ActionListener {
    private final JButton hit;
    private final JButton stand;
    private final JButton doubleDown;
    private final JButton surrender;
    private final Gra gra;
    private final WyswietlanieKarty wyswietlanieKarty;
    private final JFrame oknogry;
    private int srodki;
    private int postawione;
    private Talia talia;
    private Socket socket;
    private BufferedWriter bw;
    private BufferedReader br;
    private boolean czyStand = false;
    private boolean czyDD = false;

    public PrzyciskiGry(JButton hit, JButton stand, JButton doubleDown, JButton surrender,
                        Gra gra, WyswietlanieKarty wyswietlanieKarty, JFrame oknogry, int postawione, int srodki, Socket socket) throws IOException {
        this.srodki = srodki;
        this.postawione = postawione;
        this.gra = gra;
        this.hit = hit;
        this.stand = stand;
        this.doubleDown = doubleDown;
        this.surrender = surrender;
        this.wyswietlanieKarty = wyswietlanieKarty;
        this.oknogry = oknogry;
        this.socket = socket;
        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        hit.addActionListener(this);
        stand.addActionListener(this);
        doubleDown.addActionListener(this);
        surrender.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == hit) {
                hit();
            } else if (e.getSource() == stand) {
                stand();
            } else if (e.getSource() == doubleDown) {
                doubledown();
            } else if (e.getSource() == surrender) {
                surrender();
            }
        } catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * przy hit/dd powinniśmy wysyłać karty gdyż serwer może mieć jakąś małą liczbę pomimo posiadania 3 kart
     */
    private void hit() throws IOException {
        if(wyswietlanieKarty.sciezkiKart.size() < 11) {
            if(!czyDD) {
                bw.write("hit");
                bw.newLine();
                bw.flush();

                String sciezkaDodana;
                sciezkaDodana = gra.uzyskanieLinka();
                talia = gra.getTaliaUzytych();
                Karty karta = talia.get(talia.size() - 1);
                wyslanieKarty(karta);
                gra.pktKlienta += karta.uzyskajWartosc();
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

    // zakładamy że stand będziemy dawać po ruchu serwera
    private void stand() throws IOException {
        bw.write("stand");
        bw.newLine();
        bw.flush();

        wynik();
    }


    private void doubledown() throws IOException {
        if(this.srodki - 2 * this.postawione > 0){
            if(!czyDD) {
                bw.write("dd");
                bw.newLine();
                bw.flush();

                this.postawione *= 2;
                String sciezkaDodana;
                sciezkaDodana = gra.uzyskanieLinka();
                //System.out.println(this.postawione);
                wyswietlanieKarty.dodanieKarty(sciezkaDodana);

                talia = gra.getTaliaUzytych();
                Karty karta = talia.get(talia.size() - 1);
                wyslanieKarty(karta);
                gra.pktKlienta += karta.uzyskajWartosc();
                System.out.println(gra.getPktKlienta());
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


    private void surrender() throws IOException {
        //w razie jakbyśmy jednak chcieli zagrać na nowo
        gra.oczysczenie();
        JOptionPane.showMessageDialog(oknogry, "Dlaczego?", "Dlaczego?", JOptionPane.QUESTION_MESSAGE);
        bw.write("surrender");
        bw.newLine();
        bw.flush();

        oknogry.dispose();
    }

    public void wyslanieKarty(Karty karta) throws IOException {
        JSONArray jsonA = new JSONArray();

        JSONObject card = new JSONObject();
        card.put("kolor", karta.uzyskajKolor());
        card.put("wartosc", karta.uzyskajWartosc());
        card.put("nazwa", karta.uzyskajNazwe());

        jsonA.put(card);
        bw.write(jsonA.toString());
        bw.newLine();
        bw.flush();
    }

    private void wynik(){
        if(gra.pktSerwera < gra.pktKlienta && gra.pktKlienta < 22){
            JOptionPane.showMessageDialog(oknogry, "Wygrywasz!", "wygrana", JOptionPane.OK_OPTION);
            srodki += postawione;
        }
        else if((gra.pktSerwera == gra.pktKlienta && gra.pktKlienta < 22) || (gra.pktSerwera > 21 && gra.pktKlienta > 21)){
            JOptionPane.showMessageDialog(oknogry, "Remis :/", "remis", JOptionPane.OK_OPTION);
        }
        else if(gra.pktKlienta > 21){
            JOptionPane.showMessageDialog(oknogry, "Porażka :cc", "porażka", JOptionPane.WARNING_MESSAGE);
            srodki -= postawione;
        }
        Oknozakladu oknozakladu = new Oknozakladu();
        oknozakladu.zaklad(srodki, socket);
    }

    public void resztaGry() throws IOException {
        String wiadomosc = br.readLine();
        if(wiadomosc.equals("stand")){
            czyStand = true;
        } else{

        }
    }
}
