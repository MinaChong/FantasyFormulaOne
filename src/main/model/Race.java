package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents an abstract class that can handle sprint races and grand prix
public abstract class Race implements Writable {
    // EFFECTS: returns this as JSON object
    public abstract JSONObject toJson();

    // GETTERS:

    public abstract String getName();

    public abstract List<Driver> getPlaces();
}