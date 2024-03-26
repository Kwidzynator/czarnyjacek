package Techniczna.Przyciski;


import Techniczna.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**klasa odpowiedzialna za implementacje przycisku zawierającego instrukcje*/
public class Instrukcja extends Gui implements ActionListener {
    protected final JButton przyciskInstrukcji;
    public Instrukcja(JFrame frame){
        this.przyciskInstrukcji = new JButton("Instrukcja");
        this.przyciskInstrukcji.setBounds(10, 10, 100, 50); // Ustawienie pozycji przycisku w rogu obrazka
        this.przyciskInstrukcji.addActionListener(this); // Nasłuchujemy zdarzenia przycisku
        this.przyciskInstrukcji.setFocusable(false); // Usunięcie otoczki wokół napisu

        frame.setLayout(null);
        frame.add(przyciskInstrukcji);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == przyciskInstrukcji){
            /*UtworzenieOkna utworzenieOkna = new UtworzenieOkna();
            JFrame oknoInstrukcji = utworzenieOkna.okno();

            // Ustawienie menedżera rozkładu BorderLayout
            oknoInstrukcji.setLayout(new BorderLayout());

            JLabel instrukcja = new JLabel("Instrukcja gry w Blackjacka", SwingConstants.CENTER);
            instrukcja.setFont(instrukcja.getFont().deriveFont(20.0f)); // Ustawienie większej czcionki dla instrukcji
            oknoInstrukcji.add(instrukcja, BorderLayout.NORTH); // Umieszczenie etykiety w górnym obszarze

            // Dopasowanie rozmiaru ramki do zawartości
            oknoInstrukcji.pack();

            // Ustawienie ramki na środku ekranu
            oknoInstrukcji.setLocationRelativeTo(null);

            // Wyświetlenie ramki
            oknoInstrukcji.setVisible(true); */
            try{
                File instrukcja = new File(".\\src\\Techniczna\\Przyciski\\Instrukcja.txt");
                System.out.println("Katalog roboczy: " + System.getProperty("user.dir"));
                if (Desktop.isDesktopSupported()) {
                    // Otwarcie pliku za pomocą domyślnej aplikacji
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(instrukcja);
                } else {
                    System.out.println("Twoje środowisko uruchomieniowe nie obsługuje operacji otwierania plików.");
                    // Możesz obsłużyć sytuację, gdy nie można otworzyć pliku.
                }

            } catch (IOException ex){
                            /*UtworzenieOkna utworzenieOkna = new UtworzenieOkna();
                JFrame oknoInstrukcji = utworzenieOkna.okno();

                 // Ustawienie menedżera rozkładu BorderLayout
                    oknoInstrukcji.setLayout(new BorderLayout());

                 JLabel instrukcja = new JLabel("Instrukcja gry w Blackjacka", SwingConstants.CENTER);
                    instrukcja.setFont(instrukcja.getFont().deriveFont(20.0f)); // Ustawienie większej czcionki dla instrukcji
                oknoInstrukcji.add(instrukcja, BorderLayout.NORTH); // Umieszczenie etykiety w górnym obszarze

                // Dopasowanie rozmiaru ramki do zawartości
                oknoInstrukcji.pack();

                 // Ustawienie ramki na środku ekranu
                 oknoInstrukcji.setLocationRelativeTo(null);

                 // Wyświetlenie ramki
                   oknoInstrukcji.setVisible(true); */
            }
        }
    }
}
