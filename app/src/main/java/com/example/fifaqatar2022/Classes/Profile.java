package com.example.fifaqatar2022.Classes;

import java.util.ArrayList;
import java.util.UUID;

public class Profile {

    public String firstName;
    public String lastName;
    public String userName;
    private double points;
    public int perfectResults;
    private String uuid;

    private Prediction prediction;
    private ArrayList<String> bombers;

    static Profile profile = null;

    private Profile() {
        this.uuid = UUID.randomUUID().toString();
        this.prediction = new Prediction();
        this.bombers = new ArrayList<>();
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

    public void addPoints(double points) {
        this.points += points;
    }

    public void resetPoints() {
        this.points = 0;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getPoints() {
        return points;
    }

    public void gotPerfectResult() {
        this.perfectResults++;
    }

    public ArrayList<String> getBombers() {
        return bombers;
    }

    public void addBomber(String bomber) {
        this.bombers.add(bomber);
    }

    public Prediction getPrediction() {
        return prediction;
    }

}
