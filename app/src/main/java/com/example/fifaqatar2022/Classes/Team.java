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


    ///// OTHER METHODS /////

    public int[] getTeamInfo() {
        return teamInfo;
    }
}
