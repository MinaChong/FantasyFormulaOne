package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    @Test
    public void testWriterInvalidFile() {
        try {
            League league = new League("My League");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyLeague() {
        try {
            League league = new League("Empty League");
            JsonWriter writer = new JsonWriter("./data/testEmptyLeague.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyLeague.json");
            league = reader.read();
            assertEquals("Empty League", league.getName());
            assertEquals(0, league.getDrivers().size());
            assertEquals(0, league. getRaces().size());
            assertEquals(0, league.getTeams().size());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralLeague() {
        try {
            League league = new League("General League");
            Driver aalbon = new Driver("Alex Albon", 23);
            Driver cleclerc = new Driver("Charles Leclerc", 16);
            Driver csainz = new Driver("Carlos Sainz", 55);
            Driver dricciardo = new Driver("Daniel Ricciardo", 3);
            Driver eocon = new Driver("Esteban Ocon", 31);
            Driver falonso = new Driver("Fernando Alonso", 14);
            Driver grussell = new Driver("George Russell", 63);
            Driver gzhou = new Driver("Guanyu Zhou", 24);
            Driver kmagnussen = new Driver("Kevin Magnussen", 20);
            Driver lhamilton = new Driver("Lewis Hamilton", 44);
            Driver lnorris = new Driver("Lando Norris", 4);
            Driver lstroll = new Driver("Lance Stroll", 18);
            Driver mschumacher = new Driver("Mick Schumacher", 47);
            Driver mverstappen = new Driver("Max Verstappen", 1);
            Driver nlatifi = new Driver("Nicholas Latifi", 6);
            Driver pgasly = new Driver("Pierre Gasly", 10);
            Driver sperez = new Driver("Sergio Perez", 11);
            Driver svettel = new Driver("Sebastian Vettel", 5);
            Driver vbottas = new Driver("Valtteri Bottas", 77);
            Driver ytsunoda = new Driver("Yuki Tsunoda", 22);

            league.addDriver(aalbon);
            league.addDriver(cleclerc);
            league.addDriver(csainz);
            league.addDriver(dricciardo);
            league.addDriver(eocon);
            league.addDriver(falonso);
            league.addDriver(grussell);
            league.addDriver(gzhou);
            league.addDriver(kmagnussen);
            league.addDriver(lhamilton);
            league.addDriver(lnorris);
            league.addDriver(lstroll);
            league.addDriver(mschumacher);
            league.addDriver(mverstappen);
            league.addDriver(nlatifi);
            league.addDriver(pgasly);
            league.addDriver(sperez);
            league.addDriver(svettel);
            league.addDriver(vbottas);
            league.addDriver(ytsunoda);

            List<Driver> sprintPlaces = new ArrayList<>();
            sprintPlaces.add(aalbon);
            sprintPlaces.add(cleclerc);
            sprintPlaces.add(csainz);
            sprintPlaces.add(dricciardo);
            sprintPlaces.add(eocon);
            sprintPlaces.add(falonso);
            sprintPlaces.add(grussell);
            sprintPlaces.add(gzhou);
            Race sprint = new Sprint("Sprint Race", "11/11/11", sprintPlaces);
            league.addRace(sprint);

            List<Driver> grandPrixPlaces = new ArrayList<>();
            grandPrixPlaces.add(aalbon);
            grandPrixPlaces.add(cleclerc);
            grandPrixPlaces.add(csainz);
            grandPrixPlaces.add(dricciardo);
            grandPrixPlaces.add(eocon);
            grandPrixPlaces.add(falonso);
            grandPrixPlaces.add(grussell);
            grandPrixPlaces.add(gzhou);
            grandPrixPlaces.add(kmagnussen);
            grandPrixPlaces.add(lhamilton);
            Race grandPrix = new GrandPrix("Grand Prix", "22/22/22", grandPrixPlaces, aalbon);
            league.addRace(grandPrix);

            Team team = new Team("Team 1");
            team.addDriver(aalbon);
            team.addDriver(cleclerc);
            team.addDriver(csainz);

            league.addTeam(team);

            Team anotherTeam = new Team("Team 2");
            anotherTeam.addDriver(aalbon);
            anotherTeam.addDriver(cleclerc);
            anotherTeam.addDriver(csainz);

            league.addTeam(anotherTeam);

            JsonWriter writer = new JsonWriter("./data/testGeneralLeague.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGeneralLeague.json");
            league = reader.read();
            assertEquals("General League", league.getName());
            assertEquals(20, league.getDrivers().size());
            assertEquals(2, league.getRaces().size());
            assertEquals(2, league.getTeams().size());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}