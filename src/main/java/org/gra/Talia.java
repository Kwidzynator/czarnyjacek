package org.gra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * raczej oczywiste gdzieś musimy nasze karty przechowywać
 * */

public class Talia implements Iterable<Karty>{

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

    public Karty get(int index) {
        if (index >= 0 && index < kartyList.size()) {
            return kartyList.get(index);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<Karty> iterator() {
        return kartyList.iterator();
    }
}
