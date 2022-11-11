package ui;

import model.Driver;
import model.League;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FantasyF1GUI {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 900;
    private final JFrame fantasyFrame;
    private JPanel homePanel;
    private CardLayout cardLayout;

    private static final String JSON_STORE = "./data/league.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private League league;
    private Driver aalbon;
    private Driver cleclerc;
    private Driver csainz;
    private Driver dricciardo;
    private Driver eocon;
    private Driver falonso;
    private Driver grussell;
    private Driver gzhou;
    private Driver kmagnussen;
    private Driver lhamilton;
    private Driver lnorris;
    private Driver lstroll;
    private Driver mschumacher;
    private Driver mverstappen;
    private Driver ndevries;
    private Driver nhulkenberg;
    private Driver nlatifi;
    private Driver pgasly;
    private Driver sperez;
    private Driver svettel;
    private Driver vbottas;
    private Driver ytsunoda;

    public FantasyF1GUI() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        this.league = new League("My Fantasy F1 League");
        initializeDrivers();
        initializeGrid();
        initializeTeams();
        fantasyFrame = new JFrame("Fantasy F1 UI");
        setup();
    }

    public void setup() {
        cardLayout = new CardLayout();
        homePanel = new JPanel(cardLayout);

        setPanels();

        fantasyFrame.add(homePanel);

        cardLayout.show(homePanel, "League");

        fantasyFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        fantasyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fantasyFrame.setVisible(true);
    }

    public void setPanels() {
        setScoreboardPanel();
        setWinnerPanel();
        setRacesPanel();
        setTeamsPanel();
        setDriversPanel();
        setSavePanel();
        setLoadPanel();
        setQuitPanel();
        setLeaguePanel();
    }

    public void setScoreboardPanel() {
        JPanel scoreboardPanel = new JPanel();

        for (Team team : league.getTeams()) {
            JButton teamButton = new JButton(team.getName() + " " + team.getPoints());
            scoreboardPanel.add(teamButton);
        }

        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));
        scoreboardPanel.add(quitButton);

        homePanel.add(scoreboardPanel, "Scoreboard");
    }

    public void setWinnerPanel() {
        JPanel winnerPanel = new JPanel();
        winnerPanel.add(new JButton("winner")); // TODO

        homePanel.add(winnerPanel, "Winner");
    }

    public void setRacesPanel() {
        JPanel racesPanel = new JPanel();
        racesPanel.add(new JButton("races")); // TODO

        homePanel.add(racesPanel, "Races");
    }

    public void setTeamsPanel() {
        JPanel teamsPanel = new JPanel();
        teamsPanel.setLayout(new GridLayout(0,1));
        teamsPanel.setSize(new Dimension(0, 0));

        for (Team team : league.getTeams()) {
            JButton teamButton = new JButton(team.getName());
            teamsPanel.add(teamButton);
            JPanel teamPanel = new JPanel();

            for (Driver driver : team.getDrivers()) {
                JButton driverButton = new JButton(driver.getName());
                teamPanel.add(driverButton);
            }

            JButton addDriverButton = new JButton("Add Driver");
            addDriverButton.addActionListener(e -> addDriver(team));
            teamPanel.add(addDriverButton);

            homePanel.add(teamPanel, team.getName());
            teamButton.addActionListener(e -> cardLayout.show(homePanel, team.getName()));
        }

        JButton addTeamButton = new JButton("Add Team");
        addTeamButton.addActionListener(e -> addTeam());
        teamsPanel.add(addTeamButton);

        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));
        teamsPanel.add(quitButton);

        homePanel.add(teamsPanel, "Teams");
    }

    public void addDriver(Team team) {
        if (team.getDrivers().size() >= 3) {
            JOptionPane.showMessageDialog(null, "Maximum number of drivers already added to team.", "System Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            List<String> available = new ArrayList<>();
            for (Driver driver : league.getDrivers()) {
                if (!team.getDrivers().contains(driver)) {
                    available.add(driver.getName());
                }
            }

            String[] availableDrivers = available.toArray(new String[0]);

            Object nameOfDriverToAdd = JOptionPane.showInputDialog(null,
                    "Which driver would you like to add?",
                    "Add Driver",
                    JOptionPane.QUESTION_MESSAGE,
                    null, availableDrivers, null);
            for (Driver driver : league.getDrivers()) {
                if (driver.getName().equals(nameOfDriverToAdd)) {
                    team.addDriver(driver);
                }
            }
        }

        cardLayout.show(homePanel, "League");
        setPanels();
    }

    public void addTeam() {
        String teamName = JOptionPane.showInputDialog(null,
                "Team name?",
                "Create New Team",
                JOptionPane.QUESTION_MESSAGE);
        Team newTeam = new Team(teamName);
        league.addTeam(newTeam);

        cardLayout.show(homePanel, "League");
        setPanels();
    }

    public void setDriversPanel() {
        JPanel driversPanel = new JPanel();
        driversPanel.setLayout(new GridLayout(0,1));
        driversPanel.setSize(new Dimension(0, 0));

        for (Driver driver : league.getDrivers()) {
            JButton driverButton = new JButton(driver.getName());
            driversPanel.add(driverButton);
            JPanel driverPanel = new JPanel();
            driverPanel.add(new JLabel(driver.getName() + " has " + driver.getPoints() + " points."));

            homePanel.add(driverPanel, driver.getName());
            driverButton.addActionListener(e -> cardLayout.show(homePanel, driver.getName()));
        }

        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));
        driversPanel.add(quitButton);

        homePanel.add(driversPanel, "Drivers");
    }

    public void setSavePanel() {
        JPanel savePanel = new JPanel();
        JButton saveButton = new JButton("Save League");
        saveButton.addActionListener(e -> saveLeague());
        savePanel.add(saveButton);

        homePanel.add(savePanel, "Save");
    }

    // EFFECTS: saves league to file
    private void saveLeague() {
        try {
            jsonWriter.open();
            jsonWriter.write(league);
            jsonWriter.close();
            System.out.println("Saved " + league.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        cardLayout.show(homePanel, "League");
        setPanels();
    }

    public void setLoadPanel() {
        JPanel loadPanel = new JPanel();
        JButton loadButton = new JButton("Load League");
        loadButton.addActionListener(e -> loadLeague());
        loadPanel.add(loadButton);

        homePanel.add(loadPanel, "Load");
    }

    // MODIFIES: this
    // EFFECTS: loads league from file
    private void loadLeague() {
        try {
            league = jsonReader.read();
            System.out.println("Loaded " + league.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        cardLayout.show(homePanel, "League");
        setPanels();
    }

    public void setQuitPanel() {
        JPanel quitPanel = new JPanel();
        quitPanel.add(new JButton("quit")); // TODO

        homePanel.add(quitPanel, "Quit");
    }

    public void setLeaguePanel() {
        JPanel leaguePanel = new JPanel();
        leaguePanel.setLayout(new GridLayout(0,3));

        JButton scoreboardButton = new JButton("Scoreboard");
        JButton carButton = new JButton(new ImageIcon("images/car.jpg"));
        JButton winnerButton = new JButton("Declare Winner");
        JButton racesButton = new JButton("Races");
        JButton teamsButton = new JButton("Teams");
        JButton driversButton = new JButton("Drivers");
        JButton saveButton = new JButton("Save League");
        JButton loadButton = new JButton("Load League");
        JButton quitButton = new JButton("Quit");
        leaguePanel.add(scoreboardButton);
        leaguePanel.add(carButton);
        leaguePanel.add(winnerButton);
        leaguePanel.add(racesButton);
        leaguePanel.add(teamsButton);
        leaguePanel.add(driversButton);
        leaguePanel.add(saveButton);
        leaguePanel.add(loadButton);
        leaguePanel.add(quitButton);

        scoreboardButton.addActionListener(e -> cardLayout.show(homePanel, "Scoreboard"));
        winnerButton.addActionListener(e -> cardLayout.show(homePanel, "Winner"));
        racesButton.addActionListener(e -> cardLayout.show(homePanel, "Races"));
        teamsButton.addActionListener(e -> cardLayout.show(homePanel, "Teams"));
        driversButton.addActionListener(e -> cardLayout.show(homePanel, "Drivers"));
        saveButton.addActionListener(e -> cardLayout.show(homePanel, "Save"));
        loadButton.addActionListener(e -> cardLayout.show(homePanel, "Load"));
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "Quit"));
        homePanel.add(leaguePanel, "League");
    }

    private void initializeDrivers() {
        aalbon = new Driver("Alex Albon", 23);
        cleclerc = new Driver("Charles Leclerc", 16);
        csainz = new Driver("Carlos Sainz", 55);
        dricciardo = new Driver("Daniel Ricciardo", 3);
        eocon = new Driver("Esteban Ocon", 31);
        falonso = new Driver("Fernando Alonso", 14);
        grussell = new Driver("George Russell", 63);
        gzhou = new Driver("Guanyu Zhou", 24);
        kmagnussen = new Driver("Kevin Magnussen", 20);
        lhamilton = new Driver("Lewis Hamilton", 44);
        lnorris = new Driver("Lando Norris", 4);
        lstroll = new Driver("Lance Stroll", 18);
        mschumacher = new Driver("Mick Schumacher", 47);
        mverstappen = new Driver("Max Verstappen", 1);
        ndevries = new Driver("Nyck de Vries", 17);
        nhulkenberg = new Driver("Nico Hulkenberg", 27);
        nlatifi = new Driver("Nicholas Latifi", 6);
        pgasly = new Driver("Pierre Gasly", 10);
        sperez = new Driver("Sergio Perez", 11);
        svettel = new Driver("Sebastian Vettel", 5);
        vbottas = new Driver("Valtteri Bottas", 77);
        ytsunoda = new Driver("Yuki Tsunoda", 22);
    }

    // MODIFIES: this
    // EFFECTS: initializes current grid of 20 drivers
    private void initializeGrid() {
        league.addDriver(aalbon);
        league.addDriver(cleclerc);
        league.addDriver(csainz);
        league.addDriver(dricciardo);
        league.addDriver(eocon);
        league.addDriver(falonso);
        league.addDriver(grussell);
        league.addDriver(gzhou);
        league.addDriver(kmagnussen);
        league.addDriver(lhamilton);
        league.addDriver(lnorris);
        league.addDriver(lstroll);
        league.addDriver(mschumacher);
        league.addDriver(mverstappen);
        league.addDriver(nlatifi);
        league.addDriver(pgasly);
        league.addDriver(sperez);
        league.addDriver(svettel);
        league.addDriver(vbottas);
        league.addDriver(ytsunoda);
    }

    private void initializeTeams() {
        Team teamKimi = new Team("Kimi's Team");
        teamKimi.addDriver(mverstappen);
        teamKimi.addDriver(lhamilton);
        teamKimi.addDriver(lstroll);

        Team teamMichael = new Team("Michael's Team");
        teamMichael.addDriver(cleclerc);
        teamMichael.addDriver(lhamilton);
        teamMichael.addDriver(lnorris);

        league.addTeam(teamKimi);
        league.addTeam(teamMichael);
    }

    public static void main(String[] args) {
        new FantasyF1GUI();
    }
}
