package model;

import java.util.ArrayList;
import java.util.List;

// Represents a league having a name and list of teams
public class League {
    private String name;        // the league's name
    private List<Team> teams;   // teams in the league

    // REQUIRES: name of league has non-zero length
    // EFFECTS: creates a league with given name, and no teams
    public League(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given team to league's list of teams
    public void addTeam(Team team) {
        teams.add(team);
    }

    // MODIFIES: this
    // EFFECTS: removes given team from league's list of teams
    public void removeTeam(Team team) {
        teams.remove(team);
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }
}