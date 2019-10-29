package com.mzaru.booking.dto;

import com.mzaru.booking.entity.Room;

public class RoomDto {
    private Room room;
    private String password;

    public RoomDto(Room room, String password) {
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
