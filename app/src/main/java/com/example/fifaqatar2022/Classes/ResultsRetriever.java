package com.example.fifaqatar2022.Classes;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ResultsRetriever extends AsyncTask<String, Integer, ArrayList<Match>> {

    private static ResultsRetriever rr = null;

    public ResultsRetriever() {}

    static public ResultsRetriever getRR() {
        if (rr == null) {
            rr = new ResultsRetriever();
            return rr;
        }
        return rr;
    }

    static ArrayList<Match> all_matches = new ArrayList();


    @Override
    protected ArrayList<Match> doInBackground(String... strings) {

        ArrayList<Match> results = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://onefootball.com/it/competizione/mondiale-12/partite").get();
            Elements matchdays = doc.select("of-match-cards-list");

            for (Element matchday : matchdays) {

                String day = matchday.select("h3[class^=title-7]").text();

                Elements matches = matchday.select("[class=simple-match-card__content]");
                for (Element match : matches) {
                    Match m = new Match(day);

                    Elements teams = match.select("[class*=teams-content]");
                    Elements dates = match.select("[class*=match-content]");


                    m.setDate(dates.select("span").text());

                    boolean first = true;

                    for (Element team : teams.select("[class=simple-match-card-team]")) {
                        String team_name = team.select("[class*=team__name]").text();
                        String team_score = team.select("[class*=team__score]").text();

                        Team newTeam = new Team(team_name);


                        if (first) {
                            m.setHomeTeam(newTeam);

                            if (team_score.equals("")) {
                                team_score = "-";
                            }

                            m.setHomeScore(team_score);
                        } else {
                            m.setVisitorTeam(newTeam);

                            if (team_score.equals("")) {
                                team_score = "-";
                            }

                            m.setVisitorScore(team_score);
                        }
                        first = false;
                    }
                    m.setId(m.getHomeTeam().getName()+m.getVisitorTeam().getName());
                    results.add(m);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        all_matches = results;
        return results;
    }


    public ArrayList<Match> getAllMatches() {
        return all_matches;
    }
}
