package com.example.fifaqatar2022.Screens;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Group_enum;
import com.example.fifaqatar2022.R;

public class GroupScreen extends AppCompatActivity {

    static Group_enum selected_group = null;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_group);


        TextView screen = findViewById(R.id.screen);

        switch(selected_group) {
            case A:
                screen.append("A");
                break;
            case B:
                screen.append("B");
                break;
            case C:
                screen.append("C");
                break;
            case D:
                screen.append("D");
                break;
            case E:
                screen.append("E");
                break;
            case F:
                screen.append("F");
                break;
            case G:
                screen.append("G");
                break;
            case H:
                screen.append("H");
                break;
        }
    }

}
