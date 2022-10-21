package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents an interface that can handle sprint races and grand prix
public class Race implements Writable {
    protected String name;
    protected String date;
    protected List<Driver> places;
    protected Driver fastestLap;

    public Race(String name, String date) {
        this.name = name;
        this.date = date;
        this.places = new ArrayList<>();
        this.fastestLap = null;
    }

    public void updateDriverPoints() {
        // VOID
    }



    public String getName() {
        return name;
    }

    public List<Driver> getPlaces() {
        return places;
    }

    // EFFECTS: returns string representation of this race
    public String toString() {
        return name;
    }

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

    public void setPlaces(List<Driver> places) {
        this.places = places;
    }

    public void setPlace(Driver place) {
        places.add(place);
    }

    public void setFastestLap(Driver fastestLap) {
        this.fastestLap = fastestLap;
    }
}