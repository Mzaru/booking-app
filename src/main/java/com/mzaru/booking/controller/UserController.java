package com.mzaru.booking.controller;

import com.mzaru.booking.service.IUserService;
import com.mzaru.booking.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody UserDto wrapper) {
        userService.addUser(wrapper);
        return new ResponseEntity("Added", HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllUsers() {
        List users = userService.getAllUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity editUser(@RequestBody UserDto wrapper) {
        userService.editUser(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody UserDto wrapper) {
        userService.deleteUser(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }
}
