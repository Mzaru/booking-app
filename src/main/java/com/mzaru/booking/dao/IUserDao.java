package com.mzaru.booking.dao;

import com.mzaru.booking.entity.User;

import java.util.List;

public interface IUserDao {

    public void addUser(User user);

    public List getAllUsers();

    public void editUser(User user);

    public User getUserByLogin(User user);

    public void deleteUser(User user);
}
