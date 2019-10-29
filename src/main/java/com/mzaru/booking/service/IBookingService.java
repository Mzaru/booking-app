package com.mzaru.booking.service;

import com.mzaru.booking.entity.Booking;
import com.mzaru.booking.dto.BookingDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IBookingService {

    public void addBooking(BookingDto wrapper);

    public boolean isAvailable(Booking booking);

    public List scheduleForAll(LocalDateTime start, LocalDateTime end);
}
