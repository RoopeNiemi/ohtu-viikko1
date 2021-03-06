package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    static final double  vertailuTarkkuus = 0.0001;
    static final double DEFAULT_TILAVUUS = 10;

    @Before
    public void setUp() {
        varasto = new Varasto(DEFAULT_TILAVUUS);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        double lisattavaMaara = 8;
        varasto.lisaaVarastoon(lisattavaMaara);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(lisattavaMaara, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottamallaLiikaaPalautaKaikki() {
        varasto.lisaaVarastoon(5);
        assertTrue(varasto.otaVarastosta(15) == 5.0);
        assertTrue(varasto.getSaldo() == 0.0);
    }
    
    @Test
    public void lisäämälläLiikaaMaxSaldo() {
        varasto.lisaaVarastoon(50);
        assertTrue(varasto.getSaldo() == 10);
    }
    
    @Test
    public void negatiivinenLisäysJaPoisto() {
        varasto.lisaaVarastoon(-5);
        assertTrue(varasto.getSaldo() == 0);
        
        varasto.lisaaVarastoon(3);
        varasto.otaVarastosta(-5);
        assertTrue(varasto.getSaldo() == 3.0);
    }
    
    @Test
    public void testaaKonstruktori() {
        varasto = new Varasto(150, 5);
        assertTrue(varasto.getSaldo() == 5.0);
        assertTrue(varasto.getTilavuus() == 150.0);
        
        varasto = new Varasto(-5);
        assertTrue(varasto.getTilavuus() == 0.0);
        
        varasto = new Varasto(-5, -5);
        assertTrue(varasto.getSaldo() == 0.0);
        
        varasto = new Varasto(10,150);
        assertTrue(varasto.getSaldo() == 10.0);
    }
    
    @Test
    public void testaaToString() {
        varasto = new Varasto(100, 10);
        assertTrue("saldo = 10.0, vielä tilaa 90.0".equals(varasto.toString()));
        System.out.println(varasto);
    }
}