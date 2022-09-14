package com.example.fifaqatar2022.Classes;

public class MatchFinals {

    Team homeTeam;
    Team visitorTeam;

    boolean passesFirst;
    boolean firstTeamWon;


    public MatchFinals() {

    }

    public void setPassesFirst(boolean passesFirst) {
        this.passesFirst = passesFirst;
    }

    public boolean getPassesFirst() {
        return passesFirst;
    }

    public void setFirstTeamWon(boolean firstWin) {
        this.firstTeamWon = firstWin;
    }

    public boolean getFirstTeamWon() {
        return firstTeamWon;
    }

    public void setHomeTeam(Team team) {
        this.homeTeam = team;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setVisitorTeam(Team team) {
        this.visitorTeam = team;
    }

    public Team getVisitorTeam() {
        return visitorTeam;
    }
}
