package com.example.fifaqatar2022.Classes;

public class Match {

    private Team homeTeam;
    private Team visitorTeam;

    private String homeScore;
    private String visitorScore;

    String matchday;
    String date;

    private String id;

    public Match() {}

    public Match(String matchday) {
        this.matchday = matchday;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getHomeScore() {
        return homeScore;
    }



    public void setVisitorTeam(Team visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public Team getVisitorTeam() {
        return visitorTeam;
    }


    public void setVisitorScore(String visitorScore) {
        this.visitorScore = visitorScore;
    }

    public String getVisitorScore() {
        return visitorScore;
    }



    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getMatchday() {
        return matchday;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
