package com.example.fifaqatar2022.Classes;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class FinalsResultsRetriever extends AsyncTask<String, Integer, ArrayList<ArrayList<Result>>> {

    private static FinalsResultsRetriever frr = null;

    public FinalsResultsRetriever() {
        this.initialize();
    }

    static public FinalsResultsRetriever getFRR() {
        if (frr == null) {
            frr = new FinalsResultsRetriever();
            return frr;
        }
        return frr;
    }


    ArrayList<ArrayList<String>> all_links = new ArrayList<>();

    ArrayList<String> eight_links = new ArrayList<>();
    ArrayList<String> fourth_links = new ArrayList<>();
    ArrayList<String> semi_links = new ArrayList<>();
    ArrayList<String> final_links = new ArrayList<>();

    ArrayList<ArrayList<Result>> all_final_results = new ArrayList<>();

    void initialize() {

        eight_links.add("https://onefootball.com/it/partite?date=2022-12-03");
        eight_links.add("https://onefootball.com/it/partite?date=2022-12-04");
        eight_links.add("https://onefootball.com/it/partite?date=2022-12-05");
        eight_links.add("https://onefootball.com/it/partite?date=2022-12-06");

        fourth_links.add("https://onefootball.com/it/partite?date=2022-12-09");
        fourth_links.add("https://onefootball.com/it/partite?date=2022-12-10");

        semi_links.add("https://onefootball.com/it/partite?date=2022-12-13");
        semi_links.add("https://onefootball.com/it/partite?date=2022-12-14");

        final_links.add("https://onefootball.com/it/partite?date=2022-12-17");
        final_links.add("https://onefootball.com/it/partite?date=2022-12-18");

        all_links.add(eight_links);
        all_links.add(fourth_links);
        all_links.add(semi_links);
        all_links.add(final_links);


        for (int i = 0; i<4; i++) {
            all_final_results.add(new ArrayList<>());
        }
    }

    @Override
    protected ArrayList<ArrayList<Result>> doInBackground(String... strings) {
        ArrayList<ArrayList<Result>> results = new ArrayList<>();
        for (int i = 0; i < all_final_results.size(); i++) {
            results.add(new ArrayList<>());
        }

        int round = 0;
        for (ArrayList<String> round_links : all_links) {
            for (String link : round_links) {
                try {
                    Document doc = Jsoup.connect(link).get();

                    Elements competitions = doc.select("of-match-cards-list");

                    for (Element competition : competitions) {
                        String competition_name = competition.select("h2[class^=title-6]").text();

                        if (competition_name.equals("Mondiale")) {

                            Elements matches = competition.select("[class=simple-match-card__content]");

                            for (Element match : matches) {
                                Result result = new Result();

                                Elements teams = match.select("[class*=teams-content]");

                                boolean first = true;
                                boolean done = false;

                                for (Element team : teams.select("[class=simple-match-card-team]")) {
                                    String team_name = team.select("[class*=team__name]").text();
                                    String team_score = team.select("[class*=team__score]").text();

                                    if(team_score.contains(" ")){
                                        team_score = team_score.substring(0, team_score.indexOf(" "));
                                    }

                                    if (first) {
                                        result.setHomeTeam(team_name);

                                        if (team_score.equals("")) {
                                            team_score = "-";
                                        }

                                        result.setHomeScore(team_score);

                                        first = false;

                                    } else {
                                        result.setVisitorTeam(team_name);

                                        if (team_score.equals("")) {
                                            team_score = "-";
                                        }

                                        result.setVisitorScore(team_score);
                                        done = true;
                                    }

                                    if (done) {
                                        if (result.getHomeTeam() != null && result.getVisitorTeam() != null) {
                                            result.setId(result.getHomeTeam()+result.getVisitorTeam());
                                            results.get(round).add(result);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            round++;
        }

        all_final_results = results;

        return results;
    }

    public ArrayList<ArrayList<Result>> getAll_final_matches() {
        return all_final_results;
    }
}
