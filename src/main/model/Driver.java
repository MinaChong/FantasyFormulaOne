package model;

// Represents a driver having a name, a driver number, total number of points, and total number of wins
public class Driver {
    private String name;        // the driver's name
    private int num;            // the driver's number
    private int points;         // the driver's total number of points
    private int wins;           // the driver's total number of wins

    // REQUIRES: name of driver has non-zero length
    // EFFECTS: driver is created with given name, given driver number, 0 points, and 0 wins
    public Driver(String name, int num) {
        this.name = name;
        this.num = num;
        this.points = 0;
        this.wins = 0;
    }

    // REQUIRES: points >= 0
    // MODIFIES: this
    // EFFECTS: number of points is added to driver's total number of points
    public void addPoints(int points) {
        this.points = this.points + points;
    }

    // MODIFIES: this
    // EFFECTS: one win is added to driver's total number of wins
    public void addWin() {
        this.wins = this.wins + 1;
    }

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
}



