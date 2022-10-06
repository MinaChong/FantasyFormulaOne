package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SprintTest {
    private Sprint testSprint;

    private Driver aalbon;
    private Driver cleclerc;
    private Driver csainz;
    private Driver dricciardo;
    private Driver eocon;
    private Driver falonso;
    private Driver grussell;
    private Driver gzhou;
    private Driver kmagnussen;
    private Driver lhamilton;
    private Driver lnorris;
    private Driver lstroll;
    private Driver mschumacher;
    private Driver mverstappen;
    private Driver nlatifi;
    private Driver pgasly;
    private Driver sperez;
    private Driver svettel;
    private Driver vbottas;
    private Driver ytsunoda;

    private List<Driver> drivers;

    @BeforeEach
    public void setup() {
        aalbon = new Driver("Alex Albon", 23);
        cleclerc = new Driver("Charles Leclerc", 16);
        csainz = new Driver("Carlos Sainz", 55);
        dricciardo = new Driver("Daniel Ricciardo", 3);
        eocon = new Driver("Esteban Ocon", 31);
        falonso = new Driver("Fernando Alonso", 14);
        grussell = new Driver("George Russell", 63);
        gzhou = new Driver("Guanyu Zhou", 24);
        kmagnussen = new Driver("Kevin Magnussen", 20);
        lhamilton = new Driver("Lewis Hamilton", 44);
        lnorris = new Driver("Lando Norris", 4);
        lstroll = new Driver("Lance Stroll", 18);
        mschumacher = new Driver("Mick Schumacher", 47);
        mverstappen = new Driver("Max Verstappen", 1);
        nlatifi = new Driver("Nicholas Latifi", 6);
        pgasly = new Driver("Pierre Gasly", 10);
        sperez = new Driver("Sergio Perez", 11);
        svettel = new Driver("Sebastian Vettel", 5);
        vbottas = new Driver("Valtteri Bottas", 77);
        ytsunoda = new Driver("Yuki Tsunoda", 22);


        drivers = new ArrayList<>();
        drivers.add(sperez);
        drivers.add(cleclerc);
        drivers.add(csainz);
        drivers.add(lnorris);
        drivers.add(dricciardo);
        drivers.add(lstroll);
        drivers.add(mverstappen);
        drivers.add(svettel);

        testSprint = new Sprint("Singapore Grand Prix", "02/10/22", drivers);
    }

    @Test
    public void raceTest() {
        assertEquals("Singapore Grand Prix", testSprint.getName());
        assertEquals("02/10/22", testSprint.getDate());
        assertEquals(8, testSprint.getPlaces().size());
    }

    @Test
    public void updateDriverPointsTest() {
        testSprint.updateDriverPoints();

        assertEquals(8, sperez.getPoints());
        assertEquals(7, cleclerc.getPoints());
        assertEquals(6, csainz.getPoints());
        assertEquals(5, lnorris.getPoints());
        assertEquals(4, dricciardo.getPoints());
        assertEquals(3, lstroll.getPoints());
        assertEquals(2, mverstappen.getPoints());
        assertEquals(1, svettel.getPoints());
        assertEquals(0, lhamilton.getPoints());
        assertEquals(0, pgasly.getPoints());
        assertEquals(0, vbottas.getPoints());
        assertEquals(0, kmagnussen.getPoints());
        assertEquals(0, mschumacher.getPoints());
        assertEquals(0, grussell.getPoints());
        assertEquals(0, ytsunoda.getPoints());
        assertEquals(0, eocon.getPoints());
        assertEquals(0, aalbon.getPoints());
        assertEquals(0, falonso.getPoints());
        assertEquals(0, nlatifi.getPoints());
        assertEquals(0, gzhou.getPoints());
    }
}
