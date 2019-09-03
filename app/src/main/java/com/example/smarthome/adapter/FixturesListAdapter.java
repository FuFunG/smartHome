package com.example.smarthome.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.smarthome.R;

public class FixturesListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] fixtureName;
    private final boolean[] isOn;

    public FixturesListAdapter(Activity context, String[] fixtureName, boolean[] isOn) {
        super(context, R.layout.fixture_list_item, fixtureName);

        this.context = context;
        this.fixtureName = fixtureName;
        this.isOn = isOn;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.fixture_list_item, null,true);

        TextView fixtureText = (TextView) rowView.findViewById(R.id.fixture_name);
        Button fixtureBtn = (Button) rowView.findViewById(R.id.fixture_btn);

        fixtureText.setText(fixtureName[position]);
        if (isOn[position]) {
            fixtureBtn.setText(R.string.off);
        } else {
            fixtureBtn.setText(R.string.on);
        }

        return rowView;
    }
}