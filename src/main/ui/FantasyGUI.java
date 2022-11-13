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
import java.util.concurrent.atomic.AtomicBoolean;

public class FantasyGUI {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final Color PANEL_COLOUR = new Color(181, 234, 255);
    private final JFrame fantasyFrame;
    private JPanel homePanel;
    private CardLayout cardLayout;
    private JPanel leaguePanel;

    private JButton scoreboardButton;
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
    private Driver nhulkenberg;
    private Driver nlatifi;
    private Driver pgasly;
    private Driver sperez;
    private Driver svettel;
    private Driver vbottas;
    private Driver ytsunoda;

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

    public void setPanels() {
        setScoreboardPanel();
        setRacesPanel();
        setTeamsPanel();
        setDriversPanel();
        setLeaguePanel();
    }

    public void setScoreboardPanel() {
        JPanel scoreboardPanel = new JPanel();
        scoreboardPanel.setBackground(PANEL_COLOUR);

        JScrollPane scrollPane = new JScrollPane(setScoreBoard());
        scrollPane.setForeground(PANEL_COLOUR);

        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(WIDTH, 30));
        spacer.setOpaque(false);

        JLabel title = new JLabel("LEADERBOARD");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(0,1));
        tablePanel.setPreferredSize(new Dimension(800, 400));
        tablePanel.setBackground(new Color(0, 0, 0, 0));
        tablePanel.add(scrollPane);

        JPanel quitPanel = new JPanel();
        quitPanel.setPreferredSize(new Dimension(800,50));
        quitPanel.setBackground(PANEL_COLOUR);
        quitPanel.add(quitButton);

        scoreboardPanel.add(spacer);
        scoreboardPanel.add(title);
        scoreboardPanel.add(tablePanel);
        scoreboardPanel.add(quitPanel);

        homePanel.add(scoreboardPanel, "Scoreboard");
    }

    public JTable setScoreBoard() {
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
        scoreBoard.setBackground(new Color(255, 255, 255, 255));
        scoreBoard.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scoreBoard.setRowHeight(22);

        return scoreBoard;
    }

    // EFFECTS: returns winner based on team with most points, then most wins
    private void decideWinner() {
        Team winner = new Team("dummy");
        int p = 0;
        int w = 0;
        int l = 0;
        for (Team team : league.getTeams()) {
            if (team.getPoints() > p) {
                p = team.getPoints();
                winner = team;
            } else if (team.getPoints() == p) {
                if (team.getWins() > w) {
                    w = team.getWins();
                    winner = team;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "The champion is " + winner.getName() + "!", league.getName() + " Winner",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/trophy.jpg"));

        setPanels();
        cardLayout.show(homePanel, "League");
    }

    public void setRacesPanel() {
        JPanel racesPanel = new JPanel();
        racesPanel.setBackground(PANEL_COLOUR);
        racesPanel.setSize(new Dimension(0, 0));

        JPanel raceButtonsPanel = new JPanel();
        raceButtonsPanel.setLayout(new GridLayout(0,4));
        raceButtonsPanel.setPreferredSize(new Dimension(800,500));
        raceButtonsPanel.setBackground(PANEL_COLOUR);

        for (Race race : league.getRaces()) {
            JButton raceButton = new JButton(race.getName());
            raceButtonsPanel.add(raceButton);
            setRacePanel(race);
            raceButton.addActionListener(e -> cardLayout.show(homePanel, race.getName()));
        }

        JLabel title = new JLabel("RACES");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));

        JButton addRaceButton = new JButton("Add Race");
        addRaceButton.addActionListener(e -> addRace());

        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(800,40));
        titlePanel.setBackground(PANEL_COLOUR);
        titlePanel.add(title);

        JPanel racePanel = new JPanel();
        racePanel.setPreferredSize(new Dimension(800,40));
        racePanel.setBackground(PANEL_COLOUR);
        racePanel.add(addRaceButton);

        JPanel quitPanel = new JPanel();
        quitPanel.setPreferredSize(new Dimension(800,40));
        quitPanel.setBackground(PANEL_COLOUR);
        quitPanel.add(quitButton);

        racesPanel.add(titlePanel);
        racesPanel.add(raceButtonsPanel);
        racesPanel.add(racePanel);
        racesPanel.add(quitPanel);

        homePanel.add(racesPanel, "Races");
    }

    public void setRacePanel(Race race) {
        JPanel racePanel = new JPanel();
        racePanel.setBackground(PANEL_COLOUR);

        JPanel placesPanel = new JPanel();
        placesPanel.setPreferredSize(new Dimension(800,500));
        placesPanel.setBackground(PANEL_COLOUR);
        placesPanel.setLayout(new GridLayout(0,1));

        int i = 1;
        for (Driver driver : race.getPlaces()) {
            JLabel placeLabel = new JLabel(i + ". " + driver.getName(), JLabel.CENTER);
            placesPanel.add(placeLabel);
            i++;
        }

        JLabel title = new JLabel(race.getName().toUpperCase());
        title.setFont(new Font("SansSerif", Font.BOLD, 25));

        JLabel date = new JLabel(race.getDate().toUpperCase());
        date.setFont(new Font("SansSerif", Font.PLAIN, 15));

        JButton quitButton = new JButton("Return to Races Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "Races"));

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(800,50));
        titlePanel.setBackground(PANEL_COLOUR);
        titlePanel.add(title);

        JPanel datePanel = new JPanel();
        datePanel.setPreferredSize(new Dimension(800,30));
        datePanel.setBackground(PANEL_COLOUR);
        datePanel.add(date);

        JPanel quitPanel = new JPanel();
        quitPanel.setPreferredSize(new Dimension(800,50));
        quitPanel.setBackground(PANEL_COLOUR);
        quitPanel.add(quitButton);

        racePanel.add(titlePanel);
        racePanel.add(datePanel);
        racePanel.add(placesPanel);
        racePanel.add(quitPanel);

        homePanel.add(racePanel, race.getName());
    }

    public void addRace() {
        JPanel raceMenu = new JPanel();
        raceMenu.setLayout(new GridLayout(0, 1));
        AtomicBoolean isSprint = new AtomicBoolean(false);

        ButtonGroup raceButtons = new ButtonGroup();
        JRadioButton sprintButton = new JRadioButton("Add Sprint Race");
        JRadioButton grandPrixButton = new JRadioButton("Add Grand Prix");
        sprintButton.addActionListener(e -> isSprint.set(true));
        grandPrixButton.addActionListener(e -> isSprint.set(false));
        raceButtons.add(sprintButton);
        raceButtons.add(grandPrixButton);

        raceMenu.add(sprintButton);
        raceMenu.add(grandPrixButton);

        JButton enter = new JButton("Enter");
        enter.addActionListener(e -> {
            if (isSprint.get()) {
                addSprintRace();
            } else {
                addGrandPrix();
            }
        });
        raceMenu.add(enter);

        homePanel.add(raceMenu, "AddRace");
        cardLayout.show(homePanel, "AddRace");
    }

    public void addSprintRace() {
        String raceName = JOptionPane.showInputDialog(null,
                "Race name?",
                "Enter Race Name",
                JOptionPane.QUESTION_MESSAGE);
        String raceDate = JOptionPane.showInputDialog(null,
                "Race date?",
                "Enter Race Date",
                JOptionPane.QUESTION_MESSAGE);

        List<String> allDrivers = new ArrayList<>();
        for (Driver driver : league.getDrivers()) {
            allDrivers.add(driver.getName());
        }

        String[] drivers = allDrivers.toArray(new String[0]);
        List<Driver> places = setPlaces(drivers, 8);

        Race newRace = new Sprint(raceName, raceDate, places);
        league.addRace(newRace);

        setPanels();
        cardLayout.show(homePanel, newRace.getName());
    }

    public List<Driver> setPlaces(String[] drivers, int n) {
        List<Driver> places = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            Driver place = null;

            Object name = JOptionPane.showInputDialog(null,
                    "Which driver came in " + i + " place?",
                    "Select Place",
                    JOptionPane.QUESTION_MESSAGE,
                    null, drivers, null);

            for (Driver driver : league.getDrivers()) {
                if (driver.getName().equals(name)) {
                    place = driver;
                }
            }

            places.add(place);
        }

        return places;
    }

    public void addGrandPrix() {
        String raceName = JOptionPane.showInputDialog(null,
                "Race name?",
                "Enter Race Name",
                JOptionPane.QUESTION_MESSAGE);
        String raceDate = JOptionPane.showInputDialog(null,
                "Race date?",
                "Enter Race Date",
                JOptionPane.QUESTION_MESSAGE);

        List<String> allDrivers = new ArrayList<>();
        for (Driver driver : league.getDrivers()) {
            allDrivers.add(driver.getName());
        }

        String[] drivers = allDrivers.toArray(new String[0]);
        List<Driver> places = setPlaces(drivers, 10);
        Driver fastestLap = setFastestLap(drivers);

        Race newRace = new GrandPrix(raceName, raceDate, places, fastestLap);
        league.addRace(newRace);

        setPanels();
        cardLayout.show(homePanel, newRace.getName());
    }

    public Driver setFastestLap(String[] drivers) {
        Driver fastestLap = null;
        Object fastestLapName = JOptionPane.showInputDialog(null,
                "Which driver had the fastest lap?",
                "Fastest Lap",
                JOptionPane.QUESTION_MESSAGE,
                null, drivers, null);

        for (Driver driver : league.getDrivers()) {
            if (driver.getName().equals(fastestLapName)) {
                fastestLap = driver;
            }
        }

        return  fastestLap;
    }

    public void setTeamsPanel() {
        JPanel teamsPanel = new JPanel();
        teamsPanel.setBackground(PANEL_COLOUR);
        teamsPanel.setSize(new Dimension(0, 0));

        JPanel teamButtonsPanel = new JPanel();
        teamButtonsPanel.setLayout(new GridLayout(0,2));
        teamButtonsPanel.setPreferredSize(new Dimension(800,400));
        teamButtonsPanel.setBackground(PANEL_COLOUR);

        for (Team team : league.getTeams()) {
            JButton teamButton = new JButton(team.getName());
            teamButtonsPanel.add(teamButton);
            setTeamPanel(team);
            teamButton.addActionListener(e -> cardLayout.show(homePanel, team.getName()));
        }

        JLabel title = new JLabel("TEAMS");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));

        JButton addTeamButton = new JButton("Add Team");
        addTeamButton.addActionListener(e -> addTeam());

        JButton removeTeamButton = new JButton("Remove Team");
        removeTeamButton.addActionListener(e -> removeTeam());

        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(800,40));
        titlePanel.setBackground(PANEL_COLOUR);
        titlePanel.add(title);

        JPanel addTeamPanel = new JPanel();
        addTeamPanel.setPreferredSize(new Dimension(800,40));
        addTeamPanel.setBackground(PANEL_COLOUR);
        addTeamPanel.add(addTeamButton);

        JPanel removeTeamPanel = new JPanel();
        removeTeamPanel.setPreferredSize(new Dimension(800,40));
        removeTeamPanel.setBackground(PANEL_COLOUR);
        removeTeamPanel.add(removeTeamButton);

        JPanel quitPanel = new JPanel();
        quitPanel.setPreferredSize(new Dimension(800,40));
        quitPanel.setBackground(PANEL_COLOUR);
        quitPanel.add(quitButton);

        teamsPanel.add(titlePanel);
        teamsPanel.add(teamButtonsPanel);
        teamsPanel.add(addTeamPanel);
        teamsPanel.add(removeTeamPanel);
        teamsPanel.add(quitPanel);

        homePanel.add(teamsPanel, "Teams");
    }

    public void setTeamPanel(Team team) { // TODO make a line graph of performance, pie chart of points?
        JPanel teamPanel = new JPanel();
        teamPanel.setBackground(PANEL_COLOUR);

        JPanel driversPanel = new JPanel();
        driversPanel.setPreferredSize(new Dimension(800,400));
        driversPanel.setBackground(PANEL_COLOUR);
        driversPanel.setLayout(new GridLayout(0,1));

        for (Driver driver : team.getDrivers()) {
            JLabel placeLabel = new JLabel(driver.getName(), JLabel.CENTER);
            driversPanel.add(placeLabel);
        }

        JLabel title = new JLabel(team.getName().toUpperCase());
        title.setFont(new Font("SansSerif", Font.BOLD, 25));

        JButton addDriverButton = new JButton("Add Driver");
        addDriverButton.addActionListener(e -> addDriver(team));

        JButton removeDriverButton = new JButton("Remove Driver");
        removeDriverButton.addActionListener(e -> removeDriver(team));

        JButton quitButton = new JButton("Return to Teams Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "Teams"));

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(800,50));
        titlePanel.setBackground(PANEL_COLOUR);
        titlePanel.add(title);

        JPanel addDriverPanel = new JPanel();
        addDriverPanel.setPreferredSize(new Dimension(800,50));
        addDriverPanel.setBackground(PANEL_COLOUR);
        addDriverPanel.add(addDriverButton);

        JPanel removeDriverPanel = new JPanel();
        removeDriverPanel.setPreferredSize(new Dimension(800,50));
        removeDriverPanel.setBackground(PANEL_COLOUR);
        removeDriverPanel.add(removeDriverButton);

        JPanel quitPanel = new JPanel();
        quitPanel.setPreferredSize(new Dimension(800,50));
        quitPanel.setBackground(PANEL_COLOUR);
        quitPanel.add(quitButton);

        teamPanel.add(titlePanel);
        teamPanel.add(driversPanel);
        teamPanel.add(addDriverPanel);
        teamPanel.add(removeDriverPanel);
        teamPanel.add(quitPanel);

        homePanel.add(teamPanel, team.getName());
    }

    public void pointsBreakdown(Team team) {
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
                    "Which driver would you like to add?", "Add Driver",
                    JOptionPane.QUESTION_MESSAGE,
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

    public void removeDriver(Team team) {
        if (team.getDrivers().size() == 0) {
            JOptionPane.showMessageDialog(null, "There are no drivers on your team to remove.", "System Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            List<String> available = new ArrayList<>();
            for (Driver driver : team.getDrivers()) {
                available.add(driver.getName());
            }

            String[] availableDrivers = available.toArray(new String[0]);

            Object driverName = JOptionPane.showInputDialog(null,
                    "Which driver would you like to remove?",
                    "Remove Driver",
                    JOptionPane.QUESTION_MESSAGE,
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

    public void addTeam() {
        String teamName = JOptionPane.showInputDialog(null,
                "Team name?",
                "Create New Team",
                JOptionPane.QUESTION_MESSAGE);
        Team newTeam = new Team(teamName);
        league.addTeam(newTeam);

        setPanels();
        cardLayout.show(homePanel, newTeam.getName());
    }

    public void removeTeam() {
        List<String> available = new ArrayList<>();
        for (Team team : league.getTeams()) {
            available.add(team.getName());
        }

        String[] availableTeams = available.toArray(new String[0]);

        String teamName = String.valueOf(JOptionPane.showInputDialog(null,
                "Which team would you like to remove?",
                "Remove Team",
                JOptionPane.QUESTION_MESSAGE, null, availableTeams, null));

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

    public void setDriversPanel() {
        JPanel driversPanel = new JPanel();
        driversPanel.setBackground(PANEL_COLOUR);
        driversPanel.setSize(new Dimension(0, 0));

        JPanel driverButtonsPanel = new JPanel();
        driverButtonsPanel.setLayout(new GridLayout(0,4));
        driverButtonsPanel.setPreferredSize(new Dimension(800,500));
        driverButtonsPanel.setBackground(PANEL_COLOUR);

        for (Driver driver : league.getDrivers()) {
            JButton driverButton = new JButton(driver.getName());
            driverButtonsPanel.add(driverButton);
            driverButton.addActionListener(e -> cardLayout.show(homePanel, driver.getName()));

            setDriverPanel(driver);
        }

        JLabel title = new JLabel("DRIVERS");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));

        JButton quitButton = new JButton("Return to Main Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "League"));

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(800,40));
        titlePanel.setBackground(PANEL_COLOUR);
        titlePanel.add(title);

        JPanel quitPanel = new JPanel();
        quitPanel.setPreferredSize(new Dimension(800,40));
        quitPanel.setBackground(PANEL_COLOUR);
        quitPanel.add(quitButton);

        driversPanel.add(titlePanel);
        driversPanel.add(driverButtonsPanel);
        driversPanel.add(quitPanel);

        homePanel.add(driversPanel, "Drivers");
    }

    public void setDriverPanel(Driver driver) {
        JPanel driverPanel = new JPanel();
        driverPanel.setBackground(PANEL_COLOUR);

        JPanel statsPanel = new JPanel();
        statsPanel.setPreferredSize(new Dimension(800,300));
        statsPanel.setBackground(PANEL_COLOUR);
        statsPanel.setLayout(new GridLayout(0,1));

        statsPanel.add(new JLabel("Driver Number: " + driver.getNum()));
        statsPanel.add(new JLabel("Points: " + driver.getPoints()));
        statsPanel.add(new JLabel("Wins: " + driver.getWins()));
        statsPanel.add(new JLabel("Fastest Laps: " + driver.getFastestLaps()));

        JLabel title = new JLabel(driver.getName().toUpperCase());
        title.setFont(new Font("SansSerif", Font.BOLD, 25));

        JButton addPointsButton = new JButton("Add Points");
        addPointsButton.addActionListener(e -> addPoints(driver));

        JButton removePointsButton = new JButton("Remove Points");
        removePointsButton.addActionListener(e -> removePoints(driver));

        JButton changeDriverNumber = new JButton("Change Driver Number");
        changeDriverNumber.addActionListener(e -> changeDriverNumber(driver));

        JButton quitButton = new JButton("Return to Drivers Menu");
        quitButton.addActionListener(e -> cardLayout.show(homePanel, "Drivers"));

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(800,50));
        titlePanel.setBackground(PANEL_COLOUR);
        titlePanel.add(title);

        JPanel addPointsPanel = new JPanel();
        addPointsPanel.setPreferredSize(new Dimension(800,50));
        addPointsPanel.setBackground(PANEL_COLOUR);
        addPointsPanel.add(addPointsButton);

        JPanel removePointsPanel = new JPanel();
        removePointsPanel.setPreferredSize(new Dimension(800,50));
        removePointsPanel.setBackground(PANEL_COLOUR);
        removePointsPanel.add(removePointsButton);

        JPanel changeDriverNumPanel = new JPanel();
        changeDriverNumPanel.setPreferredSize(new Dimension(800,50));
        changeDriverNumPanel.setBackground(PANEL_COLOUR);
        changeDriverNumPanel.add(changeDriverNumber);

        JPanel quitPanel = new JPanel();
        quitPanel.setPreferredSize(new Dimension(800,50));
        quitPanel.setBackground(PANEL_COLOUR);
        quitPanel.add(quitButton);

        driverPanel.add(titlePanel);
        driverPanel.add(statsPanel);
        driverPanel.add(addPointsPanel);
        driverPanel.add(removePointsPanel);
        driverPanel.add(changeDriverNumPanel);
        driverPanel.add(quitPanel);

        homePanel.add(driverPanel, driver.getName());
    }

    public void addPoints(Driver driver) {
        int points = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter number of points to add.",
                "Add Points",
                JOptionPane.QUESTION_MESSAGE));

        driver.addPoints(points);

        setPanels();
        cardLayout.show(homePanel, driver.getName());
    }

    public void removePoints(Driver driver) {
        int points = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter number of points to remove.",
                "Add Points",
                JOptionPane.QUESTION_MESSAGE));

        driver.removePoints(points);

        setPanels();
        cardLayout.show(homePanel, driver.getName());
    }

    public void changeDriverNumber(Driver driver) {
        int num = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter new driver number.",
                "Add Points",
                JOptionPane.QUESTION_MESSAGE));

        driver.setNum(num);

        setPanels();
        cardLayout.show(homePanel, driver.getName());
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
        leaguePanel = new JPanel();
        leaguePanel.setBackground(PANEL_COLOUR);
        setButtons();

        setLeagueTopPanel();
        setLeagueCentrePanel();

        homePanel.add(leaguePanel, "League");
    }

    public void setLeagueTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(WIDTH, 40));
        topPanel.setOpaque(false);
        leaguePanel.add(topPanel);
    }

    public void setLeagueCentrePanel() {
        JPanel centrePanel = new JPanel();
        centrePanel.setPreferredSize(new Dimension(1000,750));
        centrePanel.setBackground(new Color(0, 0, 0, 0));
        setLeagueCentreTopPanel(centrePanel);
        setLeagueCentreBottomPanel(centrePanel);
        leaguePanel.add(centrePanel, BorderLayout.CENTER);
    }

    public void setLeagueCentreTopPanel(JPanel centrePanel) {
        JPanel centreTopPanel = new JPanel();
        centreTopPanel.setPreferredSize(new Dimension(1000,270));
        centreTopPanel.setLayout(new FlowLayout());
        centreTopPanel.setBackground(new Color(0, 0, 0, 0));

        setCentreTopLeftPanel(centreTopPanel);
        setCentreTopCentrePanel(centreTopPanel);
        setCentreTopRightPanel(centreTopPanel);
        centrePanel.add(centreTopPanel);
    }

    public void setCentreTopLeftPanel(JPanel centreTopPanel) {
        JPanel centreTopLeftPanel = new JPanel();
        centreTopLeftPanel.setPreferredSize(new Dimension(200,300));
        centreTopLeftPanel.setBackground(new Color(0, 0, 0, 0));
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(150,10));
        spacerPanel.setOpaque(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(150,200));
        buttonPanel.add(new JLabel(new ImageIcon("images/scoreboard.png")));
        buttonPanel.add(scoreboardButton);
        centreTopLeftPanel.add(spacerPanel);
        centreTopLeftPanel.add(buttonPanel);

        centreTopPanel.add(centreTopLeftPanel);
    }

    public void setCentreTopCentrePanel(JPanel centreTopPanel) {
        JPanel centreTopCentrePanel = new JPanel();
        centreTopCentrePanel.setPreferredSize(new Dimension(550,300));
        centreTopCentrePanel.setBackground(new Color(0, 0, 0, 0));

        JLabel title = new JLabel("MY FANTASY F1 LEAGUE");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        centreTopCentrePanel.add(title);
        JLabel spacer = new JLabel();
        spacer.setPreferredSize(new Dimension(500,20));
        centreTopCentrePanel.add(spacer);
        centreTopCentrePanel.add(new JLabel(new ImageIcon("images/logo.png")));

        centreTopPanel.add(centreTopCentrePanel);
    }

    public void setCentreTopRightPanel(JPanel centreTopPanel) {
        JPanel centreTopRightPanel = new JPanel();
        centreTopRightPanel.setPreferredSize(new Dimension(200,300));
        centreTopRightPanel.setBackground(new Color(0, 0, 0, 0));
        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(150,10));
        spacerPanel.setOpaque(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(150,200));
        buttonPanel.add(new JLabel(new ImageIcon("images/winner.png")));
        buttonPanel.add(winnerButton);
        centreTopRightPanel.add(spacerPanel);
        centreTopRightPanel.add(buttonPanel);

        centreTopPanel.add(centreTopRightPanel);
    }

    public void setLeagueCentreBottomPanel(JPanel centrePanel) {
        JPanel centreBottomPanel = new JPanel();
        centreBottomPanel.setPreferredSize(new Dimension(1000,400));
        centreBottomPanel.setBackground(new Color(0, 0, 0, 0));
        Dimension bottomTopDimension = new Dimension(290,220);
        Dimension bottomBottomDimension = new Dimension(150,50);
        Dimension spacerDimension = new Dimension(10,220);

        JPanel spacer1 = new JPanel();
        spacer1.setPreferredSize(spacerDimension);
        spacer1.setOpaque(false);
        JPanel spacer2 = new JPanel();
        spacer2.setPreferredSize(spacerDimension);
        spacer2.setOpaque(false);

        setCentreBottomTopLeft(centreBottomPanel, bottomTopDimension);
        centreBottomPanel.add(spacer1);
        setCentreBottomTopCentre(centreBottomPanel, bottomTopDimension);
        centreBottomPanel.add(spacer2);
        setCentreBottomTopRight(centreBottomPanel, bottomTopDimension);
        setCentreBottomBottomLeft(centreBottomPanel, bottomBottomDimension);
        setCentreBottomBottomCentre(centreBottomPanel, bottomBottomDimension);
        setCentreBottomBottomRight(centreBottomPanel, bottomBottomDimension);

        centrePanel.add(centreBottomPanel);
    }

    public void setCentreBottomTopLeft(JPanel centreBottomPanel, Dimension bottomTopDimension) {
        JPanel centreBottomTopLeft = new JPanel();
        centreBottomTopLeft.setPreferredSize(bottomTopDimension);
        centreBottomTopLeft.setBackground(new Color(0, 0, 0, 0));
        centreBottomTopLeft.add(new JLabel(new ImageIcon("images/races.png")));
        centreBottomTopLeft.add(racesButton);

        centreBottomPanel.add(centreBottomTopLeft);
    }

    public void setCentreBottomTopCentre(JPanel centreBottomPanel, Dimension bottomTopDimension) {
        JPanel centreBottomTopLeft = new JPanel();
        centreBottomTopLeft.setPreferredSize(bottomTopDimension);
        centreBottomTopLeft.setBackground(new Color(0, 0, 0, 0));
        centreBottomTopLeft.add(new JLabel(new ImageIcon("images/teams.png")));
        centreBottomTopLeft.add(teamsButton);

        centreBottomPanel.add(centreBottomTopLeft);
    }

    public void setCentreBottomTopRight(JPanel centreBottomPanel, Dimension bottomTopDimension) {
        JPanel centreBottomTopLeft = new JPanel();
        centreBottomTopLeft.setPreferredSize(bottomTopDimension);
        centreBottomTopLeft.setBackground(new Color(0, 0, 0, 0));
        centreBottomTopLeft.add(new JLabel(new ImageIcon("images/drivers.png")));
        centreBottomTopLeft.add(driversButton);

        centreBottomPanel.add(centreBottomTopLeft);
    }

    public void setCentreBottomBottomLeft(JPanel centreBottomPanel, Dimension bottomButtomDimension) {
        JPanel centreBottomTopLeft = new JPanel();
        centreBottomTopLeft.setPreferredSize(bottomButtomDimension);
        centreBottomTopLeft.setBackground(new Color(0, 0, 0, 0));
        centreBottomTopLeft.add(saveButton);

        centreBottomPanel.add(centreBottomTopLeft);
    }

    public void setCentreBottomBottomCentre(JPanel centreBottomPanel, Dimension bottomButtomDimension) {
        JPanel centreBottomTopLeft = new JPanel();
        centreBottomTopLeft.setPreferredSize(bottomButtomDimension);
        centreBottomTopLeft.setBackground(new Color(0, 0, 0, 0));
        centreBottomTopLeft.add(loadButton);

        centreBottomPanel.add(centreBottomTopLeft);
    }

    public void setCentreBottomBottomRight(JPanel centreBottomPanel, Dimension bottomButtomDimension) {
        JPanel centreBottomTopLeft = new JPanel();
        centreBottomTopLeft.setPreferredSize(bottomButtomDimension);
        centreBottomTopLeft.setBackground(new Color(0, 0, 0, 0));
        centreBottomTopLeft.add(quitButton);

        centreBottomPanel.add(centreBottomTopLeft);
    }

    public void setButtons() {
        createButtons();

        scoreboardButton.addActionListener(e -> cardLayout.show(homePanel, "Scoreboard"));
        winnerButton.addActionListener(e -> decideWinner());
        racesButton.addActionListener(e -> cardLayout.show(homePanel, "Races"));
        teamsButton.addActionListener(e -> cardLayout.show(homePanel, "Teams"));
        driversButton.addActionListener(e -> cardLayout.show(homePanel, "Drivers"));
        saveButton.addActionListener(e -> saveLeague());
        loadButton.addActionListener(e -> loadLeague());
        quitButton.addActionListener(e -> fantasyFrame.dispose());

        Dimension buttonDimension = new Dimension(140,30);
        Dimension otherButtonDimension = new Dimension(280,30);
        scoreboardButton.setPreferredSize(buttonDimension);
        winnerButton.setPreferredSize(buttonDimension);
        racesButton.setPreferredSize(otherButtonDimension);
        teamsButton.setPreferredSize(otherButtonDimension);
        driversButton.setPreferredSize(otherButtonDimension);
        saveButton.setPreferredSize(buttonDimension);
        loadButton.setPreferredSize(buttonDimension);
        quitButton.setPreferredSize(buttonDimension);
    }

    public void createButtons() {
        scoreboardButton = new JButton("Scoreboard");
        winnerButton = new JButton("Declare Winner");
        racesButton = new JButton("Races");
        teamsButton = new JButton("Teams");
        driversButton = new JButton("Drivers");
        saveButton = new JButton("Save League");
        loadButton = new JButton("Load League");
        quitButton = new JButton("Quit");
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
        new FantasyGUI();
    }
}
