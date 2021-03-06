package ohtu.kivipaperisakset;

public class Peliparametrit {
    
    private Tekoaly tekoaly;
    private TekoalyParannettu pahennettu;
    

    public static Peliparametrit luoKaksinpeli() {
        return new Peliparametrit(null, null);
    }

    public static Peliparametrit luoTekoalypeli(Tekoaly tekoaly) {
        return new Peliparametrit(tekoaly, null);
    }
    
    public static Peliparametrit luoPahaTekoalypeli(TekoalyParannettu pahennettu) {
        return new Peliparametrit(null, pahennettu);
    }

    protected Peliparametrit(Tekoaly tekoaly, TekoalyParannettu pahennettu) {
        this.tekoaly = tekoaly;
        this.pahennettu = pahennettu;
    }
    
    public Tekoaly getTekoaly() {
        return this.tekoaly;
    }
    
    public TekoalyParannettu getTekoalyParannettu() {
        return this.pahennettu;
    }
}
