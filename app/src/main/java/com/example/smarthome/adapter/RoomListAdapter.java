package com.example.smarthome.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smarthome.R;

public class RoomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] roomName;

    public RoomListAdapter(Activity context, String[] roomName) {
        super(context, R.layout.room_list_item, roomName);

        this.context=context;
        this.roomName=roomName;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.room_list_item, null,true);

        TextView roomNameText = (TextView) rowView.findViewById(R.id.room_name);

        roomNameText.setText(roomName[position]);

        return rowView;
    }
}