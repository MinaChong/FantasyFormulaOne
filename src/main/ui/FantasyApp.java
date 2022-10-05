package ui;

import model.Driver;
import model.League;
import model.Race;
import model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Fantasy F1 Application
public class FantasyApp {
    private League league;

    private Team teamKimi;
    private Team teamMichael;

    private Driver sperez;
    private Driver cleclerc;
    private Driver csainz;
    private Driver lnorris;
    private Driver dricciardo;
    private Driver lstroll;
    private Driver mverstappen;
    private Driver svettel;
    private Driver lhamilton;
    private Driver pgasly;
    private Driver vbottas;
    private Driver kmagnussen;
    private Driver mschumacher;
    private Driver grussell;
    private Driver ytsunoda;
    private Driver eocon;
    private Driver aalbon;
    private Driver falonso;
    private Driver nlatifi;
    private Driver gzhou;

    private List<Driver> allDrivers;

    private Scanner input;

    // EFFECTS: runs the Fantasy F1 application
    public FantasyApp() {
        runFantasy();
    }

    // MODIFIES: this
    // EFFECTS: processes user input for Fantasy F1 application
    private void runFantasy() {
        boolean keepRunning = true;
        String command = null;

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
        league = new League("My League");

        initializeDrivers();
        initializeGrid();

        teamKimi = new Team("Kimi's Team");
        teamKimi.addDriver(vbottas);
        teamKimi.addDriver(falonso);
        teamKimi.addDriver(lstroll);

        teamMichael = new Team("Michael's Team");
        teamMichael.addDriver(mschumacher);
        teamMichael.addDriver(lhamilton);
        teamMichael.addDriver(svettel);

        falonso.addPoints(10);
        lhamilton.addPoints(15);
        falonso.addWin();

        league.addTeam(teamKimi);
        league.addTeam(teamMichael);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: initializes current 20 drivers in F1
    private void initializeDrivers() {
        sperez = new Driver("Sergio Perez", 11);
        cleclerc = new Driver("Charles Leclerc", 16);
        csainz = new Driver("Carlos Sainz", 55);
        lnorris = new Driver("Lando Norris", 4);
        dricciardo = new Driver("Daniel Ricciardo", 3);
        lstroll = new Driver("Lance Stroll", 18);
        mverstappen = new Driver("Max Verstappen", 1);
        svettel = new Driver("Sebastian Vettel", 5);
        lhamilton = new Driver("Lewis Hamilton", 44);
        pgasly = new Driver("Pierre Gasly", 10);
        vbottas = new Driver("Valtteri Bottas", 77);
        kmagnussen = new Driver("Kevin Magnussen", 20);
        mschumacher = new Driver("Mick Schumacher", 47);
        grussell = new Driver("George Russell", 63);
        ytsunoda = new Driver("Yuki Tsunoda", 22);
        eocon = new Driver("Esteban Ocon", 31);
        aalbon = new Driver("Alex Albon", 23);
        falonso = new Driver("Fernando Alonso", 14);
        nlatifi = new Driver("Nicholas Latifi", 6);
        gzhou = new Driver("Guanyu Zhou", 24);
    }

    // MODIFIES: this
    // EFFECTS: initializes current grid of 20 drivers
    private void initializeGrid() {
        allDrivers = new ArrayList<>();
        allDrivers.add(sperez);
        allDrivers.add(cleclerc);
        allDrivers.add(csainz);
        allDrivers.add(lnorris);
        allDrivers.add(dricciardo);
        allDrivers.add(lstroll);
        allDrivers.add(mverstappen);
        allDrivers.add(svettel);
        allDrivers.add(lhamilton);
        allDrivers.add(pgasly);
        allDrivers.add(vbottas);
        allDrivers.add(kmagnussen);
        allDrivers.add(mschumacher);
        allDrivers.add(grussell);
        allDrivers.add(ytsunoda);
        allDrivers.add(eocon);
        allDrivers.add(aalbon);
        allDrivers.add(falonso);
        allDrivers.add(nlatifi);
        allDrivers.add(gzhou);
    }

    // EFFECTS: displays main menu to user
    private void displayMainMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t1 -> Show Scoreboard");
        System.out.println("\t2 -> Select Existing Team");
        System.out.println("\t3 -> Add New Team");
        System.out.println("\t4 -> Record a Race Result");
        System.out.println("\tQ -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for main menu
    private void processMainMenuCommand(String command) {
        if (command.equals("1")) {
            displayScoreboard();
        } else if (command.equals("2")) {
            selectTeam();
        } else if (command.equals("3")) {
            addTeam();
        } else if (command.equals("4")) {
            recordRace();
        } else {
            System.out.println("Input not recognized. Please try again.");
        }
    }

    // EFFECTS: displays a list of each team and their total points and wins
    private void displayScoreboard() {
        List<Team> currentTeams = league.getTeams();

        System.out.println("SCOREBOARD: ");

        if (currentTeams.size() > 0) {
            for (Team team : currentTeams) {
                System.out.println("\t" + team.getName() + " has " + team.getPoints() + " point(s) & "
                        + team.getWins() + " win(s)");
            }
        } else {
            System.out.println("There are no teams currently in " + league.getName() + ".");
        }
    }

    // EFFECTS: allows user to select a team from the existing teams
    private void selectTeam() {
        displayLeagueMenu();
        processLeagueMenuCommand();
    }

    // EFFECTS: displays list of teams that user can select
    private void displayLeagueMenu() {
        System.out.println("\n" + league.getName().toUpperCase() + " TEAMS: ");
        List<Team> currentTeams = league.getTeams();
        int i = 1;

        for (Team team : currentTeams) {
            System.out.println("\t" + i + " -> " + team.getName());
            i = i + 1;
        }
    }

    // EFFECTS: processes user command for league menu
    private void processLeagueMenuCommand() {
        System.out.print("Select team: ");
        List<Team> currentTeams = league.getTeams();
        int n = input.nextInt();

        if (n <= currentTeams.size()) {
            n = n - 1;
            showTeamReport(currentTeams.get(n));
        } else {
            System.out.println("Input not recognized. Please try again.");
        }
    }

    // EFFECTS: displays team report for given team and allows user to choose options to manage their team
    private void showTeamReport(Team team) {
        List<Driver> currentDrivers = team.getDrivers();
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
        System.out.println("You have " + team.getPoints() + " point(s)!");
        System.out.println("You have " + team.getWins() + " win(s)!");

        displayTeamMenu();
        processTeamMenuCommand(team);
    }

    // EFFECTS: displays driver menu to user
    private void displayTeamMenu() {
        System.out.println("\nChoose option to modify team:");
        System.out.println("\t1 -> Add driver");
        System.out.println("\t2 -> Remove driver");
        System.out.println("\t3 -> Delete team");
        System.out.println("\tS -> Select a different team");
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
        } else if (command.equals("S")) {
            selectTeam();
        } else if (command.equals("Q")) {
            System.out.println("Team changes saved.");
        } else {
            System.out.println("Input not recognized. Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to add a driver to given team
    private void addDriver(Team team) {
        List<Driver> currentDrivers = allDrivers;
        if (team.getDrivers().size() < 3) {
            List<Driver> availableDrivers = new ArrayList<>();
            int i = 1;
            System.out.println("Available Drivers:");
            for (Driver driver : currentDrivers) {
                if (!team.getDrivers().contains(driver)) {
                    System.out.println("\t" + i + ". " + driver.getName());
                    availableDrivers.add(driver);
                    i = i + 1;
                }
            }
            System.out.print("Select a driver to add: ");

            int num = input.nextInt();
            num = num - 1;
            team.addDriver(availableDrivers.get(num));

            System.out.println(availableDrivers.get(num).getName() + " has been added to " + team.getName());
        } else if (currentDrivers.size() >= 3) {
            System.out.println("You have already added the maximum number of drivers to your team (3).");
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
                System.out.println("\t" + i + ". " + driver.getName());
                i = i + 1;
                teamDrivers.add(driver);
            }

            System.out.print("Select a driver to remove: ");

            int num = input.nextInt();
            num = num - 1;
            team.removeDriver(teamDrivers.get(num));

            System.out.println(teamDrivers.get(num).getName() + " has been removed from " + team.getName());
        } else if (teamDrivers.size() == 0) {
            System.out.println("There are no drivers to remove.");
        }
        showTeamReport(team);
    }

    // MODIFIES: this
    // EFFECTS: removes given team from league
    private void deleteTeam(Team team) {
        league.removeTeam(team);
        System.out.println(team.getName() + " has been removed from " + league.getName());
        // selectTeam();
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

    // MODIFIES: this
    // EFFECTS: allows user to input results of a race to update all teams' points and wins
    private void recordRace() {
        System.out.print("Enter name of race: ");
        String name = input.next();
        Race newRace = new Race(name);
        System.out.println("Current Drivers:");
        int i = 1;
        for (Driver driver : allDrivers) {
            System.out.println("\t" + i + ". " + driver.getName());
            i = i + 1;
        }

        List<Driver> places = new ArrayList<>();
        for (int j = 1; j <= 10; j++) {
            System.out.print("Select driver in " + j + " place: ");
            int num = input.nextInt();
            num = num - 1;
            Driver driver = allDrivers.get(num);
            places.add(driver);
        }

        newRace.setPlaces(places);
        newRace.updateDriverPoints();

        System.out.println(newRace.getName() + " results have been recorded!");
        displayScoreboard();
    }
}
