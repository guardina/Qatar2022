package com.example.fifaqatar2022.Classes;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Group {

    public enum Result {ONE, X, TWO}

    String name;
    ArrayList<Team> teams = new ArrayList<>();
    ArrayList<String> team_names = new ArrayList<>();
    int group;

    ArrayList<Match> matches = new ArrayList<>();


    ///// CONSTRUCTOR /////

    public Group() {

    }

    public Group(Group_enum group) {
        switch (group) {
            case A:
                this.group = 0;
                break;
            case B:
                this.group = 1;
                break;
            case C:
                this.group = 2;
                break;
            case D:
                this.group = 3;
                break;
            case E:
                this.group = 4;
                break;
            case F:
                this.group = 5;
                break;
            case G:
                this.group = 6;
                break;
            case H:
                this.group = 7;
                break;
        }
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

    public int getGroup() {
        return group;
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

        int goalsHome = 0;
        int goalsVisitor = 0;

        if (!match.getFirst_score().equals("-")) {
            goalsHome = Integer.parseInt(match.getFirst_score());
        }
        if (!match.getSecond_score().equals("-")) {
            goalsVisitor = Integer.parseInt(match.getSecond_score());
        }

        for (Team team : teams) {
            if (team.getName().equals(match.getFirst_team().getName())) {
                team.getTeamInfo()[Team.GOALDIFF] += goalsHome - goalsVisitor;
                team.scored_goals(goalsHome);

            } else if (team.getName().equals(match.getSecond_team().getName())) {
                team.getTeamInfo()[Team.GOALDIFF] += goalsVisitor - goalsHome;
                team.scored_goals(goalsVisitor);
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


    public ArrayList<Team> getPlacement() {

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

    public void updatePlacementView(ArrayList<ImageView> logoViews, ArrayList<TextView> nameViews, ArrayList<ArrayList<TextView>> cells) {
        ArrayList<Team> placement = getPlacement();
        int tablePos = 0;

        for (Team team : placement) {
            logoViews.get(tablePos).setImageDrawable(team.getLogo());
            nameViews.get(tablePos).setText(team.getName());

            int cellPos = 0;
            int[] teamInfo = team.getTeamInfo();
            for (TextView cell : cells.get(tablePos)) {
                cell.setText(String.valueOf(teamInfo[cellPos++]));
            }
            tablePos++;
        }
    }


    public void updateGroup(ArrayList<ArrayList<Integer>> scores) {

        this.resetInfo();

        int posMatch = 0;

        for (ArrayList list : scores) {

            int scoreHome = (int) list.get(0);
            int scoreVisitor = (int) list.get(1);
            Match match = matches.get(posMatch);

            if (scoreHome > scoreVisitor) {
                this.match_result(Group.Result.ONE, match);
            } else if (scoreHome == scoreVisitor) {
                this.match_result(Group.Result.X, match);
            } else {
                this.match_result(Group.Result.TWO, match);
            }

            posMatch++;
        }
    }

}
