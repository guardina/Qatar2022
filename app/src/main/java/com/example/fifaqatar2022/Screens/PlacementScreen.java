package com.example.fifaqatar2022.Screens;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Entity;
import com.example.fifaqatar2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class

PlacementScreen extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://qatar-2022-64fef-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();

    static ArrayList<Entity> user_placement = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_placement);


        LinearLayout placementLayout = findViewById(R.id.placementLayout);

        ArrayList<TextView> usernameViews = new ArrayList<>();
        ArrayList<TextView> pointsViews = new ArrayList<>();

        ArrayList<String> all_usernames = new ArrayList<>();
        ArrayList<String> all_points = new ArrayList<>();
        ArrayList<Entity> all_users = new ArrayList<>();


        myRef.child("users").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();

                int counter = 1;

                if (user_placement.isEmpty()) {

                    while (iter.hasNext()) {
                        DataSnapshot current = iter.next();

                        HashMap<String, String> user = (HashMap) current.getValue();


                        LinearLayout newView = new LinearLayout(PlacementScreen.this);
                        newView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150));
                        newView.setOrientation(LinearLayout.HORIZONTAL);
                        newView.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                        newView.setDividerDrawable(getResources().getDrawable(R.drawable.empty_divider));


                        TextView placeView = new TextView(PlacementScreen.this);
                        TextView usernameView = new TextView(PlacementScreen.this);
                        TextView pointsView = new TextView(PlacementScreen.this);

                        placeView.setId(View.generateViewId());
                        usernameView.setId(View.generateViewId());
                        pointsView.setId(View.generateViewId());


                        placeView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                        placeView.setText(String.valueOf(counter++));
                        placeView.setTextColor(getResources().getColor(R.color.black));
                        placeView.setGravity(Gravity.CENTER_VERTICAL);
                        placeView.setTypeface(getResources().getFont(R.font.helvetica_bold));


                        all_usernames.add(user.get("userName"));
                        //TextView usernameView = new TextView(PlacementScreen.this);
                        usernameView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                        usernameView.setText(user.get("userName"));
                        usernameView.setTextColor(getResources().getColor(R.color.black));
                        usernameView.setGravity(Gravity.CENTER_VERTICAL);
                        usernameView.setTypeface(getResources().getFont(R.font.helvetica_bold));


                        all_points.add(String.valueOf(user.get("points")));
                        //TextView pointsView = new TextView(PlacementScreen.this);

                        pointsView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                        pointsView.setText(String.valueOf(user.get("points")));
                        pointsView.setTextColor(getResources().getColor(R.color.black));
                        pointsView.setGravity(Gravity.CENTER_VERTICAL);
                        pointsView.setTypeface(getResources().getFont(R.font.helvetica_bold));


                        all_users.add(new Entity(user.get("userName"), String.valueOf(user.get("points"))));

                        usernameViews.add(usernameView);
                        pointsViews.add(pointsView);

                        newView.addView(placeView);
                        newView.addView(usernameView);
                        newView.addView(pointsView);


                        placementLayout.addView(newView);
                    }
                } else {
                    while (iter.hasNext()) {
                        DataSnapshot current = iter.next();

                        HashMap<String, String> user = (HashMap) current.getValue();

                        for (int i = 0; i < user_placement.size(); i++) {

                            if (user_placement.get(i).getUsername().equals(user.get("userName"))) {
                                user_placement.get(i).setPoints(Integer.parseInt(String.valueOf(user.get("points"))));

                                LinearLayout newView = new LinearLayout(PlacementScreen.this);
                                newView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150));
                                newView.setOrientation(LinearLayout.HORIZONTAL);
                                newView.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                                newView.setDividerDrawable(getResources().getDrawable(R.drawable.empty_divider));


                                TextView placeView = new TextView(PlacementScreen.this);
                                TextView usernameView = new TextView(PlacementScreen.this);
                                TextView pointsView = new TextView(PlacementScreen.this);

                                placeView.setId(View.generateViewId());
                                usernameView.setId(View.generateViewId());
                                pointsView.setId(View.generateViewId());


                                placeView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                                placeView.setText(String.valueOf(counter++));
                                placeView.setTextColor(getResources().getColor(R.color.black));
                                placeView.setGravity(Gravity.CENTER_VERTICAL);
                                placeView.setTypeface(getResources().getFont(R.font.helvetica_bold));


                                all_usernames.add(user_placement.get(i).getUsername());
                                usernameView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                                usernameView.setText(user_placement.get(i).getUsername());
                                usernameView.setTextColor(getResources().getColor(R.color.black));
                                usernameView.setGravity(Gravity.CENTER_VERTICAL);
                                usernameView.setTypeface(getResources().getFont(R.font.helvetica_bold));


                                all_points.add(String.valueOf(user_placement.get(i).getPoints()));
                                pointsView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                                pointsView.setText(String.valueOf(user_placement.get(i).getPoints()));
                                pointsView.setTextColor(getResources().getColor(R.color.black));
                                pointsView.setGravity(Gravity.CENTER_VERTICAL);
                                pointsView.setTypeface(getResources().getFont(R.font.helvetica_bold));

                                all_users.add(new Entity(user_placement.get(i).getUsername(), String.valueOf(user_placement.get(i).getPoints())));

                                usernameViews.add(usernameView);
                                pointsViews.add(pointsView);

                                newView.addView(placeView);
                                newView.addView(usernameView);
                                newView.addView(pointsView);


                                placementLayout.addView(newView);
                            }
                        }
                    }
                }
            }
        });

        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_placement.clear();

                for (int i = 0; i < all_usernames.size(); i++) {
                    int pos = 0;
                    for (int j = pos + 1; j < all_usernames.size(); j++) {
                        if (user_placement.contains(all_users.get(pos))) {
                            pos++;
                        }
                        if (user_placement.contains(all_users.get(j))) {
                            continue;
                        } else if (all_users.get(j).betterThan(all_users.get(pos))) {
                            pos = j;
                        }
                    }
                    user_placement.add(all_users.get(pos));
                }

                for (int i = 0; i < usernameViews.size(); i++) {
                    Entity user = user_placement.get(i);
                    usernameViews.get(i).setText(user.getUsername());
                    pointsViews.get(i).setText(String.valueOf(user.getPoints()));
                }
            }
        });
    }
}
