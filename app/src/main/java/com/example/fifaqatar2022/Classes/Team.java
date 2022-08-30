package com.example.fifaqatar2022.Classes;

import android.graphics.drawable.Drawable;

public class Team {

    String name;
    Drawable logo;
    int points;
    int goals_scored;
    int goals_conceded;




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

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getGoals_scored() {
        return goals_scored;
    }

    public void scored_goals(int goals_scored) {
        this.goals_scored += goals_scored;
    }

    public int getGoals_conceded() {
        return goals_conceded;
    }

    public void conceded_goals(int goals_conceded) {
        this.goals_conceded += goals_conceded;
    }
}
