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

    public JSONObject toJson() {
        return null;
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
}