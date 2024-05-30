package org.gra;

import java.util.Objects;

/**klasa odpowiedzialna za połączenie koloru oraz wartosci karty*/
public class Karty {

    /**poniżej tworzymy nasze enumy, aby utworzyć na ich podstawie talie kart*/
    private final Kolory kolory;
    private final Wartosci wartosci;


    public Karty(Kolory kolory, Wartosci wartosci){
        this.kolory = kolory;
        this.wartosci = wartosci;

    }

    public String uzyskajKolor(){
        return kolory.printKolory();
    }

    public String uzyskajNazwe(){
        return wartosci.printNazwy();
    }

    public int uzyskajWartosc() { return wartosci.printLiczbaWartosci(); }


    /** poniższa część kodu odpowiada za właściwe przyrównania czy karty są sobie równe
     * zwyczajne porównanie equals nie zadziała gdyż Java korzysta z porównań referencji
     * a nie wartości przez co musimy każdej karcie przypisać wartość hasz, ai
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Karty karty = (Karty) o;
        return kolory == karty.kolory && wartosci == karty.wartosci;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kolory, wartosci);
    }

    @Override
    public String toString() {
        return "Karty{" +
                "kolory=" + kolory +
                ", wartosci=" + wartosci +
                '}';
    }
}
