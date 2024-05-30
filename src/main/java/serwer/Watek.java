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
    private boolean czyStand = false;
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
        otrzymanie2Kart();
    }

    private void otrzymanie2Kart() throws IOException {

            //wyczekujemy tutaj na otrzymanie dwóch pierwszych kart
            String przeslane = br.readLine();
            String przeslane2 = br.readLine();
            JSONArray jsonArray = new JSONArray(przeslane + przeslane2);

            // tutaj analogicznie jak na początku gry klienta, przyjmujemy dwie karty aby wiedzieć czego NIE dobierać
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String nazwa = jsonObject.getString("nazwa");
                String kolor = jsonObject.getString("kolor");
                int wartosc = jsonObject.getInt("wartosc");


                System.out.println(nazwa);
                System.out.println(kolor);
                System.out.println(wartosc);
                gra.pktKlienta += wartosc;
                //dodajemy kartę do talii aby się nie powatarzała
                Kolory kartaKolor = Kolory.fromString(kolor);
                Wartosci kartaWartosc = Wartosci.fromString(nazwa);
                Karty karta = new Karty(kartaKolor, kartaWartosc);
                gra.dodanie(karta);


            }
            System.out.println(gra.getPktKlienta());

            miniAI();


    }
    private void miniAI() throws IOException {
        if(gra.pktSerwera < 17){
            String link = gra.uzyskanieLinka();
            kartatmp = talia.get(talia.size() - 1);
            gra.dodanie(kartatmp);
            jsonA = new JSONArray();
            dodanieKartyDOJSONA(link, kartatmp);
            bw.write(jsonA.toString());
        }
        else{
            czyStand = true;
            bw.write("stand");
        }
        bw.newLine();
        bw.flush();
        odbieranie();
    }

    private void odbieranie() throws IOException {
        while(true){
            String wiadomosc = br.readLine();
            if(wiadomosc.equals("surrender")){
                br.close();
                bw.close();
                socket.close();
                System.out.println("koncze dzialanie watku");
                interrupt();
                break;
            }
            //serwera nie interesuje kto wygrał gdyż to i tak jest tylko gra na pkt
            if(wiadomosc.equals("stand") && czyStand){
                System.out.println("stand");
                gra.oczysczenie();
            }
            if(wiadomosc.equals("hit") || wiadomosc.equals("dd")){
                System.out.println("hit/dd");
                wiadomosc = br.readLine();

                JSONArray jsonArray = new JSONArray(wiadomosc);
                JSONObject jsonObject = new JSONObject(jsonArray);
                String nazwa = jsonObject.getString("nazwa");
                String kolor = jsonObject.getString("kolor");
                int wartosc = jsonObject.getInt("wartosc");
                gra.pktKlienta += wartosc;

                Kolory kartaKolor = Kolory.fromString(kolor);
                Wartosci kartaWartosc = Wartosci.fromString(nazwa);
                Karty karta = new Karty(kartaKolor, kartaWartosc);
                gra.dodanie(karta);
            }
            if(!czyStand){
                miniAI();
            }
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


}