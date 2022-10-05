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
    // EFFECTS: processes user input
    private void runFantasy() {
        boolean keepRunning = true;
        String command = null;

        initialize();

        while (keepRunning) {
            displayMenu();
            command = input.next();

            if (command.equals("Q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you later!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            showScoreboard();
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

    // EFFECTS: creates current grid of 20 drivers
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

    // EFFECTS: creates list of current grid of 20 drivers
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
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\t1 -> Show Scoreboard");
        System.out.println("\t2 -> Select Existing Team");
        System.out.println("\t3 -> Add New Team");
        System.out.println("\t4 -> Record a Race Result");
        System.out.println("\tQ -> Quit");
    }

    // EFFECTS: outputs a list of each team and their total points
    private void showScoreboard() {
        List<Team> currentTeams = league.getTeams();

        for (Team team : currentTeams) {
            System.out.println(team.getName() + " has " + team.getPoints() + " point(s) & "
                    + team.getWins() + " win(s)");
        }
    }

    // EFFECTS: displays a list of teams for the user to select from
    private void selectTeam() {
        boolean isSelecting = true;
        String command = null;

        while (isSelecting) {
            displayTeamMenu();
            command = input.next();

            if (command.equals("R")) {
                isSelecting = false;
            } else {
                processTeamCommand(command);
            }
        }
    }

    // EFFECTS: displays team menu for a user
    private void displayTeamMenu() {
        System.out.println("\nChoose team to view:");
        List<Team> currentTeams = league.getTeams();
        int i = 1;

        for (Team team : currentTeams) {
            System.out.println("Select " + i + " for " + team.getName());
            i = i + 1;
        }

        System.out.println("\nR -> Return to main menu");
    }

    // EFFECTS: processes user command for team menu
    private void processTeamCommand(String command) {
        List<Team> currentTeams = league.getTeams();

        if (command.equals("1")) {
            showTeam(currentTeams.get(0));
        } else if (command.equals("2")) {
            showTeam(currentTeams.get(1));
        } else if (command.equals("3")) {
            showTeam(currentTeams.get(2));
        } else if (command.equals("4")) {
            showTeam(currentTeams.get(3));
        } else if (command.equals("5")) {
            showTeam(currentTeams.get(4));
        } else {
            System.out.println("Input not recognized. Please try again.");
        }
    }

    // EFFECTS: shows user options for selecting a driver, adding a driver, or removing a driver from their team
    private void showTeam(Team team) {
        List<Driver> currentDrivers = team.getDrivers();
        int i = 1;
        System.out.println("Your drivers are:");
        for (Driver driver : currentDrivers) {
            System.out.println(i + ". " + driver.getName());
            i = i + 1;
        }
        System.out.println("You have " + team.getPoints() + " point(s)!");
        System.out.println("You have " + team.getWins() + " win(s)!");

        boolean isSelecting = true;
        String command = null;

        while (isSelecting) {
            displayDriverMenu();
            command = input.next();

            if (command.equals("S")) {
                isSelecting = false;
            } else {
                processDriverCommand(command, team);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds new team to league
    private void addTeam() {
        System.out.print("Enter name of new team: ");

        String name = input.next();
        Team newTeam = new Team(name);
        league.addTeam(newTeam);

        System.out.println(newTeam.getName() + " has been added to " + league.getName());
    }

    // EFFECTS: displays driver menu to user
    private void displayDriverMenu() {
        System.out.println("\nChoose option to modify team:");
        System.out.println("\n1 -> Add driver");
        System.out.println("\n2 -> Remove driver");
        System.out.println("\nS -> Return to teams menu");
    }

    // EFFECTS: processes user command for driver menu
    private void processDriverCommand(String command, Team team) {

        if (command.equals("1")) {
            addDriver(team);
        } else if (command.equals("2")) {
            removeDriver(team);
        } else {
            System.out.println("Input not recognized. Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: shows available drivers that can be added to a team
    private void addDriver(Team team) {
        List<Driver> currentDrivers = allDrivers;
        List<Driver> availableDrivers = new ArrayList<>();
        int i = 1;

        for (Driver driver : currentDrivers) {
            if (!team.getDrivers().contains(driver)) {
                System.out.println(i + ". " + driver.getName());
                availableDrivers.add(driver);
                i = i + 1;
            }
        }
        System.out.print("Select a driver to add: ");

        int num = input.nextInt();
        num = num - 1;
        team.addDriver(availableDrivers.get(num));

        System.out.println(availableDrivers.get(num).getName() + " has been added to " + team.getName());
    }

    // MODIFIES: this
    // EFFECTS: shows current drives that can be removed from a team
    private void removeDriver(Team team) {
        List<Driver> teamDrivers = new ArrayList<>();
        int i = 1;

        for (Driver driver : team.getDrivers()) {
            System.out.println(i + ". " + driver.getName());
            i = i + 1;
            teamDrivers.add(driver);
        }

        System.out.print("Select a driver to remove: ");

        int num = input.nextInt();
        num = num - 1;
        team.removeDriver(teamDrivers.get(num));

        System.out.println(teamDrivers.get(num).getName() + " has been removed from " + team.getName());
    }

    // MODIFIES: this
    // EFFECTS: allows user to input results of a race
    private void recordRace() {
        System.out.print("Enter name of race: ");
        String name = input.next();
        Race newRace = new Race(name);

        int i = 1;
        for (Driver driver : allDrivers) {
            System.out.println(i + ". " + driver.getName());
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


    }


}
