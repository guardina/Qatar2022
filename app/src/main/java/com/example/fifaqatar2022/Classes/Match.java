package com.example.fifaqatar2022.Classes;

public class Match {

    private Team first_team;
    private Team second_team;

    private String first_score;
    private String second_score;

    String matchday;
    String date;

    public Match(String matchday) {
        this.matchday = matchday;
    }

    public void setFirst_team(Team first_team) {
        this.first_team = first_team;
    }

    public Team getFirst_team() {
        return first_team;
    }

    public void setFirst_score(String first_score) {
        this.first_score = first_score;
    }

    public String getFirst_score() {
        return first_score;
    }



    public void setSecond_team(Team second_team) {
        this.second_team = second_team;
    }

    public Team getSecond_team() {
        return second_team;
    }


    public void setSecond_score(String second_score) {
        this.second_score = second_score;
    }

    public String getSecond_score() {
        return second_score;
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
}
