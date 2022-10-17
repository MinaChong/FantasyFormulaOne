package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeagueTest {
    private League testLeague;
    private Team testTeam;
    private Team anotherTestTeam;
    private Driver testDriver;
    private Driver anotherTestDriver;
    private Driver yetAnotherTestDriver;

    @BeforeEach
    public void setup() {
        testLeague = new League("My League");

        testTeam = new Team("Team 1");
        anotherTestTeam = new Team("Team 2");

        testDriver = new Driver("Driver 1", 1);
        anotherTestDriver = new Driver("Driver 2", 2);
        yetAnotherTestDriver = new Driver("Driver 3", 3);

        testTeam.addDriver(testDriver);
        testTeam.addDriver(anotherTestDriver);
        anotherTestTeam.addDriver(yetAnotherTestDriver);
    }

    @Test
    public void leagueTest() {
        assertEquals("My League", testLeague.getName());
        assertEquals(0, testLeague.getTeams().size());
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
}