package com.example.firebasesc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import classes.Person;

public class SignInActivity extends AppCompatActivity {
    EditText txtUsername, txtPasswd;
    Button signUp;
    FirebaseDatabase db;
    DatabaseReference people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtUsername = findViewById(R.id.txtUsername);
        txtPasswd = findViewById(R.id.txtPasswd);
        signUp = findViewById(R.id.signUp);

        db = FirebaseDatabase.getInstance();
        people = db.getReference().child("People");
    }

    public void btnSignInClicked(View v) {
        if (v.getId() == R.id.signUp) {
            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);
        } else {
            String username = txtUsername.getText().toString().trim();
            String passwd = txtPasswd.getText().toString();

            Query query = people.orderByChild("username").equalTo(username);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean flag = false;
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Person p = data.getValue(Person.class);
                        if (p.getPassword().equals(passwd)) {
                            Auth auth = new Auth(getApplicationContext());
                            auth.saveUsername(p.getUsername());
                            auth.setCurrentUser(p);
                            Intent intent = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            startActivity(intent);
                            flag = true;
                        }
                    }
                    if (!flag) {
                        Toast.makeText(getApplicationContext(),
                                "Incorrect username or password",
                                Toast.LENGTH_LONG).show();
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }


}