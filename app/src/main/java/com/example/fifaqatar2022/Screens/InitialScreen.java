package com.example.fifaqatar2022.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fifaqatar2022.R;

public class InitialScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //getSupportActionBar().hide();

        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        finish();
    }
}