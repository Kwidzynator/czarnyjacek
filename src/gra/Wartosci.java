package gra;

/** poniższy enum odpowiada za wartości dla naszych kart nadawane w celu możliwości przeprowadzenia gry*/
public enum Wartosci{
        DWA(2, "Dwa"), TRRY(3, "Trzy"), CZTERY(4, "Cztery"),
        PIEC(5, "Pięć"), SZESC(6, "Sześć"), SIEDEM(7, "Siedem"),
        OSIEM(8, "Osiem"), DZIEWIEC(9, "Dziewięć"), DZIESIEC(10, "Dziesięć"),
        JOPEK(2, "Jopek"), KROLOWA(3, "Królowa"), KROL(4, "Król"), AS(11, "As");

        /**aby nadać naszym kartom wartości musimy utworzyć prywatne pole z liczbą która będzie przypisana do danego enuma*/
        private final int liczbaWartosci;
        private final String nazwyKart;

        /**konstruktor*/
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

