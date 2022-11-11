package ui;

import model.Driver;
import model.League;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddTeamDriverGUI extends JFrame implements ActionListener {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 900;
    private JPanel addTeamDriverArea;
    private League league;
    private Team team;
    private List<Driver> availableDrivers;

    AddTeamDriverGUI(Team team, League league) {
        super("Add Driver to" + team.getName());
        this.team = team;
        this.league = league;
        this.availableDrivers = new ArrayList<>();
        initializeGraphics();
    }

    public void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        addTeamDriverArea = new JPanel();
        addTeamDriverArea.setLayout(new GridLayout(0,1));
        addTeamDriverArea.setSize(new Dimension(0, 0));
        add(addTeamDriverArea, BorderLayout.NORTH);

        for (Driver driver : league.getDrivers()) {
            if (!team.getDrivers().contains(driver)) {
                JButton driverButton = new JButton(driver.getName());
                addTeamDriverArea.add(driverButton);
                driverButton.setActionCommand(driver.getName());
                driverButton.addActionListener(this);
                availableDrivers.add(driver);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Driver driver : availableDrivers) {
            if (driver.getName().equals(e.getActionCommand())) {
                team.addDriver(driver);
            }
        }
        new TeamGUI(team, league);
    }
}
