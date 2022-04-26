package com.example.firebasesc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import classes.Person;

public class SignUpActivity extends AppCompatActivity {
    EditText txtName, txtIin, txtUsername, txtPasswd;
    FirebaseDatabase db;
    DatabaseReference people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtName = findViewById(R.id.txtName);
        txtIin = findViewById(R.id.txtIin);
        txtUsername = findViewById(R.id.txtUsername);
        txtPasswd = findViewById(R.id.txtPasswd);

        db = FirebaseDatabase.getInstance();
                people = db.getReference().child("People");

    }

    public void btnSignUpClicked(View v) {
        String name = txtName.getText().toString();
        String iin = txtIin.getText().toString();
        String username = txtUsername.getText().toString();
        String passwd = txtPasswd.getText().toString();
//Валидация
        boolean flag = true;
        if (name.isEmpty()) {
            txtName.setError("Enter name");
            flag = false;
        }
        if (iin.isEmpty()) {
            txtIin.setError("Enter iin");
            flag = false;
        }
        if (!flag) {
            return;
        }

        Query query = people.orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    Toast.makeText(getApplicationContext(),
                            "This username is busy already",
                            Toast.LENGTH_LONG).show();
                } else {
                    Person p = new Person(name.trim(), iin, username.trim(), passwd);

                    DatabaseReference push = people.push();
                    String key = push.getKey();
                    Toast.makeText(getApplicationContext(),
                            "Success", Toast.LENGTH_LONG).show();
                    p.setKey(key);
                    push.setValue(p);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}