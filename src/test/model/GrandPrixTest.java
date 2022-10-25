package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrandPrixTest {
    private GrandPrix testGrandPrix;
    private GrandPrix anotherTestGrandPrix;

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
        drivers.add(lhamilton);
        drivers.add(pgasly);

        testGrandPrix = new GrandPrix("Singapore Grand Prix", "02/10/22", drivers, sperez);
        anotherTestGrandPrix = new GrandPrix("another race", "00/00/00", drivers, grussell);
    }

    @Test
    public void grandPrixTest() {
        assertEquals("Singapore Grand Prix", testGrandPrix.getName());
        assertEquals("02/10/22", testGrandPrix.getDate());
        assertEquals(10, testGrandPrix.getPlaces().size());
        assertEquals(sperez, testGrandPrix.getFastestLap());
    }

    @Test
    public void updateDriverPointsTest() {
        testGrandPrix.updateDriverStats();

        assertEquals(26, sperez.getPoints());
        assertEquals(18, cleclerc.getPoints());
        assertEquals(15, csainz.getPoints());
        assertEquals(12, lnorris.getPoints());
        assertEquals(10, dricciardo.getPoints());
        assertEquals(8, lstroll.getPoints());
        assertEquals(6, mverstappen.getPoints());
        assertEquals(4, svettel.getPoints());
        assertEquals(2, lhamilton.getPoints());
        assertEquals(1, pgasly.getPoints());
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

        assertEquals(1, sperez.getWins());
        assertEquals(0, cleclerc.getWins());
        assertEquals(0, csainz.getWins());
        assertEquals(0, lnorris.getWins());
        assertEquals(0, dricciardo.getWins());
        assertEquals(0, lstroll.getWins());
        assertEquals(0, mverstappen.getWins());
        assertEquals(0, svettel.getWins());
        assertEquals(0, lhamilton.getWins());
        assertEquals(0, pgasly.getWins());
        assertEquals(0, vbottas.getWins());
        assertEquals(0, kmagnussen.getWins());
        assertEquals(0, mschumacher.getWins());
        assertEquals(0, grussell.getWins());
        assertEquals(0, ytsunoda.getWins());
        assertEquals(0, eocon.getWins());
        assertEquals(0, aalbon.getWins());
        assertEquals(0, falonso.getWins());
        assertEquals(0, nlatifi.getWins());
        assertEquals(0, gzhou.getWins());

        assertEquals(1, sperez.getFastestLaps());
        assertEquals(0, cleclerc.getFastestLaps());
        assertEquals(0, csainz.getFastestLaps());
        assertEquals(0, lnorris.getFastestLaps());
        assertEquals(0, dricciardo.getFastestLaps());
        assertEquals(0, lstroll.getFastestLaps());
        assertEquals(0, mverstappen.getFastestLaps());
        assertEquals(0, svettel.getFastestLaps());
        assertEquals(0, lhamilton.getFastestLaps());
        assertEquals(0, pgasly.getFastestLaps());
        assertEquals(0, vbottas.getFastestLaps());
        assertEquals(0, kmagnussen.getFastestLaps());
        assertEquals(0, mschumacher.getFastestLaps());
        assertEquals(0, grussell.getFastestLaps());
        assertEquals(0, ytsunoda.getFastestLaps());
        assertEquals(0, eocon.getFastestLaps());
        assertEquals(0, aalbon.getFastestLaps());
        assertEquals(0, falonso.getFastestLaps());
        assertEquals(0, nlatifi.getFastestLaps());
        assertEquals(0, gzhou.getFastestLaps());
    }

    @Test
    public void updateFastestLapInTopTenTest() {
        testGrandPrix.updateFastestLap();

        assertEquals(1, sperez.getPoints());
        assertEquals(0, cleclerc.getPoints());
        assertEquals(0, csainz.getPoints());
        assertEquals(0, lnorris.getPoints());
        assertEquals(0, dricciardo.getPoints());
        assertEquals(0, lstroll.getPoints());
        assertEquals(0, mverstappen.getPoints());
        assertEquals(0, svettel.getPoints());
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

        assertEquals(1, sperez.getFastestLaps());
        assertEquals(0, cleclerc.getFastestLaps());
        assertEquals(0, csainz.getFastestLaps());
        assertEquals(0, lnorris.getFastestLaps());
        assertEquals(0, dricciardo.getFastestLaps());
        assertEquals(0, lstroll.getFastestLaps());
        assertEquals(0, mverstappen.getFastestLaps());
        assertEquals(0, svettel.getFastestLaps());
        assertEquals(0, lhamilton.getFastestLaps());
        assertEquals(0, pgasly.getFastestLaps());
        assertEquals(0, vbottas.getFastestLaps());
        assertEquals(0, kmagnussen.getFastestLaps());
        assertEquals(0, mschumacher.getFastestLaps());
        assertEquals(0, grussell.getFastestLaps());
        assertEquals(0, ytsunoda.getFastestLaps());
        assertEquals(0, eocon.getFastestLaps());
        assertEquals(0, aalbon.getFastestLaps());
        assertEquals(0, falonso.getFastestLaps());
        assertEquals(0, nlatifi.getFastestLaps());
        assertEquals(0, gzhou.getFastestLaps());
    }

    @Test
    public void updateFastestLapNotInTopTenTest() {
        anotherTestGrandPrix.updateFastestLap();

        assertEquals(0, sperez.getPoints());
        assertEquals(0, cleclerc.getPoints());
        assertEquals(0, csainz.getPoints());
        assertEquals(0, lnorris.getPoints());
        assertEquals(0, dricciardo.getPoints());
        assertEquals(0, lstroll.getPoints());
        assertEquals(0, mverstappen.getPoints());
        assertEquals(0, svettel.getPoints());
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

        assertEquals(0, sperez.getFastestLaps());
        assertEquals(0, cleclerc.getFastestLaps());
        assertEquals(0, csainz.getFastestLaps());
        assertEquals(0, lnorris.getFastestLaps());
        assertEquals(0, dricciardo.getFastestLaps());
        assertEquals(0, lstroll.getFastestLaps());
        assertEquals(0, mverstappen.getFastestLaps());
        assertEquals(0, svettel.getFastestLaps());
        assertEquals(0, lhamilton.getFastestLaps());
        assertEquals(0, pgasly.getFastestLaps());
        assertEquals(0, vbottas.getFastestLaps());
        assertEquals(0, kmagnussen.getFastestLaps());
        assertEquals(0, mschumacher.getFastestLaps());
        assertEquals(1, grussell.getFastestLaps());
        assertEquals(0, ytsunoda.getFastestLaps());
        assertEquals(0, eocon.getFastestLaps());
        assertEquals(0, aalbon.getFastestLaps());
        assertEquals(0, falonso.getFastestLaps());
        assertEquals(0, nlatifi.getFastestLaps());
        assertEquals(0, gzhou.getFastestLaps());
    }
}