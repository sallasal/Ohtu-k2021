package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KiviPaperiSakset {

    private static final Scanner scanner = new Scanner(System.in);

    public KPSTekoaly(Peliparametrit parametrit) {
        super(parametrit);
    }

    @Override
    protected String toisenSiirto() {
        String siirto = super.parametrit.getTekoaly().annaSiirto();
        System.out.println(siirto);
        return siirto;
    }
}