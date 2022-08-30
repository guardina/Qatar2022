package com.example.fifaqatar2022.Classes;

import java.util.ArrayList;

public class Group {

    enum Result {ONE, X, TWO}

    String name;
    ArrayList<Team> teams = new ArrayList<>();
    ArrayList<String> team_names = new ArrayList<>();
    String[] placement = new String[4];

    ArrayList<Match> matches = new ArrayList<>();


    ///// CONSTRUCTOR /////

    public Group() {}



    ///// GETTERS AND SETTERS /////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams.addAll(teams);
    }

    public ArrayList<String> getTeam_names() {
        return team_names;
    }

    public void setTeam_names(ArrayList<String> team_names) {
        this.team_names = team_names;
    }



    ///// OTHER METHODS /////


    public void add_match(Match match) {
        this.matches.add(match);
    }

    public void match_result(Result result, Match match) {

        for (Team team : teams) {
            if (team.getName().equals(match.getFirst_team())) {
                team.scored_goals(Integer.getInteger(match.getFirst_score()));
                team.conceded_goals(Integer.getInteger(match.getSecond_score()));
            }

            if (team.getName().equals(match.getSecond_team())) {
                team.scored_goals(Integer.getInteger(match.getSecond_score()));
                team.conceded_goals(Integer.getInteger(match.getFirst_score()));
            }
        }


        switch (result) {
            case ONE:
                for (Team team : teams) {
                    if (team.getName().equals(match.getFirst_team())) {
                        team.addPoints(3);
                    }
                }
                break;

            case X:
                for (Team team : teams) {
                    if (team.getName().equals(match.getFirst_team()) || team.getName().equals(match.getSecond_team())) {
                        team.addPoints(1);
                    }
                }
                break;

            case TWO:
                for (Team team : teams) {
                    if (team.getName().equals(match.getSecond_team())) {
                        team.addPoints(3);
                    }
                }
                break;
        }
    }


    public void do_placement() {

        int max = Integer.MIN_VALUE;
        int pos = 0;

        for (int i = 0; i < teams.size(); i++) {
            for (int j = i; j < teams.size()-i; j++) {
                if (teams.get(j).getPoints() > max) {
                    max = teams.get(j).getPoints();
                    pos = j;
                }
            }
            placement[i] = teams.get(pos).getName();
        }
    }

    public String[] get_placement() {
        return placement;
    }

}
