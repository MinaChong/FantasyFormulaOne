package ui;

import model.*;
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
        initializeRaces();
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
        setLeaguePanel();
    }

    public void setScoreboardPanel() {
        JPanel scoreboardPanel = new JPanel();
        scoreboardPanel.setLayout(new GridLayout(0, 1));
        scoreboardPanel.add(new JButton("TEAM     POINTS     WINS"));

        for (Team team : league.getTeams()) {
            scoreboardPanel.add(new JButton(team.getName() + " " + team.getPoints() + " " + team.getWins()));
        }

        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));
        scoreboardPanel.add(quitButton);

        homePanel.add(scoreboardPanel, "Scoreboard");
    }

    public void setWinnerPanel() {
        JPanel winnerPanel = new JPanel();
        JButton winnerButton = new JButton("Declare " + league.getName() + " winner!");
        winnerButton.addActionListener(e -> decideWinner(league.getTeams(), winnerPanel));
        winnerPanel.add(winnerButton);

        homePanel.add(winnerPanel, "Winner");
    }

    // EFFECTS: returns winner based on team with most points, then most wins, then greatest number of fastest laps
    private void decideWinner(List<Team> teams, JPanel winnerPanel) {
        Team winner = new Team("dummy");
        int p = 0;
        int w = 0;
        int l = 0;
        for (Team team : teams) {
            if (team.getPoints() > p) {
                p = team.getPoints();
                winner = team;
            } else if (team.getPoints() == p) {
                if (team.getWins() > w) {
                    w = team.getWins();
                    winner = team;
                } else if (team.getFastestLaps() == l) {
                    if (team.getFastestLaps() > l) {
                        l = team.getFastestLaps();
                        winner = team;
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "The champion is " + winner.getName() + "!", "Declare Winner",
                JOptionPane.INFORMATION_MESSAGE);

        cardLayout.show(homePanel, "League");
        setPanels();
    }

    public void setRacesPanel() {
        JPanel racesPanel = new JPanel();
        racesPanel.setLayout(new GridLayout(0,1));
        racesPanel.setSize(new Dimension(0, 0));

        for (Race race : league.getRaces()) {
            JButton raceButton = new JButton(race.getName());
            racesPanel.add(raceButton);
            JPanel racePanel = new JPanel();
            racePanel.setLayout(new GridLayout(0,1));

            for (Driver driver : race.getPlaces()) {
                racePanel.add(new JButton(driver.getName()));
            }

            JButton quitButton = new JButton("Return to Main Menu");
            quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));
            racePanel.add(quitButton);

            homePanel.add(racePanel, race.getName());
            raceButton.addActionListener(e -> cardLayout.show(homePanel, race.getName()));
        }

        JButton addRaceButton = new JButton("Add Race");
        addRaceButton.addActionListener(e -> addRace());
        racesPanel.add(addRaceButton);

        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));
        racesPanel.add(quitButton);

        homePanel.add(racesPanel, "Races");
    }

    public void addRace() {
        // TODO
        cardLayout.show(homePanel, "League");
        setPanels();
    }

    public void setTeamsPanel() {
        JPanel teamsPanel = new JPanel();
        teamsPanel.setLayout(new GridLayout(0,1));
        teamsPanel.setSize(new Dimension(0, 0));

        for (Team team : league.getTeams()) {
            JButton teamButton = new JButton(team.getName());
            teamsPanel.add(teamButton);
            JPanel teamPanel = new JPanel();
            teamPanel.setLayout(new GridLayout(0,1));

            for (Driver driver : team.getDrivers()) {
                JButton driverButton = new JButton(driver.getName());
                teamPanel.add(driverButton);
            }

            JButton addDriverButton = new JButton("Add Driver");
            addDriverButton.addActionListener(e -> addDriver(team));
            teamPanel.add(addDriverButton);

            JButton quitButton = new JButton("Return to Main Menu");
            quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));
            teamPanel.add(quitButton);

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
            driverPanel.add(new JButton(driver.getName() + " has " + driver.getPoints() + " points."));

            homePanel.add(driverPanel, driver.getName());
            driverButton.addActionListener(e -> cardLayout.show(homePanel, driver.getName()));
        }

        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));
        driversPanel.add(quitButton);

        homePanel.add(driversPanel, "Drivers");
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
        saveButton.addActionListener(e -> saveLeague());
        loadButton.addActionListener(e -> loadLeague());
        quitButton.addActionListener(e -> fantasyFrame.dispose());

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

    // MODIFIES: this
    // EFFECTS: initializes list of F1 races that have been held so far in 2022
    private void initializeRaces() {
        addBahrain();
        addSaudiArabia();
        addAustralia();
        addEmiliaRomagnaSprint();
        addEmiliaRomagna();
        addMiami();
        addSpain();
        addMonaco();
        addAzerbaijan();
        addCanada();
        addGreatBritain();
        addAustriaSprint();
        addAustria();
        addFrance();
        addHungary();
        addBelgium();
        addNetherlands();
        addItaly();
        addSingapore();
        addJapan();
        addUnitedStates();
    }

    // MODIFIES: this
    // EFFECTS: add results of Bahrain Grand Prix
    private void addBahrain() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(cleclerc);
        drivers.add(csainz);
        drivers.add(lhamilton);
        drivers.add(grussell);
        drivers.add(kmagnussen);
        drivers.add(vbottas);
        drivers.add(eocon);
        drivers.add(ytsunoda);
        drivers.add(falonso);
        drivers.add(gzhou);
        GrandPrix bahrain = new GrandPrix("Bahrain Grand Prix", "20/03/22", drivers, cleclerc);
        bahrain.updateDriverStats();
        league.addRace(bahrain);
    }

    // MODIFIES: this
    // EFFECTS: add results of Saudi Arabian Grand Prix
    private void addSaudiArabia() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(cleclerc);
        drivers.add(csainz);
        drivers.add(sperez);
        drivers.add(grussell);
        drivers.add(eocon);
        drivers.add(lnorris);
        drivers.add(pgasly);
        drivers.add(kmagnussen);
        drivers.add(lhamilton);
        GrandPrix saudiArabia = new GrandPrix("Saudi Arabian Grand Prix", "27/03/22", drivers, cleclerc);
        saudiArabia.updateDriverStats();
        league.addRace(saudiArabia);
    }

    // MODIFIES: this
    // EFFECTS: add results of Australian Grand Prix
    private void addAustralia() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(cleclerc);
        drivers.add(sperez);
        drivers.add(grussell);
        drivers.add(lhamilton);
        drivers.add(lnorris);
        drivers.add(dricciardo);
        drivers.add(eocon);
        drivers.add(vbottas);
        drivers.add(pgasly);
        drivers.add(aalbon);
        GrandPrix australia = new GrandPrix("Australian Grand Prix", "10/04/22", drivers, cleclerc);
        australia.updateDriverStats();
        league.addRace(australia);
    }

    // MODIFIES: this
    // EFFECTS: add results of Emilia Romagna Sprint Race
    private void addEmiliaRomagnaSprint() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(cleclerc);
        drivers.add(sperez);
        drivers.add(csainz);
        drivers.add(lnorris);
        drivers.add(dricciardo);
        drivers.add(vbottas);
        drivers.add(kmagnussen);
        Sprint emiliaRomagnaSprint = new Sprint("Emilia Romagna Sprint Race", "23/04/22", drivers);
        emiliaRomagnaSprint.updateDriverStats();
        league.addRace(emiliaRomagnaSprint);
    }

    // MODIFIES: this
    // EFFECTS: add results of Emilia Romagna Grand Prix
    private void addEmiliaRomagna() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(sperez);
        drivers.add(lnorris);
        drivers.add(grussell);
        drivers.add(vbottas);
        drivers.add(cleclerc);
        drivers.add(ytsunoda);
        drivers.add(svettel);
        drivers.add(kmagnussen);
        drivers.add(lstroll);
        GrandPrix emiliaRomagna = new GrandPrix("Emilia Romagna Grand Prix", "24/04/22", drivers, mverstappen);
        emiliaRomagna.updateDriverStats();
        league.addRace(emiliaRomagna);
    }

    // MODIFIES: this
    // EFFECTS: add results of Miami Grand Prix
    private void addMiami() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(cleclerc);
        drivers.add(csainz);
        drivers.add(sperez);
        drivers.add(grussell);
        drivers.add(lhamilton);
        drivers.add(vbottas);
        drivers.add(eocon);
        drivers.add(aalbon);
        drivers.add(lstroll);
        GrandPrix miami = new GrandPrix("Miami Grand Prix", "08/05/22", drivers, mverstappen);
        miami.updateDriverStats();
        league.addRace(miami);
    }

    // MODIFIES: this
    // EFFECTS: add results of Spanish Grand Prix
    private void addSpain() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(sperez);
        drivers.add(grussell);
        drivers.add(csainz);
        drivers.add(lhamilton);
        drivers.add(vbottas);
        drivers.add(eocon);
        drivers.add(lnorris);
        drivers.add(falonso);
        drivers.add(ytsunoda);
        GrandPrix spain = new GrandPrix("Spanish Grand Prix", "22/05/22", drivers, sperez);
        spain.updateDriverStats();
        league.addRace(spain);
    }

    // MODIFIES: this
    // EFFECTS: add results of Monaco Grand Prix
    private void addMonaco() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(sperez);
        drivers.add(csainz);
        drivers.add(mverstappen);
        drivers.add(cleclerc);
        drivers.add(grussell);
        drivers.add(lnorris);
        drivers.add(falonso);
        drivers.add(lhamilton);
        drivers.add(vbottas);
        drivers.add(svettel);
        GrandPrix monaco = new GrandPrix("Monaco Grand Prix", "29/05/22", drivers, lnorris);
        monaco.updateDriverStats();
        league.addRace(monaco);
    }

    // MODIFIES: this
    // EFFECTS: add results of Azerbaijan Grand Prix
    private void addAzerbaijan() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(sperez);
        drivers.add(grussell);
        drivers.add(lhamilton);
        drivers.add(pgasly);
        drivers.add(svettel);
        drivers.add(falonso);
        drivers.add(dricciardo);
        drivers.add(lnorris);
        drivers.add(eocon);
        GrandPrix azerbaijan = new GrandPrix("Azerbaijan Grand Prix", "12/06/22", drivers, sperez);
        azerbaijan.updateDriverStats();
        league.addRace(azerbaijan);
    }

    // MODIFIES: this
    // EFFECTS: add results of Canadian Grand Prix
    private void addCanada() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(csainz);
        drivers.add(lhamilton);
        drivers.add(grussell);
        drivers.add(cleclerc);
        drivers.add(eocon);
        drivers.add(vbottas);
        drivers.add(gzhou);
        drivers.add(falonso);
        drivers.add(lstroll);
        GrandPrix canada = new GrandPrix("Canadian Grand Prix", "19/06/22", drivers, csainz);
        canada.updateDriverStats();
        league.addRace(canada);
    }

    // MODIFIES: this
    // EFFECTS: add results of British Grand Prix
    private void addGreatBritain() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(csainz);
        drivers.add(sperez);
        drivers.add(lhamilton);
        drivers.add(cleclerc);
        drivers.add(falonso);
        drivers.add(lnorris);
        drivers.add(mverstappen);
        drivers.add(mschumacher);
        drivers.add(svettel);
        drivers.add(kmagnussen);
        GrandPrix greatBritain = new GrandPrix("British Grand Prix", "03/07/22", drivers, lhamilton);
        greatBritain.updateDriverStats();
        league.addRace(greatBritain);
    }

    // MODIFIES: this
    // EFFECTS: add results of Austrian Sprint Race
    private void addAustriaSprint() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(cleclerc);
        drivers.add(csainz);
        drivers.add(grussell);
        drivers.add(sperez);
        drivers.add(eocon);
        drivers.add(kmagnussen);
        drivers.add(lhamilton);
        Sprint austriaSprint = new Sprint("Austrian Sprint Race", "10/06/22", drivers);
        austriaSprint.updateDriverStats();
        league.addRace(austriaSprint);
    }

    // MODIFIES: this
    // EFFECTS: add results of Austrian Grand Prix
    private void addAustria() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(cleclerc);
        drivers.add(mverstappen);
        drivers.add(lhamilton);
        drivers.add(grussell);
        drivers.add(eocon);
        drivers.add(mschumacher);
        drivers.add(lnorris);
        drivers.add(kmagnussen);
        drivers.add(dricciardo);
        drivers.add(falonso);
        GrandPrix austria = new GrandPrix("Austrian Grand Prix", "10/07/22", drivers, cleclerc);
        austria.updateDriverStats();
        league.addRace(austria);
    }

    // MODIFIES: this
    // EFFECTS: add results of French Grand Prix
    private void addFrance() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(lhamilton);
        drivers.add(grussell);
        drivers.add(sperez);
        drivers.add(csainz);
        drivers.add(falonso);
        drivers.add(lnorris);
        drivers.add(eocon);
        drivers.add(dricciardo);
        drivers.add(lstroll);
        GrandPrix france = new GrandPrix("French Grand Prix", "24/07/22", drivers, csainz);
        france.updateDriverStats();
        league.addRace(france);
    }

    // MODIFIES: this
    // EFFECTS: add results of Hungarian Grand Prix
    private void addHungary() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(lhamilton);
        drivers.add(grussell);
        drivers.add(csainz);
        drivers.add(sperez);
        drivers.add(cleclerc);
        drivers.add(lnorris);
        drivers.add(falonso);
        drivers.add(eocon);
        drivers.add(svettel);
        GrandPrix hungary = new GrandPrix("Hungarian Grand Prix", "31/07/22", drivers, lhamilton);
        hungary.updateDriverStats();
        league.addRace(hungary);
    }

    // MODIFIES: this
    // EFFECTS: add results of Belgian Grand Prix
    private void addBelgium() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(sperez);
        drivers.add(csainz);
        drivers.add(grussell);
        drivers.add(falonso);
        drivers.add(cleclerc);
        drivers.add(eocon);
        drivers.add(svettel);
        drivers.add(pgasly);
        drivers.add(aalbon);
        GrandPrix belgium = new GrandPrix("Belgian Grand Prix", "28/08/22", drivers, mverstappen);
        belgium.updateDriverStats();
        league.addRace(belgium);
    }

    // MODIFIES: this
    // EFFECTS: add results of Dutch Grand Prix
    private void addNetherlands() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(grussell);
        drivers.add(cleclerc);
        drivers.add(lhamilton);
        drivers.add(sperez);
        drivers.add(falonso);
        drivers.add(lnorris);
        drivers.add(csainz);
        drivers.add(eocon);
        drivers.add(lstroll);
        GrandPrix netherlands = new GrandPrix("Dutch Grand Prix", "04/09/22", drivers, mverstappen);
        netherlands.updateDriverStats();
        league.addRace(netherlands);
    }

    // MODIFIES: this
    // EFFECTS: add results of Italian Grand Prix
    private void addItaly() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(cleclerc);
        drivers.add(grussell);
        drivers.add(csainz);
        drivers.add(lhamilton);
        drivers.add(sperez);
        drivers.add(lnorris);
        drivers.add(pgasly);
        drivers.add(ndevries);
        drivers.add(gzhou);
        GrandPrix italy = new GrandPrix("Italian Grand Prix", "11/09/22", drivers, sperez);
        italy.updateDriverStats();
        league.addRace(italy);
    }

    // MODIFIES: this
    // EFFECTS: add results of Singapore Grand Prix
    private void addSingapore() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(sperez);
        drivers.add(cleclerc);
        drivers.add(csainz);
        drivers.add(lnorris);
        drivers.add(dricciardo);
        drivers.add(lstroll);
        drivers.add(mverstappen);
        drivers.add(svettel);
        drivers.add(lhamilton);
        drivers.add(pgasly);
        GrandPrix singapore = new GrandPrix("Singapore Grand Prix", "02/10/22", drivers, grussell);
        singapore.updateDriverStats();
        league.addRace(singapore);
    }

    // MODIFIES: this
    // EFFECTS: add results of Japanese Grand Prix
    private void addJapan() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(sperez);
        drivers.add(cleclerc);
        drivers.add(eocon);
        drivers.add(lhamilton);
        drivers.add(svettel);
        drivers.add(falonso);
        drivers.add(grussell);
        drivers.add(nlatifi);
        drivers.add(lnorris);
        GrandPrix japan = new GrandPrix("Japanese Grand Prix", "09/10/22", drivers, gzhou);
        japan.updateDriverStats();
        league.addRace(japan);
    }

    // MODIFIES: this
    // EFFECTS: add results of United States Grand Prix
    private void addUnitedStates() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(mverstappen);
        drivers.add(lhamilton);
        drivers.add(cleclerc);
        drivers.add(sperez);
        drivers.add(grussell);
        drivers.add(lnorris);
        drivers.add(svettel);
        drivers.add(kmagnussen);
        drivers.add(ytsunoda);
        drivers.add(eocon);
        GrandPrix unitedStates = new GrandPrix("United States Grand Prix", "23/10/22", drivers, grussell);
        unitedStates.updateDriverStats();
        league.addRace(unitedStates);
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
