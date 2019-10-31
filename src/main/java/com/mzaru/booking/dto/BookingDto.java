package com.mzaru.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mzaru.booking.entity.User;

import java.time.LocalDateTime;

public class BookingDto {
    private User user;

    private String room_name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
