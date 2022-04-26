package com.example.firebasesc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import classes.MovieInfo;

public class AddFilmInfoActivity extends AppCompatActivity {
    EditText etName;
    EditText etReleaseYear;
    EditText etRating;
    FirebaseDatabase db;
    DatabaseReference films;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_to_list);

        etName = findViewById(R.id.name);
        etReleaseYear = findViewById(R.id.releaseYear);
        etRating = findViewById(R.id.rating);

        db = FirebaseDatabase.getInstance();
        films = db.getReference().child("Films");

    }

    public void btnClicked(View v) {
        if (String.valueOf(etName.getText()).equals("") || etName == null) {
            etName.setError("Введите название фильма!");
        } else if (String.valueOf(etReleaseYear.getText()).equals("") || etReleaseYear == null) {
            etReleaseYear.setError("Введите год выхода!");
        } else if (String.valueOf(etRating.getText()).equals("") || etRating == null) {
            etRating.setError("Введите рейтинг фильма!");
        }
        else {
            // AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(AddFilmInfoActivity.this);
            builder.setTitle("Добавить новый пункт?");
            builder.setCancelable(false);
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_POSITIVE) {
                        Intent intent = new Intent();
                        MovieInfo addMediaPerson = new MovieInfo(Integer.parseInt(String.valueOf(etReleaseYear.getText())), String.valueOf(etName.getText()), Float.parseFloat(String.valueOf(etRating.getText())));
                        DatabaseReference push = films.push();
                        String key = push.getKey();
                        Toast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();
                        addMediaPerson.setId(key);
                        push.setValue(addMediaPerson);
                        finish();
                    } else if (i == DialogInterface.BUTTON_NEGATIVE) {
                        finish();
                    }
                }
            };
            builder.setPositiveButton("Да", listener);
            builder.setNegativeButton("Нет", listener);
            builder.setNeutralButton("Отмена", null);
            builder.create().show();
            // AlertDialog


        }
    }
}