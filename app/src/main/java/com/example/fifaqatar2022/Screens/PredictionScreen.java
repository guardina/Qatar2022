package com.example.fifaqatar2022.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Group;
import com.example.fifaqatar2022.Classes.Group_enum;
import com.example.fifaqatar2022.Classes.PlacementsRetriever;
import com.example.fifaqatar2022.Classes.ResultsRetriever;
import com.example.fifaqatar2022.R;

import java.util.ArrayList;

public class PredictionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_prediction);

        Button buttonA = findViewById(R.id.buttonA);
        Button buttonB = findViewById(R.id.buttonB);
        Button buttonC = findViewById(R.id.buttonC);
        Button buttonD = findViewById(R.id.buttonD);
        Button buttonE = findViewById(R.id.buttonE);
        Button buttonF = findViewById(R.id.buttonF);
        Button buttonG = findViewById(R.id.buttonG);
        Button buttonH = findViewById(R.id.buttonH);

        ResultsRetriever ir = ResultsRetriever.getRR();
        PlacementsRetriever pr = PlacementsRetriever.getCR();


        ArrayList<Group> groups = pr.getAllGroups();



        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.A;
                startActivity(new Intent(PredictionScreen.this, GroupScreen.class));
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.B;
                startActivity(new Intent(PredictionScreen.this, GroupScreen.class));
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.C;
                startActivity(new Intent(PredictionScreen.this, GroupScreen.class));
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.D;
                startActivity(new Intent(PredictionScreen.this, GroupScreen.class));
            }
        });

        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.E;
                startActivity(new Intent(PredictionScreen.this, GroupScreen.class));
            }
        });

        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.F;
                startActivity(new Intent(PredictionScreen.this, GroupScreen.class));
            }
        });

        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.G;
                startActivity(new Intent(PredictionScreen.this, GroupScreen.class));
            }
        });

        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.H;
                startActivity(new Intent(PredictionScreen.this, GroupScreen.class));
            }
        });
    }
}
