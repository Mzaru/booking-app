package com.mzaru.booking.controller;

import com.mzaru.booking.service.IRoomService;
import com.mzaru.booking.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @PostMapping
    public ResponseEntity addRoom(@RequestBody RoomDto wrapper) {
        roomService.addRoom(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllRooms() {
        List rooms = roomService.getAllRooms();
        return new ResponseEntity(rooms, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity editRoom(@RequestBody RoomDto wrapper) {
        roomService.editRoom(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteRoom(@RequestBody RoomDto wrapper) {
        roomService.deleteRoom(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }
}
