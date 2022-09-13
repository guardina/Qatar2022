package com.example.fifaqatar2022.Screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.example.fifaqatar2022.Classes.Group;
import com.example.fifaqatar2022.Classes.Group_enum;
import com.example.fifaqatar2022.Classes.Match;
import com.example.fifaqatar2022.Classes.PlacementsRetriever;
import com.example.fifaqatar2022.Classes.ResultsRetriever;
import com.example.fifaqatar2022.Classes.Team;
import com.example.fifaqatar2022.MatchEight;
import com.example.fifaqatar2022.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
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


        //ResultsRetriever rr = ResultsRetriever.getRR();

        /*try {
            if (!executed) {
                ArrayList<Match> list = rr.execute().get();
                executed = true;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/



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



        Button eightButton = findViewById(R.id.eigthButton);
        eightButton.setSelected(true);
        Button fourthButton = findViewById(R.id.fourthButton);
        Button semiButton = findViewById(R.id.semiButton);
        Button finalButton = findViewById(R.id.finalButton);



        ScrollView eightView = findViewById(R.id.eightsView);
        LinearLayout fourthLayout = findViewById(R.id.fourthLayout);
        LinearLayout semiLayout = findViewById(R.id.semiLayout);
        LinearLayout finalsLayout = findViewById(R.id.finalsLayout);




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

        ArrayList<String> firstOrder = new ArrayList<>();
        Collections.addAll(firstOrder, "0", "2", "4", "6", "1", "3", "5", "7");
        ArrayList<String> secondOrder = new ArrayList<>();
        Collections.addAll(secondOrder, "1", "3", "5", "7", "0", "2", "4", "6");


        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);


        ArrayList<Group> allGroups = pr.getAllGroups();

        ArrayList<MatchEight> matchEights = new ArrayList<>();

        for (int i = 0; i<8; i++) {
            matchEights.add(new MatchEight());
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



        ArrayList<EditText> scoresHome = new ArrayList<>();
        ArrayList<EditText> scoresVisitor = new ArrayList<>();

        scoresHome.add(findViewById(R.id.eightScoreH1));
        scoresHome.add(findViewById(R.id.eightScoreH2));
        scoresHome.add(findViewById(R.id.eightScoreH3));
        scoresHome.add(findViewById(R.id.eightScoreH4));
        scoresHome.add(findViewById(R.id.eightScoreH5));
        scoresHome.add(findViewById(R.id.eightScoreH6));
        scoresHome.add(findViewById(R.id.eightScoreH7));
        scoresHome.add(findViewById(R.id.eightScoreH8));

        scoresVisitor.add(findViewById(R.id.eightScoreV1));
        scoresVisitor.add(findViewById(R.id.eightScoreV2));
        scoresVisitor.add(findViewById(R.id.eightScoreV3));
        scoresVisitor.add(findViewById(R.id.eightScoreV4));
        scoresVisitor.add(findViewById(R.id.eightScoreV5));
        scoresVisitor.add(findViewById(R.id.eightScoreV6));
        scoresVisitor.add(findViewById(R.id.eightScoreV7));
        scoresVisitor.add(findViewById(R.id.eightScoreV8));





        for (int posScore = 0; posScore < scoresHome.size(); posScore++) {
            EditText home = scoresHome.get(posScore);
            EditText visitor = scoresVisitor.get(posScore);
            MatchEight matchEight = matchEights.get(posScore);

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


        for (int posScore = 0; posScore < scoresHome.size(); posScore++) {

            int scoreHome = 0;
            int scoreVisitor = 0;

            if (!scoresHome.get(posScore).getText().toString().equals("")) {
                scoreHome = Integer.parseInt(scoresHome.get(posScore).getText().toString());
            }

            if (!scoresVisitor.get(posScore).getText().toString().equals("")) {
                scoreVisitor = Integer.parseInt(scoresVisitor.get(posScore).getText().toString());
            }

            if (scoreHome > scoreVisitor) {
                matchEights.get(posScore).setFirstWin(true);
            } else if (scoreHome == scoreVisitor && matchEights.get(posScore).getPassesFirst()) {
                matchEights.get(posScore).setFirstWin(true);
            } else {
                matchEights.get(posScore).setFirstWin(false);
            }
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



        Button groupReloadButton = findViewById(R.id.groupReloadButton);

        groupReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });


    }
}
