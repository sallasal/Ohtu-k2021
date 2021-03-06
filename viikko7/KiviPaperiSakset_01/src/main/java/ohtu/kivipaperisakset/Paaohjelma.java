//
// Written as group assignment by Mary Ehrsted, Jenny Perttola, Salla Salokanto 6.3.2021
//

package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            if (vastaus.endsWith("a")) {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KiviPaperiSakset kaksinpeli = new KPSPelaajaVsPelaaja(Peliparametrit.luoKaksinpeli());
                kaksinpeli.pelaa();
            } else if (vastaus.endsWith("b")) {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KiviPaperiSakset tekoalypeli = new KPSTekoaly(Peliparametrit.luoTekoalypeli(new Tekoaly()));
                tekoalypeli.pelaa();
            } else if (vastaus.endsWith("c")) {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KiviPaperiSakset pahaYksinpeli = new KPSParempiTekoaly(Peliparametrit.luoPahaTekoalypeli(new TekoalyParannettu(20)));
                pahaYksinpeli.pelaa();
            } else {
                break;
            }

        }

    }
}
