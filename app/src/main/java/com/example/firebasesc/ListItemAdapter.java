package com.example.firebasesc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import classes.MovieInfo;

public class ListItemAdapter extends ArrayAdapter<MovieInfo> {
    Context context;
    ArrayList<MovieInfo> films;

    public ListItemAdapter(Context context, ArrayList<MovieInfo> youtubers) {
        super(context, R.layout.activity_main_list_item, youtubers);
        this.context = context;
        this.films = youtubers;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieInfo movie = this.films.get(position);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.activity_main_list_item, null, false);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvReleaseYear = view.findViewById(R.id.tvReleaseYear);
        TextView tvRating = view.findViewById(R.id.tvRating);

        tvName.setText(movie.getName());
        tvReleaseYear.setText(movie.getReleaseYear() + "");
        tvRating.setText(movie.getRating() + "");

        view.setTag(movie.getReleaseYear());

        return view;
    }
}
