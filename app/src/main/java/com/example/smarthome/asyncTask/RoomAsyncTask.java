package com.example.smarthome.asyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.smarthome.Constant;
import com.example.smarthome.model.Room;
import com.example.smarthome.model.RoomModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoomAsyncTask extends AsyncTask<String, Void, RoomModel> {
    public String json = "";
    public String url = Constant.ALL_ROOMS_URL;
    private RoomAsyncTask.RoomAsyncCallback roomAsyncCallback;
    private RoomModel roomList;
    private List<String> list;
    private List<Boolean> isOn;
    private Room room;
    private RoomModel response;

    public interface RoomAsyncCallback {
        void roomResponse(RoomModel list);
    }

    public RoomAsyncTask(RoomAsyncCallback roomAsyncCallback){
        this.roomAsyncCallback = roomAsyncCallback;
    }

    @Override
    protected void onPostExecute(RoomModel list) {
        super.onPostExecute(list);
        roomAsyncCallback.roomResponse(list);
    }

    @Override
    protected RoomModel doInBackground(String... params) {
        return getObject(url);
    }

    public RoomModel getObject(String urlLink) {
        try {
            Log.d("url", urlLink);
            URL url = new URL(urlLink);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(3000);
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
            String json = total.toString().trim();
            Log.d("test", "json = " + json.toString());

            response = new RoomModel();
            JSONObject rooms = new JSONObject(json).getJSONObject("rooms");
            Iterator<String> keys = rooms.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                if (rooms.get(key) instanceof JSONObject) {
                    Log.i("room", key);
                    JSONObject room = (JSONObject) rooms.get(key);
                    JSONArray roomFixtures = room.getJSONArray("fixtures");
                    response.rooms.add(setFixtures(key, roomFixtures));
                }
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new RoomModel();
        }
    }

    public Room setFixtures(String roomName, JSONArray fixtures) {
        try {
            list = new ArrayList<String>();
            isOn = new ArrayList<Boolean>();
            for (int i=0; i<fixtures.length(); i++) {
                list.add(fixtures.getString(i));
                isOn.add(false);
                Log.i("setFixtures", fixtures.getString(i));
            }
            room = new Room(roomName, list.toArray(new String[list.size()]), isOn.toArray(new Boolean[isOn.size()]));
            return room;
        } catch (Exception e) {
            e.printStackTrace();
            return new Room();
        }
    }
}
