package com.example.fifaqatar2022.Classes;

public class Scorer {

    private String name;
    private int goals;

    public Scorer(String name, int goals) {
        this.name = name;
        this.goals = goals;
    }

    public String getName() {
        return name;
    }

    public int getGoals() {
        return goals;
    }
}
