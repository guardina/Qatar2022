package com.example.fifaqatar2022.Screens;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Group;
import com.example.fifaqatar2022.Classes.Group_enum;
import com.example.fifaqatar2022.Classes.Match;
import com.example.fifaqatar2022.Classes.GroupCreator;
import com.example.fifaqatar2022.Classes.MyTimer;
import com.example.fifaqatar2022.Classes.Profile;
import com.example.fifaqatar2022.Classes.Result;
import com.example.fifaqatar2022.Classes.Team;
import com.example.fifaqatar2022.R;

import java.util.ArrayList;

public class GroupScreen extends AppCompatActivity {

    static Group_enum selected_group = null;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_group);

        GroupCreator pr = GroupCreator.getGC();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        ArrayList<Group> groups = pr.getAllGroups();

        TextView group_text = findViewById(R.id.group_text);

        Group group = new Group();

        switch (selected_group) {
            case A:
                group = groups.get(0);
                group.setGroup(Group_enum.A);
                break;
            case B:
                group = groups.get(1);
                group.setGroup(Group_enum.B);
                break;
            case C:
                group = groups.get(2);
                group.setGroup(Group_enum.C);
                break;
            case D:
                group = groups.get(3);
                group.setGroup(Group_enum.D);
                break;
            case E:
                group = groups.get(4);
                group.setGroup(Group_enum.E);
                break;
            case F:
                group = groups.get(5);
                group.setGroup(Group_enum.F);
                break;
            case G:
                group = groups.get(6);
                group.setGroup(Group_enum.G);
                break;
            case H:
                group = groups.get(7);
                group.setGroup(Group_enum.H);
                break;
        }

        group_text.append(group.getName());

        int posMatches = 0;
        ArrayList<ArrayList<Integer>> scores = new ArrayList<>();

        for (Match match : group.getMatches()) {
            scores.add(new ArrayList<>());
            scores.get(posMatches).add(sharedPreferences.getInt("goals " + match.getDate() + " " + match.getHomeTeam().getName(), MODE_PRIVATE));
            scores.get(posMatches).add(sharedPreferences.getInt("goals " + match.getDate() + " " + match.getVisitorTeam().getName(), MODE_PRIVATE));
            posMatches++;
        }
        group.updateGroup(scores);


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

        for (Team team : group.getPlacement()) {
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
            imageViewsMatchesHome.get(posMatch).setImageDrawable(match.getHomeTeam().getLogo());
            imageViewsMatchesVisitors.get(posMatch).setImageDrawable(match.getVisitorTeam().getLogo());

            textViewsMatchesHome.get(posMatch).append(match.getHomeTeam().getName());
            textViewsMatchesVisitors.get(posMatch).append(match.getVisitorTeam().getName());

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


        int posScoreH = 0;
        for (EditText scoreH : scoresHome) {
            scoreH.setText(String.valueOf(scores.get(posScoreH).get(0)));
            posScoreH++;
            if (MyTimer.getTimer().tooLate()) {
                scoreH.setEnabled(false);
            }
        }

        int posScoreV = 0;
        for (EditText scoreV : scoresVisitors) {
            scoreV.setText(String.valueOf(scores.get(posScoreV).get(1)));
            posScoreV++;
            if (MyTimer.getTimer().tooLate()) {
                scoreV.setEnabled(false);
            }
        }


        Button resetButton = findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyTimer.getTimer().tooLate()) {
                    for (EditText score : scoresHome) {
                        score.setText("");
                    }

                    for (EditText score : scoresVisitors) {
                        score.setText("");
                    }
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

        group.updatePlacementView(imageViewsPlacement, textViewsPlacement, cells);

        Button saveButton = findViewById(R.id.saveButton);

        Group finalGroup = group;

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finalGroup.resetInfo();
                int savePos = 0;

                Profile.getProfile().getPrediction().resetGroup_result(selected_group);

                for (Match match : finalGroup.getMatches()) {

                    Result result = new Result();

                    int scoreHome = 0;
                    int scoreVisitor = 0;

                    if (!scoresHome.get(savePos).getText().toString().equals("")) {
                        scoreHome = Integer.parseInt(scoresHome.get(savePos).getText().toString());
                    } else {
                        scoresHome.get(savePos).setText("0");
                    }

                    if (!scoresVisitors.get(savePos).getText().toString().equals("")) {
                        scoreVisitor = Integer.parseInt(scoresVisitors.get(savePos).getText().toString());
                    } else {
                        scoresVisitors.get(savePos).setText("0");
                    }

                    result.setHomeTeam(match.getHomeTeam().getName());
                    result.setVisitorTeam(match.getVisitorTeam().getName());

                    result.setHomeScore(String.valueOf(scoreHome));
                    result.setVisitorScore(String.valueOf(scoreVisitor));

                    result.setId(match.getHomeTeam().getName()+match.getVisitorTeam().getName());

                    editor.putInt("goals " + match.getDate() + " " + match.getHomeTeam().getName(), scoreHome);
                    editor.putInt("goals " + match.getDate() + " " + match.getVisitorTeam().getName(), scoreVisitor);
                    match.setHomeScore(String.valueOf(scoreHome));
                    match.setVisitorScore(String.valueOf(scoreVisitor));

                    if (scoreHome > scoreVisitor) {
                        finalGroup.match_result(Group.Result.ONE, match);
                    } else if (scoreHome == scoreVisitor) {
                        finalGroup.match_result(Group.Result.X, match);
                    } else {
                        finalGroup.match_result(Group.Result.TWO, match);
                    }

                    savePos++;

                    Profile.getProfile().getPrediction().addGroup_result(result, selected_group);
                }

                String firstTeam = finalGroup.getPlacement().get(0).getName();
                String secondTeam = finalGroup.getPlacement().get(1).getName();

                editor.putString("first team group " + finalGroup.getGroup(), firstTeam);
                editor.putString("second team group " + finalGroup.getGroup(), secondTeam);

                editor.commit();

                finalGroup.updatePlacementView(imageViewsPlacement, textViewsPlacement, cells);

                Toast.makeText(GroupScreen.this, "Risultati salvati!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
