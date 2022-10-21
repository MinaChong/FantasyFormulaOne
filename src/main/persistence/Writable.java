package persistence;

import org.json.JSONObject;

// NOTE: lends structure from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
