package com.mzaru.booking.dto;

import com.mzaru.booking.entity.User;

import javax.validation.Valid;

public class UserDto {
    @Valid
    private User user;
    private String password;

    public UserDto(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
