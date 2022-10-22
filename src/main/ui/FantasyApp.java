package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Fantasy F1 Application
public class FantasyApp {
    private static final String JSON_STORE = "./data/league.json";
    private Scanner input;
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

    // EFFECTS: runs the Fantasy F1 application
    public FantasyApp() throws FileNotFoundException {
        initialize();
        runFantasy();
    }

    // MODIFIES: this
    // EFFECTS: processes user input for Fantasy F1 application
    // NOTE: lends structure from TellerApp
    private void runFantasy() {
        boolean keepRunning = true;
        String command;

        while (keepRunning) {
            displayMainMenu();
            command = input.next();

            if (command.equals("Q")) {
                keepRunning = false;
            } else {
                processMainMenuCommand(command);
            }
        }
        System.out.println("\nSee you later!");
    }

    // MODIFIES: this
    // EFFECTS: initializes league
    private void initialize() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        league = new League("My Fantasy F1 League");

        initializeDrivers();
        initializeGrid();
        initializeRaces();

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

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: initializes all drivers that have driven for F1 so far in 2022
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
        Race bahrain = new GrandPrix("Bahrain Grand Prix", "20/03/22");
        bahrain.setPlaces(drivers);
        bahrain.setFastestLap(cleclerc);
        bahrain.updateDriverPoints();
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
        Race saudiArabia = new GrandPrix("Saudi Arabian Grand Prix", "27/03/22");
        saudiArabia.setPlaces(drivers);
        saudiArabia.setFastestLap(cleclerc);
        saudiArabia.updateDriverPoints();
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
        Race australia = new GrandPrix("Australian Grand Prix", "10/04/22");
        australia.setPlaces(drivers);
        australia.setFastestLap(cleclerc);
        australia.updateDriverPoints();
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
        Race emiliaRomagnaSprint = new Sprint("Emilia Romagna Sprint Race", "23/04/22");
        emiliaRomagnaSprint.setPlaces(drivers);
        emiliaRomagnaSprint.setFastestLap(mverstappen);
        emiliaRomagnaSprint.updateDriverPoints();
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
        Race emiliaRomagna = new GrandPrix("Emilia Romagna Grand Prix", "24/04/22");
        emiliaRomagna.setPlaces(drivers);
        emiliaRomagna.setFastestLap(mverstappen);
        emiliaRomagna.updateDriverPoints();
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
        Race miami = new GrandPrix("Miami Grand Prix", "08/05/22");
        miami.setPlaces(drivers);
        miami.setFastestLap(mverstappen);
        miami.updateDriverPoints();
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
        Race spain = new GrandPrix("Spanish Grand Prix", "22/05/22");
        spain.setPlaces(drivers);
        spain.setFastestLap(sperez);
        spain.updateDriverPoints();
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
        Race monaco = new GrandPrix("Monaco Grand Prix", "29/05/22");
        monaco.setPlaces(drivers);
        monaco.setFastestLap(lnorris);
        monaco.updateDriverPoints();
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
        Race azerbaijan = new GrandPrix("Azerbaijan Grand Prix", "12/06/22");
        azerbaijan.setPlaces(drivers);
        azerbaijan.setFastestLap(sperez);
        azerbaijan.updateDriverPoints();
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
        Race canada = new GrandPrix("Canadian Grand Prix", "19/06/22");
        canada.setPlaces(drivers);
        canada.setFastestLap(csainz);
        canada.updateDriverPoints();
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
        Race greatBritain = new GrandPrix("British Grand Prix", "03/07/22");
        greatBritain.setPlaces(drivers);
        greatBritain.setFastestLap(lhamilton);
        greatBritain.updateDriverPoints();
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
        Race austriaSprint = new Sprint("Austrian Sprint Race", "10/06/22");
        austriaSprint.setPlaces(drivers);
        austriaSprint.setFastestLap(mverstappen);
        austriaSprint.updateDriverPoints();
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
        Race austria = new GrandPrix("Austrian Grand Prix", "10/07/22");
        austria.setPlaces(drivers);
        austria.setFastestLap(cleclerc);
        austria.updateDriverPoints();
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
        Race france = new GrandPrix("French Grand Prix", "24/07/22");
        france.setPlaces(drivers);
        france.setFastestLap(csainz);
        france.updateDriverPoints();
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
        Race hungary = new GrandPrix("Hungarian Grand Prix", "31/07/22");
        hungary.setPlaces(drivers);
        hungary.setFastestLap(lhamilton);
        hungary.updateDriverPoints();
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
        Race belgium = new GrandPrix("Belgian Grand Prix", "28/08/22");
        belgium.setPlaces(drivers);
        belgium.setFastestLap(mverstappen);
        belgium.updateDriverPoints();
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
        Race netherlands = new GrandPrix("Dutch Grand Prix", "04/09/22");
        netherlands.setPlaces(drivers);
        netherlands.setFastestLap(mverstappen);
        netherlands.updateDriverPoints();
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
        Race italy = new GrandPrix("Italian Grand Prix", "11/09/22");
        italy.setPlaces(drivers);
        italy.setFastestLap(sperez);
        italy.updateDriverPoints();
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
        Race singapore = new GrandPrix("Singapore Grand Prix", "02/10/22");
        singapore.setPlaces(drivers);
        singapore.setFastestLap(grussell);
        singapore.updateDriverPoints();
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
        Race japan = new GrandPrix("Japanese Grand Prix", "09/10/22");
        japan.setPlaces(drivers);
        japan.setFastestLap(gzhou);
        japan.updateDriverPoints();
        league.addRace(japan);
    }

    // EFFECTS: displays main menu to user
    private void displayMainMenu() {
        System.out.println("\nMAIN MENU:");
        System.out.println("\t1 -> Show Scoreboard");
        System.out.println("\t2 -> View Races");
        System.out.println("\t3 -> View Teams");
        System.out.println("\t4 -> View Drivers");
        System.out.println("\tW -> Declare " + league.getName() + " Winner");
        System.out.println("\tS -> Save " + league.getName() + " to File");
        System.out.println("\tL -> Load " + league.getName() + " from File");
        System.out.println("\tQ -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for main menu
    private void processMainMenuCommand(String command) {
        if (command.equals("1")) {
            displayScoreboard();
        } else if (command.equals("2")) {
            selectRace();
        } else if (command.equals("3")) {
            selectTeam();
        } else if (command.equals("4")) {
            selectDriver();
        } else if (command.equals("W")) {
            declareWinner();
        } else if (command.equals("S")) {
            saveLeague();
        } else if (command.equals("L")) {
            loadLeague();
        } else {
            System.out.println("Input not recognized. Please try again.");
        }
    }

    // EFFECTS: displays a scoreboard showing each team and their total points and wins
    private void displayScoreboard() {
        System.out.println("SCOREBOARD:");

        if (league.getTeams().size() > 0) {
            for (Team team : league.getTeams()) {
                System.out.println("\t" + team.getName() + " has " + team.getPoints() + " point(s) and "
                        + team.getWins() + " win(s).");
            }
        } else {
            System.out.println("\tThere are no teams currently in " + league.getName() + ".");
        }
    }

    // EFFECTS: allows user to select a race from the previous races, or add a new race
    private void selectRace() {
        displayRaceMenu();
        processRaceMenuCommand();
    }

    // EFFECTS: displays race menu
    private void displayRaceMenu() {
        System.out.println("\nF1 RACES:");
        if (league.getRaces().size() > 0) {
            int i = 1;

            for (Race race : league.getRaces()) {
                System.out.println("\t" + i + " -> " + race.getName());
                i = i + 1;
            }
        } else {
            System.out.println("\tNo races have been recorded in " + league.getName() + " yet!");
        }
        System.out.println("\tA -> Add New Race");
    }

    // EFFECTS: processes user command for race menu
    private void processRaceMenuCommand() {
        System.out.print("Select option: ");
        String i = input.next();

        if (i.equals("A")) {
            addRace();
        } else {
            int n = Integer.parseInt(i);
            if (n <= league.getRaces().size()) {
                n = n - 1;
                showRaceReport(league.getRaces().get(n));
            } else {
                System.out.println("\tInput not recognized. Please try again.");
                selectRace();
            }
        }
    }

    // EFFECTS: displays results of given race
    private void showRaceReport(Race race) {
        System.out.println(race.getName().toUpperCase() + " RESULTS:");
        int j = 1;

        for (Driver driver : race.getPlaces()) {
            System.out.println("\t" + j + ". " + driver.getName());
            j = j + 1;
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to choose to add a sprint race or grand prix
    private void addRace() {
        System.out.println("\tS -> Add New Sprint Race");
        System.out.println("\tG -> Add New Grand Prix");
        System.out.print("Select type: ");
        String select = input.next();

        if (select.equals("S")) {
            addSprintRace();
        } else if (select.equals("G")) {
            addGrandPrix();
        } else {
            System.out.println("\tInput not recognized. Please try again.");
            addRace();
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to input results of a sprint race to update all teams' points
    private void addSprintRace() {
        System.out.print("Enter name of sprint race: ");
        String name = input.next();
        System.out.print("Enter date of sprint race as DD/MM/YY: ");
        String date = input.next();
        System.out.println("Set sprint race results:");
        int i = 1;
        for (Driver driver : league.getDrivers()) {
            System.out.println("\t" + i + " -> " + driver.getName());
            i = i + 1;
        }
        List<Driver> places = setPlaces(8);
        Driver fastestLap = setFastestLap();

        Sprint newSprint = new Sprint(name, date);
        newSprint.setPlaces(places);
        newSprint.setFastestLap(fastestLap);
        newSprint.updateDriverPoints();
        league.addRace(newSprint);
        System.out.println(newSprint.getName() + " results have been recorded!");
        displayScoreboard();
    }

    // MODIFIES: this
    // EFFECTS: allows user to input results of a race to update all teams' points, wins, and fastest laps
    private void addGrandPrix() {
        System.out.print("Enter name of grand prix: ");
        String name = input.next();
        System.out.print("Enter date of grand prix as DD/MM/YY: ");
        String date = input.next();
        System.out.println("Set grand prix results:");
        int i = 1;
        for (Driver driver : league.getDrivers()) {
            System.out.println("\t" + i + " -> " + driver.getName());
            i = i + 1;
        }
        List<Driver> places = setPlaces(10);
        Driver fastestLap = setFastestLap();

        GrandPrix newGrandPrix = new GrandPrix(name, date);
        newGrandPrix.setPlaces(places);
        newGrandPrix.setFastestLap(fastestLap);
        newGrandPrix.updateDriverPoints();
        league.addRace(newGrandPrix);
        System.out.println(newGrandPrix.getName() + " results have been recorded!");
        displayScoreboard();
    }

    // EFFECTS: returns list of drivers in order of their finishing position that user selects
    private List<Driver> setPlaces(int num) {
        List<Driver> places = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            System.out.print("Select driver in " + i + " place: ");
            int n = input.nextInt();
            n = n - 1;
            Driver driver = league.getDrivers().get(n);
            places.add(driver);
        }
        return places;
    }

    // EFFECTS: returns driver that set the fastest lap that user selects
    private Driver setFastestLap() {
        System.out.print("Select driver with fastest lap of race: ");
        int n = input.nextInt();
        n = n - 1;
        return league.getDrivers().get(n);
    }

    // EFFECTS: allows user to select a team from the existing teams, or add a new team
    private void selectTeam() {
        displayLeagueMenu();
        processLeagueMenuCommand();
    }

    // EFFECTS: displays league menu to user
    private void displayLeagueMenu() {
        List<Team> currentTeams = league.getTeams();
        System.out.println("\n" + league.getName().toUpperCase() + " TEAMS:");
        if (currentTeams.size() > 0) {
            int i = 1;

            for (Team team : currentTeams) {
                System.out.println("\t" + i + " -> " + team.getName());
                i = i + 1;
            }
        } else {
            System.out.println("\tThere are no teams currently in " + league.getName() + ".");
        }
        System.out.println("\tA -> Add New Team");
    }

    // EFFECTS: processes user command for league menu
    private void processLeagueMenuCommand() {
        System.out.print("Select option: ");
        String i = input.next();
        if (i.equals("A")) {
            addTeam();
        } else {
            int n = Integer.parseInt(i);
            if (n <= league.getTeams().size()) {
                n = n - 1;
                showTeamReport(league.getTeams().get(n));
            } else {
                System.out.println("\tInput not recognized. Please try again.");
                selectTeam();
            }
        }
    }

    // EFFECTS: displays team report for given team and allows user to choose options to manage their team
    private void showTeamReport(Team team) {
        List<Driver> currentDrivers = team.getDrivers();
        System.out.println(team.getName().toUpperCase() + ":");
        int i = 1;
        if (currentDrivers.size() == 0) {
            System.out.println("You have no drivers on your team currently.");
        } else {
            System.out.println("Your drivers are:");
        }
        for (Driver driver : currentDrivers) {
            System.out.println("\t" + i + ". " + driver.getName());
            i = i + 1;
        }
        System.out.println("You have " + team.getPoints() + " point(s) and " + team.getWins() + " win(s)!");

        displayTeamMenu();
        processTeamMenuCommand(team);
    }

    // EFFECTS: displays driver menu to user
    private void displayTeamMenu() {
        System.out.println("\nChoose option to modify team:");
        System.out.println("\t1 -> Add driver");
        System.out.println("\t2 -> Remove driver");
        System.out.println("\t3 -> Delete team");
        System.out.println("\tQ -> Return to main menu");
    }

    // EFFECTS: processes user command for driver menu
    private void processTeamMenuCommand(Team team) {
        String command = input.next();
        if (command.equals("1")) {
            addDriver(team);
        } else if (command.equals("2")) {
            removeDriver(team);
        } else if (command.equals("3")) {
            deleteTeam(team);
        } else if (command.equals("Q")) {
            System.out.println();
        } else {
            System.out.println("\tInput not recognized. Please try again.");
            displayTeamMenu();
            processTeamMenuCommand(team);
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to add a driver to given team
    private void addDriver(Team team) {
        System.out.println("Available Drivers:");
        if (team.getDrivers().size() < 3) {
            List<Driver> availableDrivers = new ArrayList<>();
            int i = 1;

            for (Driver driver : league.getDrivers()) {
                if (!team.getDrivers().contains(driver)) {
                    System.out.println("\t" + i + " -> " + driver.getName());
                    availableDrivers.add(driver);
                    i = i + 1;
                }
            }

            System.out.print("Select a driver to add: ");
            int n = input.nextInt();
            n = n - 1;
            team.addDriver(availableDrivers.get(n));
            System.out.println(availableDrivers.get(n).getName() + " has been added to " + team.getName() + ".");
        } else if (league.getDrivers().size() >= 3) {
            System.out.println("You already have the maximum number of drivers (3) and cannot add more to your team.");
        }
        showTeamReport(team);
    }

    // MODIFIES: this
    // EFFECTS: allows user to remove a driver from given team
    private void removeDriver(Team team) {
        List<Driver> teamDrivers = new ArrayList<>();
        if (team.getDrivers().size() > 0) {
            int i = 1;
            System.out.println("Current Drivers:");

            for (Driver driver : team.getDrivers()) {
                System.out.println("\t" + i + " -> " + driver.getName());
                i = i + 1;
                teamDrivers.add(driver);
            }

            System.out.print("Select a driver to remove: ");
            int n = input.nextInt();
            n = n - 1;
            team.removeDriver(teamDrivers.get(n));
            System.out.println(teamDrivers.get(n).getName() + " has been removed from " + team.getName() + ".");
        } else {
            System.out.println("There are no drivers to remove.");
        }
        showTeamReport(team);
    }

    // MODIFIES: this
    // EFFECTS: removes given team from league
    private void deleteTeam(Team team) {
        league.removeTeam(team);
        System.out.println(team.getName() + " has been removed from " + league.getName() + ".");
    }

    // MODIFIES: this
    // EFFECTS: allows user to add a new team to league
    private void addTeam() {
        System.out.print("Enter name of new team: ");
        String name = input.next();
        Team newTeam = new Team(name);
        league.addTeam(newTeam);
        System.out.println(newTeam.getName() + " has been added to " + league.getName() + ".");
        showTeamReport(newTeam);
    }

    // EFFECTS: displays list of current F1 drivers and allows user to select a driver to view their stats
    private void selectDriver() {
        System.out.println("CURRENT F1 DRIVERS:");
        int i = 1;
        for (Driver driver : league.getDrivers()) {
            System.out.println("\t" + i + " -> " + driver.getName());
            i = i + 1;
        }

        System.out.print("Select driver: ");
        int n = input.nextInt();
        n = n - 1;
        Driver driver = league.getDrivers().get(n);

        showDriverReport(driver);
    }

    // EFFECTS: displays given driver's total number of points, wins, and fastest laps
    private void showDriverReport(Driver driver) {
        System.out.println(driver.getName() + " (" + driver.getNum() + ") has " + driver.getPoints() + " point(s), "
                + driver.getWins() + " win(s), and " + driver.getFastestLaps() + " fastest lap(s)!");

        displayDriverMenu();
        processDriverMenuCommand(driver);
    }

    // EFFECTS: displays driver menu to user
    private void displayDriverMenu() {
        System.out.println("\nChoose option to modify driver:");
        System.out.println("\t1 -> Add points");
        System.out.println("\t2 -> Remove points");
        System.out.println("\t3 -> Set wins");
        System.out.println("\t4 -> Set fastest laps");
        System.out.println("\t5 -> Change driver number");
        System.out.println("\tQ -> Return to main menu");
    }

    // EFFECTS: processes user command for driver menu
    private void processDriverMenuCommand(Driver driver) {
        String command = input.next();
        if (command.equals("1")) {
            addPoints(driver);
        } else if (command.equals("2")) {
            removePoints(driver);
        } else if (command.equals("3")) {
            setWins(driver);
        } else if (command.equals("4")) {
            setFastestLaps(driver);
        } else if (command.equals("5")) {
            changeDriverNumber(driver);
        } else if (command.equals("Q")) {
            System.out.println();
        } else {
            System.out.println("\tInput not recognized. Please try again.");
            processDriverMenuCommand(driver);
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to add points to given driver
    private void addPoints(Driver driver) {
        System.out.print("Input number of points to add to " + driver.getName() + ": ");
        int n = input.nextInt();
        driver.addPoints(n);
        System.out.println(n + " point(s) have been added to " + driver.getName() + ".");
        showDriverReport(driver);
    }

    // MODIFIES: this
    // EFFECTS: allows user to remove points from given driver
    private void removePoints(Driver driver) {
        System.out.print("Input number of points to remove from " + driver.getName() + ": ");
        int n = input.nextInt();
        driver.removePoints(n);
        System.out.println(n + " point(s) have been removed from " + driver.getName() + ".");
        showDriverReport(driver);
    }

    // MODIFIES: this
    // EFFECTS: allows user to set number of wins for given driver
    private void setWins(Driver driver) {
        System.out.print("Input number of wins for " + driver.getName() + ": ");
        int n = input.nextInt();
        driver.setWins(n);
        System.out.println(driver.getName() + " has " + n + " win(s).");
        showDriverReport(driver);
    }

    // MODIFIES: this
    // EFFECTS: allows user to set number of fastest laps for given driver
    private void setFastestLaps(Driver driver) {
        System.out.print("Input number of fastest laps for " + driver.getName() + ": ");
        int n = input.nextInt();
        driver.setFastestLaps(n);
        System.out.println(driver.getName() + " has " + n + " fastest lap(s).");
        showDriverReport(driver);
    }

    // MODIFIES: this
    // EFFECTS: allows user to change driver number of given driver
    private void changeDriverNumber(Driver driver) {
        System.out.print("Input new driver number for " + driver.getName() + ": ");
        int n = input.nextInt();
        driver.setNum(n);
        System.out.println(driver.getName() + "'s driver number has been changed to " + n + ".");
        showDriverReport(driver);
    }

    // EFFECTS: allows user to declare winner of fantasy league
    private void declareWinner() {
        if (league.getTeams().size() > 0) {
            Team winner = decideWinner(league.getTeams());
            System.out.println(winner.getName() + " has won the " + league.getName() + " Fantasy F1 Championship!");
            displayScoreboard();
        } else {
            System.out.println("No teams are currently in " + league.getName() + ".");
        }
    }

    // EFFECTS: returns winner based on team with most points, then most wins, then greatest number of fastest laps
    private Team decideWinner(List<Team> teams) {
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
        return winner;
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
    }
}
