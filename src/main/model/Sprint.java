package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

// Represents a sprint race with a name, date, and list of driver places
public class Sprint extends Race {
    private String name;            // the name of the sprint race
    private String date;            // the date of the sprint race
    private List<Driver> places;    // list of drivers in the order that they finished the sprint race

    // REQUIRES: name of sprint race is of non-zero length, date of race is in form DD/MM/YY where D, M, Y are positive
    // integers, places is a list of eight drivers
    // EFFECTS: creates a sprint race with given name, date, and driver places
    public Sprint(String name, String date, List<Driver> places) {
        this.name = name;
        this.date = date;
        this.places = places;
    }

    // EFFECTS: adds points and wins to drivers according to their race placements
    public void updateDriverStats() {
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

        Driver eighth = places.get(7);
        eighth.addPoints(1);
    }

    // GETTERS:

    @Override
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public List<Driver> getPlaces() {
        return places;
    }

    @Override
    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("sprint?", true);
        json.put("name", name);
        json.put("date", date);
        json.put("places", placesToJson());
        return json;
    }

    // EFFECTS: returns drivers in places as a JSON array
    private JSONArray placesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Driver driver : places) {
            jsonArray.put(driver.toJson());
        }

        return jsonArray;
    }
}