package model;

// Represents a grand prix with a name, date, list of drivers places, and driver with the fastest lap
public class GrandPrix extends Race {
    // REQUIRES: name of grand prix is of non-zero length, date of race is in form DD/MM/YY where D, M, Y are positive
    // integers
    // EFFECTS: creates a race with given name and date
    public GrandPrix(String name, String date) {
        super(name, date);
    }

    // EFFECTS: adds points and wins to drivers according to their race placements
    public void updateDriverStats() {
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
}