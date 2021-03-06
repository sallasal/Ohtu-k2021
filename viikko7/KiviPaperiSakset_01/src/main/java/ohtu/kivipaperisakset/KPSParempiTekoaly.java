package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KiviPaperiSakset  {

    private static final Scanner scanner = new Scanner(System.in);

    public KPSParempiTekoaly(Peliparametrit parametrit) {
        super(parametrit);
    }

    @Override
    protected String toisenSiirto() {
        String siirto = super.parametrit.getTekoalyParannettu().annaSiirto();
        System.out.println(siirto);
        return siirto;
    }
}
