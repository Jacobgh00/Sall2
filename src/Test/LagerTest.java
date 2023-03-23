package Test;

import Application.Controller.Controller;
import Application.Model.Lager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LagerTest {

    private Lager lager;

    @Before
    public void setUp(){
        Controller.clearLagerListe();

    }

    @Test
    public void testTC1_testOpretLagerOgGem(){
        Lager nytLager = new Lager(1, "Lager 1", "Lokation 1", 5);
        Controller.addLager(nytLager);
        assertEquals(1, Controller.getLagerliste().size());
        assertTrue(Controller.getLagerliste().contains(nytLager));
    }

    @Test
    public void testTC2_RedigerEksisterendeLagerOgGemDet() {
        Lager nytLager = new Lager(1, "Lager 1", "Lokation 1", 5);
        Controller.addLager(nytLager);

        Lager redigeretLager = new Lager(1, "Lager 1 - Redigeret", "Lokation 1 - Redigeret", 6);
        Controller.updateLager(redigeretLager);

        Lager foundLager = Controller.findLagerById(1);
        assertEquals("Lager 1 - Redigeret", foundLager.getNavn());
        assertEquals("Lokation 1 - Redigeret", foundLager.getLokation());
        assertEquals(6, foundLager.getKapacitet());
    }

    @Test
    public void testTC3_SletEksisterendeLagerOgGemAendringerne() {
        Lager nytLager = new Lager(1, "Lager 1", "Lokation 1", 5);
        Controller.addLager(nytLager);
        Controller.deleteLager(nytLager);
        assertEquals(0, Controller.getLagerliste().size());
    }







}
