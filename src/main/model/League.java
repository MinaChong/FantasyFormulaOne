package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a league having a name, list of drivers, list of races, and list of teams
public class League implements Writable {
    private String name;            // the league's name
    private List<Driver> drivers;   // drivers in the league
    private List<Race> races;       // races in the league
    private List<Team> teams;       // teams in the league

    // REQUIRES: name of league has non-zero length
    // EFFECTS: creates a league with given name, no drivers, no races, and no teams
    public League(String name) {
        this.name = name;
        this.drivers = new ArrayList<>();
        this.races = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given driver to league's list of drivers
    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    // MODIFIES: this
    // EFFECTS: removes given driver from league's list of drivers
    public void removeDriver(Driver driver) {
        drivers.remove(driver);
    }

    // MODIFIES: this
    // EFFECTS: adds given race to league's list of races
    public void addRace(Race race) {
        races.add(race);
    }

    // MODIFIES: this
    // EFFECTS: removes given race from league's list of races
    public void removeRace(Race race) {
        races.remove(race);
    }

    // MODIFIES: this
    // EFFECTS: adds given team to league's list of teams
    public void addTeam(Team team) {
        teams.add(team);
    }

    // MODIFIES: this
    // EFFECTS: removes given team from league's list of teams
    public void removeTeam(Team team) {
        teams.remove(team);
    }

    // GETTERS:

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Race> getRaces() {
        return races;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    @Override
    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("drivers", driversToJson());
        json.put("races", racesToJson());
        json.put("teams", teamsToJson());
        return json;
    }

    // EFFECTS: returns drivers in this league as a JSON array
    private JSONArray driversToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Driver driver : drivers) {
            jsonArray.put(driver.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns races in this league as a JSON array
    private JSONArray racesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Race race : races) {
            jsonArray.put(race.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns teams in this league as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team team : teams) {
            jsonArray.put(team.toJson());
        }

        return jsonArray;
    }
}