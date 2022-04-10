package com.example.firebasesc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import classes.MediaPerson;

public class ListItemAdapter extends ArrayAdapter<MediaPerson> {
    Context context;
    ArrayList<MediaPerson> youtubersOrTwitchStreamers;

    public ListItemAdapter(Context context, ArrayList<MediaPerson> youtubers) {
        super(context, R.layout.activity_main_list_item, youtubers);
        this.context = context;
        this.youtubersOrTwitchStreamers = youtubers;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MediaPerson youtuber = this.youtubersOrTwitchStreamers.get(position);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.activity_main_list_item, null, false);

        TextView tvChannelName = view.findViewById(R.id.tvChannelName);
        TextView tvChannelPlace = view.findViewById(R.id.tvChannelPlace);
        TextView tvChannelSubscribers = view.findViewById(R.id.tvChannelSubscribers);

        tvChannelName.setText(youtuber.getChannelName());
        tvChannelPlace.setText(youtuber.getChannelPlace() + "");
        tvChannelSubscribers.setText(youtuber.getChannelSubscribers());

        view.setTag(youtuber.getChannelPlace());

        return view;
    }
}
