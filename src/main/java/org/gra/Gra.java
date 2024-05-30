package org.gra;


import java.util.Random;

/**
 * klasa odpowiedzialna za przebieg gry, za to kiedy następuje wygrana a kiedy przegrana
 * */

public class Gra {


    private Talia taliaUzytych = new Talia();
    /**
     * warto również zaznaczyć iż ze względu na wartość punktową środki i postawione
     * klasa ta będzie wiele razy przerzucana z pliku do pliku więc dzięki temu mamy taką kolejność
     *
     * Gra ->OknoGry
     *          ->WyswietlanieKarty
     *          ->PrzyciskiGry->WyswietlanieKarty
     * */
    public int pktKlienta = 0;
    public int pktSerwera = 0;
    public Karty losowanie(){
        Random random = new Random();
        Karty karta;
        int kolor = random.nextInt(4) + 1;
        int wartosc = random.nextInt(12) + 1;
        Kolory kartaKolor = Kolory.values()[kolor - 1];
        Wartosci kartaWartosc = Wartosci.values()[wartosc - 1];
        karta = new Karty(kartaKolor, kartaWartosc);
        return karta;
    }
    public String uzyskanieLinka(){
        Karty karta = losowanie();
        while(taliaUzytych.kartyList.contains(karta)){
            karta = losowanie();
        }
        dodanie(karta);
        // Karty karta = new Karty(Kolory.KIER, Wartosci.JOPEK);
        UzyskanieGrafiki ug = new UzyskanieGrafiki();
        return ug.sciezka(karta);
    }

    public void dodanie(Karty karta){
        taliaUzytych.dodaj(karta);
    }

    public int getPktKlienta() { return pktKlienta; }

    public Talia getTaliaUzytych() {
        return taliaUzytych;
    }

    public void oczysczenie(){
        taliaUzytych.wyczyszczenie();
        pktKlienta = 0;
        pktSerwera = 0;
    }

    public Gra(){
    }

    public int getPktSerwera() {
        return pktSerwera;
    }
}
