package com.example.fifaqatar2022.Classes;

public class Entity {

    String username;
    double points;

    public Entity(String username, String points) {
        this.username = username;
        this.points = Double.parseDouble(points);
    }

    public boolean betterThan(Entity other) {
        return this.points > other.points;
    }

    public String getUsername() {
        return username;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
