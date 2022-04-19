package com.example.firebasesc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import classes.MovieInfo;

public class MainActivity extends AppCompatActivity {
    ArrayList<MovieInfo> films;
    ListItemAdapter adapter;
    int ADD_ITEM_TO_LIST_ACTIVITY_REQUEST_CODE = 1;
    int channelPlace = 1;
    FirebaseDatabase db;
    DatabaseReference filmsRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        ListView youtubersOrTwitchStreamersList = findViewById(R.id.MainList);
        adapter =
                new ListItemAdapter(this, films);
        youtubersOrTwitchStreamersList.setAdapter(adapter);

        db = FirebaseDatabase.getInstance();
        filmsRef = db.getReference().child("Films");

        Query query = filmsRef;

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                films.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    MovieInfo mI = data.getValue(MovieInfo.class);
                    films.add(mI);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void init() {
        films = new ArrayList<>();
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
            Intent intent = new Intent(this, AddFilmInfoToList.class);
            startActivityForResult(intent, ADD_ITEM_TO_LIST_ACTIVITY_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_TO_LIST_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

            }
        }

    }
}
