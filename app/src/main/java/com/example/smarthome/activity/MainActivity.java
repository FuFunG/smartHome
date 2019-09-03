package com.example.smarthome.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.smarthome.R;
import com.example.smarthome.adapter.RoomListAdapter;
import com.example.smarthome.asyncTask.RoomAsyncTask;
import com.example.smarthome.model.RoomModel;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    private ListView roomList;
    private String[] listItem = {"a", "b"};
    private RoomAsyncTask.RoomAsyncCallback roomAsyncCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Log.i(TAG, listItem[position]);
            }
        });
    }

    private void initCallback() {
        roomAsyncCallback = new RoomAsyncTask.RoomAsyncCallback() {
            @Override
            public void roomResponse(RoomModel rooms) {
                RoomListAdapter adapter = new RoomListAdapter(MainActivity.this, rooms.toStringArray());
                roomList.setAdapter(adapter);
            }
        };
    }
}
