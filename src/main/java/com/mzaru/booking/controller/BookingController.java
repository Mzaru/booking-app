package com.mzaru.booking.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mzaru.booking.dto.BookingDto;
import com.mzaru.booking.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping
    public ResponseEntity addBooking(@RequestBody BookingDto wrapper) {
        bookingService.addBooking(wrapper);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity scheduleForAll(@RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start, @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end) {
        List bookings = bookingService.scheduleForAll(start, end);
        return new ResponseEntity(bookings, HttpStatus.OK);
    }

    @GetMapping(value = "/room/{name}")
    public ResponseEntity scheduleForRoom(@PathVariable("name") String room_name, @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start, @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end) {
        List bookings = bookingService.scheduleForRoom(room_name, start, end);
        return new ResponseEntity(bookings, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{name}")
    public ResponseEntity scheduleForUser(@PathVariable("name") String user_login, @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start, @RequestParam(required = false) @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end) {
        List bookings = bookingService.scheduleForUser(user_login, start, end);
        return new ResponseEntity(bookings, HttpStatus.OK);
    }
}
