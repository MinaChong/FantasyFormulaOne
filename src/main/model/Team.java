package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a team having a name, list of drivers, points total, wins total, and fastest laps total
public class Team implements Writable {
    private String name;                // the team's name
    private List<Driver> drivers;       // drivers on the team
    private int points;                 // the team's total number of points
    private int wins;                   // the team's total number of wins
    private int fastestLaps;            // the team's total number of fastest laps

    // REQUIRES: name of team has non-zero length
    // EFFECTS: team is created with given name, no drivers, 0 points, 0 wins, and 0 fastest laps
    public Team(String name) {
        this.name = name;
        this.drivers = new ArrayList<>();
        this.points = 0;
        this.wins = 0;
        this.fastestLaps = 0;
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

    public void setPoints(int points) {
        this.points = points;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setFastestLaps(int fastestLaps) {
        this.fastestLaps = fastestLaps;
    }

    public String getName() {
        return name;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    // EFFECTS: returns total number of points that a team has
    public int getPoints() {
        int total = 0;

        for (Driver driver : drivers) {
            total = total + driver.getPoints();
        }
        points = total;
        return points;
    }

    // EFFECTS: returns total number of wins that a team has
    public int getWins() {
        int total = 0;

        for (Driver driver : drivers) {
            total = total + driver.getWins();
        }
        wins = total;
        return wins;
    }

    // EFFECTS: returns total number of fastest laps that a team has
    public int getFastestLaps() {
        int total = 0;

        for (Driver driver : drivers) {
            total = total + driver.getFastestLaps();
        }
        fastestLaps = total;
        return fastestLaps;
    }

    // EFFECTS: returns string representation of this team
    public String toString() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("drivers", driversToJson());
        json.put("points", points);
        json.put("wins", wins);
        json.put("fastestlaps", fastestLaps);
        return json;
    }

    // EFFECTS returns teams in this league as a JSON array
    private JSONArray driversToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Driver driver : drivers) {
            jsonArray.put(driver.toJson());
        }

        return jsonArray;
    }
}