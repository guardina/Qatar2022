package com.example.fifaqatar2022.Screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Group;
import com.example.fifaqatar2022.Classes.Group_enum;
import com.example.fifaqatar2022.Classes.Match;
import com.example.fifaqatar2022.Classes.PlacementsRetriever;
import com.example.fifaqatar2022.Classes.ResultsRetriever;
import com.example.fifaqatar2022.Classes.Team;
import com.example.fifaqatar2022.Classes.MatchFinals;
import com.example.fifaqatar2022.R;
import com.google.api.Distribution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class GroupSelectionScreen extends AppCompatActivity {

    static boolean executed = false;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_phase_selection);

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


        Button chooseGroupsButton = findViewById(R.id.chooseGroupsButton);
        chooseGroupsButton.setSelected(true);
        Button chooseFinalsButton = findViewById(R.id.chooseFinalsButton);

        ScrollView groupsView = findViewById(R.id.groupsView);
        ScrollView finalsView = findViewById(R.id.eightsView);
        LinearLayout buttonLayout = findViewById(R.id.buttonLayout);



        chooseGroupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!chooseGroupsButton.isSelected()) {
                    chooseGroupsButton.setSelected(true);
                    chooseFinalsButton.setSelected(false);
                }
                groupsView.setVisibility(View.VISIBLE);
                finalsView.setVisibility(View.GONE);
                buttonLayout.setVisibility(View.GONE);
            }
        });


        chooseFinalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!chooseFinalsButton.isSelected()) {
                    chooseFinalsButton.setSelected(true);
                    chooseGroupsButton.setSelected(false);
                }
                finalsView.setVisibility(View.VISIBLE);
                groupsView.setVisibility(View.GONE);
                buttonLayout.setVisibility(View.VISIBLE);
            }
        });



        Button buttonA = findViewById(R.id.buttonA);
        Button buttonB = findViewById(R.id.buttonB);
        Button buttonC = findViewById(R.id.buttonC);
        Button buttonD = findViewById(R.id.buttonD);
        Button buttonE = findViewById(R.id.buttonE);
        Button buttonF = findViewById(R.id.buttonF);
        Button buttonG = findViewById(R.id.buttonG);
        Button buttonH = findViewById(R.id.buttonH);


        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);


        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.A;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.B;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.C;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.D;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.E;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.F;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.G;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.H;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });



        Button groupReloadButton = findViewById(R.id.groupReloadButton);

        groupReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });




        ScrollView eightView = findViewById(R.id.eightsView);
        LinearLayout fourthLayout = findViewById(R.id.fourthLayout);
        LinearLayout semiLayout = findViewById(R.id.semiLayout);
        LinearLayout finalsLayout = findViewById(R.id.finalsLayout);





        ////// EIGHT FINALS SCREEN ///////
        ArrayList<ImageView> logosHome = new ArrayList<>();
        ArrayList<TextView> namesHome = new ArrayList<>();

        ArrayList<ImageView> logosVisitor = new ArrayList<>();
        ArrayList<TextView> namesVisitor = new ArrayList<>();

        logosHome.add(findViewById(R.id.eightLogoH1));
        logosHome.add(findViewById(R.id.eightLogoH2));
        logosHome.add(findViewById(R.id.eightLogoH3));
        logosHome.add(findViewById(R.id.eightLogoH4));
        logosHome.add(findViewById(R.id.eightLogoH5));
        logosHome.add(findViewById(R.id.eightLogoH6));
        logosHome.add(findViewById(R.id.eightLogoH7));
        logosHome.add(findViewById(R.id.eightLogoH8));

        namesHome.add(findViewById(R.id.eightNameH1));
        namesHome.add(findViewById(R.id.eightNameH2));
        namesHome.add(findViewById(R.id.eightNameH3));
        namesHome.add(findViewById(R.id.eightNameH4));
        namesHome.add(findViewById(R.id.eightNameH5));
        namesHome.add(findViewById(R.id.eightNameH6));
        namesHome.add(findViewById(R.id.eightNameH7));
        namesHome.add(findViewById(R.id.eightNameH8));

        logosVisitor.add(findViewById(R.id.eightLogoV1));
        logosVisitor.add(findViewById(R.id.eightLogoV2));
        logosVisitor.add(findViewById(R.id.eightLogoV3));
        logosVisitor.add(findViewById(R.id.eightLogoV4));
        logosVisitor.add(findViewById(R.id.eightLogoV5));
        logosVisitor.add(findViewById(R.id.eightLogoV6));
        logosVisitor.add(findViewById(R.id.eightLogoV7));
        logosVisitor.add(findViewById(R.id.eightLogoV8));

        namesVisitor.add(findViewById(R.id.eightNameV1));
        namesVisitor.add(findViewById(R.id.eightNameV2));
        namesVisitor.add(findViewById(R.id.eightNameV3));
        namesVisitor.add(findViewById(R.id.eightNameV4));
        namesVisitor.add(findViewById(R.id.eightNameV5));
        namesVisitor.add(findViewById(R.id.eightNameV6));
        namesVisitor.add(findViewById(R.id.eightNameV7));
        namesVisitor.add(findViewById(R.id.eightNameV8));




        ArrayList<EditText> eightScoresHome = new ArrayList<>();
        ArrayList<EditText> eightScoresVisitor = new ArrayList<>();

        eightScoresHome.add(findViewById(R.id.eightScoreH1));
        eightScoresHome.add(findViewById(R.id.eightScoreH2));
        eightScoresHome.add(findViewById(R.id.eightScoreH3));
        eightScoresHome.add(findViewById(R.id.eightScoreH4));
        eightScoresHome.add(findViewById(R.id.eightScoreH5));
        eightScoresHome.add(findViewById(R.id.eightScoreH6));
        eightScoresHome.add(findViewById(R.id.eightScoreH7));
        eightScoresHome.add(findViewById(R.id.eightScoreH8));

        eightScoresVisitor.add(findViewById(R.id.eightScoreV1));
        eightScoresVisitor.add(findViewById(R.id.eightScoreV2));
        eightScoresVisitor.add(findViewById(R.id.eightScoreV3));
        eightScoresVisitor.add(findViewById(R.id.eightScoreV4));
        eightScoresVisitor.add(findViewById(R.id.eightScoreV5));
        eightScoresVisitor.add(findViewById(R.id.eightScoreV6));
        eightScoresVisitor.add(findViewById(R.id.eightScoreV7));
        eightScoresVisitor.add(findViewById(R.id.eightScoreV8));






        ////// FOURTH FINALS SCREEN ///////
        ArrayList<ImageView> fourthLogosHome = new ArrayList<>();
        ArrayList<ImageView> fourthLogosVisitor = new ArrayList<>();

        ArrayList<TextView> fourthNamesHome = new ArrayList<>();
        ArrayList<TextView> fourthNamesVisitors = new ArrayList<>();

        ArrayList<EditText> fourthScoresHome = new ArrayList<>();
        ArrayList<EditText> fourthScoresVisitor = new ArrayList<>();


        fourthLogosHome.add(findViewById(R.id.fourthLogoH1));
        fourthLogosHome.add(findViewById(R.id.fourthLogoH2));
        fourthLogosHome.add(findViewById(R.id.fourthLogoH3));
        fourthLogosHome.add(findViewById(R.id.fourthLogoH4));

        fourthLogosVisitor.add(findViewById(R.id.fourthLogoV1));
        fourthLogosVisitor.add(findViewById(R.id.fourthLogoV2));
        fourthLogosVisitor.add(findViewById(R.id.fourthLogoV3));
        fourthLogosVisitor.add(findViewById(R.id.fourthLogoV4));

        fourthNamesHome.add(findViewById(R.id.fourthNameH1));
        fourthNamesHome.add(findViewById(R.id.fourthNameH2));
        fourthNamesHome.add(findViewById(R.id.fourthNameH3));
        fourthNamesHome.add(findViewById(R.id.fourthNameH4));

        fourthNamesVisitors.add(findViewById(R.id.fourthNameV1));
        fourthNamesVisitors.add(findViewById(R.id.fourthNameV2));
        fourthNamesVisitors.add(findViewById(R.id.fourthNameV3));
        fourthNamesVisitors.add(findViewById(R.id.fourthNameV4));

        fourthScoresHome.add(findViewById(R.id.fourthScoreH1));
        fourthScoresHome.add(findViewById(R.id.fourthScoreH2));
        fourthScoresHome.add(findViewById(R.id.fourthScoreH3));
        fourthScoresHome.add(findViewById(R.id.fourthScoreH4));

        fourthScoresVisitor.add(findViewById(R.id.fourthScoreV1));
        fourthScoresVisitor.add(findViewById(R.id.fourthScoreV2));
        fourthScoresVisitor.add(findViewById(R.id.fourthScoreV3));
        fourthScoresVisitor.add(findViewById(R.id.fourthScoreV4));






        /////// SEMI FINALS SCREEN ////////
        ArrayList<ImageView> semiLogosHome = new ArrayList<>();
        ArrayList<ImageView> semiLogosVisitor = new ArrayList<>();

        ArrayList<TextView> semiNamesHome = new ArrayList<>();
        ArrayList<TextView> semiNamesVisitors = new ArrayList<>();

        ArrayList<EditText> semiScoresHome = new ArrayList<>();
        ArrayList<EditText> semiScoresVisitor = new ArrayList<>();

        semiLogosHome.add(findViewById(R.id.semiLogoH1));
        semiLogosHome.add(findViewById(R.id.semiLogoH2));

        semiLogosVisitor.add(findViewById(R.id.semiLogoV1));
        semiLogosVisitor.add(findViewById(R.id.semiLogoV2));

        semiNamesHome.add(findViewById(R.id.semiNameH1));
        semiNamesHome.add(findViewById(R.id.semiNameH2));

        semiNamesVisitors.add(findViewById(R.id.semiNameV1));
        semiNamesVisitors.add(findViewById(R.id.semiNameV2));

        semiScoresHome.add(findViewById(R.id.semiScoreH1));
        semiScoresHome.add(findViewById(R.id.semiScoreH2));

        semiScoresVisitor.add(findViewById(R.id.semiScoreV1));
        semiScoresVisitor.add(findViewById(R.id.semiScoreV2));





        ////// FINALS SCREEN ///////
        ArrayList<ImageView> finalsLogosHome = new ArrayList<>();
        ArrayList<ImageView> finalsLogosVisitor = new ArrayList<>();

        ArrayList<TextView> finalsNamesHome = new ArrayList<>();
        ArrayList<TextView> finalsNamesVisitors = new ArrayList<>();

        ArrayList<EditText> finalsScoresHome = new ArrayList<>();
        ArrayList<EditText> finalsScoresVisitor = new ArrayList<>();

        finalsLogosHome.add(findViewById(R.id.finalsLogoH1));
        finalsLogosHome.add(findViewById(R.id.finalsLogoH2));

        finalsLogosVisitor.add(findViewById(R.id.finalsLogoV1));
        finalsLogosVisitor.add(findViewById(R.id.finalsLogoV2));

        finalsNamesHome.add(findViewById(R.id.finalsNameH1));
        finalsNamesHome.add(findViewById(R.id.finalsNameH2));

        finalsNamesVisitors.add(findViewById(R.id.finalsNameV1));
        finalsNamesVisitors.add(findViewById(R.id.finalsNameV2));

        finalsScoresHome.add(findViewById(R.id.finalsScoreH1));
        finalsScoresHome.add(findViewById(R.id.finalsScoreH2));

        finalsScoresVisitor.add(findViewById(R.id.finalsScoreV1));
        finalsScoresVisitor.add(findViewById(R.id.finalsScoreV2));







        ArrayList<ArrayList<EditText>> all_scores = new ArrayList<>();
        all_scores.add(eightScoresHome);
        all_scores.add(eightScoresVisitor);
        all_scores.add(fourthScoresHome);
        all_scores.add(fourthScoresVisitor);
        all_scores.add(semiScoresHome);
        all_scores.add(semiScoresVisitor);
        all_scores.add(finalsScoresHome);
        all_scores.add(finalsScoresVisitor);



        for (int round = 0; round < all_scores.size(); round++) {
            for (int posEditText = 0; posEditText < all_scores.get(round).size(); posEditText++) {
                if (round % 2 == 0) {
                    String score = sharedPreferences.getString("Home score " + round/2 + ", " + posEditText, "0");
                    all_scores.get(round).get(posEditText).setText(score);
                } else {
                    String score = sharedPreferences.getString("Visitor score " + round/2 + ", " + posEditText, "0");
                    all_scores.get(round).get(posEditText).setText(score);
                }
            }
        }




        Button groupSaveButton = findViewById(R.id.groupSaveButton);

        groupSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                for (int round = 0; round < all_scores.size(); round++) {
                    for (int posEditText = 0; posEditText < all_scores.get(round).size(); posEditText++) {
                        if (round % 2 == 0) {
                            editor.putString("Home score " + round/2 + ", " + posEditText, all_scores.get(round).get(posEditText).getText().toString());
                        } else {
                            editor.putString("Visitor score " + round/2 + ", " + posEditText, all_scores.get(round).get(posEditText).getText().toString());
                        }
                        editor.commit();
                    }
                }
            }
        });






        Button eightButton = findViewById(R.id.eigthButton);
        eightButton.setSelected(true);
        Button fourthButton = findViewById(R.id.fourthButton);
        Button semiButton = findViewById(R.id.semiButton);
        Button finalButton = findViewById(R.id.finalButton);





        ArrayList<String> firstOrder = new ArrayList<>();
        Collections.addAll(firstOrder, "0", "2", "4", "6", "1", "3", "5", "7");
        ArrayList<String> secondOrder = new ArrayList<>();
        Collections.addAll(secondOrder, "1", "3", "5", "7", "0", "2", "4", "6");


        ArrayList<Group> allGroups = pr.getAllGroups();

        ArrayList<MatchFinals> matchEights = new ArrayList<>();

        for (int i = 0; i<8; i++) {
            matchEights.add(new MatchFinals());
        }


        for (Group group : allGroups) {
            for (int posGroup = 0; posGroup < allGroups.size(); posGroup++) {
                String firstTeam = sharedPreferences.getString("first team group " + firstOrder.get(posGroup), "");
                String secondTeam = sharedPreferences.getString("second team group " + secondOrder.get(posGroup), "");



                for (Team team : group.getTeams()) {
                    if (firstTeam.equals("")) {
                        namesHome.get(posGroup).setText("-");
                    } else if (team.getName().equals(firstTeam)) {
                        matchEights.get(posGroup).setHomeTeam(team);
                        logosHome.get(posGroup).setImageDrawable(team.getLogo());
                        namesHome.get(posGroup).setText(team.getName());
                    }
                    if (secondTeam.equals("")) {
                        namesVisitor.get(posGroup).setText("-");
                    } else if (team.getName().equals(secondTeam)) {
                        matchEights.get(posGroup).setVisitorTeam(team);
                        logosVisitor.get(posGroup).setImageDrawable(team.getLogo());
                        namesVisitor.get(posGroup).setText(team.getName());
                    }
                }
            }
        }





        for (int posScore = 0; posScore < eightScoresHome.size(); posScore++) {
            EditText home = eightScoresHome.get(posScore);
            EditText visitor = eightScoresVisitor.get(posScore);
            MatchFinals matchEight = matchEights.get(posScore);

            logosHome.get(posScore).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    home.setBackground(getResources().getDrawable(R.drawable.my_pass_background));
                    visitor.setBackground(getResources().getDrawable(R.drawable.my_score_background));
                    matchEight.setPassesFirst(true);
                }
            });


            logosVisitor.get(posScore).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    home.setBackground(getResources().getDrawable(R.drawable.my_score_background));
                    visitor.setBackground(getResources().getDrawable(R.drawable.my_pass_background));
                    matchEight.setPassesFirst(false);
                }
            });
        }


        for (int posScore = 0; posScore < eightScoresHome.size(); posScore++) {

            int scoreHome = 0;
            int scoreVisitor = 0;

            if (!eightScoresHome.get(posScore).getText().toString().equals("")) {
                scoreHome = Integer.parseInt(eightScoresHome.get(posScore).getText().toString());
            }

            if (!eightScoresVisitor.get(posScore).getText().toString().equals("")) {
                scoreVisitor = Integer.parseInt(eightScoresVisitor.get(posScore).getText().toString());
            }

            if (scoreHome > scoreVisitor) {
                matchEights.get(posScore).setFirstTeamWon(true);
            } else if (scoreHome == scoreVisitor && matchEights.get(posScore).getPassesFirst()) {
                matchEights.get(posScore).setFirstTeamWon(true);
            } else {
                matchEights.get(posScore).setFirstTeamWon(false);
            }
        }




        int fourthPos = 0;

        for (MatchFinals match : matchEights) {
            if (fourthPos % 2 == 0) {
                if (match.getFirstTeamWon()) {
                    if (match.getHomeTeam() != null) {
                        fourthLogosHome.get(fourthPos/2).setImageDrawable(match.getHomeTeam().getLogo());
                        fourthNamesHome.get(fourthPos/2).setText(match.getHomeTeam().getName());
                    }
                } else {
                    if (match.getVisitorTeam() != null) {
                        fourthLogosHome.get(fourthPos/2).setImageDrawable(match.getVisitorTeam().getLogo());
                        fourthNamesHome.get(fourthPos/2).setText(match.getVisitorTeam().getName());
                    }
                }
            } else {
                if (match.getFirstTeamWon()) {
                    if (match.getHomeTeam() != null) {
                        fourthLogosVisitor.get(fourthPos/2).setImageDrawable(match.getHomeTeam().getLogo());
                        fourthNamesVisitors.get(fourthPos/2).setText(match.getHomeTeam().getName());
                    }
                } else {
                    if (match.getVisitorTeam() != null) {
                        fourthLogosVisitor.get(fourthPos/2).setImageDrawable(match.getVisitorTeam().getLogo());
                        fourthNamesVisitors.get(fourthPos/2).setText(match.getVisitorTeam().getName());
                    }
                }
            }
            fourthPos++;
        }













        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!eightButton.isSelected()) {
                    eightButton.setSelected(true);
                    fourthButton.setSelected(false);
                    semiButton.setSelected(false);
                    finalButton.setSelected(false);

                    eightView.setVisibility(View.VISIBLE);
                    fourthLayout.setVisibility(View.GONE);
                    semiLayout.setVisibility(View.GONE);
                    finalsLayout.setVisibility(View.GONE);
                }
            }
        });



        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fourthButton.isSelected()) {
                    eightButton.setSelected(false);
                    fourthButton.setSelected(true);
                    semiButton.setSelected(false);
                    finalButton.setSelected(false);

                    eightView.setVisibility(View.GONE);
                    fourthLayout.setVisibility(View.VISIBLE);
                    semiLayout.setVisibility(View.GONE);
                    finalsLayout.setVisibility(View.GONE);
                }
            }
        });

        semiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!semiButton.isSelected()) {
                    eightButton.setSelected(false);
                    fourthButton.setSelected(false);
                    semiButton.setSelected(true);
                    finalButton.setSelected(false);

                    eightView.setVisibility(View.GONE);
                    fourthLayout.setVisibility(View.GONE);
                    semiLayout.setVisibility(View.VISIBLE);
                    finalsLayout.setVisibility(View.GONE);
                }
            }
        });

        finalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!finalButton.isSelected()) {
                    eightButton.setSelected(false);
                    fourthButton.setSelected(false);
                    semiButton.setSelected(false);
                    finalButton.setSelected(true);

                    eightView.setVisibility(View.GONE);
                    fourthLayout.setVisibility(View.GONE);
                    semiLayout.setVisibility(View.GONE);
                    finalsLayout.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
