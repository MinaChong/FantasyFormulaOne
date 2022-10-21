package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a driver having a name, number, points total, wins total, and fastest laps total
public class Driver implements Writable {
    private String name;        // the driver's name
    private int num;            // the driver's number
    private int points;         // the driver's total number of points
    private int wins;           // the driver's total number of wins
    private int fastestLaps;    // the driver's total number of fastest laps

    // REQUIRES: name of driver has non-zero length
    // EFFECTS: driver is created with given name, given driver number, 0 points, 0 wins, and 0 fastest laps
    public Driver(String name, int num) {
        this.name = name;
        this.num = num;
        this.points = 0;
        this.wins = 0;
        this.fastestLaps = 0;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("num", num);
        json.put("points", points);
        json.put("wins", wins);
        json.put("fastestlaps", fastestLaps);
        return json;
    }
}