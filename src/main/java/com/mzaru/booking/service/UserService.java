package com.mzaru.booking.service;

import com.mzaru.booking.validator.UserValidator;
import com.mzaru.booking.dao.IUserDao;
import com.mzaru.booking.entity.User;
import com.mzaru.booking.dto.UserDto;
import com.mzaru.booking.exception.user.UserAlreadyExistsException;
import com.mzaru.booking.exception.user.UserNotFoundException;
import com.mzaru.booking.exception.WrongAdminPasswordException;
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
    @Autowired
    private UserValidator validator;

    @Override
    @Transactional
    public void addUser(UserDto wrapper) {
        if (!wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            throw new WrongAdminPasswordException("The admin password is wrong");
        }
        if (userDao.getUserByLogin(wrapper.getUser().getLogin()) != null) {
            throw new UserAlreadyExistsException(String.format("User with Login %s already exists", wrapper.getUser().getLogin()));
        }
        userDao.addUser(wrapper.getUser());
    }

    @Override
    @Transactional
    public List getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void editUser(UserDto wrapper) {
        if (!wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            throw new WrongAdminPasswordException("The admin password is wrong");
        }
        User user = getUserByLogin(wrapper.getUser().getLogin());
        if (user == null) {
            throw new UserNotFoundException(String.format("User with Login %s was not found in the database", wrapper.getUser().getLogin()));
        }
        NullBeanUtils.copyNotNullProperties(wrapper.getUser(), user);
        validator.validate(user);
        userDao.editUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(UserDto wrapper) {
        if (!wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            throw new WrongAdminPasswordException("The admin password is wrong");
        }
        User user = getUserByLogin(wrapper.getUser().getLogin());
        if (user == null) {
            throw new UserNotFoundException(String.format("User with Login %s was not found in the database", wrapper.getUser().getLogin()));
        }
        userDao.deleteUser(user);
    }

    @Override
    @Transactional
    public boolean correctPassword(User user) {
        if (getUserByLogin(user.getLogin()) == null) {
            throw new UserNotFoundException(String.format("User with Login %s was not found in the database", user.getLogin()));
        }
        String password = getUserByLogin(user.getLogin()).getPassword();
        return user.getPassword().equals(password);
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
