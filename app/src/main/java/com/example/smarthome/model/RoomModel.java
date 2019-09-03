package com.example.smarthome.model;

import java.util.ArrayList;

public class RoomModel {
    public ArrayList<Room> rooms;

    public RoomModel() {
        this.rooms = new ArrayList<Room>();
    }

    public RoomModel(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public String[] toStringArray() {
        ArrayList<String> nameList = new ArrayList<String>();
        for (Room room: rooms){
            nameList.add(room.getRoomName());
        }
        return nameList.toArray(new String[nameList.size()]);
    }
}
