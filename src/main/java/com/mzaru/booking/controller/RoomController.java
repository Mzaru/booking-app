package com.mzaru.booking.controller;

import com.mzaru.booking.service.IRoomService;
import com.mzaru.booking.wrapper.RoomWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @PostMapping(value = "/room/add")
    public ResponseEntity addRoom(@RequestBody RoomWrapper wrapper) {
        roomService.addRoom(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/room/getAll")
    public ResponseEntity getAllRooms() {
        List rooms = roomService.getAllRooms();
        return new ResponseEntity(rooms, HttpStatus.OK);
    }

    @PutMapping(value = "/room/edit")
    public ResponseEntity editRoom(@RequestBody RoomWrapper wrapper) {
        roomService.editRoom(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/room/delete")
    public ResponseEntity deleteRoom(@RequestBody RoomWrapper wrapper) {
        roomService.deleteRoom(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }
}
