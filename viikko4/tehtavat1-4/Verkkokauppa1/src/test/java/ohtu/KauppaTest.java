package ohtu;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ohtu.verkkokauppa.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa kauppa;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanParametrein() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));

    }

    @Test
    public void ostetaanJaMaksetaanKaksiEriTuotetta() {
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.saldo(3)).thenReturn(5);
        when(viite.uusi()).thenReturn(365);

        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "härkis", 3));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "banaani", 1));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("esko", "67890");

        verify(pankki).tilisiirto(eq("esko"), eq(365), eq("67890"), anyString(), eq(4));

    }

    @Test
    public void ostetaanJaMaksetaanKaksiSamaaTuotetta() {
        when(varasto.saldo(4)).thenReturn(5);
        when(varasto.haeTuote(4)).thenReturn(new Tuote(4, "soijajogurtti", 2));
        when(viite.uusi()).thenReturn(987);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(4);
        kauppa.lisaaKoriin(4);
        kauppa.tilimaksu("Martina", "98765");

        verify(pankki).tilisiirto(eq("Martina"), eq(987), eq("98765"), anyString(), eq(4));
    }

    @Test
    public void ostetaanJaMaksetaanTuoteJotaOnJaTuoteJotaEiOle() {
        when(varasto.saldo(5)).thenReturn(7);
        when(varasto.saldo(6)).thenReturn(0);
        when(viite.uusi()).thenReturn(678);

        when(varasto.haeTuote(5)).thenReturn(new Tuote(5, "hammastahna", 3));
        when(varasto.haeTuote(6)).thenReturn(new Tuote(6, "pähkinämysli", 4));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(5);
        kauppa.lisaaKoriin(6);
        kauppa.tilimaksu("Peppiina", "24680");

        verify(pankki).tilisiirto(eq("Peppiina"), eq(678), eq("24680"), anyString(), eq(3));
    }

    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksen() {
        when(varasto.saldo(7)).thenReturn(10);
        when(viite.uusi()).thenReturn(123);

        when(varasto.haeTuote(7)).thenReturn(new Tuote(7, "tumma suklaa", 2));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(7);
        kauppa.lisaaKoriin(7);
        kauppa.tilimaksu("Masa", "12345");

        verify(pankki).tilisiirto(eq("Masa"), eq(123), eq("12345"), anyString(), eq(4));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(7);
        kauppa.tilimaksu("Väykkä", "23456");

        verify(pankki).tilisiirto(eq("Väykkä"), eq(123), eq("23456"), anyString(), eq(2));
    }

    @Test
    public void kutsuuUudenViitenumeronJokaMaksulle() {
        when(varasto.saldo(8)).thenReturn(10);
        when(viite.uusi())
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3);
        
        when(varasto.haeTuote(8)).thenReturn(new Tuote(8, "paprika", 2));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(8);
        kauppa.tilimaksu("Esko", "123");
        
        verify(viite, times(1)).uusi();
        verify(pankki).tilisiirto(eq("Esko"), eq(1), eq("123"), anyString(), eq(2));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(8);
        kauppa.lisaaKoriin(8);
        kauppa.tilimaksu("Eemeli", "567");
        
        verify(viite, times(2)).uusi();
        verify(pankki).tilisiirto(eq("Eemeli"), eq(2), eq("567"), anyString(), eq(4));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(8);
        kauppa.tilimaksu("Iida", "1357");
        
        verify(viite, times(3)).uusi();
        verify(pankki).tilisiirto(eq("Iida"), eq(3), eq("1357"), anyString(), eq(2));
    }
    
    @Test
    public void koristaPoistaminenPaivittaaSummanOikein() {
        when(varasto.saldo(9)).thenReturn(10);
        when(viite.uusi()).thenReturn(135);
        
        when(varasto.haeTuote(9)).thenReturn(new Tuote(9, "hummus", 3));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(9);
        kauppa.lisaaKoriin(9);
        kauppa.poistaKorista(9);
        kauppa.tilimaksu("Mervi", "4563");
        
        verify(pankki).tilisiirto(eq("Mervi"), eq(135), eq("4563"), anyString(), eq(6));

    }
}
