package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KiviPaperiSakset {

    private static final Scanner scanner = new Scanner(System.in);

    public KPSTekoaly(Peliparametrit parametrit) {
        super(parametrit);
    }

//    public void pelaa() {
//        Tuomari tuomari = new Tuomari();
//        Tekoaly tekoaly = new Tekoaly();
//
//        System.out.println("Ensimmäisen pelaajan siirto: ");
//        String ekanSiirto = scanner.nextLine();
//        String tokanSiirto;
//
//        tokanSiirto = tekoaly.annaSiirto();
//        System.out.println("Tietokone valitsi: " + tokanSiirto);
//
//
//        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
//            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
//            System.out.println(tuomari);
//            System.out.println();
//
//            System.out.println("Ensimmäisen pelaajan siirto: ");
//            ekanSiirto = scanner.nextLine();
//
//            tokanSiirto = tekoaly.annaSiirto();
//            System.out.println("Tietokone valitsi: " + tokanSiirto);
//            tekoaly.asetaSiirto(ekanSiirto);
//
//        }
//
//        System.out.println();
//        System.out.println("Kiitos!");
//        System.out.println(tuomari);
//    }
//
//    private static boolean onkoOkSiirto(String siirto) {
//        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
//    }

    @Override
    protected String toisenSiirto() {
        String siirto = super.parametrit.getTekoaly().annaSiirto();
        System.out.println(siirto);
        return siirto;
    }
}