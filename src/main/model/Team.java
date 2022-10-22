package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a team having a name and list of drivers
public class Team implements Writable {
    private String name;                // the team's name
    private List<Driver> drivers;       // drivers on the team

    // REQUIRES: name of team has non-zero length
    // EFFECTS: team is created with given name and no drivers
    public Team(String name) {
        this.name = name;
        this.drivers = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given driver to list of drivers on team
    public void addDriver(Driver driver) {
        drivers.add(driver);
        driver.addTeamName(name);
    }

    // MODIFIES: this
    // EFFECTS: removes given driver from list of drivers on team
    public void removeDriver(Driver driver) {
        drivers.remove(driver);
        driver.removeTeamName(name);
    }

    // GETTERS:

    public String getName() {
        return name;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    // EFFECTS: returns total number of points that a team has
    public int getPoints() {
        int totalPoints = 0;

        for (Driver driver : drivers) {
            totalPoints = totalPoints + driver.getPoints();
        }

        return totalPoints;
    }

    // EFFECTS: returns total number of wins that a team has
    public int getWins() {
        int totalWins = 0;

        for (Driver driver : drivers) {
            totalWins = totalWins + driver.getWins();
        }

        return totalWins;
    }

    // EFFECTS: returns total number of fastest laps that a team has
    public int getFastestLaps() {
        int totalFastestLaps = 0;

        for (Driver driver : drivers) {
            totalFastestLaps = totalFastestLaps + driver.getFastestLaps();
        }

        return totalFastestLaps;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}