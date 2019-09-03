package com.example.smarthome.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.smarthome.R;
import com.example.smarthome.adapter.RoomListAdapter;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    private ListView roomList;
    private String[] listItem = {"a", "b"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initCallback();

        RoomListAdapter adapter = new RoomListAdapter(this, listItem);
        roomList.setAdapter(adapter);

//        RoomAsyncTask roomAsyncTask = new RoomAsyncTask(roomAsyncCallback);
//        roomAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
//        roomAsyncCallback = new RoomAsyncTask.RoomAsyncCallback() {
//            @Override
//            public void roomResponse(RoomModel room) {
//                Log.i(TAG, String.valueOf(room.getBedroom() != null));
//            }
//        };
    }
}
