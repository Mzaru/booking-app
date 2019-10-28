package com.mzaru.booking.service;

import com.mzaru.booking.dao.IUserDao;
import com.mzaru.booking.entity.User;
import com.mzaru.booking.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private Environment env;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public List getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void editUser(UserWrapper wrapper) {
        if (wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            userDao.editUser(wrapper.getUser());
        } else {
//            throw new AuthenticationException("The admin password is incorrect");
            System.out.println("Wrong password");
        }
    }
}
