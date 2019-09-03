package com.example.smarthome.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.example.smarthome.model.PreferencesUtils;
import com.example.smarthome.model.Room;

public class FixturesListAdapter extends ArrayAdapter<String> {
    private final static String TAG = "FixturesListAdapter";
    private PreferencesUtils preferencesUtils;

    private final String[] fixtureName;
    private final Boolean[] isOn;

    private final Context context;

    public FixturesListAdapter(Context context, Room room) {
        super(context, R.layout.fixture_list_item, room.getFixtures());
        preferencesUtils = PreferencesUtils.getInstance(context);

        this.context = context;
        this.fixtureName = room.getFixtures();
        this.isOn = room.getIsOn();
    }

    static class ViewHolder {
        TextView fixtureText;
        Switch fixtureBtn;
        int position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView = inflater.inflate(R.layout.fixture_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.position = position;
            viewHolder.fixtureText = (TextView) convertView.findViewById(R.id.fixture_name);
            viewHolder.fixtureBtn = (Switch) convertView.findViewById(R.id.fixture_btn);
            viewHolder.fixtureBtn
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean isOn) {
                            Log.i(TAG, position+" "+isOn);
                        }
                    });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.fixtureText.setText(fixtureName[position]);
        if (isOn[position]) {
            viewHolder.fixtureBtn.setChecked(false);
        } else {
            viewHolder.fixtureBtn.setChecked(true);
        }

        viewHolder.position = position;
        return convertView;
    }
}