package com.example.fifaqatar2022.Classes;

import android.graphics.drawable.Drawable;

public class Team {

    static final int GAMES = 0;
    static final int WINS = 1;
    static final int DRAWS = 2;
    static final int LOSSES = 3;
    static final int GOALDIFF = 4;
    static final int POINTS = 5;

    private String name;
    private Drawable logo;
    private int goal_scored;

    private int[] teamInfo = new int[6];




    ///// CONSTRUCTOR /////

    public Team(String name) {
        this.name = name;
    }

///// GETTERS AND SETTER /////

    public String getName() {
        return name;
    }

    public Drawable getLogo() {
        return logo;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
    }

    public int getGoal_scored() {
        return goal_scored;
    }


    ///// OTHER METHODS /////

    public void scored_goals(int goals) {
        this.goal_scored += goals;
    }

    public int[] getTeamInfo() {
        return teamInfo;
    }

    public void resetInfo() {
        teamInfo[GAMES] = 0;
        teamInfo[WINS] = 0;
        teamInfo[DRAWS] = 0;
        teamInfo[LOSSES] = 0;
        teamInfo[GOALDIFF] = 0;
        teamInfo[POINTS] = 0;
        goal_scored = 0;
    }
}
