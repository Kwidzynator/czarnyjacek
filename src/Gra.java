import Techniczna.Gui;


public class Gra {
    public static void main(String[] args) {

        Gui gui = new Gui();
        gui.okno();




        /*/gracz na start otrzymuje dwie karty w związku z czym powinniśmy wylosować najpierw liczbę z przedziału 1-4 a następnie z przedziału
        //2 - 12
        Random random = new Random();
        Karty k1, k2;

        //losowanie karty nr 1
        int k1KolorIndex = random.nextInt(4) + 1;
        int k1WartoscIndex = random.nextInt(12) + 1;
        Kolory k1Kolor = Kolory.values()[k1KolorIndex - 1];
        Wartosci k1Wartosc = Wartosci.values()[k1WartoscIndex - 1];

        //losowanie karty nr2
        int k2KolorIndex;
        int k2wartoscIndex;
        Kolory k2Kolor;
        Wartosci k2Wartosc;
        //zabezpieczenie w razie otrzymania tej samej karty
        do {
            k2KolorIndex = random.nextInt(4) + 1;
            k2wartoscIndex = random.nextInt(10) + 2;
            k2Kolor = Kolory.values()[k2KolorIndex - 1];
            k2Wartosc = Wartosci.values()[k2wartoscIndex - 1];
        }
        while(k1KolorIndex == k2KolorIndex && k1WartoscIndex == k2wartoscIndex);

        k1 = new Karty(k1Kolor, k1Wartosc);
        k2 = new Karty(k2Kolor, k2Wartosc);

        System.out.println(k1.toString());
        System.out.println(k2.toString());

        Talia taliagracza = new Talia();
        taliagracza.dodaj(k1);
        taliagracza.dodaj(k2);
        System.out.println(taliagracza.pokazKarty());*/

    }
}