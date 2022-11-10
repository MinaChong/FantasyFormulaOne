package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads league from JSON data stored in file
// NOTE: lends structure from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads league from file and returns it;
    // throws IOException if an error occurs reading data from file
    public League read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses league from JSON object and returns it
    private League parseLeague(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        League league = new League(name);
        addDrivers(league, jsonObject);
        addRaces(league, jsonObject);
        addTeams(league, jsonObject);
        return league;
    }

    // MODIFIES: league
    // EFFECTS: parses drivers from JSON object and adds them to league
    private void addDrivers(League league, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("drivers");
        for (Object json : jsonArray) {
            JSONObject nextDriver = (JSONObject) json;
            addDriver(league, nextDriver);
        }
    }

    // MODIFIES: league
    // EFFECTS: parses driver from JSON object and adds it to team
    private void addDriver(League league, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int num = jsonObject.getInt("num");
        int points = jsonObject.getInt("points");
        int wins = jsonObject.getInt("wins");
        int fastestlaps = jsonObject.getInt("fastestLaps");
        Driver driver = new Driver(name, num);
        driver.addPoints(points);
        driver.setWins(wins);
        driver.setFastestLaps(fastestlaps);
        addDriverTeams(driver, jsonObject);
        league.addDriver(driver);
    }

    // MODIFIES: driver
    // EFFECTS: parses teams from JSON object and adds it to driver
    private void addDriverTeams(Driver driver, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");

        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            driver.addTeam(addDriverTeam(nextTeam));
        }
    }

    // EFFECTS: parses driver's team from JSON object and returns it
    private Team addDriverTeam(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        return new Team(name);
    }


    // MODIFIES: league
    // EFFECTS: parses races from JSON object and adds them to league
    private void addRaces(League league, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("races");
        for (Object json : jsonArray) {
            JSONObject nextRace = (JSONObject) json;
            addRace(league, nextRace);
        }
    }

    // MODIFIES: league
    // EFFECTS: parses race from JSON object and adds it to league
    private void addRace(League league, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("date");
        List<Driver> places = addPlaces(jsonObject);

        if (jsonObject.getBoolean("sprint?")) {
            Sprint sprint = new Sprint(name, date, places);
            league.addRace(sprint);
        } else {
            Driver fastestLap = addFastestLap(jsonObject);
            GrandPrix grandPrix = new GrandPrix(name, date, places, fastestLap);
            league.addRace(grandPrix);
        }
    }

    // EFFECTS: parses list of places from JSON object and returns it
    private List<Driver> addPlaces(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("places");
        List<Driver> places = new ArrayList<>();

        for (Object json : jsonArray) {
            JSONObject nextPlace = (JSONObject) json;
            places.add(addPlace(nextPlace));
        }
        return places;
    }

    // EFFECTS: parses place from JSON object and returns it
    private Driver addPlace(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int num = jsonObject.getInt("num");
        int points = jsonObject.getInt("points");
        int wins = jsonObject.getInt("wins");
        int fastestlaps = jsonObject.getInt("fastestLaps");
        Driver place = new Driver(name, num);
        place.addPoints(points);
        place.setWins(wins);
        place.setFastestLaps(fastestlaps);
        return place;
    }

    // EFFECTS: parses driver with fastest lap from JSON object and returns it
    private Driver addFastestLap(JSONObject jsonObject) {
        JSONObject json = (JSONObject) jsonObject.get("fastestLap");
        String name = json.getString("name");
        int num = json.getInt("num");
        int points = json.getInt("points");
        int wins = json.getInt("wins");
        int fastestlaps = json.getInt("fastestLaps");
        Driver fastestLap = new Driver(name, num);
        fastestLap.addPoints(points);
        fastestLap.setWins(wins);
        fastestLap.setFastestLaps(fastestlaps);
        return fastestLap;
    }

    // MODIFIES: league
    // EFFECTS: parses teams from JSON object and adds them to league
    private void addTeams(League league, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");

        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(league, nextTeam);
        }
    }

    // MODIFIES: league
    // EFFECTS: parses team from JSON object and adds them to league
    private void addTeam(League league, JSONObject jsonObject) {
        List<Driver> drivers = league.getDrivers();
        Team team = new Team(jsonObject.getString("name"));

        for (Driver driver : drivers) {
            if (driver.getTeams().contains(team)) {
                team.addDriver(driver);
            }
        }
        league.addTeam(team);
    }
}