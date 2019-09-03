package com.example.smarthome.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.smarthome.R;
import com.example.smarthome.adapter.FixturesListAdapter;
import com.example.smarthome.adapter.RoomListAdapter;
import com.example.smarthome.asyncTask.RoomAsyncTask;
import com.example.smarthome.model.PreferencesUtils;
import com.example.smarthome.model.Room;
import com.example.smarthome.model.RoomModel;

public class FixturesActivity extends AppCompatActivity {
    private final static String TAG = "FixturesActivity";

    private ListView fixturesList;
    private RoomAsyncTask.RoomAsyncCallback roomAsyncCallback;
    private RoomModel roomModel;
    private PreferencesUtils preferencesUtils;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);
        preferencesUtils = PreferencesUtils.getInstance(this);
        roomModel = preferencesUtils.getRoomModel();
        Log.i(TAG, roomModel.rooms.size()+"");

        initView();
        initListener();
        initCallback();

        Bundle bundle = this.getIntent().getExtras();
//        String[] fixtures = bundle.getStringArray("fixtures");
//        boolean[] isOn = bundle.getBooleanArray("isOn");
        position = bundle.getInt("position");
        Room room = roomModel.rooms.get(position);
//        String[] fixtures = room.getFixtures();
//        Boolean[] isOn = room.getIsOn();
        FixturesListAdapter adapter = new FixturesListAdapter(FixturesActivity.this, room);
        fixturesList.setAdapter(adapter);

//        RoomAsyncTask roomAsyncTask = new RoomAsyncTask(roomAsyncCallback);
//        roomAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void initView() {
        fixturesList = findViewById(R.id.fixtureList);
    }

    public void initListener() {
        fixturesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i(TAG, roomModel.rooms.get(position).roomName);
            }
        });
    }

    private void initCallback() {
//        roomAsyncCallback = new RoomAsyncTask.RoomAsyncCallback() {
//            @Override
//            public void roomResponse(RoomModel rooms) {
//                roomModel = rooms;
//                RoomListAdapter adapter = new RoomListAdapter(FixturesActivity.this, roomModel.toStringArray());
//                fixturesList.setAdapter(adapter);
//            }
//        };
    }
}
