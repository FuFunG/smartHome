package com.example.smarthome.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.smarthome.R;

public class FixturesListAdapter extends ArrayAdapter<String> {
    private final static String TAG = "FixturesListAdapter";

    private final Activity context;
    private final String[] fixtureName;
    private final boolean[] isOn;

    private TextView fixtureText;
    private Switch fixtureBtn;

    public FixturesListAdapter(Activity context, String[] fixtureName, boolean[] isOn) {
        super(context, R.layout.fixture_list_item, fixtureName);

        this.context = context;
        this.fixtureName = fixtureName;
        this.isOn = isOn;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.fixture_list_item, null,true);

        fixtureText = (TextView) rowView.findViewById(R.id.fixture_name);
        fixtureBtn = (Switch) rowView.findViewById(R.id.fixture_btn);

        fixtureText.setText(fixtureName[position]);
        if (isOn[position]) {
            fixtureBtn.setChecked(false);
        } else {
            fixtureBtn.setChecked(true);
        }

        fixtureBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isOn) {
                Log.i(TAG, isOn+"");

            }
        });

        return rowView;
    }
}