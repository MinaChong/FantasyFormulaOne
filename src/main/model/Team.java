package model;

import java.util.ArrayList;
import java.util.List;

// Represents a team having a name, list of drivers, total number of points, and total number of wins
public class Team {
    private String name;                // the team's name
    private List<Driver> drivers;       // drivers on the team
    private int points;                 // the team's total number of points
    private int wins;                   // the team's total number of wins

    // REQUIRES: name of team has non-zero length
    // EFFECTS: team is created with given name, no drivers, 0 points, and 0 wins
    public Team(String name) {
        this.name = name;
        this.drivers = new ArrayList<Driver>();
        this.points = 0;
        this.wins = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds given driver to list of drivers on team
    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    // MODIFIES: this
    // EFFECTS: removes given driver from list of drivers on team
    public void removeDriver(Driver driver) {
        drivers.remove(driver);
    }

    public String getName() {
        return name;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public int getPoints() {
        int total = 0;

        for (Driver driver : drivers) {
            total = total + driver.getPoints();
        }
        points = total;
        return points;
    }

    public int getWins() {
        int total = 0;
        for (Driver driver : drivers) {
            total = total + driver.getWins();
        }
        wins = total;
        return wins;
    }
}
