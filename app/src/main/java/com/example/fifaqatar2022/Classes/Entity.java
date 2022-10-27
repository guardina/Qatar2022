package com.example.fifaqatar2022.Classes;

public class Entity {

    String username;
    int points;

    public Entity(String username, String points) {
        this.username = username;
        this.points = Integer.parseInt(points);
    }

    public boolean betterThan(Entity other) {
        return this.points > other.points;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
