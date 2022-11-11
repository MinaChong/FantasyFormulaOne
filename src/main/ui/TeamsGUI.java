package ui;

import model.League;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TeamsGUI extends JFrame implements ActionListener {
    private JPanel teamsArea;
    private League league;

    public TeamsGUI(League league) {
        super("Teams");
        this.league = league;
        initializeGraphics();
    }

    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        teamsArea = new JPanel();
        teamsArea.setLayout(new GridLayout(0,1));
        teamsArea.setSize(new Dimension(0, 0));
        add(teamsArea, BorderLayout.NORTH);

        for (Team team : league.getTeams()) {
            JButton teamButton = new JButton(team.getName());
            teamsArea.add(teamButton);
            teamButton.setActionCommand(team.getName());
            teamButton.addActionListener(this);
        }

        JButton addTeamButton = new JButton("Add Team");
        teamsArea.add(addTeamButton);
        addTeamButton.setActionCommand("addTeam");
        addTeamButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("addTeam".equals(e.getActionCommand())) {
            String teamName = JOptionPane.showInputDialog(null,
                    "Team name?",
                    "Create New Team",
                    JOptionPane.QUESTION_MESSAGE);
            new TeamGUI(new Team(teamName), league);
        }

        for (Team team : league.getTeams()) {
            if (team.getName().equals(e.getActionCommand())) {
                new TeamGUI(team, league);
            }
        }
    }
}