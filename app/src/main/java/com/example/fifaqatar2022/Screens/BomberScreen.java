package com.example.fifaqatar2022.Screens;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.MyTimer;
import com.example.fifaqatar2022.Classes.Profile;
import com.example.fifaqatar2022.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;

public class BomberScreen extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://qatar-2022-64fef-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomber);


        EditText bomber1 = findViewById(R.id.bomber1);
        EditText bomber2 = findViewById(R.id.bomber2);
        EditText bomber3 = findViewById(R.id.bomber3);


        MyTimer timer = MyTimer.getTimer();

        if (timer.tooLate()) {
            bomber1.setEnabled(false);
            bomber2.setEnabled(false);
            bomber3.setEnabled(false);
        }

        Profile profile = Profile.getProfile();

        ArrayList<EditText> bomberEditTexts = new ArrayList<>();

        bomberEditTexts.add(bomber1);
        bomberEditTexts.add(bomber2);
        bomberEditTexts.add(bomber3);



        myRef.child("bombers").child(profile.getUuid()).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();

                int pos = 0;
                while (iter.hasNext()) {
                    DataSnapshot current = iter.next();

                    if (current == null) {
                        break;
                    }

                    String bomber = (String) current.getValue();
                    bomberEditTexts.get(pos++).setText(bomber);
                }
            }
        });


        Button saveBomberButton = findViewById(R.id.saveBomberButton);

        saveBomberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameBomber1 = bomber1.getText().toString();
                String nameBomber2 = bomber2.getText().toString();
                String nameBomber3 = bomber3.getText().toString();

                profile.addBomber(nameBomber1);
                profile.addBomber(nameBomber2);
                profile.addBomber(nameBomber3);

                myRef.child("bombers").child(profile.getUuid()).child("bomber1").setValue(nameBomber1);
                myRef.child("bombers").child(profile.getUuid()).child("bomber2").setValue(nameBomber2);
                myRef.child("bombers").child(profile.getUuid()).child("bomber3").setValue(nameBomber3);

                if (timer.tooLate()) {
                    Toast.makeText(BomberScreen.this, "I bomber non si possono più cambiare!\n\uD83E\uDD21", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(BomberScreen.this, "Bomber salvati!\nSpero per te segnino un sacco \uD83E\uDD89", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
