package com.example.smarthome.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class PreferencesUtils {
    private static final String preferencesName = "myPreferences";

    private static PreferencesUtils preferencesUtils;
    private Context context;

    public PreferencesUtils(Context context) {
        this.context = context;
    }

    public static synchronized PreferencesUtils getInstance(Context context) {
        if (preferencesUtils == null) {
            preferencesUtils = new PreferencesUtils(context);
        }
        return preferencesUtils;
    }

    public void saveRoomModel(RoomModel roomModel){
        SharedPreferences settings = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();
        String json = gson.toJson(roomModel);
        editor.putString("roomModel", json);
        editor.commit();
    }

    public RoomModel getRoomModel() {
        SharedPreferences settings = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = settings.getString("roomModel", "");
        RoomModel roomModel = gson.fromJson(json, RoomModel.class);
        return roomModel;
    }
}
