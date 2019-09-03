package com.example.smarthome.model;

public class Room {
    public String roomName;
    public String[] fixtures;
    public Boolean[] isOn;

    public Room() {
    }

    public Room(String roomName, String[] fixtures, Boolean[] isOn) {
        this.roomName = roomName;
        this.fixtures = fixtures;
        this.isOn = isOn;
    }

    public String[] getFixtures() {
        return fixtures;
    }

    public void setFixtures(String[] fixtures) {
        this.fixtures = fixtures;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Boolean[] getIsOn() {
        return isOn;
    }

    public void setIsOn(Boolean[] isOn) {
        this.isOn = isOn;
    }
}