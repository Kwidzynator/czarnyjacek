package serwer;

import org.gra.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class Watek extends Thread {

    private BufferedWriter bw;
    private BufferedReader br;
    private Socket socket;
    private Karty kartatmp;
    private Gra gra = new Gra();
    private JSONArray jsonA;
    private Talia talia;
    public Watek(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            poczatekGry();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void poczatekGry() throws IOException {
        jsonA = new JSONArray();


        String linkkarta1;
        String linkkarta2;

        linkkarta1 = gra.uzyskanieLinka();
        linkkarta2 = gra.uzyskanieLinka();

        talia = gra.getTaliaUzytych();

        kartatmp = talia.get(0);
        Karty kartatmp2 = talia.get(1);
        dodanieKartyDOJSONA(linkkarta1, kartatmp);

        dodanieKartyDOJSONA(linkkarta2, kartatmp2);

        System.out.println(jsonA);


        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //będziemy wysyłać tak: wartość punktową, co ma zostać otwarte dla graficznej części, oraz kolor karty i jej nazwe
        bw.write(jsonA.toString());
        bw.newLine();
        bw.flush();

        //nie chcemy aby ta funkcja była za długa to przechodzimy do reszty gry
        resztaGry();
    }

    private void resztaGry() throws IOException {
        while(true){

            String otrzymana = br.readLine();

            jsonA = new JSONArray(otrzymana);
            String nazwa = jsonA.getString(0);
            String kolor = jsonA.getString(1);
            int wartosc = jsonA.getInt(2);

            gra.pktKlienta += wartosc;
            Kolory kartaKolor = Kolory.valueOf(kolor);
            Wartosci kartaWartosc = Wartosci.valueOf(nazwa);
            kartatmp = new Karty(kartaKolor, kartaWartosc);
            gra.dodanie(kartatmp);

            nazwa = jsonA.getString(3);
            kolor = jsonA.getString(4);
            wartosc = jsonA.getInt(5);
            gra.pktKlienta += wartosc;
            kartaKolor = Kolory.valueOf(kolor);
            kartaWartosc = Wartosci.valueOf(nazwa);
            kartatmp = new Karty(kartaKolor, kartaWartosc);
            gra.dodanie(kartatmp);

            miniAI();

        }
    }
    private void dodanieKartyDOJSONA(String linkkarta, Karty karta){
        JSONObject card = new JSONObject();
        card.put("link", linkkarta);
        card.put("kolor", karta.uzyskajKolor());
        card.put("wartosc", karta.uzyskajWartosc());
        card.put("nazwa", karta.uzyskajNazwe());

        jsonA.put(card);
        gra.pktSerwera += karta.uzyskajWartosc();
    }
    private void miniAI(){
        if(gra.pktSerwera < 17){
            kartatmp = gra.losowanie();
            gra.dodanie(kartatmp);
            jsonA = new JSONArray();
        }
    }
}