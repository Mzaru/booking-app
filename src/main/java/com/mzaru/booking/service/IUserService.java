package com.mzaru.booking.service;

import com.mzaru.booking.entity.User;
import com.mzaru.booking.wrapper.UserWrapper;

import java.util.List;

public interface IUserService {

    public void addUser(User user);

    public List getAllUsers();

    public void editUser(UserWrapper wrapper);

}
