package org.gra;

import java.util.ArrayList;
import java.util.Random;

public class Gra {
    Talia talia = new Talia();
    public int iloscPkt = 0;
    public Karty losowanie(){
        Random random = new Random();
        Karty karta;
        int kolor = random.nextInt(4) + 1;
        int wartosc = random.nextInt(12) + 1;
        Kolory kartaKolor = Kolory.values()[kolor - 1];
        Wartosci kartaWartosc = Wartosci.values()[wartosc - 1];
        karta = new Karty(kartaKolor, kartaWartosc);
        iloscPkt += karta.uzyskajWartosc();
        return karta;
    }
    public String dodanieDoTalii(){
        Karty karta = losowanie();
        while(talia.kartyList.contains(karta)){
            karta = losowanie();
        }
        talia.dodaj(karta);
        // Karty karta = new Karty(Kolory.KIER, Wartosci.JOPEK);
        UzyskanieGrafiki ug = new UzyskanieGrafiki();
        return ug.sciezka(karta);
    }

    public int getIloscPkt() { return iloscPkt; }

    public Gra(){
    }




}
