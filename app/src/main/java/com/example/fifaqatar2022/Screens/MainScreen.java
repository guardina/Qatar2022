package com.example.fifaqatar2022.Screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.FinalsResultsRetriever;
import com.example.fifaqatar2022.Classes.Match;
import com.example.fifaqatar2022.Classes.Profile;
import com.example.fifaqatar2022.Classes.Result;
import com.example.fifaqatar2022.Classes.ResultsRetriever;
import com.example.fifaqatar2022.Classes.Scorer;
import com.example.fifaqatar2022.Classes.Team;
import com.example.fifaqatar2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MainScreen extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://qatar-2022-64fef-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();

    static boolean executed_FRR = false;
    static boolean executed_RR = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FinalsResultsRetriever frr = FinalsResultsRetriever.getFRR();


        try {
            if (!executed_FRR) {
                ArrayList<ArrayList<Result>> list = frr.execute().get();
                executed_FRR = true;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        ResultsRetriever rr = ResultsRetriever.getRR();

        try {
            if (!executed_RR) {
                ArrayList<Match> list = rr.execute().get();
                executed_RR = true;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ImageView profilePic = findViewById(R.id.profilePic);


        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, ProfileScreen.class));
            }
        });


        Button bomberButton = findViewById(R.id.bomberButton);

        bomberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, BomberScreen.class));
            }
        });


        Button predictionButton = findViewById(R.id.predButton);

        predictionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, GroupSelectionScreen.class));
            }
        });


        Profile profile = Profile.getProfile();


        Button downloadButton = findViewById(R.id.downloadButton);
        Button placementButton = findViewById(R.id.generalPlacementButton);


        ArrayList<Scorer> scorers = new ArrayList<>();

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.child("bombers").child(profile.getUuid()).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();

                        profile.getBombers().clear();


                        while (iter.hasNext()) {
                            DataSnapshot current = iter.next();

                            String bomber = (String) current.getValue();

                            profile.addBomber(bomber);
                        }
                    }
                });


                myRef.child("scorers").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();

                        scorers.clear();
                        while (iter.hasNext()) {
                            DataSnapshot current = iter.next();

                            String name = current.getKey();
                            int goals = Integer.parseInt(String.valueOf(current.getValue()));

                            Scorer scorer = new Scorer(name, goals);

                            scorers.add(scorer);
                        }
                    }
                });


                List<String> group_names = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");

                for (String group_name : group_names) {
                    ArrayList<DataSnapshot> snapshots = new ArrayList<>();
                    myRef.child("predictions").child(Profile.getProfile().getUuid()).child("group").child(group_name).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot dataSnapshot) {
                            Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();

                            while (iter.hasNext()) {
                                snapshots.add(iter.next());
                            }

                            profile.getPrediction().getGroup_results().get(group_names.indexOf(group_name)).clear();

                            for (int i = 0; i < snapshots.size(); i++) {
                                DataSnapshot current = snapshots.get(i);

                                HashMap<String, String> group_pred = (HashMap) current.getValue();

                                Result result = new Result();

                                result.setHomeTeam(group_pred.get("homeTeamName"));
                                result.setHomeScore(group_pred.get("homeTeamScore"));
                                result.setVisitorTeam(group_pred.get("visitorTeamName"));
                                result.setVisitorScore(group_pred.get("visitorTeamScore"));
                                result.setId(group_pred.get("homeTeamName") + group_pred.get("visitorTeamName"));

                                profile.getPrediction().getGroup_results().get(group_names.indexOf(group_name)).add(result);
                            }
                        }
                    });
                }


                String[] stages = {"eight", "fourth", "semi", "final"};

                ArrayList<Result> curPredictions;

                for (String stage : stages) {

                    switch (stage) {
                        case "eight":
                            curPredictions = Profile.getProfile().getPrediction().getEight_results();
                            break;
                        case "fourth":
                            curPredictions = Profile.getProfile().getPrediction().getFourth_results();
                            break;
                        case "semi":
                            curPredictions = Profile.getProfile().getPrediction().getSemi_results();
                            break;
                        case "final":
                            curPredictions = Profile.getProfile().getPrediction().getFinal_results();
                            break;
                        default:
                            curPredictions = null;
                            break;
                    }

                    final ArrayList<Result> tempCurPredictions = curPredictions;
                    curPredictions.clear();

                    if (curPredictions != null) {
                        myRef.child("predictions").child(Profile.getProfile().getUuid()).child(stage).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                            @Override
                            public void onSuccess(DataSnapshot dataSnapshot) {
                                Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();

                                while (iter.hasNext()) {
                                    DataSnapshot current = iter.next();

                                    HashMap<String, String> stage_pred = (HashMap) current.getValue();

                                    Result result = new Result();

                                    result.setHomeTeam(stage_pred.get("homeTeamName"));
                                    result.setHomeScore(stage_pred.get("homeTeamScore"));
                                    result.setVisitorTeam(stage_pred.get("visitorTeamName"));
                                    result.setVisitorScore(stage_pred.get("visitorTeamScore"));
                                    result.setId(stage_pred.get("homeTeamName") + stage_pred.get("visitorTeamName"));

                                    tempCurPredictions.add(result);
                                }

                                if (stage.equals("eight")) {
                                    profile.getPrediction().setEight_results(tempCurPredictions);
                                } else if (stage.equals("fourth")) {
                                    profile.getPrediction().setFourth_results(tempCurPredictions);
                                } else if (stage.equals("semi")) {
                                    profile.getPrediction().setSemi_results(tempCurPredictions);
                                } else {
                                    profile.getPrediction().setFinal_results(tempCurPredictions);
                                }
                            }
                        });
                    }
                }

                placementButton.setVisibility(View.VISIBLE);
                downloadButton.setVisibility(View.GONE);
            }
        });

        placementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profile.resetPoints();
                for (ArrayList<Result> group_results : profile.getPrediction().getGroup_results()) {
                    for (Result prediction : group_results) {
                        for (Match match : rr.getAllMatches()) {

                            int homeScorePredicted = Integer.parseInt(prediction.getHomeScore());
                            int visitorScorePredicted = Integer.parseInt(prediction.getVisitorScore());

                            int actualHomeScore = -1;
                            int actualVisitorScore = -1;

                            if (!match.getHomeScore().equals("-") && !match.getVisitorScore().equals("-")) {
                                actualHomeScore = Integer.parseInt(match.getHomeScore());
                                actualVisitorScore = Integer.parseInt(match.getVisitorScore());
                            }

                            if (match.getId().equals(prediction.getId())) {
                                if (actualHomeScore != -1 && actualVisitorScore != -1) {
                                    if (homeScorePredicted == actualHomeScore && visitorScorePredicted == actualVisitorScore) {
                                        profile.addPoints(3);
                                        profile.gotPerfectResult();
                                    } else if (homeScorePredicted > visitorScorePredicted && actualHomeScore > actualVisitorScore) {
                                        profile.addPoints(1);
                                    } else if (homeScorePredicted == visitorScorePredicted && actualHomeScore == actualVisitorScore) {
                                        profile.addPoints(1);
                                    } else if (homeScorePredicted < visitorScorePredicted && actualHomeScore < actualVisitorScore) {
                                        profile.addPoints(1);
                                    }
                                }
                            }
                        }
                    }
                }


                ArrayList<ArrayList<Result>> all_final_pred = new ArrayList<>();

                all_final_pred.add(profile.getPrediction().getEight_results());
                all_final_pred.add(profile.getPrediction().getFourth_results());
                all_final_pred.add(profile.getPrediction().getSemi_results());
                all_final_pred.add(profile.getPrediction().getFinal_results());


                int round = 0;
                for (ArrayList<Result> actual_results : frr.getAll_final_matches()) {
                    ArrayList<Result> pred_results = all_final_pred.get(round);

                    for (Result actual_result : actual_results) {
                        for (Result pred_result : pred_results) {

                            int homeScorePredicted = Integer.parseInt(pred_result.getHomeScore());
                            int visitorScorePredicted = Integer.parseInt(pred_result.getVisitorScore());

                            int actualHomeScore = -1;
                            int actualVisitorScore = -1;

                            if (!actual_result.getHomeScore().equals("-") && !actual_result.getVisitorScore().equals("-")) {
                                actualHomeScore = Integer.parseInt(actual_result.getHomeScore());
                                actualVisitorScore = Integer.parseInt(actual_result.getVisitorScore());
                            }

                            String invertedId = actual_result.getVisitorTeam() + actual_result.getHomeTeam();

                            if (round == 0) {
                                if (actual_result.getId().equals(pred_result.getId()) || invertedId.equals(pred_result.getId())) {
                                    profile.addPoints(2);
                                    if (actualHomeScore != -1 && actualVisitorScore != -1) {
                                        if (homeScorePredicted == actualHomeScore && visitorScorePredicted == actualVisitorScore) {
                                            profile.addPoints(3);
                                            profile.gotPerfectResult();
                                        } else if (homeScorePredicted > visitorScorePredicted && actualHomeScore > actualVisitorScore) {
                                            profile.addPoints(1);
                                        } else if (homeScorePredicted == visitorScorePredicted && actualHomeScore == actualVisitorScore) {
                                            profile.addPoints(1);
                                        } else if (homeScorePredicted < visitorScorePredicted && actualHomeScore < actualVisitorScore) {
                                            profile.addPoints(1);
                                        }
                                    }
                                }

                            } else if (round == 1) {
                                if (pred_result.getHomeTeam().equals(actual_result.getHomeTeam()) || pred_result.getHomeTeam().equals(actual_result.getVisitorTeam())) {
                                    profile.addPoints(2);
                                }

                                if (pred_result.getVisitorTeam().equals(actual_result.getHomeTeam()) || pred_result.getVisitorTeam().equals(actual_result.getVisitorTeam())) {
                                    profile.addPoints(2);
                                }

                                if (actual_result.getId().equals(pred_result.getId()) || invertedId.equals(pred_result.getId())) {
                                    profile.addPoints(2);
                                    if (actualHomeScore != -1 && actualVisitorScore != -1) {
                                        if (homeScorePredicted == actualHomeScore && visitorScorePredicted == actualVisitorScore) {
                                            profile.addPoints(5);
                                            profile.gotPerfectResult();
                                        } else if (homeScorePredicted > visitorScorePredicted && actualHomeScore > actualVisitorScore) {
                                            profile.addPoints(2);
                                        } else if (homeScorePredicted == visitorScorePredicted && actualHomeScore == actualVisitorScore) {
                                            profile.addPoints(2);
                                        } else if (homeScorePredicted < visitorScorePredicted && actualHomeScore < actualVisitorScore) {
                                            profile.addPoints(2);
                                        }
                                    }
                                }

                            } else if (round == 2) {
                                if (pred_result.getHomeTeam().equals(actual_result.getHomeTeam()) || pred_result.getHomeTeam().equals(actual_result.getVisitorTeam())) {
                                    profile.addPoints(3);
                                }

                                if (pred_result.getVisitorTeam().equals(actual_result.getHomeTeam()) || pred_result.getVisitorTeam().equals(actual_result.getVisitorTeam())) {
                                    profile.addPoints(3);
                                }

                                if (actual_result.getId().equals(pred_result.getId()) || invertedId.equals(pred_result.getId())) {
                                    profile.addPoints(2);
                                    if (actualHomeScore != -1 && actualVisitorScore != -1) {
                                        if (homeScorePredicted == actualHomeScore && visitorScorePredicted == actualVisitorScore) {
                                            profile.addPoints(5);
                                            profile.gotPerfectResult();
                                        } else if (homeScorePredicted > visitorScorePredicted && actualHomeScore > actualVisitorScore) {
                                            profile.addPoints(2);
                                        } else if (homeScorePredicted == visitorScorePredicted && actualHomeScore == actualVisitorScore) {
                                            profile.addPoints(2);
                                        } else if (homeScorePredicted < visitorScorePredicted && actualHomeScore < actualVisitorScore) {
                                            profile.addPoints(2);
                                        }
                                    }
                                }

                            } else {
                                if (pred_result.getHomeTeam().equals(actual_result.getHomeTeam()) || pred_result.getHomeTeam().equals(actual_result.getVisitorTeam())) {
                                    profile.addPoints(4);
                                }

                                if (pred_result.getVisitorTeam().equals(actual_result.getHomeTeam()) || pred_result.getVisitorTeam().equals(actual_result.getVisitorTeam())) {
                                    profile.addPoints(4);
                                }

                                if (actual_result.getId().equals(pred_result.getId()) || invertedId.equals(pred_result.getId())) {
                                    profile.addPoints(2);
                                    if (actualHomeScore != -1 && actualVisitorScore != -1) {
                                        if (homeScorePredicted == actualHomeScore && visitorScorePredicted == actualVisitorScore) {
                                            profile.addPoints(5);
                                            profile.gotPerfectResult();
                                            if (actual_result.getHomeTeam().equals(pred_result.getHomeTeam()) || actual_result.getVisitorTeam().equals(pred_result.getVisitorTeam())) {
                                                profile.addPoints(6);
                                            }
                                        } else if (homeScorePredicted > visitorScorePredicted && actualHomeScore > actualVisitorScore) {
                                            profile.addPoints(2);
                                            if (actual_result.getHomeTeam().equals(pred_result.getHomeTeam()) || actual_result.getVisitorTeam().equals(pred_result.getVisitorTeam())) {
                                                profile.addPoints(6);
                                            }
                                        } else if (homeScorePredicted == visitorScorePredicted && actualHomeScore == actualVisitorScore) {
                                            profile.addPoints(2);
                                            profile.gotPerfectResult();
                                            if (actual_result.getHomeTeam().equals(pred_result.getHomeTeam()) || actual_result.getVisitorTeam().equals(pred_result.getVisitorTeam())) {
                                                profile.addPoints(6);
                                            }
                                        } else if (homeScorePredicted < visitorScorePredicted && actualHomeScore < actualVisitorScore) {
                                            profile.addPoints(2);
                                            if (actual_result.getHomeTeam().equals(pred_result.getHomeTeam()) || actual_result.getVisitorTeam().equals(pred_result.getVisitorTeam())) {
                                                profile.addPoints(6);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    round++;
                }


                for (Scorer scorer : scorers) {
                    for (String bomber : profile.getBombers()) {
                        if (bomber.toLowerCase(Locale.ROOT).equals(scorer.getName().toLowerCase(Locale.ROOT))) {
                            profile.addPoints(scorer.getGoals() * .5);
                        }
                    }
                }


                myRef.child("users").child(profile.getUuid()).setValue(profile);

                startActivity(new Intent(MainScreen.this, PlacementScreen.class));
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        String uuid = sharedPreferences.getString("uuid", "");

        if (uuid == null) {
            startActivity(new Intent(MainScreen.this, StartScreen.class));
        }

        Profile.getProfile().setUuid(uuid);


        myRef.child("users").child(uuid).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                HashMap<String, String> my_user = (HashMap) dataSnapshot.getValue();

                if (my_user == null) {
                    Toast.makeText(MainScreen.this, uuid + "\nPOINTS " + profile.getPoints(), Toast.LENGTH_LONG).show();
                }

                Profile profile = Profile.getProfile();

                profile.setFirstName(my_user.get("firstName"));
                profile.setLastName(my_user.get("lastName"));
                profile.setUserName(my_user.get("userName"));
                profile.setPoints(Double.parseDouble(String.valueOf(my_user.get("points"))));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Button placementButton = findViewById(R.id.generalPlacementButton);
        Button downloadButton = findViewById(R.id.downloadButton);

        placementButton.setVisibility(View.GONE);
        downloadButton.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        String uuid = sharedPreferences.getString("uuid", "");

        myRef.child("users").child(uuid).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                HashMap<String, String> my_user = (HashMap) dataSnapshot.getValue();

                if (my_user == null) {
                    Toast.makeText(MainScreen.this, uuid + "\nPOINTS " + Profile.getProfile().getPoints(), Toast.LENGTH_LONG).show();
                }

                Profile profile = Profile.getProfile();

                profile.setFirstName(my_user.get("firstName"));
                profile.setLastName(my_user.get("lastName"));
                profile.setUserName(my_user.get("userName"));
                profile.setPoints(Double.parseDouble(String.valueOf(my_user.get("points"))));
            }
        });
    }
}
