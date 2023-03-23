package Test;

import Application.Controller.Controller;
import Application.Model.Fad;
import Application.Model.Lager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class FadTest {

    private Lager lager;

    @Before
    public void setUp(){
        Controller.clearLagerListe();
        lager = new Lager(1, "Lager 1", "Lokation 1", 5);
        Controller.addLager(lager);
    }

    @Test
    public void testTC4_OpretNytFadOgGemDet() {
        Fad nytFad = new Fad(1, "FadType 1", 100, 80, 3, lager);
        Controller.addFad(lager, nytFad);
        assertEquals(1, Controller.getFadListe(lager).size());
        assertTrue(Controller.getFadListe(lager).contains(nytFad));
    }

    @Test
    public void testTC5_RedigerEksisterendeFadOgGemDet() {
        Fad nytFad = new Fad(1, "FadType 1", 100, 80, 3, lager);
        Controller.addFad(lager, nytFad);

        Fad redigeretFad = new Fad(1, "FadType 1 - Redigeret", 120, 90, 4, lager);
        Controller.updateFad(redigeretFad);

        Fad foundFad = null;
        for (Fad fad : Controller.getFadListe(lager)) {
            if (fad.getId() == redigeretFad.getId()) {
                foundFad = fad;
                break;
            }
        }
        assertEquals("FadType 1 - Redigeret", foundFad.getFadType());
        assertEquals(120, foundFad.getVolumen(), 0.001);
        assertEquals(90, foundFad.getNuværendeVolumen(), 0.001);
        assertEquals(4, foundFad.getAntalGangeBrugt(), 0.001);
    }

    @Test
    public void testTC6_SletEksisterendeFadOgGemAendringerne() {
        Fad nytFad = new Fad(1, "FadType 1", 100, 80, 3, lager);
        Controller.addFad(lager, nytFad);
        Controller.deleteFad(nytFad);
        assertEquals(0, Controller.getFadListe(lager).size());
        assertFalse(Controller.getFadListe(lager).contains(nytFad));
    }
}
