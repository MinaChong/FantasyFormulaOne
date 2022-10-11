package model;

import java.util.List;

// Represents a race with a name, date, list of drivers in the order that they finished, and driver with the fastest lap
public class GrandPrix implements Race {
    private String name;            // the name of the race
    private String date;            // the date of the race as DD/MM/YY
    private List<Driver> places;    // the list of drivers in order that they finished
    private Driver fastestLap;      // the driver with the fastest lap of the race

    // REQUIRES: name of race is of non-zero length
    // EFFECTS: creates a race with given name, date, list of drivers in order that they finished,
    // and driver with the fastest lap of the race
    public GrandPrix(String name, String date, List<Driver> places, Driver fastestLap) {
        this.name = name;
        this.date = date;
        this.places = places;
        this.fastestLap = fastestLap;
    }

    // EFFECTS: adds points and wins to drivers according to their race placements
    public void updateDriverPoints() {
        updateFastestLap();

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

        Driver eighth = places.get(7);
        eighth.addPoints(4);

        Driver ninth = places.get(8);
        ninth.addPoints(2);

        Driver tenth = places.get(9);
        tenth.addPoints(1);
    }

    public void updateFastestLap() {
        fastestLap.addFastestLap();
        if (places.contains(fastestLap)) {
            fastestLap.addPoints(1);
        }
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public List<Driver> getPlaces() {
        return places;
    }

    public Driver getFastestLap() {
        return fastestLap;
    }
}
