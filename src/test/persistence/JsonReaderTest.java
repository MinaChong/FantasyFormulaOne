package persistence;

import model.League;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            League league = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyLeague() {
        JsonReader reader = new JsonReader("./data/testEmptyLeague.json");
        try {
            League league = reader.read();
            assertEquals("Empty League", league.getName());
            assertEquals(0, league.getDrivers().size());
            assertEquals(0, league.getRaces().size());
            assertEquals(0, league.getTeams().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralLeague() {
        JsonReader reader = new JsonReader("./data/testGeneralLeague.json");
        try {
            League league = reader.read();
            assertEquals("General League", league.getName());
            assertEquals(20, league.getDrivers().size());
            assertEquals(2, league.getTeams().size());
            assertEquals(2, league.getRaces().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}