package gra;

/**klasa odpowiedzialna za połączenie koloru oraz wartosci karty*/
public class Karty {

    /**poniżej tworzymy nasze enumy, aby utworzyć na ich podstawie talie kart*/
    private final Kolory kolory;
    private final Wartosci wartosci;

    /**dosyc oczywiste, sprawdza czy karta jest w ręce gracza*/
    private final boolean czyWRece;

    public Karty(Kolory kolory, Wartosci wartosci){
        this.kolory = kolory;
        this.wartosci = wartosci;
        czyWRece = true;
    }

    public String uzyskajKolor(){
        return kolory.printKolory();
    }

    public String uzyskajWartosc(){
        return wartosci.printNazwy();
    }

    @Override
    public String toString() {
        String str = "";

        if(czyWRece) {
             str += "Kolor: " + kolory.printKolory() + " wartosc: " + wartosci.printNazwy() + "\n";
        }

        return str;
    }
}
