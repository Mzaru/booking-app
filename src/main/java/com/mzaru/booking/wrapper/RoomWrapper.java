package com.mzaru.booking.wrapper;

import com.mzaru.booking.entity.Room;

public class RoomWrapper {
    private Room room;
    private String password;

    public RoomWrapper(Room room, String password) {
        this.room = room;
        this.password = password;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
