package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a driver having a name, number, points total, wins total, and fastest laps total
public class Driver implements Writable {
    private String name;        // the driver's name
    private int num;            // the driver's number
    private int points;         // the driver's total number of points
    private int wins;           // the driver's total number of wins
    private int fastestLaps;    // the driver's total number of fastest laps
    private List<String> teamNames;   // the names of teams that the driver is on

    // REQUIRES: name of driver has non-zero length
    // EFFECTS: driver is created with given name, given driver number, 0 points, 0 wins, 0 fastest laps, and 0 teams
    public Driver(String name, int num) {
        this.name = name;
        this.num = num;
        this.points = 0;
        this.wins = 0;
        this.fastestLaps = 0;
        this.teamNames = new ArrayList<>();
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
    // EFFECTS: given team is added to driver's list of teams
    public void addTeamName(String teamName) {
        teamNames.add(teamName);
    }

    // MODIFIES: this
    // EFFECTS: given team is removed from driver's list of teams
    public void removeTeamName(String teamName) {
        teamNames.remove(teamName);
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

    public void setTeamNames(List<String> teamNames) {
        this.teamNames = teamNames;
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

    public List<String> getTeamNames() {
        return teamNames;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("num", num);
        json.put("points", points);
        json.put("wins", wins);
        json.put("fastestLaps", fastestLaps);
        json.put("teamNames", teamNamesToJson());
        return json;
    }

    // EFFECTS: returns teams in this league as a JSON array
    private JSONArray teamNamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String teamName : teamNames) {
            jsonArray.put(stringToJson(teamName));
        }

        return jsonArray;
    }

    // EFFECTS: returns team name as a JSON object
    private JSONObject stringToJson(String teamName) {
        JSONObject json = new JSONObject();
        json.put("name", teamName);
        return json;
    }
}