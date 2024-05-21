package org.gra;

/** poniższy enum odpowiada za wartości dla naszych kart nadawane w celu możliwości przeprowadzenia gry*/
public enum Wartosci{
        DWA(2, "Dwa"), TRRY(3, "Trzy"), CZTERY(4, "Cztery"),
        PIEC(5, "Piec"), SZESC(6, "Szesc"), SIEDEM(7, "Siedem"),
        OSIEM(8, "Osiem"), DZIEWIEC(9, "Dziewiec"), DZIESIEC(10, "Dziesiec"),
        JOPEK(10, "Jopek"), KROLOWA(10, "Krolowa"), KROL(10, "Krol"), AS(11, "As");

        /**aby nadać naszym kartom wartości musimy utworzyć prywatne pole z liczbą która będzie przypisana do danego enuma*/
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
}

