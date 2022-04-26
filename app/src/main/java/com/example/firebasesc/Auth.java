package com.example.firebasesc;

import android.content.Context;
import android.content.SharedPreferences;

import classes.Person;

public class Auth {
    private Context context;
    private static String username = null;
    private static Person person = null;

    public Auth(Context context) {
        this.context = context;
    }

    public String getUsername() {
        if (username == null) {
            SharedPreferences sp = context.getSharedPreferences("data", context.MODE_PRIVATE);
            username = sp.getString("username", null);
        }
        return username;
    }

    public void saveUsername(String username) {
        Auth.username = username;
        SharedPreferences sp = context.getSharedPreferences("data",
                context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("username", username);
        edit.apply();
    }

    public void setCurrentUser(Person p) {
        person = p;
    }

    public Person getCurrentUser() {
        return person;
    }
}



