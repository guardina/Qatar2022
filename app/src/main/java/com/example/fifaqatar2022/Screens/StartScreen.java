package com.example.fifaqatar2022.Screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Profile;
import com.example.fifaqatar2022.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class StartScreen extends AppCompatActivity {

    String prevStarted = "yes";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://qatar-2022-64fef-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();
    //FirebaseStorage storage = FirebaseStorage.getInstance();
    //StorageReference storageReference = storage.getReference();


    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(prevStarted, false)) {
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


        ImageView profilePicture = findViewById(R.id.profilePicture);
        EditText firstnameEditText = findViewById(R.id.firstnameCreator);
        EditText lastnameEditText = findViewById(R.id.lastnameCreator);
        EditText usernameEditText = findViewById(R.id.surnameCreator);
        Button saveInfo = findViewById(R.id.saveProfileButton);


        /*profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCropActivity();
            }
        });*/


        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String firstName = firstnameEditText.getText().toString();
                String lastName = lastnameEditText.getText().toString();
                String userName = usernameEditText.getText().toString();


                if (firstName.equals("") || userName.equals("")) {
                    Toast.makeText(StartScreen.this, "Elementi richiesti mancanti!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Profile newProfile = Profile.getProfile();

                newProfile.setFirstName(firstName);
                newProfile.setLastName(lastName);
                newProfile.setUserName(userName);



                //StorageReference profilePicsRef = storageReference.child("pictures/" + userName + ".jpg");

                myRef.child("users").child(newProfile.getUuid()).setValue(newProfile);

                /*Bitmap bitmap = ((BitmapDrawable) picture).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                profilePicsRef.putBytes(data);*/

                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("uuid", newProfile.getUuid());
                editor.apply();

                startActivity(new Intent(StartScreen.this, MainScreen.class));
            }
        });
    }

    private void startCropActivity() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            ImageView profilePicture = findViewById(R.id.profilePicture);
            TextView profilePicText = findViewById(R.id.profilePicText);

            CropImage.ActivityResult activityResult = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri pictureURI = activityResult.getUri();
                profilePicture.setImageURI(pictureURI);
                profilePicText.setText("");
            }
        }
    }


    public void moveToSecondary() {
        Intent intent = new Intent(StartScreen.this, MainScreen.class);
        startActivity(intent);
    }
}
