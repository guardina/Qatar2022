package com.example.fifaqatar2022.Classes;

import java.util.ArrayList;
import java.util.Collections;

public class Group {

    public enum Result {ONE, X, TWO}

    String name;
    ArrayList<Team> teams = new ArrayList<>();
    ArrayList<String> team_names = new ArrayList<>();

    ArrayList<Match> matches = new ArrayList<>();
    ArrayList<Match> predictions = new ArrayList<>();


    ///// CONSTRUCTOR /////

    public Group() {
    }


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

    public ArrayList<Match> getMatches() {
        return matches;
    }


    ///// OTHER METHODS /////


    public void add_match(Match match) {
        for (Team team : this.teams) {
            if (team.getName().equals(match.getFirst_team().getName())) {
                match.getFirst_team().setLogo(team.getLogo());
            } else if (team.getName().equals(match.getSecond_team().getName())) {
                match.getSecond_team().setLogo(team.getLogo());
            }
        }
        this.matches.add(match);
    }

    public void match_result(Result result, Match match) {

        for (Team team : teams) {
            if (team.getName().equals(match.getFirst_team().getName())) {
                team.getTeamInfo()[Team.GOALDIFF] += Integer.parseInt(match.getFirst_score()) - Integer.parseInt(match.getSecond_score());
                team.scored_goals(Integer.parseInt(match.getFirst_score()));

            } else if (team.getName().equals(match.getSecond_team().getName())) {
                team.getTeamInfo()[Team.GOALDIFF] += Integer.parseInt(match.getSecond_score()) - Integer.parseInt(match.getFirst_score());
                team.scored_goals(Integer.parseInt(match.getSecond_score()));
            }
        }


        switch (result) {
            case ONE:
                for (Team team : teams) {
                    if (team.getName().equals(match.getFirst_team().getName())) {
                        team.getTeamInfo()[Team.POINTS] += 3;
                        team.getTeamInfo()[Team.GAMES]++;
                        team.getTeamInfo()[Team.WINS]++;
                    } else if (team.getName().equals(match.getSecond_team().getName())) {
                        team.getTeamInfo()[Team.GAMES]++;
                        team.getTeamInfo()[Team.LOSSES]++;
                    }
                }
                break;

            case X:
                for (Team team : teams) {
                    if (team.getName().equals(match.getFirst_team().getName()) || team.getName().equals(match.getSecond_team().getName())) {
                        team.getTeamInfo()[Team.POINTS] += 1;
                        team.getTeamInfo()[Team.GAMES]++;
                        team.getTeamInfo()[Team.DRAWS]++;
                    }
                }
                break;

            case TWO:
                for (Team team : teams) {
                    if (team.getName().equals(match.getSecond_team().getName())) {
                        team.getTeamInfo()[Team.POINTS] += 3;
                        team.getTeamInfo()[Team.GAMES]++;
                        team.getTeamInfo()[Team.WINS]++;
                    } else if (team.getName().equals(match.getFirst_team().getName())) {
                        team.getTeamInfo()[Team.GAMES]++;
                        team.getTeamInfo()[Team.LOSSES]++;
                    }
                }
                break;
        }
    }


    public void resetInfo() {
        for (Team team : teams) {
            team.resetInfo();
        }
    }


    public ArrayList<Team> get_placement() {

        ArrayList<String> chosen_teams = new ArrayList<>();
        ArrayList<Team> placement = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++) {

            int max = Integer.MIN_VALUE;
            int pos = 0;

            for (int j = 0; j < teams.size(); j++) {
                if (!chosen_teams.contains(teams.get(j).getName())) {
                    if (teams.get(j).getTeamInfo()[Team.POINTS] > max) {
                        max = teams.get(j).getTeamInfo()[Team.POINTS];
                        pos = j;
                    } else if (teams.get(j).getTeamInfo()[Team.POINTS] == max) {
                        if (teams.get(j).getTeamInfo()[Team.GOALDIFF] > teams.get(pos).getTeamInfo()[Team.GOALDIFF]) {
                            max = teams.get(j).getTeamInfo()[Team.POINTS];
                            pos = j;
                        } else if (teams.get(j).getTeamInfo()[Team.GOALDIFF] == teams.get(pos).getTeamInfo()[Team.GOALDIFF]) {
                            if (teams.get(j).getGoal_scored() > teams.get(pos).getGoal_scored()) {
                                max = teams.get(j).getTeamInfo()[Team.POINTS];
                                pos = j;
                            }
                        }
                    }
                }
            }
            chosen_teams.add(teams.get(pos).getName());
            placement.add(teams.get(pos));
        }

        return placement;
    }
}
