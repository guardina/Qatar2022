package com.example.fifaqatar2022.Screens;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Profile;
import com.example.fifaqatar2022.R;

import java.io.IOException;
import java.io.InputStream;

public class StartScreen extends AppCompatActivity {

    String prevStarted = "yes";


    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        if (sharedPreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        } else {
            moveToSecondary();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_start);


        ImageView profilePicure = findViewById(R.id.profilePicture);
        EditText firstnameEditText = findViewById(R.id.firstnameEditText);
        EditText lastnameEditText = findViewById(R.id.lastnameEditText);
        EditText usernameEditText = findViewById(R.id.surnameEditText);
        Button saveInfo = findViewById(R.id.saveProfileButton);

        profilePicure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                pictureActivity.launch(photoPicker);
            }
        });


        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstnameEditText.getText().toString();
                String lastName = lastnameEditText.getText().toString();
                String userName = usernameEditText.getText().toString();
                Drawable picture = profilePicure.getDrawable();
                Profile newProfile = new Profile(firstName, lastName, userName, picture);
            }
        });
    }

    ActivityResultLauncher<Intent> pictureActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        ImageView profilePicture = findViewById(R.id.profilePicture);
                        TextView profilePicText = findViewById(R.id.profilePicText);

                        try {
                            Intent data = result.getData();
                            Uri selectedStream = data.getData();
                            InputStream imageStream = getContentResolver().openInputStream(selectedStream);
                            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            profilePicture.setImageBitmap(selectedImage);
                            profilePicText.setText("");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    public void moveToSecondary() {
        Intent intent = new Intent(StartScreen.this, MainScreen.class);
        startActivity(intent);
    }
}
