package com.example.smarthome.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.smarthome.model.RoomModel;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    private ListView roomList;
    private RoomAsyncTask.RoomAsyncCallback roomAsyncCallback;
    private RoomModel roomModel;
    private boolean[] bundleIsOn;
    private PreferencesUtils preferencesUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferencesUtils = PreferencesUtils.getInstance(this);

        initView();
        initListener();
        initCallback();

        RoomAsyncTask roomAsyncTask = new RoomAsyncTask(roomAsyncCallback);
        roomAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void initView() {
        roomList = findViewById(R.id.roomList);
    }

    public void initListener() {
        roomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, roomModel.rooms.get(position).roomName);
                Bundle bundle = new Bundle();
                bundle.putStringArray("fixtures", roomModel.rooms.get(position).getFixtures());
                bundleIsOn = new boolean[roomModel.rooms.get(position).getIsOn().length];
                for (int i=0; i<roomModel.rooms.get(position).getIsOn().length; i++){
                    bundleIsOn[i] = roomModel.rooms.get(position).getIsOn()[i];
                }
                bundle.putBooleanArray("isOn", bundleIsOn);
                bundle.putInt("position", position);
                Intent intent = new Intent(MainActivity.this, FixturesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initCallback() {
        roomAsyncCallback = new RoomAsyncTask.RoomAsyncCallback() {
            @Override
            public void roomResponse(RoomModel rooms) {
                roomModel = rooms;
                preferencesUtils.saveRoomModel(rooms);
                RoomListAdapter adapter = new RoomListAdapter(MainActivity.this, roomModel.toStringArray());
                roomList.setAdapter(adapter);
            }
        };
    }
}
