package com.mzaru.booking.controller;

import com.mzaru.booking.entity.User;
import com.mzaru.booking.service.IUserService;
import com.mzaru.booking.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/user/add")
    public ResponseEntity addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity("Added", HttpStatus.OK);
    }

    @GetMapping(value = "/user/getAll")
    public ResponseEntity getAllUsers() {
        List users = userService.getAllUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @PostMapping(value = "/user/edit")
    public ResponseEntity editUser(@RequestBody UserWrapper wrapper) {
        userService.editUser(wrapper);
        return new ResponseEntity(HttpStatus.OK);
    }
}
