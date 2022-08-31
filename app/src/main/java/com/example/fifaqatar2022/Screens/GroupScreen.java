package com.example.fifaqatar2022.Screens;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Group;
import com.example.fifaqatar2022.Classes.Group_enum;
import com.example.fifaqatar2022.Classes.Match;
import com.example.fifaqatar2022.Classes.PlacementsRetriever;
import com.example.fifaqatar2022.Classes.ResultsRetriever;
import com.example.fifaqatar2022.Classes.Team;
import com.example.fifaqatar2022.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GroupScreen extends AppCompatActivity {

    static Group_enum selected_group = null;

    static boolean executed = false;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_group);


        ResultsRetriever rr = ResultsRetriever.getRR();
        PlacementsRetriever pr = PlacementsRetriever.getPR();


        try {
            ArrayList<Match> list = rr.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            if (!executed) {
                ArrayList<Group> list = pr.execute().get();
                executed = true;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ArrayList<Group> groups = pr.getAllGroups();
        ArrayList<Match> matches = rr.getAllMatches();

        TextView group_text = findViewById(R.id.group_text);

        Group group = new Group();

        switch (selected_group) {
            case A:
                group = groups.get(0);
                break;
            case B:
                group = groups.get(1);
                break;
            case C:
                group = groups.get(2);
                break;
            case D:
                group = groups.get(3);
                break;
            case E:
                group = groups.get(4);
                break;
            case F:
                group = groups.get(5);
                break;
            case G:
                group = groups.get(6);
                break;
            case H:
                group = groups.get(7);
                break;
        }

        group_text.append(group.getName());


        ArrayList<ImageView> imageViewsPlacement = new ArrayList<>();

        imageViewsPlacement.add(findViewById(R.id.first_logo));
        imageViewsPlacement.add(findViewById(R.id.second_logo));
        imageViewsPlacement.add(findViewById(R.id.third_logo));
        imageViewsPlacement.add(findViewById(R.id.fourth_logo));

        ArrayList<TextView> textViewsPlacement = new ArrayList<>();

        textViewsPlacement.add(findViewById(R.id.first_name));
        textViewsPlacement.add(findViewById(R.id.second_name));
        textViewsPlacement.add(findViewById(R.id.third_name));
        textViewsPlacement.add(findViewById(R.id.fourth_name));

        int posPlace = 0;

        for (Team team : group.getTeams()) {
            imageViewsPlacement.get(posPlace).setImageDrawable(team.getLogo());
            textViewsPlacement.get(posPlace).append(team.getName());
            posPlace++;
        }


        ArrayList<ImageView> imageViewsMatches = new ArrayList<>();

        imageViewsMatches.add(findViewById(R.id.homeLogo1));
        /*imageViewsMatches.add(findViewById(R.id.homeLogo2));
        imageViewsMatches.add(findViewById(R.id.homeLogo3));
        imageViewsMatches.add(findViewById(R.id.homeLogo4));
        imageViewsMatches.add(findViewById(R.id.homeLogo5));
        imageViewsMatches.add(findViewById(R.id.homeLogo6));*/

        int posMatch = 0;

        imageViewsMatches.get(0).setImageDrawable(group.getMatches().get(0).getFirst_team().getLogo());

        /*
        for (Match match : group.getMatches()) {
            imageViewsMatches.get(posMatch).setImageDrawable(match.getFirst_team().getLogo());
            posMatch++;
        }
        */
    }

}
