package Techniczna;

import Techniczna.Przyciski.Instrukcja;
import Techniczna.Przyciski.Zagraj;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Gui {

    /**poniżej zmienna odpowiadająca za nasze okno, oraz zmienne do card layout mające na celu przerzucanie nas od
     * menu do ekranu gry*/
    protected JFrame frame;
    /**panelCont przechowuje nasze pozostałe panele,
     * panel menu - stan kiedy uruchamiamy program
     * panel gra - stan po kliknięciu przycisku zagraj (nasza gra)*/
    protected JPanel panelCont = new JPanel();
    protected JPanel menu = new JPanel();
    protected JPanel menuTlo = new JPanel();
    protected JPanel menuPrzyciski = new JPanel();
    protected JPanel gra = new JPanel();

    /**kontruktor postawiony w ramach formalności, implementacje pod nim będą odpowiedzialne za nasze GUI*/
    public Gui(){};

    /**funkcja okno jest odpowiedzialna za ogólną deklarację naszego okna w niej będą wywoływane wszelkiego rodzaju
     * inne funkcje ozdabiające nasze gui*/
    public void okno(){

        this.frame = new JFrame();
        this.frame.setSize(1820, 1820);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);

        this.frame.setContentPane(menu);
        this.menu.setLayout(new BorderLayout());
        tlo();

        przyciski();

        ikona();

        this.frame.setVisible(true);
    }

    /**funkcja odpowiedzialna za przydzielenie ikonki*/
    protected void ikona(){
        ImageIcon imageIcon = new ImageIcon((Objects.requireNonNull(getClass().getResource("./Obrazki/catgambling.png"))));
        this.frame.setIconImage(imageIcon.getImage());
    }

    /**funkcja ustawiająca tło*/
    protected void tlo(){
        ImageIcon kot = new ImageIcon(Objects.requireNonNull(getClass().getResource("./Obrazki/catjamgamblin.png")));
        JLabel tloLabel = new JLabel(kot);
        this.menuTlo.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.menuTlo.add(tloLabel);
        this.menuTlo.setBackground(new Color(0x333A3A));
        this.menu.add(this.menuTlo);
    }

    /**funkcja ustawiająca przyciski, planowane rozbicie na 3: instrukcja, logowanie(to na potem), rozpoczęcie gry*/
    protected void przyciski(){
        menuPrzyciski.setLayout(new BoxLayout(menuPrzyciski, BoxLayout.PAGE_AXIS));
        Instrukcja instrukcja = new Instrukcja(this.menuPrzyciski);
        Zagraj zagraj = new Zagraj(this.menuPrzyciski, this.frame);
        menuPrzyciski.setAlignmentX(Component.LEFT_ALIGNMENT); // Ustawienie do lewej
        menuPrzyciski.setAlignmentY(Component.TOP_ALIGNMENT); // Ustawienie u góry

        this.menuTlo.add(menuPrzyciski);
    }

    protected void uruchomienieGry(){

    }


}
