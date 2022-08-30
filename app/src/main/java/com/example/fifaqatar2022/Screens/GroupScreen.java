package com.example.fifaqatar2022.Screens;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fifaqatar2022.Classes.Group;
import com.example.fifaqatar2022.Classes.Group_enum;
import com.example.fifaqatar2022.Classes.PlacementsRetriever;
import com.example.fifaqatar2022.Classes.Team;
import com.example.fifaqatar2022.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GroupScreen extends AppCompatActivity {

    static Group_enum selected_group = null;

    static boolean executed = false;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_group);


        PlacementsRetriever pr = PlacementsRetriever.getPR();

        try {
            if (!executed) {
                ArrayList<Group> list = pr.execute().get();
                executed = true;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ArrayList<Group> groups = pr.getAllGroups();

        TextView group_text = findViewById(R.id.group_text);

        Group group = new Group();

        switch(selected_group) {
            case A:
                group = groups.get(0);
                break;
            case B:
                group = groups.get(1);
                break;
            case C:
                group = groups.get(2);
                break;
            case D:
                group = groups.get(3);
                break;
            case E:
                group = groups.get(4);
                break;
            case F:
                group = groups.get(5);
                break;
            case G:
                group = groups.get(6);
                break;
            case H:
                group = groups.get(7);
                break;
        }

        group_text.append(group.getName());


        ArrayList<ImageView> imageViews = new ArrayList<>();

        imageViews.add(findViewById(R.id.first_logo));
        imageViews.add(findViewById(R.id.second_logo));
        imageViews.add(findViewById(R.id.third_logo));
        imageViews.add(findViewById(R.id.fourth_logo));

        ArrayList<TextView> textViews = new ArrayList<>();

        textViews.add(findViewById(R.id.first_name));
        textViews.add(findViewById(R.id.second_name));
        textViews.add(findViewById(R.id.third_name));
        textViews.add(findViewById(R.id.fourth_name));


        int pos = 0;

        for (Team team : group.getTeams()) {
                imageViews.get(pos).setImageDrawable(team.getLogo());
                textViews.get(pos).append(team.getName());
                pos++;
        }

    }

}
