package org.gra;


public class UzyskanieGrafiki  {

    public String sciezka(Karty karta){
        switch(karta.uzyskajKolor()){
            case "Kier":
                switch(karta.uzyskajNazwe()) {
                    case "As":
                        return "/karty/asKier.png";
                    case "Krol":
                        return "/karty/krolKier.png";
                    case "Krolowa":
                        return "/karty/krolowaKier.png";
                    case "Jopek":
                        return "/karty/jopekKier.png";
                    case "Dziesiec":
                        return "/karty/dziesiecKier.png";
                    case "Dziewiec":
                        return "/karty/dziewiecKier.png";
                    case "Osiem":
                        return "/karty/osiemKier.png";
                    case "Siedem":
                        return "/karty/siedemKier.png";
                    case "Szesc":
                        return "/karty/szescKier.png";
                    case "Piec":
                        return "/karty/piecKier.png";
                    case "Cztery":
                        return "/karty/czteryKier.png";
                    case "Trzy":
                        return "/karty/trzyKier.png";
                    case "Dwa":
                        return "/karty/dwaKier.png";
                }
            case "Karo":
                switch(karta.uzyskajNazwe()){
                    case "As":
                        return "/karty/asKaro.PNG";
                    case "Krol":
                        return "/karty/krolKaro.png";
                    case "Krolowa":
                        return "/karty/krolowaKaro.png";
                    case "Jopek":
                        return "/karty/jopekKaro.png";
                    case "Dziesiec":
                        return "/karty/dziesiecKaro.png";
                    case "Dziewiec":
                        return "/karty/dziewiecKaro.png";
                    case "Osiem":
                        return "/karty/osiemKaro.png";
                    case "Siedem":
                        return "/karty/siedemKaro.png";
                    case "Szesc":
                        return "/karty/szescKaro.png";
                    case "Piec":
                        return "/karty/piecKaro.png";
                    case "Cztery":
                        return "/karty/czteryKaro.png";
                    case "Trzy":
                        return "/karty/trzyKaro.png";
                    case "Dwa":
                        return "/karty/dwaKaro.png";
                }
            case "Pik":
                switch(karta.uzyskajNazwe()){
                    case "As":
                        return "/karty/asPik.png";
                    case "Krol":
                        return "/karty/krolPik.png";
                    case "Krolowa":
                        return "/karty/krolowaPik.png";
                    case "Jopek":
                        return "/karty/jopekPik.png";
                    case "Dziesiec":
                        return "/karty/dziesiecPik.png";
                    case "Dziewiec":
                        return "/karty/dziewiecPik.png";
                    case "Osiem":
                        return "/karty/osiemPik.png";
                    case "Siedem":
                        return "/karty/siedemPik.png";
                    case "Szesc":
                        return "/karty/szescPik.png";
                    case "Piec":
                        return "/karty/piecPik.png";
                    case "Cztery":
                        return "/karty/czteryPik.png";
                    case "Trzy":
                        return "/karty/trzyPik.png";
                    case "Dwa":
                        return "/karty/dwaPik.png";
                }
            case "Trefl":
                switch(karta.uzyskajNazwe()){
                    case "As":
                        return "/karty/asTrefl.png";
                    case "Krol":
                        return "/karty/krolTrefl.png";
                    case "Krolowa":
                        return "/karty/krolowaTrefl.png";
                    case "Jopek":
                        return "/karty/jopekTrefl.png";
                    case "Dziesiec":
                        return "/karty/dziesiecTrefl.png";
                    case "Dziewiec":
                        return "/karty/dziewiecTrefl.png";
                    case "Osiem":
                        return "/karty/osiemTrefl.png";
                    case "Siedem":
                        return "/karty/siedemTrefl.png";
                    case "Szesc":
                        return "/karty/szescTrefl.png";
                    case "Piec":
                        return "/karty/piecTrefl.png";
                    case "Cztery":
                        return "/karty/czteryTrefl.png";
                    case "Trzy":
                        return "/karty/trzyTrefl.png";
                    case "Dwa":
                        return "/karty/dwaTrefl.png";
                }
            default:
                return "/karty/tylKarty.png";
        }

    }
}
