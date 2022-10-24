package com.example.fifaqatar2022.Classes;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;


public class GroupCreator {

    private static GroupCreator pr = null;

    public GroupCreator() {

        this.logos = new ArrayList<>();
    }

    static public GroupCreator getGC() {
        if (pr == null) {
            pr = new GroupCreator();
            return pr;
        }
        return pr;
    }

    private ArrayList<ArrayList<Drawable>> logos;
    private ArrayList<ArrayList<String>> names;

    public void setLogos(ArrayList<ArrayList<Drawable>> logos) {
        this.logos = logos;
    }

    public void setNames(ArrayList<ArrayList<String>> names) {
        this.names = names;
    }

    static ArrayList<Group> all_groups = new ArrayList<>();


    public void create_groups() {

        ArrayList<Group> placements = new ArrayList<>();
        String[] groups = {"A", "B", "C", "D", "E", "F", "G", "H"};


        for (int i = 0; i < names.size(); i++) {
            Group newGroup = new Group();
            newGroup.setName("Gruppo " + groups[i]);

            ArrayList<Team> teams = new ArrayList<>();
            ArrayList<String> team_names = new ArrayList<>();

            for (int j = 0; j < names.get(i).size(); j++) {
                String team_name = names.get(i).get(j);
                Drawable logo = logos.get(i).get(j);

                Team newTeam = new Team(team_name);
                newTeam.setLogo(logo);

                teams.add(newTeam);
                team_names.add(team_name);
            }

            newGroup.setTeams(teams);
            newGroup.setTeam_names(team_names);


            ResultsRetriever rr = ResultsRetriever.getRR();

            ArrayList<Match> matches = rr.getAllMatches();

            for (Match match : matches) {
                if (newGroup.getTeam_names().contains(match.getHomeTeam().getName())) {
                    newGroup.add_match(match);
                }
            }

            placements.add(newGroup);
        }

        all_groups = placements;
    }


    public ArrayList<Group> getAllGroups() {
        return all_groups;
    }
}
