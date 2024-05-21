package org.gra;

import java.util.ArrayList;
import java.util.List;
/**
 * raczej oczywiste gdzieś musimy nasze karty przechowywać
 * */

public class Talia {

    public final List<Karty> kartyList;

    public Talia() {
        this.kartyList = new ArrayList<>();
    }

    public void wyczyszczenie(){
        this.kartyList.clear();
    }
    public void dodaj(Karty karta){
        this.kartyList.add(karta);
    }

    public StringBuilder pokazKarty(){
        StringBuilder str = new StringBuilder();
        for(Karty k : kartyList){
            str.append(k.toString());
        }
        return str;
    }

    public boolean oddaj(Karty karta, Talia talia){
        if(!kartyList.contains(karta)){
            return false;
        }
        kartyList.remove(karta);
        talia.dodaj(karta);
        return true;
    }
}
