package com.example.fifaqatar2022.Screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.FinalsResultsRetriever;
import com.example.fifaqatar2022.Classes.Group;
import com.example.fifaqatar2022.Classes.Group_enum;
import com.example.fifaqatar2022.Classes.Match;
import com.example.fifaqatar2022.Classes.MatchFinals;
import com.example.fifaqatar2022.Classes.GroupCreator;
import com.example.fifaqatar2022.Classes.Profile;
import com.example.fifaqatar2022.Classes.Result;
import com.example.fifaqatar2022.Classes.ResultData;
import com.example.fifaqatar2022.Classes.Team;
import com.example.fifaqatar2022.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class GroupSelectionScreen extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://qatar-2022-64fef-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = firebaseDatabase.getReference();

    static boolean executed = false;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_phase_selection);

        //ResultsRetriever rr = ResultsRetriever.getRR();


        GroupCreator gc = GroupCreator.getGC();

        JSONObject obj = null;


        try {
            obj = new JSONObject(loadJSONFromAsset("groups"));

            JSONArray teams_JSON = obj.getJSONArray("group");
            String[] groups = {"A", "B", "C", "D", "E", "F", "G", "H"};
            String[] squads = {"first", "second", "third", "fourth"};

            ArrayList<ArrayList<Drawable>> logos = new ArrayList<>();
            ArrayList<ArrayList<String>> names = new ArrayList<>();


            for (int i = 0; i < teams_JSON.length(); i++) {
                logos.add(new ArrayList<>());
                names.add(new ArrayList<>());

                JSONObject json = teams_JSON.getJSONObject(i);
                if (json.has(groups[i])) {
                    for (String squad : squads) {
                        String team_name = (String) json.getJSONObject(groups[i]).getJSONObject(squad).get("name");
                        String logo_link = (String) json.getJSONObject(groups[i]).getJSONObject(squad).get("logo");

                        Drawable logo = getResources().getDrawable(getResources().getIdentifier(logo_link, "drawable", getApplicationContext().getPackageName()));

                        names.get(i).add(team_name);
                        logos.get(i).add(logo);
                    }
                }
            }

            gc.setNames(names);
            gc.setLogos(logos);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        gc.create_groups();


        Button chooseGroupsButton = findViewById(R.id.chooseGroupsButton);
        chooseGroupsButton.setSelected(true);
        Button chooseFinalsButton = findViewById(R.id.chooseFinalsButton);

        ScrollView groupsView = findViewById(R.id.groupsView);
        ScrollView eightsView = findViewById(R.id.eightsView);
        LinearLayout fourthLayout = findViewById(R.id.fourthLayout);
        LinearLayout semiLayout = findViewById(R.id.semiLayout);
        LinearLayout finalsLayout = findViewById(R.id.finalsLayout);
        LinearLayout buttonLayout = findViewById(R.id.buttonLayout);


        chooseGroupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!chooseGroupsButton.isSelected()) {
                    chooseGroupsButton.setSelected(true);
                    chooseFinalsButton.setSelected(false);
                }
                groupsView.setVisibility(View.VISIBLE);
                eightsView.setVisibility(View.GONE);
                fourthLayout.setVisibility(View.GONE);
                semiLayout.setVisibility(View.GONE);
                finalsLayout.setVisibility(View.GONE);
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
                eightsView.setVisibility(View.VISIBLE);
                fourthLayout.setVisibility(View.GONE);
                semiLayout.setVisibility(View.GONE);
                finalsLayout.setVisibility(View.GONE);
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


        /////////////////////                           EIGHT FINALS SCREEN                        ////////////////////

        ArrayList<ImageView> eightLogosHome = new ArrayList<>();
        ArrayList<TextView> eightNamesHome = new ArrayList<>();

        ArrayList<ImageView> eightLogosVisitor = new ArrayList<>();
        ArrayList<TextView> eightNamesVisitor = new ArrayList<>();

        eightLogosHome.add(findViewById(R.id.eightLogoH1));
        eightLogosHome.add(findViewById(R.id.eightLogoH2));
        eightLogosHome.add(findViewById(R.id.eightLogoH3));
        eightLogosHome.add(findViewById(R.id.eightLogoH4));
        eightLogosHome.add(findViewById(R.id.eightLogoH5));
        eightLogosHome.add(findViewById(R.id.eightLogoH6));
        eightLogosHome.add(findViewById(R.id.eightLogoH7));
        eightLogosHome.add(findViewById(R.id.eightLogoH8));

        eightNamesHome.add(findViewById(R.id.eightNameH1));
        eightNamesHome.add(findViewById(R.id.eightNameH2));
        eightNamesHome.add(findViewById(R.id.eightNameH3));
        eightNamesHome.add(findViewById(R.id.eightNameH4));
        eightNamesHome.add(findViewById(R.id.eightNameH5));
        eightNamesHome.add(findViewById(R.id.eightNameH6));
        eightNamesHome.add(findViewById(R.id.eightNameH7));
        eightNamesHome.add(findViewById(R.id.eightNameH8));

        eightLogosVisitor.add(findViewById(R.id.eightLogoV1));
        eightLogosVisitor.add(findViewById(R.id.eightLogoV2));
        eightLogosVisitor.add(findViewById(R.id.eightLogoV3));
        eightLogosVisitor.add(findViewById(R.id.eightLogoV4));
        eightLogosVisitor.add(findViewById(R.id.eightLogoV5));
        eightLogosVisitor.add(findViewById(R.id.eightLogoV6));
        eightLogosVisitor.add(findViewById(R.id.eightLogoV7));
        eightLogosVisitor.add(findViewById(R.id.eightLogoV8));

        eightNamesVisitor.add(findViewById(R.id.eightNameV1));
        eightNamesVisitor.add(findViewById(R.id.eightNameV2));
        eightNamesVisitor.add(findViewById(R.id.eightNameV3));
        eightNamesVisitor.add(findViewById(R.id.eightNameV4));
        eightNamesVisitor.add(findViewById(R.id.eightNameV5));
        eightNamesVisitor.add(findViewById(R.id.eightNameV6));
        eightNamesVisitor.add(findViewById(R.id.eightNameV7));
        eightNamesVisitor.add(findViewById(R.id.eightNameV8));


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


        //////////////////////////////////               FOURTH FINALS SCREEN                   /////////////////////////////////


        ArrayList<ImageView> fourthLogosHome = new ArrayList<>();
        ArrayList<ImageView> fourthLogosVisitor = new ArrayList<>();

        ArrayList<TextView> fourthNamesHome = new ArrayList<>();
        ArrayList<TextView> fourthNamesVisitor = new ArrayList<>();

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

        fourthNamesVisitor.add(findViewById(R.id.fourthNameV1));
        fourthNamesVisitor.add(findViewById(R.id.fourthNameV2));
        fourthNamesVisitor.add(findViewById(R.id.fourthNameV3));
        fourthNamesVisitor.add(findViewById(R.id.fourthNameV4));

        fourthScoresHome.add(findViewById(R.id.fourthScoreH1));
        fourthScoresHome.add(findViewById(R.id.fourthScoreH2));
        fourthScoresHome.add(findViewById(R.id.fourthScoreH3));
        fourthScoresHome.add(findViewById(R.id.fourthScoreH4));

        fourthScoresVisitor.add(findViewById(R.id.fourthScoreV1));
        fourthScoresVisitor.add(findViewById(R.id.fourthScoreV2));
        fourthScoresVisitor.add(findViewById(R.id.fourthScoreV3));
        fourthScoresVisitor.add(findViewById(R.id.fourthScoreV4));


        ///////////////////////////////                  SEMI FINALS SCREEN                  //////////////////


        ArrayList<ImageView> semiLogosHome = new ArrayList<>();
        ArrayList<ImageView> semiLogosVisitor = new ArrayList<>();

        ArrayList<TextView> semiNamesHome = new ArrayList<>();
        ArrayList<TextView> semiNamesVisitor = new ArrayList<>();

        ArrayList<EditText> semiScoresHome = new ArrayList<>();
        ArrayList<EditText> semiScoresVisitor = new ArrayList<>();

        semiLogosHome.add(findViewById(R.id.semiLogoH1));
        semiLogosHome.add(findViewById(R.id.semiLogoH2));

        semiLogosVisitor.add(findViewById(R.id.semiLogoV1));
        semiLogosVisitor.add(findViewById(R.id.semiLogoV2));

        semiNamesHome.add(findViewById(R.id.semiNameH1));
        semiNamesHome.add(findViewById(R.id.semiNameH2));

        semiNamesVisitor.add(findViewById(R.id.semiNameV1));
        semiNamesVisitor.add(findViewById(R.id.semiNameV2));

        semiScoresHome.add(findViewById(R.id.semiScoreH1));
        semiScoresHome.add(findViewById(R.id.semiScoreH2));

        semiScoresVisitor.add(findViewById(R.id.semiScoreV1));
        semiScoresVisitor.add(findViewById(R.id.semiScoreV2));


        /////////////////////////                FINALS SCREEN                     ///////////////////////////


        ArrayList<ImageView> finalsLogosHome = new ArrayList<>();
        ArrayList<ImageView> finalsLogosVisitor = new ArrayList<>();

        ArrayList<TextView> finalsNamesHome = new ArrayList<>();
        ArrayList<TextView> finalsNamesVisitor = new ArrayList<>();

        ArrayList<EditText> finalsScoresHome = new ArrayList<>();
        ArrayList<EditText> finalsScoresVisitor = new ArrayList<>();

        finalsLogosHome.add(findViewById(R.id.finalsLogoH1));
        finalsLogosHome.add(findViewById(R.id.finalsLogoH2));

        finalsLogosVisitor.add(findViewById(R.id.finalsLogoV1));
        finalsLogosVisitor.add(findViewById(R.id.finalsLogoV2));

        finalsNamesHome.add(findViewById(R.id.finalsNameH1));
        finalsNamesHome.add(findViewById(R.id.finalsNameH2));

        finalsNamesVisitor.add(findViewById(R.id.finalsNameV1));
        finalsNamesVisitor.add(findViewById(R.id.finalsNameV2));

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


        ArrayList<ArrayList<ImageView>> all_logos = new ArrayList<>();
        all_logos.add(eightLogosHome);
        all_logos.add(eightLogosVisitor);
        all_logos.add(fourthLogosHome);
        all_logos.add(fourthLogosVisitor);
        all_logos.add(semiLogosHome);
        all_logos.add(semiLogosVisitor);
        all_logos.add(finalsLogosHome);
        all_logos.add(finalsLogosVisitor);


        ArrayList<ArrayList<TextView>> all_names = new ArrayList<>();
        all_names.add(eightNamesHome);
        all_names.add(eightNamesVisitor);
        all_names.add(fourthNamesHome);
        all_names.add(fourthNamesVisitor);
        all_names.add(semiNamesHome);
        all_names.add(semiNamesVisitor);
        all_names.add(finalsNamesHome);
        all_names.add(finalsNamesVisitor);


        for (int round = 0; round < all_scores.size(); round++) {
            for (int posEditText = 0; posEditText < all_scores.get(round).size(); posEditText++) {
                if (round % 2 == 0) {
                    String score = sharedPreferences.getString("Home score " + round / 2 + ", " + posEditText, "0");
                    all_scores.get(round).get(posEditText).setText(score);
                } else {
                    String score = sharedPreferences.getString("Visitor score " + round / 2 + ", " + posEditText, "0");
                    all_scores.get(round).get(posEditText).setText(score);
                }
            }
        }


        Button eightButton = findViewById(R.id.eigthButton);
        eightButton.setSelected(true);
        Button fourthButton = findViewById(R.id.fourthButton);
        Button semiButton = findViewById(R.id.semiButton);
        Button finalButton = findViewById(R.id.finalButton);


        ArrayList<String> firstOrder = new ArrayList<>();
        Collections.addAll(firstOrder, "0", "2", "4", "6", "1", "3", "5", "7");
        ArrayList<String> secondOrder = new ArrayList<>();
        Collections.addAll(secondOrder, "1", "3", "5", "7", "0", "2", "4", "6");


        ArrayList<Group> allGroups = gc.getAllGroups();


        ArrayList<MatchFinals> matchesEight = new ArrayList<>();
        ArrayList<MatchFinals> matchesFourth = new ArrayList<>();
        ArrayList<MatchFinals> matchesSemi = new ArrayList<>();
        ArrayList<MatchFinals> matchesFinal = new ArrayList<>();

        ArrayList<ArrayList<MatchFinals>> rounds = new ArrayList<>();
        rounds.add(matchesEight);
        rounds.add(matchesFourth);
        rounds.add(matchesSemi);
        rounds.add(matchesFinal);


        for (int i = 0; i < 8; i++) {
            matchesEight.add(new MatchFinals());
        }

        for (int i = 0; i < 4; i++) {
            matchesFourth.add(new MatchFinals());
        }

        for (int i = 0; i < 2; i++) {
            matchesSemi.add(new MatchFinals());
            matchesFinal.add(new MatchFinals());
        }


        ///////////////////////////////                      SETUP EIGHT OF FINALS ACCORDING TO GROUP PLACEMENTS               //////////////////////////


        for (Group group : allGroups) {
            for (int posGroup = 0; posGroup < allGroups.size(); posGroup++) {
                String firstTeam = sharedPreferences.getString("first team group " + firstOrder.get(posGroup), "");
                String secondTeam = sharedPreferences.getString("second team group " + secondOrder.get(posGroup), "");


                for (Team team : group.getTeams()) {
                    if (firstTeam.equals("")) {
                        eightNamesHome.get(posGroup).setText("-");
                    } else if (team.getName().equals(firstTeam)) {
                        matchesEight.get(posGroup).setHomeTeam(team);
                        eightLogosHome.get(posGroup).setImageDrawable(team.getLogo());
                        eightNamesHome.get(posGroup).setText(team.getName());
                    }
                    if (secondTeam.equals("")) {
                        eightNamesVisitor.get(posGroup).setText("-");
                    } else if (team.getName().equals(secondTeam)) {
                        matchesEight.get(posGroup).setVisitorTeam(team);
                        eightLogosVisitor.get(posGroup).setImageDrawable(team.getLogo());
                        eightNamesVisitor.get(posGroup).setText(team.getName());
                    }
                }
            }
        }


        for (int round = 0; round < rounds.size(); round++) {
            for (int i = 0; i < rounds.get(round).size(); i++) {
                for (Group group : allGroups) {


                    String firstTeam = "";
                    String secondTeam = "";


                    if (round == 0) {
                        firstTeam = sharedPreferences.getString("first team group " + firstOrder.get(i), "");
                        secondTeam = sharedPreferences.getString("second team group " + secondOrder.get(i), "");
                    }

                    for (Team team : group.getTeams()) {
                        if (firstTeam.equals("")) {
                            all_names.get(2 * round).get(i).setText("-");
                        } else if (team.getName().equals(firstTeam)) {
                            rounds.get(round).get(i).setHomeTeam(team);
                            all_logos.get(2 * round).get(i).setImageDrawable(team.getLogo());
                            all_names.get(2 * round).get(i).setText(team.getName());
                        }
                        if (secondTeam.equals("")) {
                            all_names.get(2 * round + 1).get(i).setText("-");
                        } else if (team.getName().equals(secondTeam)) {
                            rounds.get(round).get(i).setVisitorTeam(team);
                            all_logos.get(2 * round + 1).get(i).setImageDrawable(team.getLogo());
                            all_names.get(2 * round + 1).get(i).setText(team.getName());
                        }
                    }
                }
            }
        }


        ////////////////////////////////              SETUP ALL ONCLICK FOR IMAGES IN THE FINAL STAGES         //////////////////////////

        for (int round = 0; round < rounds.size(); round++) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for (int numMatches = 0; numMatches < rounds.get(round).size(); numMatches++) {
                EditText home = all_scores.get(2 * round).get(numMatches);
                EditText visitor = all_scores.get(2 * round + 1).get(numMatches);
                MatchFinals match = rounds.get(round).get(numMatches);

                final int finalRound = round;
                final int finalNumMatches = numMatches;

                all_logos.get(2 * round).get(numMatches).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        home.setBackground(getResources().getDrawable(R.drawable.my_pass_background));
                        visitor.setBackground(getResources().getDrawable(R.drawable.my_score_background));
                        match.setPassesFirst(true);
                        editor.putString("First Team winner " + finalRound + ", " + finalNumMatches, "true");
                        editor.commit();
                    }
                });


                all_logos.get(2 * round + 1).get(numMatches).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        home.setBackground(getResources().getDrawable(R.drawable.my_score_background));
                        visitor.setBackground(getResources().getDrawable(R.drawable.my_pass_background));
                        match.setPassesFirst(false);
                        editor.putString("First Team winner " + finalRound + ", " + finalNumMatches, "false");
                        editor.commit();
                    }
                });
            }
        }


        ///////////////////////////                  DETERMINE WINNER BY COMPARING RESULTS                  ///////////////////////////

        for (int round = 0; round < rounds.size(); round++) {
            for (int posMatch = 0; posMatch < rounds.get(round).size(); posMatch++) {
                int scoreHome = 0;
                int scoreVisitor = 0;

                if (!all_scores.get(2 * round).get(posMatch).getText().toString().equals("-")) {
                    scoreHome = Integer.parseInt(all_scores.get(2 * round).get(posMatch).getText().toString());
                }

                if (!all_scores.get(2 * round + 1).get(posMatch).getText().toString().equals("-")) {
                    scoreVisitor = Integer.parseInt(all_scores.get(2 * round + 1).get(posMatch).getText().toString());
                }

                String firstTeamWin = sharedPreferences.getString("First Team winner " + round + ", " + posMatch, "");

                EditText home = all_scores.get(2 * round).get(posMatch);
                EditText visitor = all_scores.get(2 * round + 1).get(posMatch);


                if (firstTeamWin.equals("true")) {
                    rounds.get(round).get(posMatch).setPassesFirst(true);
                    home.setBackground(getResources().getDrawable(R.drawable.my_pass_background));
                    visitor.setBackground(getResources().getDrawable(R.drawable.my_score_background));
                } else if (firstTeamWin.equals("false")) {
                    rounds.get(round).get(posMatch).setPassesFirst(false);
                    home.setBackground(getResources().getDrawable(R.drawable.my_score_background));
                    visitor.setBackground(getResources().getDrawable(R.drawable.my_pass_background));
                }

                if (scoreHome > scoreVisitor) {
                    rounds.get(round).get(posMatch).setFirstTeamWon(true);
                } else if (scoreHome == scoreVisitor && rounds.get(round).get(posMatch).getPassesFirst()) {
                    rounds.get(round).get(posMatch).setFirstTeamWon(true);
                } else {
                    rounds.get(round).get(posMatch).setFirstTeamWon(false);
                }

            }
        }


        for (int round = 0; round < rounds.size() - 1; round++) {
            int pos = 0;
            for (MatchFinals match : rounds.get(round)) {
                if (pos % 2 == 0) {
                    if (match.getFirstTeamWon()) {
                        if (match.getHomeTeam() != null) {
                            rounds.get(round + 1).get(pos / 2).setHomeTeam(match.getHomeTeam());
                            if (round == rounds.size() - 2 && match.getVisitorTeam() != null) {
                                rounds.get(round + 1).get(1).setHomeTeam(match.getVisitorTeam());
                            }
                        }
                    } else {
                        if (match.getVisitorTeam() != null) {
                            rounds.get(round + 1).get(pos / 2).setHomeTeam(match.getVisitorTeam());
                            if (round == rounds.size() - 2 && match.getHomeTeam() != null) {
                                rounds.get(round + 1).get(1).setHomeTeam(match.getHomeTeam());
                            }
                        }
                    }
                } else {

                    if (match.getFirstTeamWon()) {
                        if (match.getHomeTeam() != null) {
                            rounds.get(round + 1).get(pos / 2).setVisitorTeam(match.getHomeTeam());
                            if (round == rounds.size() - 2 && match.getVisitorTeam() != null) {
                                rounds.get(round + 1).get(1).setVisitorTeam(match.getVisitorTeam());
                            }

                        }
                    } else {
                        if (match.getVisitorTeam() != null) {
                            rounds.get(round + 1).get(pos / 2).setVisitorTeam(match.getVisitorTeam());
                            if (round == rounds.size() - 2 && match.getHomeTeam() != null) {
                                rounds.get(round + 1).get(1).setVisitorTeam(match.getHomeTeam());
                            }
                        }
                    }
                }
                pos++;
            }
        }


        for (int round = 0; round < rounds.size(); round++) {
            int pos = 0;
            for (MatchFinals match : rounds.get(round)) {
                for (int i = 0; i < 2; i++) {
                    if (pos % 2 == 0) {
                        if (match.getHomeTeam() != null && i == 0) {
                            all_logos.get(2 * (round)).get(pos / 2).setImageDrawable(match.getHomeTeam().getLogo());
                            all_names.get(2 * (round)).get(pos / 2).setText(match.getHomeTeam().getName());
                        } else {
                            if (match.getVisitorTeam() != null) {
                                all_logos.get(2 * (round)).get(pos / 2).setImageDrawable(match.getVisitorTeam().getLogo());
                                all_names.get(2 * (round)).get(pos / 2).setText(match.getVisitorTeam().getName());
                            }
                        }
                    } else {
                        if (match.getHomeTeam() != null && i == 0) {
                            all_logos.get(2 * (round) + 1).get(pos / 2).setImageDrawable(match.getHomeTeam().getLogo());
                            all_names.get(2 * (round) + 1).get(pos / 2).setText(match.getHomeTeam().getName());
                        } else {
                            if (match.getVisitorTeam() != null) {
                                all_logos.get(2 * (round) + 1).get(pos / 2).setImageDrawable(match.getVisitorTeam().getLogo());
                                all_names.get(2 * (round) + 1).get(pos / 2).setText(match.getVisitorTeam().getName());
                            }
                        }
                    }
                    pos++;
                }
            }
        }


        Button groupSaveButton = findViewById(R.id.groupSaveButton);

        groupSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                boolean firstTime = true;

                ArrayList<Result> tempResult = null;

                for (int round = 0; round < all_scores.size(); round++) {

                    if (firstTime) {
                        tempResult = new ArrayList<>();
                        for (int i = 0; i < all_scores.get(round).size(); i++) {
                            tempResult.add(new Result());
                        }
                    }

                    for (int posEditText = 0; posEditText < all_scores.get(round).size(); posEditText++) {
                        if (round % 2 == 0) {
                            editor.putString("Home score " + round / 2 + ", " + posEditText, all_scores.get(round).get(posEditText).getText().toString());
                            tempResult.get(posEditText).setHomeScore(all_scores.get(round).get(posEditText).getText().toString());
                            tempResult.get(posEditText).setHomeTeam(all_names.get(round).get(posEditText).getText().toString());
                        } else {
                            editor.putString("Visitor score " + round / 2 + ", " + posEditText, all_scores.get(round).get(posEditText).getText().toString());
                            tempResult.get(posEditText).setVisitorScore(all_scores.get(round).get(posEditText).getText().toString());
                            tempResult.get(posEditText).setVisitorTeam(all_names.get(round).get(posEditText).getText().toString());
                            String tempId = tempResult.get(posEditText).getHomeTeam() + tempResult.get(posEditText).getVisitorTeam();
                            tempResult.get(posEditText).setId(tempId);
                        }
                        editor.commit();
                    }

                    if (round % 2 == 0) {
                        firstTime = false;
                    } else {
                        firstTime = true;

                        for (Result result : tempResult) {
                            Profile.getProfile().getPrediction().addFinals_result(result, round / 2);
                        }
                    }
                }


                ////////// SAVE GROUP PREDICTIONS ///////////

                String[] group_names = {"A", "B", "C", "D", "E", "F", "G", "H"};
                int i = 0;

                for (ArrayList<Result> group_matches : Profile.getProfile().getPrediction().getGroup_results()) {
                    int match_num = 0;
                    for (Result result : group_matches) {
                        ResultData data = new ResultData();

                        data.setHomeTeamName(result.getHomeTeam());
                        data.setVisitorTeamName(result.getVisitorTeam());
                        data.setHomeTeamScore(result.getHomeScore());
                        data.setVisitorTeamScore(result.getVisitorScore());

                        editor.putString("group " + group_names[i] + " match " + match_num++, result.getId());

                        myRef.child("predictions").child(Profile.getProfile().getUuid()).child("group").child(group_names[i]).child(result.getId()).setValue(data);
                    }
                    i++;
                }

                ////////// SAVE EIGHTS PREDICTIONS /////////////////

                myRef.child("predictions").child(Profile.getProfile().getUuid()).child("eight").removeValue();
                for (Result result : Profile.getProfile().getPrediction().getEight_results()) {

                    if (result.getHomeTeam().equals("-") || result.getVisitorTeam().equals("-")) {
                        continue;
                    }

                    ResultData data = new ResultData();

                    data.setHomeTeamName(result.getHomeTeam());
                    data.setVisitorTeamName(result.getVisitorTeam());
                    data.setHomeTeamScore(result.getHomeScore());
                    data.setVisitorTeamScore(result.getVisitorScore());

                    myRef.child("predictions").child(Profile.getProfile().getUuid()).child("eight").child(result.getId()).setValue(data);
                }


                ////////// SAVE FOURTH PREDICTIONS /////////////////

                myRef.child("predictions").child(Profile.getProfile().getUuid()).child("fourth").removeValue();
                for (Result result : Profile.getProfile().getPrediction().getFourth_results()) {

                    if (result.getHomeTeam().equals("-") || result.getVisitorTeam().equals("-")) {
                        continue;
                    }

                    ResultData data = new ResultData();

                    data.setHomeTeamName(result.getHomeTeam());
                    data.setVisitorTeamName(result.getVisitorTeam());
                    data.setHomeTeamScore(result.getHomeScore());
                    data.setVisitorTeamScore(result.getVisitorScore());

                    myRef.child("predictions").child(Profile.getProfile().getUuid()).child("fourth").child(result.getId()).setValue(data);
                }


                ////////// SAVE SEMI PREDICTIONS /////////////////

                myRef.child("predictions").child(Profile.getProfile().getUuid()).child("semi").removeValue();
                for (Result result : Profile.getProfile().getPrediction().getSemi_results()) {

                    if (result.getHomeTeam().equals("-") || result.getVisitorTeam().equals("-")) {
                        continue;
                    }

                    ResultData data = new ResultData();

                    data.setHomeTeamName(result.getHomeTeam());
                    data.setVisitorTeamName(result.getVisitorTeam());
                    data.setHomeTeamScore(result.getHomeScore());
                    data.setVisitorTeamScore(result.getVisitorScore());

                    myRef.child("predictions").child(Profile.getProfile().getUuid()).child("semi").child(result.getId()).setValue(data);
                }


                ////////// SAVE FINAL PREDICTIONS /////////////////

                myRef.child("predictions").child(Profile.getProfile().getUuid()).child("final").removeValue();
                for (Result result : Profile.getProfile().getPrediction().getFinal_results()) {

                    if (result.getHomeTeam().equals("-") || result.getVisitorTeam().equals("-")) {
                        continue;
                    }

                    ResultData data = new ResultData();

                    data.setHomeTeamName(result.getHomeTeam());
                    data.setVisitorTeamName(result.getVisitorTeam());
                    data.setHomeTeamScore(result.getHomeScore());
                    data.setVisitorTeamScore(result.getVisitorScore());

                    myRef.child("predictions").child(Profile.getProfile().getUuid()).child("final").child(result.getId()).setValue(data);
                }


                editor.commit();

                Toast.makeText(GroupSelectionScreen.this, "Risultati salvati\nPremere AGGIORNA per visualizzarli", Toast.LENGTH_SHORT).show();
            }
        });


        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!eightButton.isSelected()) {
                    eightButton.setSelected(true);
                    fourthButton.setSelected(false);
                    semiButton.setSelected(false);
                    finalButton.setSelected(false);

                    eightsView.setVisibility(View.VISIBLE);
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

                    eightsView.setVisibility(View.GONE);
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

                    eightsView.setVisibility(View.GONE);
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

                    eightsView.setVisibility(View.GONE);
                    fourthLayout.setVisibility(View.GONE);
                    semiLayout.setVisibility(View.GONE);
                    finalsLayout.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    public String loadJSONFromAsset(String file) {
        String json = null;
        try {
            InputStream is = getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            System.out.println("File not found! Try changing the name or to locate the file.");
        }
        return json;
    }
}
