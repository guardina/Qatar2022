package com.example.fifaqatar2022.Classes;

import java.util.ArrayList;

public class Prediction {

    ArrayList<ArrayList<Result>> group_results;
    ArrayList<Result> eight_results;
    ArrayList<Result> fourth_results;
    ArrayList<Result> semi_results;
    ArrayList<Result> final_results;


    public Prediction() {
        this.group_results = new ArrayList<>();
        for(int i = 0; i<8; i++) {
            group_results.add(new ArrayList<>());
        }

        this.eight_results = new ArrayList<>();
        this.fourth_results = new ArrayList<>();
        this.semi_results = new ArrayList<>();
        this.final_results = new ArrayList<>();
    }


    public ArrayList<ArrayList<Result>> getGroup_results() {
        return group_results;
    }

    public void addGroup_result(Result group_result, Group_enum group_enum) {
        int group = 0;

        switch (group_enum) {
            case A:
                group = 0;
                break;
            case B:
                group = 1;
                break;
            case C:
                group = 2;
                break;
            case D:
                group = 3;
                break;
            case E:
                group = 4;
                break;
            case F:
                group = 5;
                break;
            case G:
                group = 6;
                break;
            case H:
                group = 7;
                break;
        }
        group_results.get(group).add(group_result);
    }


    public void addFinals_result(Result result, int round) {
        ArrayList<Result> ref;

        switch (round) {
            case 0:
                ref = eight_results;
                break;
            case 1:
                ref = fourth_results;
                break;
            case 2:
                ref = semi_results;
                break;
            case 3:
                ref = final_results;
                break;
            default:
                ref = null;
        }

        if (ref != null) {
            ref.add(result);
        }
    }


    public void resetGroup_result(Group_enum group_enum) {
        int group = 0;

        switch (group_enum) {
            case A:
                group = 0;
                break;
            case B:
                group = 1;
                break;
            case C:
                group = 2;
                break;
            case D:
                group = 3;
                break;
            case E:
                group = 4;
                break;
            case F:
                group = 5;
                break;
            case G:
                group = 6;
                break;
            case H:
                group = 7;
                break;
        }
        group_results.get(group).clear();
    }

    public void resetGroup_results() {
        this.group_results = new ArrayList<>();
    }

    public ArrayList<Result> getEight_results() {
        return eight_results;
    }

    public void setEight_results(ArrayList<Result> eight_results) {
        this.eight_results = eight_results;
    }

    public ArrayList<Result> getFourth_results() {
        return fourth_results;
    }

    public void setFourth_results(ArrayList<Result> fourth_results) {
        this.fourth_results = fourth_results;
    }

    public ArrayList<Result> getSemi_results() {
        return semi_results;
    }

    public void setSemi_results(ArrayList<Result> semi_results) {
        this.semi_results = semi_results;
    }

    public ArrayList<Result> getFinal_results() {
        return final_results;
    }

    public void setFinal_results(ArrayList<Result> final_results) {
        this.final_results = final_results;
    }
}
