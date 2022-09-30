package com.example.fifaqatar2022.Screens;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.fifaqatar2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;

public class PlacementScreen extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://qatar-2022-64fef-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_placement);


        LinearLayout placementLayout = findViewById(R.id.placementLayout);


        System.out.println(myRef.child("users").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();

                int counter = 1;

                while(iter.hasNext()) {
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
                    //usernameView.setId(View.generateViewId());
                    //pointsView.setId(View.generateViewId());



                    placeView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
                    placeView.setText(String.valueOf(counter++));
                    placeView.setTextColor(getResources().getColor(R.color.black));
                    placeView.setGravity(Gravity.CENTER_VERTICAL);
                    placeView.setTypeface(getResources().getFont(R.font.helvetica_bold));


                    //TextView usernameView = new TextView(PlacementScreen.this);
                    usernameView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
                    usernameView.setText(user.get("userName"));
                    usernameView.setTextColor(getResources().getColor(R.color.black));
                    usernameView.setGravity(Gravity.CENTER_VERTICAL);
                    usernameView.setTypeface(getResources().getFont(R.font.helvetica_bold));


                    //TextView pointsView = new TextView(PlacementScreen.this);
                    pointsView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
                    pointsView.setText(String.valueOf(user.get("points")));
                    pointsView.setTextColor(getResources().getColor(R.color.black));
                    pointsView.setGravity(Gravity.CENTER_VERTICAL);
                    pointsView.setTypeface(getResources().getFont(R.font.helvetica_bold));


                    newView.addView(placeView);
                    newView.addView(usernameView);
                    newView.addView(pointsView);


                    placementLayout.addView(newView);
                }
            }
        }));
    }
}
