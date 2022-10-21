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
        JsonReader reader = new JsonReader("./data/emptyLeague.json");
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
//
//    @Test
//    void testReaderGeneralWorkRoom() {
//        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
//        try {
//            WorkRoom wr = reader.read();
//            assertEquals("My work room", wr.getName());
//            List<Thingy> thingies = wr.getThingies();
//            assertEquals(2, thingies.size());
//            checkThingy("needle", Category.STITCHING, thingies.get(0));
//            checkThingy("saw", Category.WOODWORK, thingies.get(1));
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
    @Test
    public void testReaderGeneralLeague() {
        JsonReader reader = new JsonReader("./data/generalLeague.json");
        try {
            League league = reader.read();
            assertEquals("General League", league.getName());
            assertEquals(20, league.getDrivers().size());
            assertEquals(1, league.getTeams().size());
            assertEquals(2, league.getRaces().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
