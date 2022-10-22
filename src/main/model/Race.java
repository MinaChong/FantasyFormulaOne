package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// TODO Represents an abstract class?? that can handle sprint races and grand prix
public class Race implements Writable {
    protected String name;
    protected String date;
    protected List<Driver> places;
    protected Driver fastestLap;

    // REQUIRES: name of race is of non-zero length, date of race is in form DD/MM/YY where D, M, Y are positive
    // integers
    // EFFECTS: creates a race with given name and date
    public Race(String name, String date) {
        this.name = name;
        this.date = date;
        this.places = new ArrayList<>();
        this.fastestLap = null;
    }

    // SETTERS:

    public void setPlaces(List<Driver> places) {
        this.places = places;
    }

    public void setFastestLap(Driver fastestLap) {
        this.fastestLap = fastestLap;
    }

    // GETTERS:

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("places", placesToJson());
        json.put("fastestLap", fastestLapToJson());
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

    // EFFECTS: returns driver with fastest lap as a JSON object
    private JSONObject fastestLapToJson() {
        JSONObject json = new JSONObject();
        json.put("name", fastestLap.getName());
        json.put("num", fastestLap.getNum());
        json.put("points", fastestLap.getPoints());
        json.put("wins", fastestLap.getWins());
        json.put("fastestLaps", fastestLap.getFastestLaps());
        return json;
    }
}