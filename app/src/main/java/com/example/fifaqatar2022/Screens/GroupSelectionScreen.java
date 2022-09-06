package com.example.fifaqatar2022.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Group_enum;
import com.example.fifaqatar2022.Classes.Match;
import com.example.fifaqatar2022.Classes.ResultsRetriever;
import com.example.fifaqatar2022.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GroupSelectionScreen extends AppCompatActivity {

    boolean executed = false;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_phase_selection);

        Button chooseGroupsButton = findViewById(R.id.chooseGroupsButton);
        chooseGroupsButton.setSelected(true);
        Button chooseFinalsButton = findViewById(R.id.chooseFinalsButton);

        ScrollView groupsView = findViewById(R.id.groupsView);
        ScrollView finalsView = findViewById(R.id.finalsView);
        LinearLayout buttonLayout = findViewById(R.id.buttonLayout);



        chooseGroupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!chooseGroupsButton.isSelected()) {
                    chooseGroupsButton.setSelected(true);
                    chooseFinalsButton.setSelected(false);
                }
                groupsView.setVisibility(View.VISIBLE);
                finalsView.setVisibility(View.GONE);
                buttonLayout.setVisibility(View.GONE);
            }
        });


        chooseFinalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!chooseFinalsButton.isSelected()) {
                    chooseFinalsButton.setSelected(true);
                    chooseGroupsButton.setSelected(false);
                }
                finalsView.setVisibility(View.VISIBLE);
                groupsView.setVisibility(View.GONE);
                buttonLayout.setVisibility(View.VISIBLE);
            }
        });


        Button eightButton = findViewById(R.id.eigthButton);
        Button fourthButton = findViewById(R.id.fourthButton);
        Button semiButton = findViewById(R.id.semiButton);
        Button finalButton = findViewById(R.id.finalButton);


        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!eightButton.isSelected()) {
                    eightButton.setSelected(true);
                    fourthButton.setSelected(false);
                    semiButton.setSelected(false);
                    finalButton.setSelected(false);
                }
            }
        });

        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fourthButton.isSelected()) {
                    eightButton.setSelected(false);
                    fourthButton.setSelected(true);
                    semiButton.setSelected(false);
                    finalButton.setSelected(false);
                }
            }
        });

        semiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!semiButton.isSelected()) {
                    eightButton.setSelected(false);
                    fourthButton.setSelected(false);
                    semiButton.setSelected(true);
                    finalButton.setSelected(false);
                }
            }
        });

        finalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!finalButton.isSelected()) {
                    eightButton.setSelected(false);
                    fourthButton.setSelected(false);
                    semiButton.setSelected(false);
                    finalButton.setSelected(true);
                }
            }
        });





        Button buttonA = findViewById(R.id.buttonA);
        Button buttonB = findViewById(R.id.buttonB);
        Button buttonC = findViewById(R.id.buttonC);
        Button buttonD = findViewById(R.id.buttonD);
        Button buttonE = findViewById(R.id.buttonE);
        Button buttonF = findViewById(R.id.buttonF);
        Button buttonG = findViewById(R.id.buttonG);
        Button buttonH = findViewById(R.id.buttonH);


        ResultsRetriever rr = ResultsRetriever.getRR();

        try {
            if (!executed) {
                ArrayList<Match> list = rr.execute().get();
                executed = true;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.A;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.B;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.C;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.D;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.E;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.F;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.G;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });

        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupScreen.selected_group = Group_enum.H;
                startActivity(new Intent(GroupSelectionScreen.this, GroupScreen.class));
            }
        });
    }
}
