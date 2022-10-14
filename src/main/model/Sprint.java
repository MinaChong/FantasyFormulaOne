package model;

import java.util.List;

// Represents a sprint race with a name and a list of drivers in the order that they finished
public class Sprint implements Race {
    private String name;            // the name of the sprint race
    private String date;            // the date of the sprint race as DD/MM/YY
    private List<Driver> places;    // the list of drivers in order that they finished

    // REQUIRES: name of sprint race is of non-zero length
    // EFFECTS: creates a sprint race with given name, date, list of driver in order that they finished,
    public Sprint(String name, String date, List<Driver> places) {
        this.name = name;
        this.date = date;
        this.places = places;
    }

    @Override
    // EFFECTS: adds points and wins to drivers according to their race placements
    public void updateDriverPoints() {
        Driver first = places.get(0);
        first.addPoints(8);

        Driver second = places.get(1);
        second.addPoints(7);

        Driver third = places.get(2);
        third.addPoints(6);

        Driver fourth = places.get(3);
        fourth.addPoints(5);

        Driver fifth = places.get(4);
        fifth.addPoints(4);

        Driver sixth = places.get(5);
        sixth.addPoints(3);

        Driver seventh = places.get(6);
        seventh.addPoints(2);

        Driver eigth = places.get(7);
        eigth.addPoints(1);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public List<Driver> getPlaces() {
        return places;
    }
}
