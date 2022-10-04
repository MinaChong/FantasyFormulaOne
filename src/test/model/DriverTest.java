package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriverTest {
    private Driver testDriver;

    @BeforeEach
    public void setup() {
        testDriver = new Driver("Bob the Driver", 1);
    }

    @Test
    public void driverTest() {
        assertEquals("Bob the Driver", testDriver.getName());
        assertEquals(1, testDriver.getNum());
        assertEquals(0, testDriver.getPoints());
        assertEquals(0, testDriver.getWins());
    }

    @Test
    public void addPointsTest() {
        testDriver.addPoints(10);
        assertEquals(10, testDriver.getPoints());
    }

    @Test
    public void addPointsMultipleTimesTest() {
        testDriver.addPoints(10);
        assertEquals(10, testDriver.getPoints());

        testDriver.addPoints(5);
        assertEquals(15, testDriver.getPoints());
    }

    @Test
    public void addWinTest() {
        testDriver.addWin();
        assertEquals(1, testDriver.getWins());
    }

    @Test
    public void addWinMultipleTimesTest() {
        testDriver.addWin();
        assertEquals(1, testDriver.getWins());

        testDriver.addWin();
        assertEquals(2, testDriver.getWins());
    }


}