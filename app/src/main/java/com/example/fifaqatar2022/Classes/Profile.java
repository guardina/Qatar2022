package com.example.fifaqatar2022.Classes;

import java.util.UUID;

public class Profile {

    public String firstName;
    public String lastName;
    public String userName;
    public int points;
    public int perfectResults;
    final private String uuid;

    static Profile profile = null;

    private Profile() {
        this.uuid = UUID.randomUUID().toString();
    }

    static public Profile getProfile() {
        if (profile == null) {
            return new Profile();
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

    public void addPoints(int points) {
        this.points += points;
    }

    public void gotPerfectResult() {
        this.perfectResults++;
    }

}
