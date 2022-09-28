package com.example.fifaqatar2022.Screens;

import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.Distribution;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SnapshotMetadata;
import com.squareup.okhttp.internal.DiskLruCache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Consumer;

public class PlacementScreen extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://qatar-2022-64fef-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_placement);


        ScrollView placementView = findViewById(R.id.placementView);
        LinearLayout placementLayout = findViewById(R.id.placementLayout);


        System.out.println(myRef.child("users").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();

                int counter = 1;

                while(iter.hasNext()) {
                    DataSnapshot current = iter.next();

                    HashMap<String, String> user = (HashMap) current.getValue();

                    LinearLayout newView = new LinearLayout(PlacementScreen.this);
                    newView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    newView.setOrientation(LinearLayout.HORIZONTAL);

                    TextView placeView = new TextView(PlacementScreen.this);
                    placeView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    placeView.setText(String.valueOf(counter++));
                    placeView.setTextColor(getResources().getColor(R.color.black));

                    TextView usernameView = new TextView(PlacementScreen.this);
                    usernameView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    usernameView.setText(user.get("userName"));

                    TextView pointsView = new TextView(PlacementScreen.this);
                    pointsView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    pointsView.setText(String.valueOf(user.get("points")));


                    newView.addView(placeView);
                    newView.addView(usernameView);
                    newView.addView(pointsView);


                    placementLayout.addView(newView);
                }
            }
        }));
    }
}
