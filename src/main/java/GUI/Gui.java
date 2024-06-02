package GUI;

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

    protected JPanel menu = new JPanel();
    protected JPanel menuTlo = new JPanel();
    protected JPanel menuPrzyciski = new JPanel();
    protected JPanel gra = new JPanel();
    protected JButton przyciskZagraj;


    /**kontruktor postawiony w ramach formalności, implementacje pod nim będą odpowiedzialne za nasze GUI*/
    public Gui(){};

    /**funkcja okno jest odpowiedzialna za ogólną deklarację naszego okna w niej będą wywoływane wszelkiego rodzaju
     * inne funkcje ozdabiające nasze gui*/
    public void okno(){
        //tutaj nie stosujemy pliku UtworzenieOkna gdyż te okno jest na tyle różne od pozostałych że użycie tego zdaje się być bez sensu
        this.frame = new JFrame();
        this.frame.setSize(1820, 1820);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);

        this.frame.setContentPane(menu);
        this.menu.setLayout(new BoxLayout(this.menu, BoxLayout.PAGE_AXIS));

        oknoLogowania(() -> {
            przyciski();
            tlo();
            ikona();
            this.frame.setVisible(true);
        });

    }

    private void oknoLogowania(Runnable callback){
        OknoLogowaniaRejestracji olr = new OknoLogowaniaRejestracji();
        olr.lr(callback);
    }

    /**funkcja odpowiedzialna za przydzielenie ikonki*/
    private void ikona(){
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Obrazki/catgambling.png")));
        this.frame.setIconImage(imageIcon.getImage());
    }

    /**funkcja ustawiająca tło*/
    private void tlo(){
        ImageIcon kot = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Obrazki/catjamgamblin.png")));
        JLabel tloLabel = new JLabel(kot);
        this.menuTlo.setLayout(new BoxLayout(menuTlo, BoxLayout.LINE_AXIS));
        this.menuTlo.add(tloLabel);
        this.menu.setBackground(new Color(0x333A3A));
        this.menu.add(this.menuTlo);
    }

    /**funkcja ustawiająca przyciski, planowane rozbicie na 3: instrukcja, logowanie(to na potem), rozpoczęcie gry*/
    private void przyciski(){
        this.menuPrzyciski.setLayout(new BoxLayout(menuPrzyciski, BoxLayout.LINE_AXIS));
        this.menuPrzyciski.setPreferredSize(new Dimension(200, 200));
        this.menuPrzyciski.setBackground(new Color(0x333A3A));

        Instrukcja instrukcja = new Instrukcja(this.menuPrzyciski);
        this.przyciskZagraj = new JButton("Zagraj");
        this.przyciskZagraj.setFocusable(false); // Usunięcie otoczki wokół napisu
        Zagraj zagraj = new Zagraj(przyciskZagraj);
        menuPrzyciski.add(przyciskZagraj);
        this.menu.add(menuPrzyciski);
    }

}
