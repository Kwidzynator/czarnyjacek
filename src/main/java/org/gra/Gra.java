package org.gra;


import java.util.Random;

/**
 * klasa odpowiedzialna za przebieg gry, za to kiedy następuje wygrana a kiedy przegrana
 * */

public class Gra {

    public int postawione = 0;
    public int srodki = 5000;
    Talia talia = new Talia();
    /**
     * warto również zaznaczyć iż ze względu na wartość punktową środki i postawione
     * klasa ta będzie wiele razy przerzucana z pliku do pliku więc dzięki temu mamy taką kolejność
     *
     * Gra ->OknoGry
     *          ->WyswietlanieKarty
     *          ->PrzyciskiGry->WyswietlanieKarty
     * */
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
