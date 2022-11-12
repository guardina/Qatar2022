package com.example.fifaqatar2022.Screens;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Profile;
import com.example.fifaqatar2022.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileScreen extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://qatar-2022-64fef-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = firebaseDatabase.getReference();

    Profile profile = Profile.getProfile();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_profile);


        EditText firstNameChanger = findViewById(R.id.firstNameChanger);
        EditText lastNameChanger = findViewById(R.id.lastNameChanger);
        EditText userNameChanger = findViewById(R.id.userNameChanger);
        userNameChanger.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });
        TextView pointsShower = findViewById(R.id.pointsShower);
        TextView perfectResultsShower = findViewById(R.id.perfectResultsShower);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        String uuid = sharedPreferences.getString("uuid", "");

        myRef.child("users").child(uuid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Profile profile = snapshot.getValue(Profile.class);
                firstNameChanger.setText(profile.firstName);
                lastNameChanger.setText(profile.lastName);
                userNameChanger.setText(profile.userName);
                pointsShower.setText("Punti: \n" + profile.getPoints());
                perfectResultsShower.setText("Risultati esatti:   \n" + profile.perfectResults);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        Button changeButton = findViewById(R.id.changeButton);

        changeButton.setOnClickListener(new View.OnClickListener() {

            String uuid = sharedPreferences.getString("uuid", "");
            @Override
            public void onClick(View view) {
                if (firstNameChanger.getText().toString().equals("") || userNameChanger.getText().toString().equals("")) {
                    Toast.makeText(ProfileScreen.this, "Nome e/o username mancanti", Toast.LENGTH_SHORT).show();
                    return;
                }

                profile.setFirstName(firstNameChanger.getText().toString());
                profile.setLastName(lastNameChanger.getText().toString());
                profile.setUserName(userNameChanger.getText().toString());
                profile.setUuid(uuid);

                myRef.child("users").child(uuid).setValue(profile);
                finish();
            }
        });
    }
}
