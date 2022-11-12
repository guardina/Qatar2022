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

    public ResultsRetriever() {
        Match match1 = new Match();
        match1.setId("QatarEcuador");
        match1.setHomeScore("0");
        match1.setVisitorScore("0");
        match1.setHomeTeam(new Team("Qatar"));
        match1.setVisitorTeam(new Team("Ecuador"));

        Match match2 = new Match();
        match2.setId("SenegalOlanda");
        match2.setHomeScore("0");
        match2.setVisitorScore("1");
        match2.setHomeTeam(new Team("Senegal"));
        match2.setVisitorTeam(new Team("Olanda"));

        Match match3 = new Match();
        match3.setId("QatarSenegal");
        match3.setHomeScore("1");
        match3.setVisitorScore("2");
        match3.setHomeTeam(new Team("Qatar"));
        match3.setVisitorTeam(new Team("Senegal"));

        Match match4 = new Match();
        match4.setId("OlandaEcuador");
        match4.setHomeScore("3");
        match4.setVisitorScore("0");
        match4.setHomeTeam(new Team("Olanda"));
        match4.setVisitorTeam(new Team("Ecuador"));

        Match match5 = new Match();
        match5.setId("EcuadorSenegal");
        match5.setHomeScore("2");
        match5.setVisitorScore("0");
        match5.setHomeTeam(new Team("Ecuador"));
        match5.setVisitorTeam(new Team("Senegal"));

        Match match6 = new Match();
        match6.setId("OlandaQatar");
        match6.setHomeScore("1");
        match6.setVisitorScore("0");
        match6.setHomeTeam(new Team("Olanda"));
        match6.setVisitorTeam(new Team("Qatar"));

        all_matches.add(match1);
        all_matches.add(match2);
        all_matches.add(match3);
        all_matches.add(match4);
        all_matches.add(match5);
        all_matches.add(match6);
    }

    static public ResultsRetriever getRR() {
        if (rr == null) {
            rr = new ResultsRetriever();
            return rr;
        }
        return rr;
    }

    ArrayList<Match> all_matches = new ArrayList();


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
