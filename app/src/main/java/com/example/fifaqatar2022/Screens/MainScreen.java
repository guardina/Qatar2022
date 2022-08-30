package com.example.fifaqatar2022.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Group;
import com.example.fifaqatar2022.Classes.Match;
import com.example.fifaqatar2022.Classes.PlacementsRetriever;
import com.example.fifaqatar2022.Classes.ResultsRetriever;
import com.example.fifaqatar2022.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button res_button = (Button) findViewById(R.id.res_button);
        Button pred_button = (Button) findViewById(R.id.pred_button);


        res_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, ResultsScreen.class);
                startActivity(intent);
            }
        });


        pred_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, PredictionScreen.class);
                startActivity(intent);
            }
        });
    }
}
