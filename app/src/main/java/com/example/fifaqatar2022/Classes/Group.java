package com.example.fifaqatar2022.Classes;

import java.util.ArrayList;

public class Group {

    enum Result {ONE, X, TWO}

    String name;
    String[] teams = new String[4];
    String[] placement = new String[4];
    int[] points = new int[4];
    int[] goals_scored = new int[4];
    int[] goals_conceded = new int[4];

    ArrayList<Match> match_groups;

    public Group() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setTeams(ArrayList<String> teams) {
        int pos = 0;
        for (String team : teams) {
            this.teams[pos++] = team;
        }
    }

    public void add_match(Match match) {
        this.match_groups.add(match);
    }

    public void match_result(Result result, Match match) {

        for (int i = 0; i < teams.length; i++) {
            if (teams[i].equals(match.getFirst_team())) {
                goals_scored[i] += Integer.getInteger(match.getFirst_score());
                goals_conceded[i] += Integer.getInteger(match.getSecond_score());
            }

            if (teams[i].equals(match.getSecond_team())) {
                goals_scored[i] += Integer.getInteger(match.getSecond_score());
                goals_conceded[i] += Integer.getInteger(match.getFirst_score());
            }
        }


        switch (result) {
            case ONE:
                for (int i = 0; i < teams.length; i++) {
                    if (teams[i].equals(match.getFirst_team())) {
                        points[i] += 3;
                    }
                }
                break;

            case X:
                for (int i = 0; i < teams.length; i++) {
                    if (teams[i].equals(match.getFirst_team()) || teams[i].equals(match.getSecond_team())) {
                        points[i] += 1;
                    }
                }
                break;

            case TWO:
                for (int i = 0; i < teams.length; i++) {
                    if (teams[i].equals(match.getSecond_team())) {
                        points[i] += 3;
                    }
                }
                break;
        }
    }


    public void do_placement() {

        int max = Integer.MIN_VALUE;
        int pos = 0;

        for (int i = 0; i < teams.length; i++) {
            for (int j = i; j < teams.length-i; j++) {
                if (points[j] > max) {
                    max = points[j];
                    pos = j;
                }
            }
            placement[i] = teams[pos];
        }
    }

    public String[] get_placement() {
        return placement;
    }

}
