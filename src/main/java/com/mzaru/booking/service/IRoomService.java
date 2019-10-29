package com.mzaru.booking.service;

import com.mzaru.booking.dto.RoomDto;
import com.mzaru.booking.entity.Room;

import java.util.List;

public interface IRoomService {

    public void addRoom(RoomDto wrapper);

    public List getAllRooms();

    public void editRoom(RoomDto wrapper);

    public void deleteRoom(RoomDto wrapper);

    public Room getRoomByName(String name);
}
