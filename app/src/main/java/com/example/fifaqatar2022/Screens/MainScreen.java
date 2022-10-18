package com.example.fifaqatar2022.Screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fifaqatar2022.Classes.Profile;
import com.example.fifaqatar2022.Classes.Result;
import com.example.fifaqatar2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class MainScreen extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://qatar-2022-64fef-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button predictionButton = findViewById(R.id.predButton);
        ImageView profilePic = findViewById(R.id.profilePic);


        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, ProfileScreen.class));
            }
        });


        predictionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, GroupSelectionScreen.class);
                startActivity(intent);
            }
        });


        Button placementButton = findViewById(R.id.generalPlacementButton);

        placementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainScreen.this, PlacementScreen.class));
            }
        });


        //Profile myProfile = Profile.getProfile();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        String uuid = sharedPreferences.getString("uuid", "");
        Profile.getProfile().setUuid(uuid);

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

                    for (int i = 0; i < snapshots.size(); i++) {
                        DataSnapshot current = snapshots.get(i);

                        HashMap<String, String> group_pred = (HashMap) current.getValue();

                        Result result = new Result();

                        result.setHomeTeam(group_pred.get("homeTeamName"));
                        result.setHomeScore(group_pred.get("homeTeamScore"));
                        result.setVisitorTeam(group_pred.get("visitorTeamName"));
                        result.setVisitorScore(group_pred.get("visitorTeamScore"));
                        result.setId(group_pred.get("homeTeamName") + group_pred.get("visitorTeamName"));

                        Profile.getProfile().getPrediction().getGroup_results().get(group_names.indexOf(group_name)).add(result);
                    }
                }
            });
        }
    }
}
