package com.example.firebasesc;

import androidx.annotation.NonNull;
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
import classes.Person;

public class MainActivity extends AppCompatActivity {
    ArrayList<MovieInfo> films;
    ListItemAdapter adapter;
    int ADD_FILM_INFO_ACTIVITY_REQUEST_CODE = 1;
    int EDIT_FILM_INFO_ACTIVITY_REQUEST_CODE = 2;
    FirebaseDatabase db;
    DatabaseReference filmsRef;
    Auth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        ListView filmsList = findViewById(R.id.MainList);
        adapter =
                new ListItemAdapter(this, films);
        filmsList.setAdapter(adapter);

        auth = new Auth(this);

        if (auth.getUsername() == null) {
            Intent intent = new Intent(getApplicationContext(),
                    SignInActivity.class);
            startActivity(intent);
        }

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

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Person p = data.getValue(Person.class);
                    auth.setCurrentUser(p);
                }
                if (auth.getCurrentUser() == null) {
                    Intent intent = new Intent(getApplicationContext(),
                            SignInActivity.class);
                    startActivity(intent);
                }
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
        public boolean onCreateOptionsMenu (Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.activity_main_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            if (item.getItemId() == R.id.addItemToList) {
                Intent intent = new Intent(this, AddFilmInfoActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.signOut) {
                auth.saveUsername(null);
                Intent intent = new Intent(getApplicationContext(),
                        SignInActivity.class);
                startActivity(intent);
            }
            return super.onOptionsItemSelected(item);
        }


    }
