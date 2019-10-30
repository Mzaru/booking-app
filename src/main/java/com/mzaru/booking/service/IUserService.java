package com.mzaru.booking.service;

import com.mzaru.booking.entity.User;
import com.mzaru.booking.dto.UserDto;

import java.util.List;

public interface IUserService {

    public void addUser(UserDto wrapper);

    public List getAllUsers();

    public void editUser(UserDto wrapper);

    public void deleteUser(UserDto wrapper);

    public boolean correctPassword(User user);

    public User getUserByLogin(String login);

}
