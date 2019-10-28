package com.mzaru.booking.controller;

import com.mzaru.booking.entity.User;
import com.mzaru.booking.service.IUserService;
import com.mzaru.booking.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/user/add")
    public ResponseEntity addUser(@RequestBody UserWrapper wrapper) {
        userService.addUser(wrapper);
        return new ResponseEntity("Added", HttpStatus.OK);
    }

    @GetMapping(value = "/user/getAll")
    public ResponseEntity getAllUsers() {
        List users = userService.getAllUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @PutMapping(value = "/user/edit")
    public ResponseEntity editUser(@RequestBody UserWrapper wrapper) {
        userService.editUser(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "user/delete")
    public ResponseEntity deleteUser(@RequestBody UserWrapper wrapper) {
        userService.deleteUser(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }
}
