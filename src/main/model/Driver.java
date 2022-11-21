package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a driver having a name, number, points total, wins total, fastest laps total, and list of names of teams
// that they are on
public class Driver implements Writable {
    private String name;                // the driver's name
    private int num;                    // the driver's number
    private int points;                 // the driver's total number of points
    private int wins;                   // the driver's total number of wins
    private int fastestLaps;            // the driver's total number of fastest laps
    private List<Team> teams;           // the teams that the driver is on

    // REQUIRES: name of driver has non-zero length
    // EFFECTS: driver is created with given name, given driver number, 0 points, 0 wins, 0 fastest laps, and 0 teams
    public Driver(String name, int num) {
        this.name = name;
        this.num = num;
        this.points = 0;
        this.wins = 0;
        this.fastestLaps = 0;
        this.teams = new ArrayList<>();
    }

    // REQUIRES: points >= 0
    // MODIFIES: this
    // EFFECTS: number of points is added to driver's total number of points
    public void addPoints(int points) {
        this.points = this.points + points;
    }

    // REQUIRES: points >= 0
    // MODIFIES: this
    // EFFECTS: number of points is removed from driver's total number of points
    public void removePoints(int points) {
        this.points = this.points - points;
    }

    // MODIFIES: this
    // EFFECTS: one win is added to driver's total number of wins
    public void addWin() {
        this.wins = this.wins + 1;
    }

    // MODIFIES: this
    // EFFECTS: one fastest lap is added to driver's total number of fastest laps
    public void addFastestLap() {
        this.fastestLaps = this.fastestLaps + 1;
    }

    // MODIFIES: this
    // EFFECTS: given team is added to list of teams that the driver is on
    public void addTeam(Team team) {
        if (!teams.contains(team)) {
            teams.add(team);
            team.addDriver(this);
            EventLog.getInstance().logEvent(new Event(team.getName() + " added to " + this.getName()
                    + "'s list of teams."));
        }
    }

    // MODIFIES: this
    // EFFECTS: given team is removed from list of teams that the driver is on
    public void removeTeam(Team team) {
        if (teams.contains(team)) {
            teams.remove(team);
            team.removeDriver(this);
            EventLog.getInstance().logEvent(new Event(team.getName() + " removed from " + this.getName()
                    + "'s list of teams."));
        }
    }

    // SETTERS:

    // REQUIRES: driver number must be two digits long and >= 0
    // MODIFIES: this
    // EFFECTS: changes the driver's number to the given number
    public void setNum(int num) {
        this.num = num;
    }

    // REQUIRES: wins >= 0
    // MODIFIES: this
    // EFFECTS: sets wins for given driver
    public void setWins(int wins) {
        this.wins = wins;
    }

    // REQUIRES: fastestLaps >= 0
    // MODIFIES: this
    // EFFECTS: sets fastest laps for given driver
    public void setFastestLaps(int fastestLaps) {
        this.fastestLaps = fastestLaps;
    }

    // GETTERS:

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public int getFastestLaps() {
        return fastestLaps;
    }

    public List<Team> getTeams() {
        return teams;
    }

    @Override
    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("num", num);
        json.put("points", points);
        json.put("wins", wins);
        json.put("fastestLaps", fastestLaps);
        json.put("teams", teamsToJson());
        return json;
    }

    // EFFECTS: returns given driver's teams as JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team team : teams) {
            jsonArray.put(team.toJson());
        }

        return jsonArray;
    }
}