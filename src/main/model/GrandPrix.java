package model;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a grand prix with a name, date, list of drivers places, and driver with the fastest lap
public class GrandPrix extends Race {
    // REQUIRES: name of grand prix is of non-zero length, date of race is in form DD/MM/YY where D, M, Y are positive
    // integers, and places is a list of ten drivers
    // EFFECTS: creates a race with given name, date, list of drivers in order that they finished,
    // and driver with the fastest lap of the race
    public GrandPrix(String name, String date) {
        super(name, date);
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("places", placesToJson());
        json.put("fastestlap", fastestLapToJson());
        return json;
    }

    // EFFECTS returns drivers in places as a JSON array
    private JSONArray placesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Driver driver : places) {
            jsonArray.put(driver.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns driver with fastest lap as a JSON object
    private JSONObject fastestLapToJson() {
        JSONObject json = new JSONObject();
        json.put("name", fastestLap.getName());
        json.put("num", fastestLap.getNum());
        json.put("points", fastestLap.getPoints());
        json.put("wins", fastestLap.getWins());
        json.put("fastestlaps", fastestLap.getFastestLaps());
        return json;
    }
}