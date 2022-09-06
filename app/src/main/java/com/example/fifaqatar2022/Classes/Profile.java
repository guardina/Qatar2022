package com.example.fifaqatar2022.Classes;

import android.graphics.drawable.Drawable;

public class Profile {

    private String firstName;
    private String lastName;
    private String userName;
    private Drawable picture;
    private int points;

    public Profile(String firstName, String lastName, String userName, Drawable picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.picture = picture;
    }
}
