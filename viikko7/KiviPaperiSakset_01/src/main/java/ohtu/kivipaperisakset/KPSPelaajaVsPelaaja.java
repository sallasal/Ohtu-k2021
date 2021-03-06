package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {

    private static final Scanner scanner = new Scanner(System.in);

    public KPSPelaajaVsPelaaja(Peliparametrit parametrit) {
        super(parametrit);
    }

//    public void pelaa() {
//        Tuomari tuomari = new Tuomari();
//
//        System.out.println("Ensimmäisen pelaajan siirto: ");
//        String ekanSiirto = scanner.nextLine();
//        System.out.println("Toisen pelaajan siirto: ");
//        String tokanSiirto = scanner.nextLine();
//
//        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
//            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
//            System.out.println(tuomari);
//            System.out.println();
//
//            System.out.println("Ensimmäisen pelaajan siirto: ");
//            ekanSiirto = scanner.nextLine();
//            
//            System.out.println("Toisen pelaajan siirto: ");
//            tokanSiirto = scanner.nextLine();
//        }
//
//        System.out.println();
//        System.out.println("Kiitos!");
//        System.out.println(tuomari);
//    }

//    private static boolean onkoOkSiirto(String siirto) {
//        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
//    }

    @Override
    protected String toisenSiirto() {
        return scanner.nextLine();
    }
}