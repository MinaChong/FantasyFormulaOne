package ui;

import model.Driver;
import model.League;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamGUI extends JFrame implements ActionListener {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 900;
    private JPanel teamArea;
    private League league;
    private Team team;

    TeamGUI(Team team, League league) {
        super("Team");
        this.setBackground(Color.blue);
        this.team = team;
        this.league = league;
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
//        initializeGraphics();
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTeamArea();

        for (Driver driver : team.getDrivers()) {
            JButton driverButton = new JButton(driver.getName());
            teamArea.add(driverButton);
        }

        JButton addDriverButton = new JButton("Add Driver");
        teamArea.add(addDriverButton);
        addDriverButton.setActionCommand("addDriver");
        addDriverButton.addActionListener(this);
    }

    private void setTeamArea() {
        teamArea = new JPanel();
        teamArea.setLayout(new GridLayout(0,1));
        teamArea.setSize(new Dimension(0, 0));
        add(teamArea, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("addDriver".equals(e.getActionCommand())) {
            if (team.getDrivers().size() >= 3) {
                JOptionPane.showMessageDialog(null, "Maximum number of drivers already on team.", "System Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                new AddTeamDriverGUI(team, league);
            }
        }
        remove(teamArea);
        setTeamArea();
        validate();
    }

    public static void main(String[] args) {
        new TeamGUI(new Team("dummy"), new League("dummy"));
    }
}