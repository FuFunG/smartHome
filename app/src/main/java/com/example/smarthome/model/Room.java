package com.example.smarthome.model;

public class Room {
    public String roomName;
    public String[] fixtures;

    public Room() {
    }

    public Room(String roomName, String[] fixtures) {
        this.roomName = roomName;
        this.fixtures = fixtures;
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
}