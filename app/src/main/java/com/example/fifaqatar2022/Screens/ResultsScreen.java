package com.example.fifaqatar2022.Screens;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Match;
import com.example.fifaqatar2022.Classes.ResultsRetriever;
import com.example.fifaqatar2022.R;

import java.util.ArrayList;

public class ResultsScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView res_view = findViewById(R.id.res_view);
        Button back_button = findViewById(R.id.res_back_button);

        ResultsRetriever ir = ResultsRetriever.getRR();

        ArrayList<ArrayList<Match>> results = ir.getAllMatches();

        res_view.setMovementMethod(new ScrollingMovementMethod());
        for (ArrayList<Match> list : results) {
            for (Match match : list) {
                res_view.append(match.getDate() + "\n");
                res_view.append(match.getFirst_team() + "  " + match.getFirst_score() + "    ");
                res_view.append(match.getSecond_team() + "  " + match.getFirst_score());
                res_view.append("\n\n");
            }
        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(ResultsScreen.this, MainScreen.class);
                //startActivity(intent);
                finish();
            }
        });
    }
}
