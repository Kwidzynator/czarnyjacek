package org.gra;

public enum Wartosci {
    DWA(2, "Dwa"), TRRY(3, "Trzy"), CZTERY(4, "Cztery"),
    PIEC(5, "Piec"), SZESC(6, "Szesc"), SIEDEM(7, "Siedem"),
    OSIEM(8, "Osiem"), DZIEWIEC(9, "Dziewiec"), DZIESIEC(10, "Dziesiec"),
    JOPEK(10, "Jopek"), KROLOWA(10, "Krolowa"), KROL(10, "Krol"), AS(11, "As");

    private final int liczbaWartosci;
    private final String nazwyKart;

    private Wartosci(int liczbyWartosci, String nazwySpecjalnych){
        this.liczbaWartosci = liczbyWartosci;
        this.nazwyKart = nazwySpecjalnych;
    }

    public String printNazwy(){
        return nazwyKart;
    }

    public int printLiczbaWartosci(){
        return liczbaWartosci;
    }

    public static Wartosci fromString(String text) {
        for (Wartosci wartosci : Wartosci.values()) {
            if (wartosci.nazwyKart.equalsIgnoreCase(text)) {
                return wartosci;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found in Wartosci enum");
    }
}

