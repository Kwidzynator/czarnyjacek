package GUI;

import org.gra.Gra;
import org.gra.Karty;
import org.gra.Kolory;
import org.gra.Wartosci;
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
    private WyswietlanieKarty wyswietlanieKarty;

    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;

    private final Gra gra = new Gra();

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

        przyciski();
        PrzyciskiGry przyciskiGry = new PrzyciskiGry(hit, stand, doubleDown, surrender, gra,
                wyswietlanieKarty, oknogry, postawione, srodki);

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
        JSONArray jsonArray = new JSONArray(przeslane);
        wyswietlanieKarty = new WyswietlanieKarty();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            //ma to na celu aby nasza pierwsza otrzymana karta była zasłonięta, losuje dwie karty więc no
            String link;
            if(i != 0) {
                link = jsonObject.getString("link");
            }
            else{
                link = "";
            }
            System.out.println(link);
            String nazwa = jsonObject.getString("nazwa");
            String kolor = jsonObject.getString("kolor");
            int wartosc = jsonObject.getInt("wartosc");


            System.out.println(nazwa);
            System.out.println(kolor);
            System.out.println(wartosc);
            //dodajemy kartę do talii aby się nie powatarzała
            Kolory kartaKolor = Kolory.fromString(kolor);
            Wartosci kartaWartosc = Wartosci.fromString(nazwa);
            Karty karta = new Karty(kartaKolor, kartaWartosc);
            gra.dodanie(karta);


        }
        kartyStartowe();

    }

    private void kartyStartowe(){
        String sciezkaKarty1, sciezkakarty2;
        sciezkaKarty1 = gra.uzyskanieLinka();
        sciezkakarty2 = gra.uzyskanieLinka();
        System.out.println(gra.getPktKlienta());
        wyswietlanieKarty = new WyswietlanieKarty();
        panelKart = wyswietlanieKarty.startGry(sciezkaKarty1, sciezkakarty2);

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
}