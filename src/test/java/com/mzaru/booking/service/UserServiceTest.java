package com.mzaru.booking.service;

import com.mzaru.booking.dao.IUserDao;
import com.mzaru.booking.dto.UserDto;
import com.mzaru.booking.entity.User;
import com.mzaru.booking.exception.WrongAdminPasswordException;
import com.mzaru.booking.exception.user.UserAlreadyExistsException;
import com.mzaru.booking.exception.user.UserNotFoundException;
import com.mzaru.booking.validator.UserValidator;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private IUserDao userDao;
    @Mock
    private UserValidator validator;
    @Mock
    private Environment env;
    private final String correctPassword = "correctPassword";
    private final String incorrectPassword = "incorrectPassword";
    private final String login = "login";
    @InjectMocks
    private UserService unit = new UserService();

    @BeforeEach //remove Each
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddUserWithCorrectPassword() {
        User user = new User();
        UserDto wrapper = new UserDto(user, correctPassword);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        unit.addUser(wrapper);

        verify(userDao, Mockito.times(1)).addUser(user);
    }

    @Test
    void testAddUserWithIncorrectPassword() {
        User user = new User();
        UserDto wrapper = new UserDto(user, incorrectPassword);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        assertThrows(WrongAdminPasswordException.class, () -> unit.addUser(wrapper));
    }

    @Test
    void testAddUserWithIncorrectLogin() {
        User user = new User();
        user.setLogin(login);
        UserDto wrapper = new UserDto(user, correctPassword);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);
        when(userDao.getUserByLogin(login)).thenReturn(user);

        assertThrows(UserAlreadyExistsException.class, () -> unit.addUser(wrapper));
    }

    @Test
    void getAllUsers() {
        List expectedResult = new ArrayList();
        when(userDao.getAllUsers()).thenReturn(expectedResult);

        List actualResult = unit.getAllUsers();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void editUserWithCorrectPassword() {
        User user = new User();
        UserDto wrapper = new UserDto(user, correctPassword);
        user.setLogin(login);
        when(userDao.getUserByLogin(login)).thenReturn(user);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        unit.editUser(wrapper);

        verify(userDao, times(1)).editUser(user);
        verify(validator, times(1)).validate(user);
    }

    @Test
    void editUserWithIncorrectPassword() {
        User user = new User();
        UserDto wrapper = new UserDto(user, incorrectPassword);
        user.setLogin(login);
        when(userDao.getUserByLogin(login)).thenReturn(user);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        assertThrows(WrongAdminPasswordException.class, () -> unit.editUser(wrapper));
    }

    @Test
    void editUserWithIncorrectLogin() {
        User user = new User();
        UserDto wrapper = new UserDto(user, correctPassword);
        user.setLogin(login);
        when(userDao.getUserByLogin(login)).thenReturn(null);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        assertThrows(UserNotFoundException.class, () -> unit.editUser(wrapper));
    }

    @Test
    void deleteUserWithCorrectPassword() {
        User user = new User();
        UserDto wrapper = new UserDto(user, correctPassword);
        user.setLogin(login);
        when(userDao.getUserByLogin(login)).thenReturn(user);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        unit.deleteUser(wrapper);

        verify(userDao, times(1)).deleteUser(user);
    }

    @Test
    void deleteUserWithIncorrectPassword() {
        User user = new User();
        UserDto wrapper = new UserDto(user, incorrectPassword);
        user.setLogin(login);
        when(userDao.getUserByLogin(login)).thenReturn(user);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        assertThrows(WrongAdminPasswordException.class, () -> unit.deleteUser(wrapper));
    }

    @Test
    void deleteUserWithIncorrectLogin() {
        User user = new User();
        UserDto wrapper = new UserDto(user, correctPassword);
        user.setLogin(login);
        when(userDao.getUserByLogin(login)).thenReturn(null);
        when(env.getProperty("admin.password")).thenReturn(correctPassword);

        assertThrows(UserNotFoundException.class, () -> unit.deleteUser(wrapper));
    }

    @Test
    void testCorrectPassword() {
        User user = new User();
        user.setLogin(login);
        user.setPassword(correctPassword);
        User storedUser = new User();
        storedUser.setPassword(correctPassword);
        when(userDao.getUserByLogin(login)).thenReturn(storedUser);

        boolean result = unit.correctPassword(user);

        assertTrue(result);
    }

    @Test
    void testIncorrectPassword() {
        User user = new User();
        user.setLogin(login);
        user.setPassword(incorrectPassword);
        User storedUser = new User();
        storedUser.setPassword(correctPassword);
        when(userDao.getUserByLogin(login)).thenReturn(storedUser);

        boolean result = unit.correctPassword(user);

        assertFalse(result);
    }

    @Test
    void testCorrectPasswordWithIncorrectLogin() {
        User user = new User();
        user.setLogin(login);
        user.setPassword(correctPassword);
        when(userDao.getUserByLogin(login)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> unit.correctPassword(user));
    }

    @Test
    void getUserByLogin() {
        User user = new User();
        when(userDao.getUserByLogin(login)).thenReturn(user);

        User result = unit.getUserByLogin(login);

        assertEquals(result, user);
    }
}