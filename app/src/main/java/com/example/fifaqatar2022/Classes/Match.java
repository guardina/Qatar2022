package com.example.fifaqatar2022.Classes;

public class Match {

    private String first_team;
    private String second_team;

    private String first_picture;
    private String second_picture;

    private String first_score;
    private String second_score;

    String matchday;
    String date;

    public Match(String matchday) {
        this.matchday = matchday;
    }

    public void setFirst_team(String first_team) {
        this.first_team = first_team;
    }

    public String getFirst_team() {
        return first_team;
    }

    public void setFirst_picture(String first_picture) {
        this.first_picture = first_picture;
    }

    public String getFirst_picture() {
        return first_picture;
    }

    public void setFirst_score(String first_score) {
        this.first_score = first_score;
    }

    public String getFirst_score() {
        return first_score;
    }



    public void setSecond_team(String second_team) {
        this.second_team = second_team;
    }

    public String getSecond_team() {
        return second_team;
    }

    public void setSecond_picture(String second_picture) {
        this.second_picture = second_picture;
    }

    public String getSecond_picture() {
        return second_picture;
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
