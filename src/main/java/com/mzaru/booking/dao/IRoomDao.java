package com.mzaru.booking.dao;

import com.mzaru.booking.entity.Room;

import java.util.List;

public interface IRoomDao {

    public void addRoom(Room room);

    public List getAllRooms();

    public void editRoom(Room room);

    public Room getRoomByName(Room room);

    public void deleteRoom(Room room);
}
