package com.mzaru.booking.wrapper;

import com.mzaru.booking.entity.User;

public class UserWrapper {
    private User user;
    private String password;

    public UserWrapper(User user, String password) {
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
