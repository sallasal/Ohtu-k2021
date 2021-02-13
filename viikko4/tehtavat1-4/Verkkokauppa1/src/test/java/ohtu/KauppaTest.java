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
    
//    aloitetaan asiointi, koriin lisätään tuote, jota on varastossa tarpeeksi ja 
//            tuote joka on loppu ja suoritetaan ostos, varmista että kutsutaan pankin 
//            metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla

    
    @Test
    public void ostetaanJaMaksetaanTuoteJotaOnJaTuoteJotaEiOle() {
        when(varasto.saldo(5)).thenReturn(7);
        when(varasto.saldo(6)).thenReturn(0);
        when (viite.uusi()).thenReturn(678);
        
        when(varasto.haeTuote(5)).thenReturn(new Tuote(5, "hammastahna", 3));
        when(varasto.haeTuote(6)).thenReturn(new Tuote(6, "pähkinämysli", 4));
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(5);
        kauppa.lisaaKoriin(6);
        kauppa.tilimaksu("Peppiina", "24680");
        
        verify(pankki).tilisiirto(eq("Peppiina"), eq(678), eq("24680"), anyString(), eq(3));
    }
}
