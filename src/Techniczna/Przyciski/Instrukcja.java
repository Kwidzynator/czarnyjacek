package Techniczna.Przyciski;


import Techniczna.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.jar.JarEntry;


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
            try{
                File instrukcja = new File(".\\src\\Techniczna\\Przyciski\\Instrukcja.txt");
                System.out.println("Katalog roboczy: " + System.getProperty("user.dir"));
                if (Desktop.isDesktopSupported()) {
                    // Otwarcie pliku za pomocą domyślnej aplikacji
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(instrukcja);
                } else {
                    System.out.println("Twoje środowisko uruchomieniowe nie obsługuje operacji otwierania plików.");
                }

            } catch (Exception ex){
                UtworzenieOkna utworzenieOkna = new UtworzenieOkna();
                JFrame oknoBledu = utworzenieOkna.okno();

                 // Ustawienie menedżera rozkładu BorderLayout
                oknoBledu.setLayout(new BorderLayout());

                 JLabel instrukcja = new JLabel("Wystąpił błąd przy otwarciu instrukcji", SwingConstants.CENTER);
                 instrukcja.setFont(instrukcja.getFont().deriveFont(20.0f));
                 oknoBledu.add(instrukcja, BorderLayout.NORTH); // Umieszczenie etykiety w górnym obszarze

                // Dopasowanie rozmiaru ramki do zawartości
                oknoBledu.pack();

                 // Ustawienie ramki na środku ekranu
                 oknoBledu.setLocationRelativeTo(null);

                 // Wyświetlenie ramki
                   oknoBledu.setVisible(true);
            }
        }
    }
}
