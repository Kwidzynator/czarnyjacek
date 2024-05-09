package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**klasa odpowiedzialna za implementacje przycisku zawierającego instrukcje*/
public class Instrukcja extends Gui implements ActionListener {
    protected final JButton przyciskInstrukcji;

    public Instrukcja(JPanel panel){
        this.przyciskInstrukcji = new JButton("Instrukcja");
        this.przyciskInstrukcji.addActionListener(this);
        this.przyciskInstrukcji.setFocusable(false);

        panel.add(przyciskInstrukcji);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == przyciskInstrukcji){
                UtworzenieOkna utworzenieOkna = new UtworzenieOkna();
                JFrame oknoInstrukcji = utworzenieOkna.okno();

                // Ustawienie menedżera rozkładu BorderLayout
                oknoInstrukcji.setLayout(new BorderLayout());

                JLabel instrukcja = new JLabel("<html><font color = 'white'> <font size = '20'> <center> Instrukcja gry w Blackjack'a</center> </font> <br><br>" +
                        "<font color = 'red'> Cel gry:</font><br>" +
                        "<ul> Celem gry Blackjack jest uzyskanie ręki kart o wartości jak najbliższej 21, nie przekraczając jej. " +
                        "<br> Gracz rywalizuje z krupierem, starając się uzyskać lepszą rękę, ale bez przekroczenia wartości 21. </ul> <br>" +
                        "<font color = 'red'> Wartości kart: </font> <br>" +
                        "<ul>Karty od 2 do 10 mają wartość odpowiadającą ich numerowi.<br>" +
                        "Jopek (J) ma wartość 2.<br>" +
                        "Królowa (Q) ma wartość 3.<br>" +
                        "Król (K) ma wartość 4.<br>" +
                        "As (A) ma wartość 11.</ul><br>" +
                        "<font color = 'red'> Ogólne zasady: </font><br>" +
                        "<ul>Gracz rozpoczyna grę stawiając zakład.<br>" +
                        "Każdy gracz oraz krupier otrzymuje dwie karty. Jedna karta krupiera jest odkryta, a druga zakryta.</ul><br>" +
                        "<font color = 'red'> Gracz ma kilka możliwych ruchów: </font> <br>" +
                        "<ul> Hit: Poproszenie o kolejną kartę.<br>" +
                        "Stand: Pozostanie przy obecnej ręce.<br>" +
                        "Double Down: Podwojenie zakładu i otrzymanie tylko jednej kolejnej karty. (opcjonalnie)<br>" +
                        "Surrender: Zrzeczenie się połowy zakładu, rezygnując z dalszej gry. (opcjonalnie)</ul><br>" +
                        "<font color = 'red'> Gracz wygrywa w momencie gdy: </font><br>" +
                        "<ul>Ma rękę o wartości bliższej 21 niż krupier, ale nie przekraczającą 21.<br>" +
                        "Ma rękę o wartości 21 (Blackjack), gdy krupier nie ma Blackjacka.<br>" +
                        "Krupier przekracza 21 punktów, a gracz nie.<br>" +
                        "W przypadku remisu (tzw. push), gracz odzyskuje swój zakład.<br>" +
                        "Jeśli gracz ma Blackjacka, otrzymuje wypłatę 3:2 (czyli 1.5-krotność zakładu), chyba że krupier także ma Blackjacka, wtedy jest to remis.<br>" +
                        "Jeśli gracz przekroczy 21 punktów (Bust), przegrywa swój zakład. </ul> </font> </html>", SwingConstants.CENTER);

                instrukcja.setFont(instrukcja.getFont().deriveFont(20.0f));
                oknoInstrukcji.add(instrukcja, BorderLayout.NORTH); // Umieszczenie etykiety w górnym obszarze

                // Dopasowanie rozmiaru ramki do zawartości
                oknoInstrukcji.pack();

                // Ustawienie ramki na środku ekranu
                oknoInstrukcji.setLocationRelativeTo(null);

                // Wyświetlenie ramki
                oknoInstrukcji.setVisible(true);

        }
    }
}
