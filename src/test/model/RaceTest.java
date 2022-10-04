package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceTest {
    private Race testRace;

    private Driver sPerez;
    private Driver cLeclerc;
    private Driver cSainz;
    private Driver lNorris;
    private Driver dRicciardo;
    private Driver lStroll;
    private Driver mVerstappen;
    private Driver sVettel;
    private Driver lHamilton;
    private Driver pGasly;
    private Driver vBottas;
    private Driver kMagnussen;
    private Driver mSchumacher;
    private Driver gRussell;
    private Driver yTsunoda;
    private Driver eOcon;
    private Driver aAlbon;
    private Driver fAlonso;
    private Driver nLatifi;
    private Driver gZhou;

    private List<Driver> driverList;

    @BeforeEach
    public void setup() {
        testRace = new Race("Singapore Grand Prix");

        sPerez = new Driver("Sergio Perez", 11);
        cLeclerc = new Driver("Charles Leclerc", 16);
        cSainz = new Driver("Carlos Sainz", 55);
        lNorris = new Driver("Lando Norris", 4);
        dRicciardo = new Driver("Daniel Ricciardo", 3);
        lStroll = new Driver("Lance Stroll", 18);
        mVerstappen = new Driver("Max Verstappen", 1);
        sVettel = new Driver("Sebastian Vettel", 5);
        lHamilton = new Driver("Lewis Hamilton", 44);
        pGasly = new Driver("Pierre Gasly", 10);
        vBottas = new Driver("Valtteri Bottas", 77);
        kMagnussen = new Driver("Kevin Magnussen", 20);
        mSchumacher = new Driver("Mick Schumacher", 47);
        gRussell = new Driver("George Russell", 63);
        yTsunoda = new Driver("Yuki Tsunoda", 22);
        eOcon = new Driver("Esteban Ocon", 31);
        aAlbon = new Driver("Alex Albon", 23);
        fAlonso = new Driver("Fernando Alonso", 14);
        nLatifi = new Driver("Nicholas Latifi", 6);
        gZhou = new Driver("Guanyu Zhou", 24);


        driverList = new ArrayList<>();
        driverList.add(sPerez);
        driverList.add(cLeclerc);
        driverList.add(cSainz);
        driverList.add(lNorris);
        driverList.add(dRicciardo);
        driverList.add(lStroll);
        driverList.add(mVerstappen);
        driverList.add(sVettel);
        driverList.add(lHamilton);
        driverList.add(pGasly);
        driverList.add(vBottas);
        driverList.add(kMagnussen);
        driverList.add(mSchumacher);
        driverList.add(gRussell);
        driverList.add(yTsunoda);
        driverList.add(eOcon);
        driverList.add(aAlbon);
        driverList.add(fAlonso);
        driverList.add(nLatifi);
        driverList.add(gZhou);
    }

    @Test
    public void raceTest() {
        assertEquals("Singapore Grand Prix", testRace.getName());
        assertEquals(0, testRace.getPlaces().size());
    }

    @Test
    public void setPlacesTest() {
        testRace.setPlaces(driverList);
        assertEquals(20, testRace.getPlaces().size());
    }

    @Test
    public void updateDriverPointsTest() {
        testRace.setPlaces(driverList);
        testRace.updateDriverPoints();

        assertEquals(25, sPerez.getPoints());
        assertEquals(18, cLeclerc.getPoints());
        assertEquals(15, cSainz.getPoints());
        assertEquals(12, lNorris.getPoints());
        assertEquals(10, dRicciardo.getPoints());
        assertEquals(8, lStroll.getPoints());
        assertEquals(6, mVerstappen.getPoints());
        assertEquals(4, sVettel.getPoints());
        assertEquals(2, lHamilton.getPoints());
        assertEquals(1, pGasly.getPoints());
        assertEquals(0, vBottas.getPoints());
        assertEquals(0, kMagnussen.getPoints());
        assertEquals(0, mSchumacher.getPoints());
        assertEquals(0, gRussell.getPoints());
        assertEquals(0, yTsunoda.getPoints());
        assertEquals(0, eOcon.getPoints());
        assertEquals(0, aAlbon.getPoints());
        assertEquals(0, fAlonso.getPoints());
        assertEquals(0, nLatifi.getPoints());
        assertEquals(0, gZhou.getPoints());

        assertEquals(1, sPerez.getWins());
        assertEquals(0, cLeclerc.getWins());
        assertEquals(0, cSainz.getWins());
        assertEquals(0, lNorris.getWins());
        assertEquals(0, dRicciardo.getWins());
        assertEquals(0, lStroll.getWins());
        assertEquals(0, mVerstappen.getWins());
        assertEquals(0, sVettel.getWins());
        assertEquals(0, lHamilton.getWins());
        assertEquals(0, pGasly.getWins());
        assertEquals(0, vBottas.getWins());
        assertEquals(0, kMagnussen.getWins());
        assertEquals(0, mSchumacher.getWins());
        assertEquals(0, gRussell.getWins());
        assertEquals(0, yTsunoda.getWins());
        assertEquals(0, eOcon.getWins());
        assertEquals(0, aAlbon.getWins());
        assertEquals(0, fAlonso.getWins());
        assertEquals(0, nLatifi.getWins());
        assertEquals(0, gZhou.getWins());

    }
}
