package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team testTeam;
    private Team anotherTestTeam;
    private Team yetAnotherTestTeam;
    private Driver testDriver;
    private Driver anotherTestDriver;

    @BeforeEach
    public void setup() {
        testTeam = new Team("Red Team");
        anotherTestTeam = new Team("Red Team");
        yetAnotherTestTeam = new Team("Blue Team");
        testDriver = new Driver("Fernando", 2);
        anotherTestDriver = new Driver("Alonso", 4);
    }

    @Test
    public void teamTest() {
        assertEquals("Red Team", testTeam.getName());
        assertEquals(0, testTeam.getDrivers().size());
    }

    @Test
    public void addDriverTest() {
        testTeam.addDriver(testDriver);
        assertEquals(1, testTeam.getDrivers().size());
        assertEquals(testDriver, testTeam.getDrivers().get(0));
    }

    @Test
    public void addDriverMultipleTimesTest() {
        testTeam.addDriver(testDriver);
        assertEquals(1, testTeam.getDrivers().size());
        assertEquals(testDriver, testTeam.getDrivers().get(0));

        testTeam.addDriver(anotherTestDriver);
        assertEquals(2, testTeam.getDrivers().size());
        assertEquals(testDriver, testTeam.getDrivers().get(0));
        assertEquals(anotherTestDriver, testTeam.getDrivers().get(1));
    }

    @Test
    public void addDriverEventLogTest() {
        EventLog.getInstance().clear();

        testTeam.addDriver(testDriver);

        List<String> eventDescriptions = new ArrayList<>();
        for (Event event : EventLog.getInstance()) {
            eventDescriptions.add(event.getDescription());
        }

        assertTrue(eventDescriptions.contains("Fernando added to Red Team."));
    }

    @Test
    public void removeDriverTest() {
        testTeam.addDriver(testDriver);
        assertEquals(1, testTeam.getDrivers().size());
        assertEquals(testDriver, testTeam.getDrivers().get(0));

        testTeam.removeDriver(testDriver);
        assertEquals(0, testTeam.getDrivers().size());
    }

    @Test
    public void removeDriverMultipleTimesTest() {
        testTeam.addDriver(testDriver);
        assertEquals(1, testTeam.getDrivers().size());
        assertEquals(testDriver, testTeam.getDrivers().get(0));

        testTeam.addDriver(anotherTestDriver);
        assertEquals(2, testTeam.getDrivers().size());
        assertEquals(testDriver, testTeam.getDrivers().get(0));
        assertEquals(anotherTestDriver, testTeam.getDrivers().get(1));

        testTeam.removeDriver(testDriver);
        assertEquals(1, testTeam.getDrivers().size());

        testTeam.removeDriver(anotherTestDriver);
        assertEquals(0, testTeam.getDrivers().size());
    }

    @Test
    public void removeDriverEventLogTest() {
        EventLog.getInstance().clear();

        testTeam.addDriver(testDriver);
        testTeam.removeDriver(testDriver);

        List<String> eventDescriptions = new ArrayList<>();
        for (Event event : EventLog.getInstance()) {
            eventDescriptions.add(event.getDescription());
        }

        assertTrue(eventDescriptions.contains("Fernando removed from Red Team."));
    }

    @Test
    public void getPointsTest() {
        testTeam.addDriver(testDriver);
        testDriver.addPoints(10);
        assertEquals(10, testTeam.getPoints());
    }

    @Test
    public void getPointsMultipleTimesTest() {
        testTeam.addDriver(testDriver);
        testDriver.addPoints(10);
        assertEquals(10, testTeam.getPoints());

        testTeam.addDriver(anotherTestDriver);
        anotherTestDriver.addPoints(25);
        assertEquals(35, testTeam.getPoints());
    }

    @Test
    public void getWinsTest() {
        testTeam.addDriver(testDriver);
        testDriver.addWin();
        assertEquals(1, testTeam.getWins());
    }

    @Test
    public void getWinsMultipleTimesTest() {
        testTeam.addDriver(testDriver);
        testDriver.addWin();
        assertEquals(1, testTeam.getWins());

        testTeam.addDriver(anotherTestDriver);
        anotherTestDriver.addWin();
        assertEquals(2, testTeam.getWins());
    }

    @Test
    public void getFastestLapsTest() {
        testTeam.addDriver(testDriver);
        testDriver.addFastestLap();
        assertEquals(1, testTeam.getFastestLaps());
    }

    @Test
    public void getFastestLapsMultipleTimesTest() {
        testTeam.addDriver(testDriver);
        testDriver.addFastestLap();
        assertEquals(1, testTeam.getFastestLaps());

        testTeam.addDriver(anotherTestDriver);
        anotherTestDriver.addFastestLap();
        assertEquals(2, testTeam.getFastestLaps());
    }

    @Test
    public void equalsTeamTest() {
        assertTrue(testTeam.equals(anotherTestTeam));
    }

    @Test
    public void notEqualTeamsTest() {
        assertFalse(testTeam.equals(yetAnotherTestTeam));
    }

    @Test
    public void notEqualTeamObjectTest() {
        assertFalse(testTeam.equals(testDriver));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(-779811605, testTeam.hashCode());
    }
}