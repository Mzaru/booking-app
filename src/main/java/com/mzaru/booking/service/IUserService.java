package com.mzaru.booking.service;

import com.mzaru.booking.entity.User;
import com.mzaru.booking.wrapper.UserWrapper;

import java.util.List;

public interface IUserService {

    public void addUser(UserWrapper wrapper);

    public List getAllUsers();

    public void editUser(UserWrapper wrapper);

    public void deleteUser(UserWrapper wrapper);

}
