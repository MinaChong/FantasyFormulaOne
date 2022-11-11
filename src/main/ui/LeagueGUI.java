package ui;

import model.League;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeagueGUI extends JPanel implements ActionListener {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 900;

    public LeagueGUI() {
//        super("My Fantasy F1 League");
        initializeGraphics();
    }

    private void setLeagueArea() {
        JPanel leagueArea = new JPanel();
        leagueArea.setLayout(new GridLayout(0,3));
        leagueArea.setSize(new Dimension(0, 0));

        JButton scoreboardButton = new JButton("Scoreboard");
        JButton carButton = new JButton(new ImageIcon("images/car.jpg"));
        JButton winnerButton = new JButton("Declare Winner");
        JButton racesButton = new JButton("Races");
        JButton teamsButton = new JButton("Teams");
        JButton driversButton = new JButton("Drivers");
        JButton saveButton = new JButton("Save League");
        JButton loadButton = new JButton("Load League");
        JButton quitButton = new JButton("Quit");
        leagueArea.add(scoreboardButton);
        leagueArea.add(carButton);
        leagueArea.add(winnerButton);
        leagueArea.add(racesButton);
        leagueArea.add(teamsButton);
        leagueArea.add(driversButton);
        leagueArea.add(saveButton);
        leagueArea.add(loadButton);
        leagueArea.add(quitButton);

        scoreboardButton.setActionCommand("scoreboard");
        winnerButton.setActionCommand("winner");
        racesButton.setActionCommand("races");
        teamsButton.setActionCommand("teams");
        driversButton.setActionCommand("drivers");
        saveButton.setActionCommand("save");
        loadButton.setActionCommand("load");
        quitButton.setActionCommand("quit");

        scoreboardButton.addActionListener(this);
        winnerButton.addActionListener(this);
        racesButton.addActionListener(this);
        teamsButton.addActionListener(this);
        driversButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        quitButton.addActionListener(this);

        add(leagueArea, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("scoreboard".equals(e.getActionCommand())) {
            // TODO
        } else if ("winner".equals(e.getActionCommand())) {
            // TODO
        } else if ("races".equals(e.getActionCommand())) {
            // TODO
        } else if ("teams".equals(e.getActionCommand())) {
            // new TeamsGUI(league);
        } else if ("drivers".equals(e.getActionCommand())) {
            // TODO
        } else if ("save".equals(e.getActionCommand())) {
            // TODO
        } else if ("load".equals(e.getActionCommand())) {
            // TODO
        } else if ("quit".equals(e.getActionCommand())) {
            // TODO
        }
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
        setVisible(true);
    }
}
