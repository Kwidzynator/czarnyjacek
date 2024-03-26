package Techniczna;

import Techniczna.Przyciski.Instrukcja;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Gui {

    /**kontruktor postawiony w ramach formalności, implementacje pod nim będą odpowiedzialne za nasze GUI*/
    public Gui(){};
    protected JFrame frame;
    /**funkcja okno jest odpowiedzialna za ogólną deklarację naszego okna w niej będą wywoływane wszelkiego rodzaju
     * inne funkcje ozdabiające nasze gui*/
    public void okno(){

        this.frame = new JFrame();    //tworzy okno
        this.frame.setSize(1820, 1820);        //ustala rozdzielczość
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //pozwala na zamknięcie po kliknięciu "x"
        this.frame.setResizable(false);

        ozdoby(); // aktywacja funkcji ozdoby zawierającej ikonke

        przyciski(); // część kodu odpowiedzialna za działalność przycisków

        ikona(); // zmiana ikonki

        this.frame.setVisible(true);         //opcja aby było widoczne dla użytkownika
    }
    /**funkcja odpowiedzialna za przydzielenie ikonki*/
    protected void ikona(){
        ImageIcon imageIcon = new ImageIcon((Objects.requireNonNull(getClass().getResource("./Obrazki/catgambling.png"))));
        this.frame.setIconImage(imageIcon.getImage());
    }

    /**funkcja ustawiająca tło*/
    protected void ozdoby(){
        // Dodajemy obrazek jako tło ramki
        ImageIcon kot = new ImageIcon(Objects.requireNonNull(getClass().getResource("./Obrazki/catjamgamblin.png")));
        JLabel tlo = new JLabel(kot);
        tlo.setLayout(new BorderLayout());
        tlo.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // Ustawienie rozmiaru obrazka na rozmiar ramki
        tlo.setOpaque(true); // Ustawienie tła jako nieprzeźroczystego
        tlo.setBackground(new Color(0x333A3A)); // Ustawienie koloru tła

        frame.add(tlo); // Dodajemy obrazek do ramki

        frame.setVisible(true); // Ustawienie ramki jako widocznej
    }

    /**funkcja ustawiająca przyciski, planowane rozbicie na 3: instrukcja, logowanie(to na potem), rozpoczęcie gry*/
    protected void przyciski(){
        Instrukcja instrukcja = new Instrukcja(this.frame); //konstruktor klasy instrukcja, odpowiedzialnej za implementacje przycisku

    }

}
