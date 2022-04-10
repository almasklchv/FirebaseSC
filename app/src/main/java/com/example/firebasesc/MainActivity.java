package com.example.firebasesc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import classes.MediaPerson;

public class MainActivity extends AppCompatActivity {
    ArrayList<MediaPerson> youtubersOrTwitchStreamers;
    ListItemAdapter adapter;
    int ADD_ITEM_TO_LIST_ACTIVITY_REQUEST_CODE = 1;
    int channelPlace = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        ListView youtubersOrTwitchStreamersList = findViewById(R.id.MainList);
        adapter =
                new ListItemAdapter(this, youtubersOrTwitchStreamers);
        youtubersOrTwitchStreamersList.setAdapter(adapter);
    }

    public void init() {
        youtubersOrTwitchStreamers = new ArrayList<>();
        youtubersOrTwitchStreamers.add(new MediaPerson(1, "secret", "127"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addItemToList) {
            Intent intent = new Intent(this, AddItemToListActivity.class);
            startActivityForResult(intent, ADD_ITEM_TO_LIST_ACTIVITY_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }
}