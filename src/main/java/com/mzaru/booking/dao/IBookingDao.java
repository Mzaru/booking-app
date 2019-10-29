package com.mzaru.booking.dao;

import com.mzaru.booking.dto.BookingDto;
import com.mzaru.booking.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface IBookingDao {

    public void addBooking(Booking booking);

    public boolean isAvailable(Booking booking);

    public List scheduleForAll(LocalDateTime start, LocalDateTime end);
}
