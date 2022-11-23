package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Fantasy F1 Graphic User Interface (GUI)
public class FantasyGUI {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final Color PANEL_COLOUR = new Color(181, 234, 255);
    private final JFrame fantasyFrame;
    private JPanel homePanel;
    private CardLayout cardLayout;
    private JButton leaderboardButton;
    private JButton winnerButton;
    private JButton racesButton;
    private JButton teamsButton;
    private JButton driversButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton quitButton;

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
    private Driver nlatifi;
    private Driver pgasly;
    private Driver sperez;
    private Driver svettel;
    private Driver vbottas;
    private Driver ytsunoda;

    // MODIFIES: this
    // EFFECTS: creates Fantasy F1 graphic user interface
    public FantasyGUI() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        this.league = new League("My Fantasy F1 League");
        initializeDrivers();
        initializeGrid();
        initializeRaces();
        initializeTeams();
        fantasyFrame = new JFrame("Fantasy F1 UI");

        cardLayout = new CardLayout();
        homePanel = new JPanel(cardLayout);

        setPanels();

        fantasyFrame.add(homePanel);
        cardLayout.show(homePanel, "League");
        fantasyFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        fantasyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fantasyFrame.setVisible(true);
    }

    // EFFECTS: sets panels for GUI
    public void setPanels() {
        setScoreboardPanel();
        setRacesPanel();
        setTeamsPanel();
        setDriversPanel();
        setLeaguePanel();
    }

    // MODIFIES: this
    // EFFECTS: sets scoreboard panel for GUI
    public void setScoreboardPanel() {
        JPanel scoreboardPanel = new JPanel();
        scoreboardPanel.setBackground(PANEL_COLOUR);

        JLabel title = new JLabel("LEADERBOARD");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(0, 1));
        tablePanel.setPreferredSize(new Dimension(800, 400));
        tablePanel.setBackground(new Color(0, 0, 0, 0));
        tablePanel.add(setScoreBoard());

        JPanel quitPanel = setQuitPanel();

        scoreboardPanel.add(title);
        scoreboardPanel.add(tablePanel);
        scoreboardPanel.add(quitPanel);

        homePanel.add(scoreboardPanel, "Scoreboard");
    }

    // EFFECTS: returns scoreboard for GUI
    public JScrollPane setScoreBoard() {
        String[] header = {"TEAM", "POINTS", "WINS"};
        List<String[]> rows = new ArrayList<>();

        for (Team team : league.getTeams()) {
            List<String> row = new ArrayList<>();
            row.add(team.getName());
            row.add(Integer.toString(team.getPoints()));
            row.add(Integer.toString(team.getWins()));
            rows.add(row.toArray(new String[0]));
        }

        String[][] table = rows.toArray(new String[0][]);
        JTable scoreBoard = new JTable(table, header);
        scoreBoard.setFont(new Font("SansSerif", Font.PLAIN, 15));
        scoreBoard.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        scoreBoard.setBackground(Color.WHITE);
        scoreBoard.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scoreBoard.setRowHeight(22);

        return new JScrollPane(scoreBoard);
    }

    // EFFECTS: displays winner based on team with most points, then most wins
    private void decideWinner() {
        Team winner = new Team("dummy");
        int mostPoints = 0;
        int mostWins = 0;

        for (Team team : league.getTeams()) {
            if (team.getPoints() > mostPoints) {
                mostPoints = team.getPoints();
                winner = team;
            } else if (team.getPoints() == mostPoints) {
                if (team.getWins() > mostWins) {
                    mostWins = team.getWins();
                    winner = team;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "The champion is " + winner.getName() + "!",
                league.getName() + " Winner", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("images/trophy.jpg"));

        setPanels();
        cardLayout.show(homePanel, "League");
    }

    // MODIFIES: this
    // EFFECTS: sets races panel for GUI
    public void setRacesPanel() {
        JPanel racesPanel = new JPanel();
        racesPanel.setBackground(PANEL_COLOUR);

        JPanel titlePanel = setTitlePanel("RACES");

        JPanel raceButtonsPanel = new JPanel();
        raceButtonsPanel.setLayout(new GridLayout(0, 4));
        raceButtonsPanel.setPreferredSize(new Dimension(800, 500));
        raceButtonsPanel.setBackground(PANEL_COLOUR);
        for (Race race : league.getRaces()) {
            JButton raceButton = new JButton(race.getName());
            raceButtonsPanel.add(raceButton);
            setRacePanel(race);
            raceButton.addActionListener(e -> cardLayout.show(homePanel, race.getName()));
        }

        JButton addRaceButton = new JButton("Add Race");
        addRaceButton.addActionListener(e -> addRace());
        JPanel racePanel = setButtonPanel(addRaceButton);

        JPanel quitPanel = setQuitPanel();

        racesPanel.add(titlePanel);
        racesPanel.add(raceButtonsPanel);
        racesPanel.add(racePanel);
        racesPanel.add(quitPanel);

        homePanel.add(racesPanel, "Races");
    }

    // MODIFIES: this
    // EFFECTS: sets race panel for given race for GUI
    public void setRacePanel(Race race) {
        JPanel racePanel = new JPanel();
        racePanel.setBackground(PANEL_COLOUR);

        JPanel titlePanel = setTitlePanel(race.getName().toUpperCase());

        JPanel datePanel = setInfoPanel(race.getDate());

        JPanel placesPanel = new JPanel();
        placesPanel.setPreferredSize(new Dimension(800, 500));
        placesPanel.setBackground(PANEL_COLOUR);
        placesPanel.setLayout(new GridLayout(0, 1));
        int i = 1;
        for (Driver driver : race.getPlaces()) {
            JLabel placeLabel = new JLabel(i + ". " + driver.getName(), JLabel.CENTER);
            placesPanel.add(placeLabel);
            i++;
        }

        JPanel quitPanel = setQuitPanel();

        racePanel.add(titlePanel);
        racePanel.add(datePanel);
        racePanel.add(placesPanel);
        racePanel.add(quitPanel);

        homePanel.add(racePanel, race.getName());
    }

    // MODIFIES: this
    // EFFECTS: allows user to add a race
    public void addRace() {
        String[] raceTypes = { "Grand Prix", "Sprint Race" };
        String raceType = String.valueOf(JOptionPane.showInputDialog(null,
                "Which type of place would you like to add?", "Add Race", JOptionPane.QUESTION_MESSAGE,
                null, raceTypes, null));

        String raceName = JOptionPane.showInputDialog(null, "Race name?", "Enter Race Name",
                JOptionPane.QUESTION_MESSAGE);
        String raceDate = JOptionPane.showInputDialog(null, "Race date?", "Enter Race Date",
                JOptionPane.QUESTION_MESSAGE);

        Race newRace;

        if (raceType.equals("Grand Prix")) {
            List<Driver> places = setPlaces(10);
            Driver fastestLap = setFastestLap();
            newRace = new GrandPrix(raceName, raceDate, places, fastestLap);
        } else {
            List<Driver> places = setPlaces(8);
            newRace = new Sprint(raceName, raceDate, places);
        }

        league.addRace(newRace);

        setPanels();
        cardLayout.show(homePanel, newRace.getName());
    }

    // EFFECTS: allows user to set places for a race
    public List<Driver> setPlaces(int n) {
        List<Driver> places = new ArrayList<>();

        List<String> allDrivers = new ArrayList<>();
        for (Driver driver : league.getDrivers()) {
            allDrivers.add(driver.getName());
        }

        String[] drivers = allDrivers.toArray(new String[0]);

        for (int i = 1; i <= n; i++) {
            Driver place = null;

            Object name = JOptionPane.showInputDialog(null,
                    "Which driver came in " + i + " place?", "Select Place", JOptionPane.QUESTION_MESSAGE,
                    null, drivers, null);

            for (Driver driver : league.getDrivers()) {
                if (driver.getName().equals(name)) {
                    place = driver;
                }
            }

            places.add(place);
            allDrivers.remove(name);
            drivers = allDrivers.toArray(new String[0]);
        }

        return places;
    }

    // EFFECTS: allows user to select driver with the fastest lap of a grand prix
    public Driver setFastestLap() {
        Driver fastestLap = null;

        List<String> allDrivers = new ArrayList<>();
        for (Driver driver : league.getDrivers()) {
            allDrivers.add(driver.getName());
        }

        String[] drivers = allDrivers.toArray(new String[0]);

        Object fastestLapName = JOptionPane.showInputDialog(null,
                "Which driver had the fastest lap?", "Fastest Lap", JOptionPane.QUESTION_MESSAGE,
                null, drivers, null);

        for (Driver driver : league.getDrivers()) {
            if (driver.getName().equals(fastestLapName)) {
                fastestLap = driver;
            }
        }

        return fastestLap;
    }

    // MODIFIES: this
    // EFFECTS: sets teams panel for GUI
    public void setTeamsPanel() {
        JPanel teamsPanel = new JPanel();
        teamsPanel.setBackground(PANEL_COLOUR);

        JPanel titlePanel = setTitlePanel("TEAMS");

        JPanel teamButtonsPanel = setTeamButtonsPanel();

        JButton addTeamButton = new JButton("Add Team");
        addTeamButton.addActionListener(e -> addTeam());
        JPanel addTeamPanel = setButtonPanel(addTeamButton);

        JButton removeTeamButton = new JButton("Remove Team");
        removeTeamButton.addActionListener(e -> removeTeam());
        JPanel removeTeamPanel = setButtonPanel(removeTeamButton);

        JPanel quitPanel = setQuitPanel();

        teamsPanel.add(titlePanel);
        teamsPanel.add(teamButtonsPanel);
        teamsPanel.add(addTeamPanel);
        teamsPanel.add(removeTeamPanel);
        teamsPanel.add(quitPanel);

        homePanel.add(teamsPanel, "Teams");
    }

    // EFFECTS: returns panel of buttons for each team in league for GUI
    public JPanel setTeamButtonsPanel() {
        JPanel teamButtonsPanel = new JPanel();
        teamButtonsPanel.setLayout(new GridLayout(0, 2));
        teamButtonsPanel.setPreferredSize(new Dimension(800, 400));
        teamButtonsPanel.setBackground(PANEL_COLOUR);

        for (Team team : league.getTeams()) {
            JButton teamButton = new JButton(team.getName());
            teamButtonsPanel.add(teamButton);
            setTeamPanel(team);
            teamButton.addActionListener(e -> cardLayout.show(homePanel, team.getName()));
        }

        return teamButtonsPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets team panel for given team for GUI
    public void setTeamPanel(Team team) {
        JPanel teamPanel = new JPanel();
        teamPanel.setBackground(PANEL_COLOUR);

        JPanel titlePanel = setTitlePanel(team.getName().toUpperCase());

        JPanel pointsPanel = setInfoPanel("Points: " + team.getPoints());
        JPanel winsPanel = setInfoPanel("Wins: " + team.getWins());

        JPanel teamDriversPanel = setTeamDriversPanel(team);

        JPanel quitPanel = setQuitPanel();

        teamPanel.add(titlePanel);
        teamPanel.add(pointsPanel);
        teamPanel.add(winsPanel);
        teamPanel.add(teamDriversPanel);
        setTeamPanelButtons(team, teamPanel);
        teamPanel.add(quitPanel);

        homePanel.add(teamPanel, team.getName());
    }

    // EFFECTS: sets panel of drivers on given team for GUI
    public JPanel setTeamDriversPanel(Team team) {
        JPanel teamDriversPanel = new JPanel();
        teamDriversPanel.setPreferredSize(new Dimension(800, 300));
        teamDriversPanel.setBackground(PANEL_COLOUR);
        teamDriversPanel.setLayout(new GridLayout(0, 1));

        for (Driver driver : team.getDrivers()) {
            JLabel placeLabel = new JLabel(driver.getName(), JLabel.CENTER);
            teamDriversPanel.add(placeLabel);
        }

        return teamDriversPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets team buttons panel for given team for GUI
    public void setTeamPanelButtons(Team team, JPanel teamPanel) {
        JButton showGraphButton = new JButton("Show Team Performance Graph");
        showGraphButton.addActionListener(e -> pointsBreakdown(team));
        JPanel showGraphButtonPanel = setButtonPanel(showGraphButton);

        JButton addDriverButton = new JButton("Add Driver");
        addDriverButton.addActionListener(e -> addDriver(team));
        JPanel addDriverPanel = setButtonPanel(addDriverButton);

        JButton removeDriverButton = new JButton("Remove Driver");
        removeDriverButton.addActionListener(e -> removeDriver(team));
        JPanel removeDriverPanel = setButtonPanel(removeDriverButton);

        teamPanel.add(showGraphButtonPanel);
        teamPanel.add(addDriverPanel);
        teamPanel.add(removeDriverPanel);
    }

    // EFFECTS: shows chart of driver points breakdown for given team for GUI
    public void pointsBreakdown(Team team) {
        JPanel chartPanel = new JPanel();
        chartPanel.setBackground(PANEL_COLOUR);

        JPanel titlePanel = setTitlePanel(team.getName().toUpperCase() + " POINTS BREAKDOWN");

        JPanel graphPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        graphPanel.setBackground(Color.white);
        graphPanel.setPreferredSize(new Dimension(900,400));
        int barHeight = 50;
        graphPanel.add(setSpacerPanel(new Dimension(900, barHeight)));

        for (Driver driver : team.getDrivers()) {
            JButton driverBar = new JButton(driver.getName());
            driverBar.setPreferredSize(new Dimension(driver.getPoints() * 2, barHeight));
            driverBar.setBackground(Color.red);
            driverBar.addActionListener(e -> driverBar.setText(driver.getName() + ": " + driver.getPoints()
                    + "/" + team.getPoints() + " points"));
            graphPanel.add(driverBar);
            graphPanel.add(setSpacerPanel(new Dimension(900, barHeight)));
        }

        JPanel quitPanel = setQuitPanel();

        chartPanel.add(titlePanel);
        chartPanel.add(graphPanel);
        chartPanel.add(quitPanel);

        homePanel.add(chartPanel, "Graph");
        cardLayout.show(homePanel, "Graph");
    }

    // EFFECTS: allows user to add a driver to given team
    public void addDriver(Team team) {
        if (team.getDrivers().size() >= 3) {
            JOptionPane.showMessageDialog(null,
                    "Maximum number of drivers already added to team.",
                    "System Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List<String> available = new ArrayList<>();
            for (Driver driver : league.getDrivers()) {
                if (!team.getDrivers().contains(driver)) {
                    available.add(driver.getName());
                }
            }

            String[] availableDrivers = available.toArray(new String[0]);

            Object nameOfDriverToAdd = JOptionPane.showInputDialog(null,
                    "Which driver would you like to add?", "Add Driver", JOptionPane.QUESTION_MESSAGE,
                    null, availableDrivers, null);
            for (Driver driver : league.getDrivers()) {
                if (driver.getName().equals(nameOfDriverToAdd)) {
                    team.addDriver(driver);
                }
            }
        }

        setPanels();
        cardLayout.show(homePanel, team.getName());
    }

    // EFFECTS: allows user to remove a driver from given team
    public void removeDriver(Team team) {
        if (team.getDrivers().size() == 0) {
            JOptionPane.showMessageDialog(null, "There are no drivers on your team to remove.",
                    "System Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List<String> available = new ArrayList<>();
            for (Driver driver : team.getDrivers()) {
                available.add(driver.getName());
            }

            String[] availableDrivers = available.toArray(new String[0]);

            Object driverName = JOptionPane.showInputDialog(null,
                    "Which driver would you like to remove?", "Remove Driver", JOptionPane.QUESTION_MESSAGE,
                    null, availableDrivers, null);
            for (Driver driver : league.getDrivers()) {
                if (driver.getName().equals(driverName)) {
                    team.removeDriver(driver);
                }
            }
        }

        setPanels();
        cardLayout.show(homePanel, team.getName());
    }

    // MODIFIES: this
    // EFFECTS: allows user to add a team to league
    public void addTeam() {
        String teamName = JOptionPane.showInputDialog(null, "Team name?",
                "Create New Team", JOptionPane.QUESTION_MESSAGE);
        Team newTeam = new Team(teamName);
        league.addTeam(newTeam);

        setPanels();
        cardLayout.show(homePanel, newTeam.getName());
    }

    // MODIFIES: this
    // EFFECTS: allows user to remove a team from league
    public void removeTeam() {
        List<String> available = new ArrayList<>();
        for (Team team : league.getTeams()) {
            available.add(team.getName());
        }

        String[] availableTeams = available.toArray(new String[0]);

        String teamName = String.valueOf(JOptionPane.showInputDialog(null,
                "Which team would you like to remove?", "Remove Team", JOptionPane.QUESTION_MESSAGE,
                null, availableTeams, null));

        Team teamToRemove = null;

        for (Team team : league.getTeams()) {
            if (team.getName().equals(teamName)) {
                teamToRemove = team;
            }
        }

        league.removeTeam(teamToRemove);

        setPanels();
        cardLayout.show(homePanel, "Teams");
    }

    // MODIFIES: this
    // EFFECTS: sets drivers panel for GUI
    public void setDriversPanel() {
        JPanel driversPanel = new JPanel();
        driversPanel.setBackground(PANEL_COLOUR);

        JPanel titlePanel = setTitlePanel("DRIVERS");

        JPanel driverButtonsPanel = new JPanel();
        driverButtonsPanel.setLayout(new GridLayout(0, 4));
        driverButtonsPanel.setPreferredSize(new Dimension(800, 500));
        driverButtonsPanel.setBackground(PANEL_COLOUR);
        for (Driver driver : league.getDrivers()) {
            JButton driverButton = new JButton(driver.getName());
            driverButtonsPanel.add(driverButton);
            driverButton.addActionListener(e -> cardLayout.show(homePanel, driver.getName()));

            setDriverPanel(driver);
        }

        JPanel quitPanel = setQuitPanel();

        driversPanel.add(titlePanel);
        driversPanel.add(driverButtonsPanel);
        driversPanel.add(quitPanel);

        homePanel.add(driversPanel, "Drivers");
    }

    // MODIFIES: this
    // EFFECTS: sets driver panel for given driver for GUI
    public void setDriverPanel(Driver driver) {
        JPanel driverPanel = new JPanel();
        driverPanel.setBackground(PANEL_COLOUR);

        JPanel titlePanel = setTitlePanel(driver.getName().toUpperCase());

        JPanel statsPanel = setStatsPanel(driver);

        JButton addPointsButton = new JButton("Add Points");
        addPointsButton.addActionListener(e -> addPoints(driver));
        JPanel addPointsPanel = setButtonPanel(addPointsButton);

        JButton removePointsButton = new JButton("Remove Points");
        removePointsButton.addActionListener(e -> removePoints(driver));
        JPanel removePointsPanel = setButtonPanel(removePointsButton);

        JButton changeDriverNumber = new JButton("Change Driver Number");
        changeDriverNumber.addActionListener(e -> changeDriverNumber(driver));
        JPanel changeDriverNumPanel = setButtonPanel(changeDriverNumber);

        JPanel quitPanel = setQuitPanel();

        driverPanel.add(titlePanel);
        driverPanel.add(statsPanel);
        driverPanel.add(addPointsPanel);
        driverPanel.add(removePointsPanel);
        driverPanel.add(changeDriverNumPanel);
        driverPanel.add(quitPanel);

        homePanel.add(driverPanel, driver.getName());
    }

    // EFFECTS: returns stats panel for given driver for GUI
    public JPanel setStatsPanel(Driver driver) {
        JPanel statsPanel = new JPanel();
        statsPanel.setPreferredSize(new Dimension(800, 300));
        statsPanel.setBackground(PANEL_COLOUR);
        statsPanel.setLayout(new GridLayout(0, 1));

        statsPanel.add(new JLabel("Driver Number: " + driver.getNum()));
        statsPanel.add(new JLabel("Points: " + driver.getPoints()));
        statsPanel.add(new JLabel("Wins: " + driver.getWins()));
        statsPanel.add(new JLabel("Fastest Laps: " + driver.getFastestLaps()));

        return statsPanel;
    }

    // EFFECTS: allows user to add points to given driver
    public void addPoints(Driver driver) {
        int points = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter number of points to add.", "Add Points", JOptionPane.QUESTION_MESSAGE));

        if (points < 0 || points > 1000) {
            JOptionPane.showMessageDialog(null,
                    "Invalid number of points entered.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            driver.addPoints(points);
        }

        setPanels();
        cardLayout.show(homePanel, driver.getName());
    }

    // EFFECTS: allows user to remove points from given driver
    public void removePoints(Driver driver) {
        int points = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter number of points to remove.", "Add Points", JOptionPane.QUESTION_MESSAGE));

        if (points < 0 || points > driver.getPoints()) {
            JOptionPane.showMessageDialog(null,
                    "Invalid number of points entered.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            driver.removePoints(points);
        }

        setPanels();
        cardLayout.show(homePanel, driver.getName());
    }

    // EFFECTS: allows user to change number of given driver
    public void changeDriverNumber(Driver driver) {
        int num = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter new driver number.", "Add Points", JOptionPane.QUESTION_MESSAGE));

        if (num < 0) {
            JOptionPane.showMessageDialog(null,
                    "Invalid number entered.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            driver.setNum(num);
        }

        setPanels();
        cardLayout.show(homePanel, driver.getName());
    }

    // EFFECTS: saves league to file
    private void saveLeague() {
        try {
            jsonWriter.open();
            jsonWriter.write(league);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved " + league.getName() + "!",
                    "Save League", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("images/save.png"));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        setPanels();
        cardLayout.show(homePanel, "League");
    }

    // MODIFIES: this
    // EFFECTS: loads league from file
    private void loadLeague() {
        try {
            league = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Loaded " + league.getName() + "!",
                    "Load League", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("images/load.png"));
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        setPanels();
        cardLayout.show(homePanel, "League");
    }

    // EFFECTS: quits Fantasy F1 GUI and prints event log
    private void quitLeague() {
        printLog(EventLog.getInstance());

        fantasyFrame.dispose();
    }

    // EFFECTS: prints event log
    private void printLog(EventLog el) {
        for (Event event : el) {
            System.out.println(event.toString());
        }
    }

    // EFFECTS: returns spacer panel with given dimension
    public JPanel setSpacerPanel(Dimension dimension) {
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(dimension);
        spacerPanel.setOpaque(false);

        return spacerPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets league panel for GUI
    public void setLeaguePanel() {
        JPanel leaguePanel = new JPanel();
        leaguePanel.setBackground(PANEL_COLOUR);
        setButtons();

        leaguePanel.add(setSpacerPanel(new Dimension(WIDTH, 40)));

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1000, 750));
        mainPanel.setBackground(null);

        JPanel mainTopPanel = setMainTopPanel();
        JPanel mainBottomPanel = setMainBottomPanel();
        mainPanel.add(mainTopPanel);
        mainPanel.add(mainBottomPanel);

        leaguePanel.add(mainPanel, BorderLayout.CENTER);

        homePanel.add(leaguePanel, "League");
    }

    // EFFECTS: returns main top panel for GUI
    public JPanel setMainTopPanel() {
        JPanel mainTopPanel = new JPanel();
        mainTopPanel.setPreferredSize(new Dimension(1000, 270));
        mainTopPanel.setBackground(null);

        JPanel mainTopLeftPanel = setMainTopLeftPanel();
        JPanel mainTopCentrePanel = setMainTopCentrePanel();
        JPanel mainTopRightPanel = setMainTopRightPanel();

        mainTopPanel.add(mainTopLeftPanel);
        mainTopPanel.add(mainTopCentrePanel);
        mainTopPanel.add(mainTopRightPanel);

        return mainTopPanel;
    }

    // EFFECTS: returns main top left panel for GUI
    public JPanel setMainTopLeftPanel() {
        JPanel mainTopLeftPanel = new JPanel();
        mainTopLeftPanel.setPreferredSize(new Dimension(200, 300));
        mainTopLeftPanel.setBackground(null);

        JPanel spacerPanel = setSpacerPanel(new Dimension(150, 10));

        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setOpaque(false);
        leaderboardPanel.setPreferredSize(new Dimension(150, 200));
        leaderboardPanel.add(new JLabel(new ImageIcon("images/scoreboard.png")));
        leaderboardPanel.add(leaderboardButton);

        mainTopLeftPanel.add(spacerPanel);
        mainTopLeftPanel.add(leaderboardPanel);

        return mainTopLeftPanel;
    }

    // EFFECTS: returns main top centre panel for GUI
    public JPanel setMainTopCentrePanel() {
        JPanel mainTopCentrePanel = new JPanel();
        mainTopCentrePanel.setPreferredSize(new Dimension(550, 300));
        mainTopCentrePanel.setBackground(null);

        JLabel title = new JLabel("MY FANTASY F1 LEAGUE");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        mainTopCentrePanel.add(title);

        JPanel spacer = setSpacerPanel(new Dimension(500, 20));
        mainTopCentrePanel.add(spacer);

        mainTopCentrePanel.add(new JLabel(new ImageIcon("images/logo.png")));

        return mainTopCentrePanel;
    }

    // EFFECTS: returns main top right panel for GUI
    public JPanel setMainTopRightPanel() {
        JPanel mainTopRightPanel = new JPanel();
        mainTopRightPanel.setPreferredSize(new Dimension(200, 300));
        mainTopRightPanel.setBackground(null);

        JPanel spacerPanel = setSpacerPanel(new Dimension(150, 10));
        mainTopRightPanel.add(spacerPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(150, 200));
        buttonPanel.add(new JLabel(new ImageIcon("images/winner.png")));
        buttonPanel.add(winnerButton);
        mainTopRightPanel.add(buttonPanel);

        return mainTopRightPanel;
    }

    // EFFECTS: returns main bottom panel for GUI
    public JPanel setMainBottomPanel() {
        JPanel mainBottomPanel = new JPanel();
        mainBottomPanel.setPreferredSize(new Dimension(WIDTH, 400));
        mainBottomPanel.setBackground(null);

        Dimension bottomBottomDimension = new Dimension(150, 50);

        JPanel mainBottomTopLeftPanel = setMainBottomTopPanel("images/races.png", racesButton);
        JPanel mainBottomTopCentrePanel = setMainBottomTopPanel("images/teams.png", teamsButton);
        JPanel mainBottomTopRightPanel = setMainBottomTopPanel("images/drivers.png", driversButton);

        JPanel mainBottomBottomLeftPanel = setMainBottomBottomPanel(saveButton);
        JPanel mainBottomBottomCentrePanel = setMainBottomBottomPanel(loadButton);
        JPanel mainBottomBottomRightPanel = setMainBottomBottomPanel(quitButton);

        mainBottomPanel.add(mainBottomTopLeftPanel);
        mainBottomPanel.add(mainBottomTopCentrePanel);
        mainBottomPanel.add(mainBottomTopRightPanel);
        mainBottomPanel.add(mainBottomBottomLeftPanel);
        mainBottomPanel.add(mainBottomBottomCentrePanel);
        mainBottomPanel.add(mainBottomBottomRightPanel);

        return mainBottomPanel;
    }

    // EFFECTS: returns main bottom top panel for GUI
    public JPanel setMainBottomTopPanel(String address, JButton button) {
        JPanel mainBottomTopPanel = new JPanel();
        mainBottomTopPanel.setPreferredSize(new Dimension(305, 220));
        mainBottomTopPanel.setBackground(null);
        mainBottomTopPanel.add(new JLabel(new ImageIcon(address)));
        mainBottomTopPanel.add(button);

        return mainBottomTopPanel;
    }

    // EFFECTS: returns main bottom bottom panel for GUI
    public JPanel setMainBottomBottomPanel(JButton button) {
        JPanel mainBottomBottomPanel = new JPanel();
        mainBottomBottomPanel.setPreferredSize(new Dimension(150, 50));
        mainBottomBottomPanel.setBackground(null);
        mainBottomBottomPanel.add(button);

        return mainBottomBottomPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets buttons for GUI
    public void setButtons() {
        createButtons();

        leaderboardButton.addActionListener(e -> cardLayout.show(homePanel, "Scoreboard"));
        winnerButton.addActionListener(e -> decideWinner());
        racesButton.addActionListener(e -> cardLayout.show(homePanel, "Races"));
        teamsButton.addActionListener(e -> cardLayout.show(homePanel, "Teams"));
        driversButton.addActionListener(e -> cardLayout.show(homePanel, "Drivers"));
        saveButton.addActionListener(e -> saveLeague());
        loadButton.addActionListener(e -> loadLeague());
        quitButton.addActionListener(e -> quitLeague());

        Dimension smallButtonDimension = new Dimension(140, 30);
        Dimension largeButtonDimension = new Dimension(280, 30);

        leaderboardButton.setPreferredSize(smallButtonDimension);
        winnerButton.setPreferredSize(smallButtonDimension);
        racesButton.setPreferredSize(largeButtonDimension);
        teamsButton.setPreferredSize(largeButtonDimension);
        driversButton.setPreferredSize(largeButtonDimension);
        saveButton.setPreferredSize(smallButtonDimension);
        loadButton.setPreferredSize(smallButtonDimension);
        quitButton.setPreferredSize(smallButtonDimension);
    }

    // MODIFIES: this
    // EFFECTS: creates buttons for GUI
    public void createButtons() {
        leaderboardButton = new JButton("Scoreboard");
        winnerButton = new JButton("Declare Winner");
        racesButton = new JButton("Races");
        teamsButton = new JButton("Teams");
        driversButton = new JButton("Drivers");
        saveButton = new JButton("Save League");
        loadButton = new JButton("Load League");
        quitButton = new JButton("Quit");
    }

    // EFFECTS: returns panel with given title for GUI
    public JPanel setTitlePanel(String name) {
        JLabel title = new JLabel(name);
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(800, 40));
        titlePanel.setBackground(PANEL_COLOUR);
        titlePanel.add(title);

        return titlePanel;
    }

    // EFFECTS: returns panel for given info for GUI
    public JPanel setInfoPanel(String infoString) {
        JLabel info = new JLabel(infoString);
        info.setFont(new Font("SansSerif", Font.PLAIN, 15));
        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(800, 30));
        infoPanel.setBackground(PANEL_COLOUR);
        infoPanel.add(info);

        return infoPanel;
    }

    // EFFECTS: returns panel with given button for GUI
    public JPanel setButtonPanel(JButton button) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(800, 50));
        buttonPanel.setBackground(PANEL_COLOUR);
        buttonPanel.add(button);

        return buttonPanel;
    }

    // EFFECTS: returns panel with quit button for GUI
    public JPanel setQuitPanel() {
        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));
        JPanel quitPanel = new JPanel();
        quitPanel.setPreferredSize(new Dimension(800, 40));
        quitPanel.setBackground(PANEL_COLOUR);
        quitPanel.add(quitButton);

        return quitPanel;
    }

    // MODIFIES: this
    // EFFECTS: initializes drivers that have driven in F1 in 2022
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
        Driver nhulkenberg = new Driver("Nico Hulkenberg", 27);
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

    // MODIFIES: this
    // EFFECTS: initializes teams for demonstrative purposes
    private void initializeTeams() {
        Team teamKimi = new Team("Kimi's Team");
        teamKimi.addDriver(mverstappen);
        teamKimi.addDriver(lhamilton);
        teamKimi.addDriver(lnorris);

        Team teamMichael = new Team("Michael's Team");
        teamMichael.addDriver(cleclerc);
        teamMichael.addDriver(grussell);
        teamMichael.addDriver(sperez);

        league.addTeam(teamKimi);
        league.addTeam(teamMichael);
    }

    // EFFECTS: runs Fantasy F1 graphic user interface
    public static void main(String[] args) {
        new FantasyGUI();
    }
}
