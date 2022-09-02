package com.example.fifaqatar2022.Screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.gms.common.api.Result;

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
            textViewsPlacement.get(posPlace).setText(team.getName());
            posPlace++;
        }


        ArrayList<ImageView> imageViewsMatchesHome = new ArrayList<>();
        ArrayList<ImageView> imageViewsMatchesVisitors = new ArrayList<>();

        imageViewsMatchesHome.add(findViewById(R.id.logoH1));
        imageViewsMatchesHome.add(findViewById(R.id.logoH2));
        imageViewsMatchesHome.add(findViewById(R.id.logoH3));
        imageViewsMatchesHome.add(findViewById(R.id.logoH4));
        imageViewsMatchesHome.add(findViewById(R.id.logoH5));
        imageViewsMatchesHome.add(findViewById(R.id.logoH6));

        imageViewsMatchesVisitors.add(findViewById(R.id.logoV1));
        imageViewsMatchesVisitors.add(findViewById(R.id.logoV2));
        imageViewsMatchesVisitors.add(findViewById(R.id.logoV3));
        imageViewsMatchesVisitors.add(findViewById(R.id.logoV4));
        imageViewsMatchesVisitors.add(findViewById(R.id.logoV5));
        imageViewsMatchesVisitors.add(findViewById(R.id.logoV6));

        ArrayList<TextView> textViewsMatchesHome = new ArrayList<>();
        ArrayList<TextView> textViewsMatchesVisitors = new ArrayList<>();

        textViewsMatchesHome.add(findViewById(R.id.nameH1));
        textViewsMatchesHome.add(findViewById(R.id.nameH2));
        textViewsMatchesHome.add(findViewById(R.id.nameH3));
        textViewsMatchesHome.add(findViewById(R.id.nameH4));
        textViewsMatchesHome.add(findViewById(R.id.nameH5));
        textViewsMatchesHome.add(findViewById(R.id.nameH6));

        textViewsMatchesVisitors.add(findViewById(R.id.nameV1));
        textViewsMatchesVisitors.add(findViewById(R.id.nameV2));
        textViewsMatchesVisitors.add(findViewById(R.id.nameV3));
        textViewsMatchesVisitors.add(findViewById(R.id.nameV4));
        textViewsMatchesVisitors.add(findViewById(R.id.nameV5));
        textViewsMatchesVisitors.add(findViewById(R.id.nameV6));

        int posMatch = 0;


        for (Match match : group.getMatches()) {
            imageViewsMatchesHome.get(posMatch).setImageDrawable(match.getFirst_team().getLogo());
            imageViewsMatchesVisitors.get(posMatch).setImageDrawable(match.getSecond_team().getLogo());

            textViewsMatchesHome.get(posMatch).append(match.getFirst_team().getName());
            textViewsMatchesVisitors.get(posMatch).append(match.getSecond_team().getName());

            posMatch++;
        }


        ArrayList<EditText> scoresHome = new ArrayList<>();
        ArrayList<EditText> scoresVisitors = new ArrayList<>();

        scoresHome.add(findViewById(R.id.scoreH1));
        scoresHome.add(findViewById(R.id.scoreH2));
        scoresHome.add(findViewById(R.id.scoreH3));
        scoresHome.add(findViewById(R.id.scoreH4));
        scoresHome.add(findViewById(R.id.scoreH5));
        scoresHome.add(findViewById(R.id.scoreH6));

        scoresVisitors.add(findViewById(R.id.scoreV1));
        scoresVisitors.add(findViewById(R.id.scoreV2));
        scoresVisitors.add(findViewById(R.id.scoreV3));
        scoresVisitors.add(findViewById(R.id.scoreV4));
        scoresVisitors.add(findViewById(R.id.scoreV5));
        scoresVisitors.add(findViewById(R.id.scoreV6));



        Button resetButton = findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText score : scoresHome) {
                    score.setText("");
                }

                for (EditText score : scoresVisitors) {
                    score.setText("");
                }
            }
        });






        ArrayList<ArrayList<TextView>> cells = new ArrayList<>();
        ArrayList<TextView> team1Info = new ArrayList<>();
        ArrayList<TextView> team2Info = new ArrayList<>();
        ArrayList<TextView> team3Info = new ArrayList<>();
        ArrayList<TextView> team4Info = new ArrayList<>();

        team1Info.add(findViewById(R.id.match1));
        team1Info.add(findViewById(R.id.win1));
        team1Info.add(findViewById(R.id.draw1));
        team1Info.add(findViewById(R.id.loss1));
        team1Info.add(findViewById(R.id.goals1));
        team1Info.add(findViewById(R.id.point1));

        team2Info.add(findViewById(R.id.match2));
        team2Info.add(findViewById(R.id.win2));
        team2Info.add(findViewById(R.id.draw2));
        team2Info.add(findViewById(R.id.loss2));
        team2Info.add(findViewById(R.id.goals2));
        team2Info.add(findViewById(R.id.point2));

        team3Info.add(findViewById(R.id.match3));
        team3Info.add(findViewById(R.id.win3));
        team3Info.add(findViewById(R.id.draw3));
        team3Info.add(findViewById(R.id.loss3));
        team3Info.add(findViewById(R.id.goals3));
        team3Info.add(findViewById(R.id.point3));

        team4Info.add(findViewById(R.id.match4));
        team4Info.add(findViewById(R.id.win4));
        team4Info.add(findViewById(R.id.draw4));
        team4Info.add(findViewById(R.id.loss4));
        team4Info.add(findViewById(R.id.goals4));
        team4Info.add(findViewById(R.id.point4));

        cells.add(team1Info);
        cells.add(team2Info);
        cells.add(team3Info);
        cells.add(team4Info);




        Button saveButton = findViewById(R.id.saveButton);

        Group finalGroup = group;

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int savePos = 0;

                for (Match match : finalGroup.getMatches()) {
                    int scoreHome = Integer.parseInt(scoresHome.get(savePos).getText().toString());
                    int scoreVisitor = Integer.parseInt(scoresVisitors.get(savePos).getText().toString());

                    match.setFirst_score(String.valueOf(scoreHome));
                    match.setSecond_score(String.valueOf(scoreVisitor));

                    if (scoreHome > scoreVisitor) {
                        finalGroup.match_result(Group.Result.ONE, match);
                    } else if (scoreHome == scoreVisitor) {
                        finalGroup.match_result(Group.Result.X, match);
                    } else {
                        finalGroup.match_result(Group.Result.TWO, match);
                    }

                    savePos++;
                }


                ArrayList<Team> finalPlacement = finalGroup.get_placement();


                int tablePos = 0;

                for (Team team : finalPlacement) {
                    imageViewsPlacement.get(tablePos).setImageDrawable(team.getLogo());
                    textViewsPlacement.get(tablePos).setText(team.getName());

                    int cellPos = 0;
                    int[] teamInfo = team.getTeamInfo();
                    for (TextView cell : cells.get(tablePos)) {
                        cell.setText(String.valueOf(teamInfo[cellPos++]));
                    }
                }
            }
        });

    }

}
