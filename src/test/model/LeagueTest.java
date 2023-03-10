package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeagueTest {
    private League testLeague;
    private Team testTeam;
    private Team anotherTestTeam;
    private Driver testDriver;
    private Driver anotherTestDriver;
    private Driver yetAnotherTestDriver;
    private Sprint testRace;
    private Sprint anotherTestRace;

    @BeforeEach
    public void setup() {
        testLeague = new League("My League");

        testTeam = new Team("Team 1");
        anotherTestTeam = new Team("Team 2");

        testDriver = new Driver("Driver 1", 1);
        anotherTestDriver = new Driver("Driver 2", 2);
        yetAnotherTestDriver = new Driver("Driver 3", 3);

        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver("Alex Albon", 23));
        drivers.add(new Driver("Charles Leclerc", 16));
        drivers.add(new Driver("Carlos Sainz", 55));
        drivers.add(new Driver("Daniel Ricciardo", 3));
        drivers.add(new Driver("Esteban Ocon", 31));
        drivers.add(new Driver("Fernando Alonso", 14));
        drivers.add(new Driver("George Russell", 63));
        drivers.add(new Driver("Guanyu Zhou", 24));
        testRace = new Sprint("Race 1", "11/11/11", drivers);

        anotherTestRace = new Sprint("Race 2", "22/22/22", drivers);

        testTeam.addDriver(testDriver);
        testTeam.addDriver(anotherTestDriver);
        anotherTestTeam.addDriver(yetAnotherTestDriver);
    }

    @Test
    public void leagueTest() {
        assertEquals("My League", testLeague.getName());
        assertEquals(0, testLeague.getDrivers().size());
        assertEquals(0, testLeague.getRaces().size());
        assertEquals(0, testLeague.getTeams().size());
    }

    @Test
    public void addDriverTest() {
        testLeague.addDriver(testDriver);
        assertEquals(1, testLeague.getDrivers().size());
    }

    @Test
    public void addDriverMultipleTimesTest() {
        testLeague.addDriver(testDriver);
        assertEquals(1, testLeague.getDrivers().size());

        testLeague.addDriver(anotherTestDriver);
        assertEquals(2, testLeague.getDrivers().size());
    }

    @Test
    public void removeDriverTest() {
        testLeague.addDriver(testDriver);
        assertEquals(1, testLeague.getDrivers().size());

        testLeague.removeDriver(testDriver);
        assertEquals(0, testLeague.getDrivers().size());
    }

    @Test
    public void removeDriverMultipleTimesTest() {
        testLeague.addDriver(testDriver);
        assertEquals(1, testLeague.getDrivers().size());

        testLeague.addDriver(anotherTestDriver);
        assertEquals(2, testLeague.getDrivers().size());

        testLeague.removeDriver(testDriver);
        assertEquals(1, testLeague.getDrivers().size());

        testLeague.removeDriver(anotherTestDriver);
        assertEquals(0, testLeague.getDrivers().size());
    }

    @Test
    public void addRaceTest() {
        testLeague.addRace(testRace);
        assertEquals(1, testLeague.getRaces().size());
    }

    @Test
    public void addRaceMultipleTimesTest() {
        testLeague.addRace(testRace);
        assertEquals(1, testLeague.getRaces().size());

        testLeague.addRace(anotherTestRace);
        assertEquals(2, testLeague.getRaces().size());
    }

    @Test
    public void addRaceEventLogTest() {
        EventLog.getInstance().clear();

        testLeague.addRace(testRace);

        List<String> eventDescriptions = new ArrayList<>();
        for (Event event : EventLog.getInstance()) {
            eventDescriptions.add(event.getDescription());
        }

        assertTrue(eventDescriptions.contains("Race 1 added to My League."));
    }


    @Test
    public void removeRaceTest() {
        testLeague.addRace(testRace);
        assertEquals(1, testLeague.getRaces().size());

        testLeague.removeRace(testRace);
        assertEquals(0, testLeague.getRaces().size());
    }

    @Test
    public void removeRaceMultipleTimesTest() {
        testLeague.addRace(testRace);
        assertEquals(1, testLeague.getRaces().size());

        testLeague.addRace(anotherTestRace);
        assertEquals(2, testLeague.getRaces().size());

        testLeague.removeRace(testRace);
        assertEquals(1, testLeague.getRaces().size());

        testLeague.removeRace(anotherTestRace);
        assertEquals(0, testLeague.getRaces().size());
    }

    @Test
    public void removeRaceEventLogTest() {
        EventLog.getInstance().clear();

        testLeague.removeRace(testRace);

        List<String> eventDescriptions = new ArrayList<>();
        for (Event event : EventLog.getInstance()) {
            eventDescriptions.add(event.getDescription());
        }

        assertTrue(eventDescriptions.contains("Race 1 removed from My League."));
    }

    @Test
    public void addTeamTest() {
        testLeague.addTeam(testTeam);
        assertEquals(1, testLeague.getTeams().size());
    }

    @Test
    public void addTeamMultipleTimesTest() {
        testLeague.addTeam(testTeam);
        assertEquals(1, testLeague.getTeams().size());

        testLeague.addTeam(anotherTestTeam);
        assertEquals(2, testLeague.getTeams().size());
    }

    @Test
    public void addTeamEventLogTest() {
        EventLog.getInstance().clear();

        testLeague.addTeam(testTeam);

        List<String> eventDescriptions = new ArrayList<>();
        for (Event event : EventLog.getInstance()) {
            eventDescriptions.add(event.getDescription());
        }

        assertTrue(eventDescriptions.contains("Team 1 added to My League."));
    }

    @Test
    public void removeTeamTest() {
        testLeague.addTeam(testTeam);
        assertEquals(1, testLeague.getTeams().size());

        testLeague.removeTeam(testTeam);
        assertEquals(0, testLeague.getTeams().size());
    }

    @Test
    public void removeTeamMultipleTimesTest() {
        testLeague.addTeam(testTeam);
        assertEquals(1, testLeague.getTeams().size());

        testLeague.addTeam(anotherTestTeam);
        assertEquals(2, testLeague.getTeams().size());

        testLeague.removeTeam(testTeam);
        assertEquals(1, testLeague.getTeams().size());

        testLeague.removeTeam(anotherTestTeam);
        assertEquals(0, testLeague.getTeams().size());
    }

    @Test
    public void removeTeamEventLogTest() {
        EventLog.getInstance().clear();

        testLeague.removeTeam(testTeam);

        List<String> eventDescriptions = new ArrayList<>();
        for (Event event : EventLog.getInstance()) {
            eventDescriptions.add(event.getDescription());
        }

        assertTrue(eventDescriptions.contains("Team 1 removed from My League."));
    }
}