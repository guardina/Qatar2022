package com.example.fifaqatar2022.Classes;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ResultsRetriever extends AsyncTask<String, Integer, ArrayList<ArrayList<Match>>> {

    private static ResultsRetriever ir = null;

    public ResultsRetriever() {}

    static public ResultsRetriever getRR() {
        if (ir == null) {
            ResultsRetriever newIr = new ResultsRetriever();
            newIr.execute();
            return newIr;
        }
        return ir;
    }

    static ArrayList<ArrayList<Match>> all_matches = new ArrayList();


    @Override
    protected ArrayList<ArrayList<Match>> doInBackground(String... strings) {

        ArrayList<ArrayList<Match>> results = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://onefootball.com/it/competizione/mondiale-12/partite").get();
            Elements matchdays = doc.select("of-match-cards-list");

            int i = 0;

            for (Element matchday : matchdays) {

                String day = matchday.select("h3[class^=title-7]").text();

                results.add(new ArrayList<>());

                Elements matches = matchday.select("[class=simple-match-card__content]");
                for (Element match : matches) {
                    Match m = new Match(day);

                    Elements teams = match.select("[class*=teams-content]");
                    Elements dates = match.select("[class*=match-content]");


                    m.setDate(dates.select("span").text());

                    boolean first = true;

                    for (Element team : teams.select("[class=simple-match-card-team]")) {
                        String team_name = team.select("[class*=team__name]").text();
                        String score = team.select(("[class*=team__score]")).text();
                        String picture_link = team.select(".of-image__img").first().absUrl("src");


                        if (first) {
                            m.setFirst_team(team_name);

                            if (score.equals("")) {
                                score = "-";
                            }

                            m.setFirst_score(score);
                            m.setFirst_picture(picture_link);
                        } else {
                            m.setSecond_team(team_name);

                            if (score.equals("")) {
                                score = "-";
                            }

                            m.setSecond_score(score);
                            m.setSecond_picture(picture_link);
                        }
                        first = false;
                    }
                    results.get(i).add(m);
                }
                i++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        all_matches = results;
        return results;
    }


    public ArrayList<ArrayList<Match>> getAllMatches() {
        return all_matches;
    }
}
