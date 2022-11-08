package com.example.fifaqatar2022.Classes;

import java.util.UUID;

public class Profile {

    public String firstName;
    public String lastName;
    public String userName;
    private int points;
    public int perfectResults;
    private String uuid;

    private Prediction prediction;

    static Profile profile = null;

    private Profile() {
        this.uuid = UUID.randomUUID().toString();
        this.prediction = new Prediction();
    }

    static public Profile getProfile() {
        if (profile == null) {
            profile = new Profile();
            return profile;
        }
        return profile;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void resetPoints() {
        this.points = 0;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void gotPerfectResult() {
        this.perfectResults++;
    }

    public Prediction getPrediction() {
        return prediction;
    }

}
