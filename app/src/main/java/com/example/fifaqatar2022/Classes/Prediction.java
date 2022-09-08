package com.example.fifaqatar2022.Classes;

public class Prediction {

    final int A = 0;
    final int B = 1;
    final int C = 2;
    final int D = 3;
    final int E = 4;
    final int F = 5;
    final int G = 6;
    final int H = 7;


    private Team[] firstTeams = new Team[8];
    private Team[] secondTeams = new Team[8];

    private Prediction prediction = null;

    public Prediction() {

    }

    public Prediction getPrediction() {
        if (prediction == null) {
            return new Prediction();
        }
        return prediction;
    }

    public Team[] getFirstTeams() {
        return firstTeams;
    }

    public Team[] getSecondTeams() {
        return secondTeams;
    }

    public void setTeams(Team first, Team second, int group) {
        firstTeams[group] = first;
        secondTeams[group] = second;
    }
}
