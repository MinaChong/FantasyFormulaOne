//package ui;
//
//import model.Driver;
//import model.League;
//import model.Team;
//
//import javax.swing.*;
//import java.awt.*;
//
//// NOTE: lends structure from AlarmSystem - AlarmControllerUI.java
//public class dum extends JFrame {
//    private static final int WIDTH = 900;
//    private static final int HEIGHT = 900;
//    private JPanel leaguePanel;
//    private JPanel leagueArea;
//    private JPanel teamsArea;
//    private JPanel teamArea;
//    private JPanel addTeamDriverArea;
//    private CardLayout cardLayout;
//
//    private League league;
//    private Driver aalbon;
//    private Driver cleclerc;
//    private Driver csainz;
//    private Driver dricciardo;
//    private Driver eocon;
//    private Driver falonso;
//    private Driver grussell;
//    private Driver gzhou;
//    private Driver kmagnussen;
//    private Driver lhamilton;
//    private Driver lnorris;
//    private Driver lstroll;
//    private Driver mschumacher;
//    private Driver mverstappen;
//    private Driver ndevries;
//    private Driver nhulkenberg;
//    private Driver nlatifi;
//    private Driver pgasly;
//    private Driver sperez;
//    private Driver svettel;
//    private Driver vbottas;
//    private Driver ytsunoda;
//
//    public dum() {
//        super("Fantasy F1 UI");
//        setBackground(Color.blue);
//
////        cardLayout = new CardLayout();
////        leaguePanel = new JPanel(cardLayout);
////        fantasyFrame.add(leaguePanel);
////
////        leagueArea = new JPanel();
////        JButton scoreboardButton = new JButton("Scoreboard");
////        leagueArea.add(scoreboardButton);
////
//////        setLeagueArea();
////        leaguePanel.add(leagueArea, "league");
//////        setTeamsPanel();
//////        setTeamPanel();
//////        setAddTeamDriverPanel();
////
////        cardLayout.show(leaguePanel, "league");
//        setLayout(new BorderLayout());
//        setMinimumSize(new Dimension(WIDTH, HEIGHT));
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        pack();
//        setVisible(true);
//    }
//
//    private void setLeagueArea() {
//        leagueArea = new JPanel();
//
////        leagueArea.setLayout(new GridLayout(0,3));
////        leagueArea.setSize(new Dimension(0, 0));
//
//        JButton scoreboardButton = new JButton("Scoreboard");
//        JButton carButton = new JButton(new ImageIcon("images/car.jpg"));
//        JButton winnerButton = new JButton("Declare Winner");
//        JButton racesButton = new JButton("Races");
//        JButton teamsButton = new JButton("Teams");
//        JButton driversButton = new JButton("Drivers");
//        JButton saveButton = new JButton("Save League");
//        JButton loadButton = new JButton("Load League");
//        JButton quitButton = new JButton("Quit");
//        leagueArea.add(scoreboardButton);
//        leagueArea.add(carButton);
//        leagueArea.add(winnerButton);
//        leagueArea.add(racesButton);
//        leagueArea.add(teamsButton);
//        leagueArea.add(driversButton);
//        leagueArea.add(saveButton);
//        leagueArea.add(loadButton);
//        leagueArea.add(quitButton);
//
//        scoreboardButton.addActionListener(e -> cardLayout.show(homePanel, "Other Panel"));
//
//
//    }
//
//    private void initializeDrivers() {
//        aalbon = new Driver("Alex Albon", 23);
//        cleclerc = new Driver("Charles Leclerc", 16);
//        csainz = new Driver("Carlos Sainz", 55);
//        dricciardo = new Driver("Daniel Ricciardo", 3);
//        eocon = new Driver("Esteban Ocon", 31);
//        falonso = new Driver("Fernando Alonso", 14);
//        grussell = new Driver("George Russell", 63);
//        gzhou = new Driver("Guanyu Zhou", 24);
//        kmagnussen = new Driver("Kevin Magnussen", 20);
//        lhamilton = new Driver("Lewis Hamilton", 44);
//        lnorris = new Driver("Lando Norris", 4);
//        lstroll = new Driver("Lance Stroll", 18);
//        mschumacher = new Driver("Mick Schumacher", 47);
//        mverstappen = new Driver("Max Verstappen", 1);
//        ndevries = new Driver("Nyck de Vries", 17);
//        nhulkenberg = new Driver("Nico Hulkenberg", 27);
//        nlatifi = new Driver("Nicholas Latifi", 6);
//        pgasly = new Driver("Pierre Gasly", 10);
//        sperez = new Driver("Sergio Perez", 11);
//        svettel = new Driver("Sebastian Vettel", 5);
//        vbottas = new Driver("Valtteri Bottas", 77);
//        ytsunoda = new Driver("Yuki Tsunoda", 22);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes current grid of 20 drivers
//    private void initializeGrid() {
//        league.addDriver(aalbon);
//        league.addDriver(cleclerc);
//        league.addDriver(csainz);
//        league.addDriver(dricciardo);
//        league.addDriver(eocon);
//        league.addDriver(falonso);
//        league.addDriver(grussell);
//        league.addDriver(gzhou);
//        league.addDriver(kmagnussen);
//        league.addDriver(lhamilton);
//        league.addDriver(lnorris);
//        league.addDriver(lstroll);
//        league.addDriver(mschumacher);
//        league.addDriver(mverstappen);
//        league.addDriver(nlatifi);
//        league.addDriver(pgasly);
//        league.addDriver(sperez);
//        league.addDriver(svettel);
//        league.addDriver(vbottas);
//        league.addDriver(ytsunoda);
//    }
//
//    private void initializeTeams() {
//        Team teamKimi = new Team("Kimi's Team");
//        teamKimi.addDriver(mverstappen);
//        teamKimi.addDriver(lhamilton);
//        teamKimi.addDriver(lstroll);
//
//        Team teamMichael = new Team("Michael's Team");
//        teamMichael.addDriver(cleclerc);
//        teamMichael.addDriver(lhamilton);
//        teamMichael.addDriver(lnorris);
//
//        league.addTeam(teamKimi);
//        league.addTeam(teamMichael);
//    }
//
//    public static void main(String[] args) {
//        new FantasyGUI();
//    }
//}