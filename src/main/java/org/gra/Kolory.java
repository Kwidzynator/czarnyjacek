package org.gra;

/** poniższy enum odpowiada za kolory dla naszych kart nadawane w celu możliwości przeprowadzenia gry*/
public enum Kolory{
    KIER("Kier"),
    KARO("Karo"),
    PIK("Pik"),
    TREFL("Trefl");

    /** tekstKolory poniżej są nam potrzebny
     * w celu wypełnienia naszych kolorów kart stringami*/
    private final String tekstKolory;

    /**konstruktor*/
    private Kolory(String tekstKolory){

        this.tekstKolory = tekstKolory;
    }
    public String printKolory(){
        return this.tekstKolory;
    }

    public static Kolory fromString(String text) {
        for (Kolory kolory : Kolory.values()) {
            if (kolory.tekstKolory.equalsIgnoreCase(text)) {
                return kolory;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found in Kolory enum");
    }
}