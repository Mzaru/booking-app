package com.mzaru.booking.service;

import com.mzaru.booking.wrapper.RoomWrapper;

import java.util.List;

public interface IRoomService {

    public void addRoom(RoomWrapper wrapper);

    public List getAllRooms();

    public void editRoom(RoomWrapper wrapper);

    public void deleteRoom(RoomWrapper wrapper);
}
