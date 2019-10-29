package com.mzaru.booking.dto;

import com.mzaru.booking.entity.Booking;
import com.mzaru.booking.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class BookingDto {
    private User user;

    private String room_name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime start;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime end;

    public BookingDto(User user, String room_name, LocalDateTime start, LocalDateTime end) {
        this.user = user;
        this.room_name = room_name;
        this.start = start;
        this.end = end;
    }

    public BookingDto() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
