package model;

import java.util.List;

public interface Race {
    // EFFECTS: adds points and wins to drivers according to their race placements
    void updateDriverPoints();

    String getName();

    String getDate();

    List<Driver> getPlaces();
}
