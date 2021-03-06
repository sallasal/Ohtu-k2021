package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {

    private static final Scanner scanner = new Scanner(System.in);

    public KPSPelaajaVsPelaaja(Peliparametrit parametrit) {
        super(parametrit);
    }

    @Override
    protected String toisenSiirto() {
        return scanner.nextLine();
    }
}