package model;

import java.util.ArrayList;
import java.util.List;

// Represents a race with a name and a list of drivers in the order that they finished
public class Race {
    private String name;            // the name of the race
    private List<Driver> places;    // the list of drivers in order that they finished

    // REQUIRES: name of race is of non-zero length
    // EFFECTS: creates a race of given name
    public Race(String name) {
        this.name = name;
        this.places = new ArrayList<>();
    }

    // REQUIRES: list of drivers must be exactly 20 drivers long
    // MODIFIES: this
    // EFFECTS: sets the placements of drivers for a given race
    public void setPlaces(List<Driver> places) {
        this.places = places;
    }

    // EFFECTS: adds points and wins to drivers according to their race placements
    public void updateDriverPoints() {
        Driver first = places.get(0);
        first.addPoints(25);
        first.addWin();

        Driver second = places.get(1);
        second.addPoints(18);

        Driver third = places.get(2);
        third.addPoints(15);

        Driver fourth = places.get(3);
        fourth.addPoints(12);

        Driver fifth = places.get(4);
        fifth.addPoints(10);

        Driver sixth = places.get(5);
        sixth.addPoints(8);

        Driver seventh = places.get(6);
        seventh.addPoints(6);

        Driver eigth = places.get(7);
        eigth.addPoints(4);

        Driver ninth = places.get(8);
        ninth.addPoints(2);

        Driver tenth = places.get(9);
        tenth.addPoints(1);
    }

    public String getName() {
        return name;
    }

    public List<Driver> getPlaces() {
        return places;
    }
}
