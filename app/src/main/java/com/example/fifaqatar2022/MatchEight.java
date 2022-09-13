package com.example.fifaqatar2022;

import com.example.fifaqatar2022.Classes.Team;

public class MatchEight {

    Team homeTeam;
    Team visitorTeam;

    boolean passesFirst;
    boolean firstWin;


    public MatchEight() {

    }

    public void setPassesFirst(boolean passesFirst) {
        this.passesFirst = passesFirst;
    }

    public boolean getPassesFirst() {
        return passesFirst;
    }

    public void setFirstWin(boolean firstWin) {
        this.firstWin = firstWin;
    }

    public void setHomeTeam(Team team) {
        this.homeTeam = team;
    }

    public void setVisitorTeam(Team team) {
        this.visitorTeam = team;
    }
}
