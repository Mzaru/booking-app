package com.mzaru.booking.dao;

import com.mzaru.booking.entity.Booking;
import com.mzaru.booking.entity.Room;
import com.mzaru.booking.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface IBookingDao {

    public void addBooking(Booking booking);

    public boolean isAvailable(Booking booking);

    public List scheduleForAll(LocalDateTime start, LocalDateTime end);

    public List scheduleForRoom(Room room, LocalDateTime start, LocalDateTime end);

    public List scheduleForUser(User user, LocalDateTime start, LocalDateTime end);
}
