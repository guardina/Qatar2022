package com.example.fifaqatar2022.Classes;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class PlacementsRetriever extends AsyncTask<String, Integer, ArrayList<Group>> {

    private static PlacementsRetriever cr = null;

    public PlacementsRetriever() {}

    static public PlacementsRetriever getCR() {
        if (cr == null) {
            PlacementsRetriever newCr = new PlacementsRetriever();
            newCr.execute();
            return newCr;
        }
        return cr;
    }


    static ArrayList<Group> all_groups = new ArrayList<>();

    @Override
    protected ArrayList<Group> doInBackground(String... strings) {

        ArrayList<Group> placements = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://onefootball.com/it/competizione/mondiale-12/classifica").get();

            for (Element group : doc.select("[class*=xpa-layout-container__switch--standings]")) {

                Group newGroup = new Group();

                String group_name = group.select("[class*=standings__table-header-text]").text();
                newGroup.setName(group_name);

                ArrayList<String> teams = new ArrayList<>();

                for (Element team : group.select("[class*=standings__row--link]")) {

                    String team_name = team.select("[class*=standings__team-name]").text();
                    teams.add(team_name);
                }

                newGroup.setTeams(teams);

                placements.add(newGroup);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        all_groups = placements;
        return placements;
    }

    public ArrayList<Group> getAllGroups() {
        return all_groups;
    }
}
