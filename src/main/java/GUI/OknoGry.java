package GUI;

import org.gra.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

/**
 * klasa ta jest odpowiedzialna za wygląd okna gry, m.in aby zainicjować przyciski, ustawić karty na stół
 *      oraz przy pierwszym uruchomieniu zainicjować panel logowania
 * */
public class OknoGry{

    private int postawione;
    private int srodki;

    protected GridBagConstraints gbc = new GridBagConstraints();
    protected JPanel calosc = new JPanel();
    protected JPanel przyciski = new JPanel();

    protected final JButton hit = new JButton("hit");
    protected final JButton stand = new JButton("stand");
    protected final JButton doubleDown = new JButton("double down");
    protected final JButton surrender = new JButton("surrender");
    protected JPanel panelKart;
    private JPanel panelSerwer;
    private WyswietlanieKarty wyswietlanieKarty;

    private Talia talia;
    private Socket socket;
    private final BufferedReader br;
    private final BufferedWriter bw;
    private final Gra gra = new Gra();

    private PrzyciskiGry przyciskiGry;

    public OknoGry(int postawione, int srodki, Socket socket) throws IOException {
        this.postawione = postawione;
        this.srodki = srodki;
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }


    /**
     * WAŻNE bo tutaj mamy troche skakania po klasach, chcemy abyśmy MUSIELI najpierw postawić zakład, a potem grać w związku z czym:
     * 1. w klasie zagraj tworzymy konstruktor klasy okno zakładu
     * 2. okno zakładu zamknie się dopiero PO wprowadzeniu liczby i wtedy uruchomi naszą klase okno gry w raz z funkcją po zakładzie
     * 3. następnie po zakładzie przedstawi całą sytuację i będziemy resetować wszystko w przypadku poddania się/zakonczenia gry
     * */
    protected void poZakladzie() throws IOException {


        calosc.setLayout(new GridBagLayout());
        calosc.setOpaque(false);

        UtworzenieOkna utworzenieOkna = new UtworzenieOkna();
        JFrame oknogry = utworzenieOkna.okno();

        kartyStartoweSerwera();

        kartyStartowe();


        przyciski();

        przyciskiGry = new PrzyciskiGry(hit, stand, doubleDown, surrender, gra,
                wyswietlanieKarty, oknogry, postawione, srodki, socket, this);

        ustawieniePrzyciskow();

        oknogry.add(calosc);


        /*objaśnienie: tutaj nasłuchujemy czy nasze okno jest już otwarte gdyż nie chcemy, umożliwić użytkownikowi
            otwarcia dwóch okien z grami, oraz pozbywamy się przycisków w przypadku "schowania okna"*/
        oknogry.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                przyciski.removeAll();
            }
        });
    }

    private void ustawieniePrzyciskow(){
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; // Wypełnienie komórki siatki w obu kierunkach
        gbc.insets = new Insets(10, 10, 10, 10); // Marginesy

        calosc.add(przyciski, gbc);


    }

    private void kartyStartoweSerwera() throws IOException {
        String przeslane = br.readLine();
        System.out.println("Received JSON string: " + przeslane);
        JSONArray jsonArray = new JSONArray(przeslane);
        wyswietlanieKarty = new WyswietlanieKarty();
        String link1 = null;
        String link2 = null;
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            //ma to na celu aby nasza pierwsza otrzymana karta była zasłonięta, losuje dwie karty więc no

            if(i != 0) {
                link2 = jsonObject.getString("link");
                System.out.println(link2);
            }
            else{
                link1 = "/karty/tylKarty.png";
                System.out.println(link1);
            }

            String nazwa = jsonObject.getString("nazwa");
            String kolor = jsonObject.getString("kolor");
            int wartosc = jsonObject.getInt("wartosc");


            System.out.println(nazwa);
            System.out.println(kolor);
            System.out.println(wartosc);
            gra.pktSerwera += wartosc;
            //dodajemy kartę do talii aby się nie powatarzała
            Kolory kartaKolor = Kolory.fromString(kolor);
            Wartosci kartaWartosc = Wartosci.fromString(nazwa);
            Karty karta = new Karty(kartaKolor, kartaWartosc);
            gra.dodanie(karta);


        }

        System.out.println("pkt serwera: " + gra.getPktSerwera());
        wyswietlanieKarty = new WyswietlanieKarty();
        panelSerwer = wyswietlanieKarty.startGry(link1, link2, 1);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.anchor |= GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        calosc.add(panelSerwer, gbc);
    }

    private void kartyStartowe() throws IOException {
        String sciezkaKarty1, sciezkakarty2;
        sciezkaKarty1 = gra.uzyskanieLinka();
        sciezkakarty2 = gra.uzyskanieLinka();

        talia = gra.getTaliaUzytych();
        Karty karta = talia.get(talia.size() - 1);
        gra.pktKlienta += karta.uzyskajWartosc();
        wyslanieKarty(karta);

        karta = talia.get(talia.size() - 2);
        gra.pktKlienta += karta.uzyskajWartosc();
        System.out.println("pkt klienta: " + gra.getPktKlienta());
        wyslanieKarty(karta);


        wyswietlanieKarty = new WyswietlanieKarty();
        panelKart = wyswietlanieKarty.startGry(sciezkaKarty1, sciezkakarty2, 0);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.anchor |= GridBagConstraints.SOUTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; // Wypełnienie komórki siatki w obu kierunkach

        calosc.add(panelKart, gbc);

    }
    private void przyciski(){
        przyciski.setOpaque(false);
        przyciski.setLayout(new BoxLayout(przyciski, BoxLayout.LINE_AXIS));
        przyciski.setPreferredSize(new Dimension(200, 200));

        hit.setFocusable(false);
        stand.setFocusable(false);
        doubleDown.setFocusable(false);
        surrender.setFocusable(false);

        JPanel lewogora = new JPanel();
        lewogora.setOpaque(false);
        lewogora.setLayout(new FlowLayout(FlowLayout.LEFT));
        lewogora.setBackground(new Color(0x333A3A));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(0x333A3A));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Marginesy dla panelu przycisków

        buttonPanel.add(hit);
        buttonPanel.add(stand);
        buttonPanel.add(doubleDown);
        buttonPanel.add(surrender);

        lewogora.add(buttonPanel);
        przyciski.add(lewogora, BorderLayout.NORTH); // Dodajemy panel przycisków do kontenera, ustawiając go na górze
    }



    private void wyslanieKarty(Karty karta) throws IOException {
        JSONArray jsonA = new JSONArray();

        JSONObject card = new JSONObject();
        card.put("kolor", karta.uzyskajKolor());
        card.put("wartosc", karta.uzyskajWartosc());
        card.put("nazwa", karta.uzyskajNazwe());

        jsonA.put(card);
        bw.write(jsonA.toString());
        bw.newLine();
        bw.flush();
    }

    public void reset() throws IOException {

        gra.oczysczenie();
        panelKart.removeAll();
        panelSerwer.removeAll();
        przyciski.removeAll();
        przyciski();
        ustawieniePrzyciskow();
        calosc.revalidate();
        calosc.repaint();
    }


}