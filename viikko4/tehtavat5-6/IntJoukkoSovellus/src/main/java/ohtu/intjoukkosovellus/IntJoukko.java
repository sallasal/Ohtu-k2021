package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int ALOITUSKOKO = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukonLuvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alustaJoukko(ALOITUSKOKO, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alustaJoukko(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int aloituskoko, int kasvatuskoko) {
        if (aloituskoko < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Tarkista aloituskoko ja kasvatuskoko.");
        }
        alustaJoukko(aloituskoko, kasvatuskoko);
    }

    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }

    private void alustaJoukko(int aloituskoko, int kasvatuskoko) {
        joukonLuvut = new int[aloituskoko];
        for (int i = 0; i < joukonLuvut.length; i++) {
            joukonLuvut[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean kuuluuJoukkoon(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonLuvut[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean lisaa(int luku) {
        if (!kuuluuJoukkoon(luku)) {
            joukonLuvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % joukonLuvut.length == 0) {
                int[] taulukkoOld = new int[joukonLuvut.length];
                taulukkoOld = joukonLuvut;
                kopioiTaulukko(joukonLuvut, taulukkoOld);
                joukonLuvut = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, joukonLuvut);
            }
            return true;
        }
        return false;
    }

    public boolean poista(int luku) {
        int luvunSijainti = -1;

        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonLuvut[i]) {
                luvunSijainti = i;
                joukonLuvut[luvunSijainti] = 0;
                break;
            }
        }
        if (luvunSijainti != -1) {
            for (int j = luvunSijainti; j < alkioidenLkm - 1; j++) {
                int alkuperainenLuku = joukonLuvut[j];
                joukonLuvut[j] = joukonLuvut[j + 1];
                joukonLuvut[j + 1] = alkuperainenLuku;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        }

        return erotus;
    }
    
        private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + joukonLuvut[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += joukonLuvut[i];
                tuotos += ", ";
            }
            tuotos += joukonLuvut[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(joukonLuvut, 0, taulu, 0, taulu.length);
        return taulu;
    }
}
