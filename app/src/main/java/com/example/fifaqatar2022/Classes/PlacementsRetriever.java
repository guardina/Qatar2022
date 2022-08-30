package com.example.fifaqatar2022.Classes;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PlacementsRetriever extends AsyncTask<String, Integer, ArrayList<Group>> {

    private static PlacementsRetriever pr = null;

    public PlacementsRetriever() {}

    static public PlacementsRetriever getPR() {
        if (pr == null) {
            return new PlacementsRetriever();
        }
        return pr;
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

                ArrayList<Team> teams = new ArrayList<>();
                ArrayList<String> team_names = new ArrayList<>();

                for (Element team : group.select("[class*=standings__row--link]")) {

                    String team_name = team.select("[class*=standings__team-name]").text();
                    String logo_link = team.select("img").first().absUrl("src");

                    InputStream is = (InputStream) new URL(logo_link).getContent();
                    Drawable logo = Drawable.createFromStream(is, "image");

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
                    if (newGroup.getTeam_names().contains(match.getFirst_team())) {
                        newGroup.add_match(match);
                    }
                }

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
