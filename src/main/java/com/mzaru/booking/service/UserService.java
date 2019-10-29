package com.mzaru.booking.service;

import com.mzaru.booking.dao.IUserDao;
import com.mzaru.booking.entity.User;
import com.mzaru.booking.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import util.NullBeanUtils;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private Environment env;

    @Override
    @Transactional
    public void addUser(UserDto wrapper) {
        if (wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            userDao.addUser(wrapper.getUser());
        } else {
            System.out.println("Wrong password");
        }
    }

    @Override
    @Transactional
    public List getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void editUser(UserDto wrapper) {
        if (wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            User user = getUserByLogin(wrapper.getUser().getLogin());
            NullBeanUtils.copyNotNullProperties(wrapper.getUser(), user);
            userDao.editUser(user);
        } else {
            System.out.println("Wrong password");
        }
    }

    @Override
    @Transactional
    public void deleteUser(UserDto wrapper) {
        if (wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            User user = getUserByLogin(wrapper.getUser().getLogin());
            userDao.deleteUser(user);
        } else {
            System.out.println("Wrong password");
        }
    }

    @Override
    @Transactional
    public boolean checkPassword(User user) {
        String password = getUserByLogin(user.getLogin()).getPassword();
        return user.getPassword().equals(password);
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
