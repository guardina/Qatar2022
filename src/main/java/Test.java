import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Test {


    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://onefootball.com/it/competizione/mondiale-12/partite").get();
            Elements matchdays = doc.select("of-match-cards-list");

            for (Element matchday : matchdays) {
                System.out.println(matchday.select("h3[class^=title-7]").text() + "\n");

                Elements matches = matchday.select("[class^=simple-match-card__content]");
                for (Element match : matches) {
                    Elements teams = match.select("[class^=simple-match-card__teams-content]");
                    Elements dates = match.select("[class^=simple-match-card__match-content]");

                    System.out.println(dates.select("span").text());
                    for (Element child : teams.select("[class^=title-8]")) {
                        System.out.println(child.text());
                    }
                    System.out.println();
                }
                System.out.println("\n\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
