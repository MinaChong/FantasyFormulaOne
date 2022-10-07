package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Fantasy F1 Application
public class FantasyApp {
    private League league;
    private List<Driver> allDrivers;
    private List<Race> allRaces;

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

    private Scanner input;

    // EFFECTS: runs the Fantasy F1 application
    public FantasyApp() {
        runFantasy();
    }

    // MODIFIES: this
    // EFFECTS: processes user input for Fantasy F1 application
    private void runFantasy() {
        boolean keepRunning = true;
        String command;

        initialize();

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
        league = new League("My Fantasy F1 League");

        initializeDrivers();
        initializeGrid();
        initializeRaces();

        Team teamKimi = new Team("Kimi's Team");
        teamKimi.addDriver(mverstappen);
        teamKimi.addDriver(lhamilton);
        teamKimi.addDriver(lstroll);

        Team teamMichael = new Team("Michael's Team");
        teamMichael.addDriver(cleclerc);
        teamMichael.addDriver(lnorris);
        teamMichael.addDriver(ytsunoda);

        league.addTeam(teamKimi);
        league.addTeam(teamMichael);

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
        allDrivers = new ArrayList<>();
        allDrivers.add(aalbon);
        allDrivers.add(cleclerc);
        allDrivers.add(csainz);
        allDrivers.add(dricciardo);
        allDrivers.add(eocon);
        allDrivers.add(falonso);
        allDrivers.add(grussell);
        allDrivers.add(gzhou);
        allDrivers.add(kmagnussen);
        allDrivers.add(lhamilton);
        allDrivers.add(lnorris);
        allDrivers.add(lstroll);
        allDrivers.add(mschumacher);
        allDrivers.add(mverstappen);
        allDrivers.add(nlatifi);
        allDrivers.add(pgasly);
        allDrivers.add(sperez);
        allDrivers.add(svettel);
        allDrivers.add(vbottas);
        allDrivers.add(ytsunoda);
    }

    // MODIFIES: this
    // EFFECTS: initializes list of F1 races that have been held so far in 2022
    private void initializeRaces() {
        allRaces = new ArrayList<>();
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
        Race bahrain = new Race("Bahrain Grand Prix", "20/03/22", drivers, cleclerc);
        bahrain.updateDriverPoints();
        allRaces.add(bahrain);
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
        Race saudiArabia = new Race("Saudi Arabian Grand Prix", "27/03/22", drivers, cleclerc);
        saudiArabia.updateDriverPoints();
        allRaces.add(saudiArabia);
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
        Race australia = new Race("Australian Grand Prix", "10/04/22", drivers, cleclerc);
        australia.updateDriverPoints();
        allRaces.add(australia);
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
        emiliaRomagnaSprint.updateDriverPoints();
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
        Race emiliaRomagna = new Race("Emilia Romagna Grand Prix", "24/04/22", drivers, mverstappen);
        emiliaRomagna.updateDriverPoints();
        allRaces.add(emiliaRomagna);
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
        Race miami = new Race("Miami Grand Prix", "08/05/22", drivers, mverstappen);
        miami.updateDriverPoints();
        allRaces.add(miami);
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
        Race spain = new Race("Spanish Grand Prix", "22/05/22", drivers, sperez);
        spain.updateDriverPoints();
        allRaces.add(spain);
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
        Race monaco = new Race("Monaco Grand Prix", "29/05/22", drivers, lnorris);
        monaco.updateDriverPoints();
        allRaces.add(monaco);
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
        Race azerbaijan = new Race("Azerbaijan Grand Prix", "12/06/22", drivers, sperez);
        azerbaijan.updateDriverPoints();
        allRaces.add(azerbaijan);
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
        Race canada = new Race("Canadian Grand Prix", "19/06/22", drivers, csainz);
        canada.updateDriverPoints();
        allRaces.add(canada);
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
        Race greatBritain = new Race("British Grand Prix", "03/07/22", drivers, lhamilton);
        greatBritain.updateDriverPoints();
        allRaces.add(greatBritain);
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
        austriaSprint.updateDriverPoints();
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
        Race austria = new Race("Austrian Grand Prix", "10/07/22", drivers, mverstappen);
        austria.updateDriverPoints();
        allRaces.add(austria);
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
        Race france = new Race("French Grand Prix", "24/07/22", drivers, csainz);
        france.updateDriverPoints();
        allRaces.add(france);
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
        Race hungary = new Race("Hungarian Grand Prix", "31/07/22", drivers, lhamilton);
        hungary.updateDriverPoints();
        allRaces.add(hungary);
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
        Race belgium = new Race("Belgian Grand Prix", "28/08/22", drivers, mverstappen);
        belgium.updateDriverPoints();
        allRaces.add(belgium);
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
        Race netherlands = new Race("Dutch Grand Prix", "04/09/22", drivers, mverstappen);
        netherlands.updateDriverPoints();
        allRaces.add(netherlands);
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
        Race italy = new Race("Italian Grand Prix", "11/09/22", drivers, sperez);
        italy.updateDriverPoints();
        allRaces.add(italy);
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
        Race singapore = new Race("Singapore Grand Prix", "02/10/22", drivers, grussell);
        singapore.updateDriverPoints();
        allRaces.add(singapore);
    }

    // EFFECTS: displays main menu to user
    private void displayMainMenu() {
        System.out.println("\nMAIN MENU:");
        System.out.println("\t1 -> Show Scoreboard");
        System.out.println("\t2 -> View Races");
        System.out.println("\t3 -> View Teams");
        System.out.println("\t4 -> View Drivers");
        System.out.println("\tW -> Declare " + league.getName() + " Winner");
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
        } else {
            System.out.println("Input not recognized. Please try again.");
        }
    }

    // EFFECTS: displays a scoreboard showing each team and their total points and wins
    private void displayScoreboard() {
        System.out.println("SCOREBOARD:");

        if (league.getTeams().size() > 0) {
            for (Team team : league.getTeams()) {
                System.out.println("\t" + team.getName() + " has " + team.getPoints() + " point(s), "
                        + team.getWins() + " win(s).");
            }
        } else {
            System.out.println("\tThere are no teams currently in " + league.getName() + ".");
        }
    }

    // EFFECTS: allows user to select a race from the previous races, or add a new race
    private void selectRace() {
        displayRaceMenu();
        processRaceMenuCommand(); // TODO
    }

    // EFFECTS: displays race menu
    private void displayRaceMenu() {
        System.out.println("\nF1 RACES:");
        if (allRaces.size() > 0) {
            int i = 1;

            for (Race race : allRaces) {
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
            if (n <= allRaces.size()) {
                n = n - 1;
                showRaceReport(allRaces.get(n));
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
    // EFFECTS: allows user to input results of a race to update all teams' points, wins, and fastest laps
    private void addRace() {
        System.out.print("Enter name of race: ");
        String name = input.next();
        System.out.print("Enter date of race as DD/MM/YY: ");
        String date = input.next();
        System.out.println("Set race results:");
        int i = 1;
        for (Driver driver : allDrivers) {
            System.out.println("\t" + i + ". " + driver.getName());
            i = i + 1;
        }
        List<Driver> places = setPlaces();
        Driver fastestLap = setFastestLap();

        Race newRace = new Race(name, date, places, fastestLap);
        newRace.updateDriverPoints();
        allRaces.add(newRace);
        System.out.println(newRace.getName() + " results have been recorded!");
        displayScoreboard();
    }

    // EFFECTS: returns list of drivers in order of their finishing position that user selects
    private List<Driver> setPlaces() {
        List<Driver> places = new ArrayList<>();
        for (int j = 1; j <= 10; j++) {
            System.out.print("Select driver in " + j + " place: ");
            int n = input.nextInt();
            n = n - 1;
            Driver driver = allDrivers.get(n);
            places.add(driver);
        }
        return places;
    }

    // EFFECTS: returns driver that set the fastest lap that user selects
    private Driver setFastestLap() {
        System.out.print("Select driver with fastest lap of race: ");
        int n = input.nextInt();
        n = n - 1;
        return allDrivers.get(n);
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

            for (Driver driver : allDrivers) {
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
        } else if (allDrivers.size() >= 3) {
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
        for (Driver driver : allDrivers) {
            System.out.println("\t" + i + " -> " + driver.getName());
            i = i + 1;
        }

        System.out.print("Select driver: ");
        int n = input.nextInt();
        n = n - 1;
        Driver driver = allDrivers.get(n);

        showDriverReport(driver);
    }

    // EFFECTS: displays given driver's total number of points, wins, and fastest laps
    private void showDriverReport(Driver driver) {
        System.out.println(driver.getName() + " (" + driver.getNum() + ") has " + driver.getPoints()
                + " point(s), " + driver.getWins() + " win(s), and " + driver.getFastestLaps() + " fastest lap(s)!");

        displayDriverMenu();
        processDriverMenuCommand(driver);
    }

    // EFFECTS: displays driver menu to user
    private void displayDriverMenu() {
        System.out.println("\nChoose option to modify driver:");
        System.out.println("\t1 -> Add points");
        System.out.println("\t2 -> Remove points");
        System.out.println("\t3 -> Add win");
        System.out.println("\t4 -> Add fastest lap");
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
            addWin(driver);
        } else if (command.equals("4")) {
            addFastestLap(driver);
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
    // EFFECTS: allows user to add win to given driver
    private void addWin(Driver driver) {
        driver.addWin();
        System.out.println("A win has been added to " + driver.getName() + ".");
        showDriverReport(driver);
    }

    // MODIFIES: this
    // EFFECTS: allows user to add fastest lap to given driver
    private void addFastestLap(Driver driver) {
        driver.addFastestLap();
        System.out.println("A fastest lap has been added to " + driver.getName() + ".");
        showDriverReport(driver);
    }

    // MODIFIES: this
    // EFFECTS: allows user to change driver number of given driver
    private void changeDriverNumber(Driver driver) {
        System.out.print("Input new driver number for " + driver.getName() + ": ");
        int n = input.nextInt();
        driver.changeDriverNum(n);
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
        displayDriverScoreboard(); // TODO here to check for myself
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

    // TODO DELETE JUST METHOD TO CHECK FOR MY OWN USE
    private void displayDriverScoreboard() {
        for (Driver driver : allDrivers) {
            System.out.println(driver.getName() + " (" + driver.getNum() + ") has " + driver.getPoints() + " point(s), "
                    + driver.getWins() + " win(s), and " + driver.getFastestLaps() + " fastest lap(s)!");
        }
    }
}
