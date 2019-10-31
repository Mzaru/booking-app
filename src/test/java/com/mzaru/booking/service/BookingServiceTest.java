package com.mzaru.booking.service;

import com.mzaru.booking.dao.IBookingDao;
import com.mzaru.booking.dto.BookingDto;
import com.mzaru.booking.entity.Booking;
import com.mzaru.booking.entity.Room;
import com.mzaru.booking.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private IBookingDao bookingDao;

    @Mock
    private IUserService userService;

    @Mock
    private IRoomService roomService;

    @Mock
    private Environment env;

    private final String correctPassword = "correctPassword";
    private final String incorrectPassword = "incorrectPassword";
//    private final String login = "login";

    @InjectMocks
    BookingService unit = new BookingService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void isAvailable() {
        Booking booking = new Booking();
        boolean expectedResult = true;
        when(bookingDao.isAvailable(booking)).thenReturn(expectedResult);

        boolean actualResult = unit.isAvailable(booking);

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void scheduleForAll() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        List expectedResult = new ArrayList();
        when(bookingDao.scheduleForAll(start, end)).thenReturn(expectedResult);

        List actualResult = unit.scheduleForAll(start, end);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void scheduleForRoom() {
        Room room = new Room();
        String roomName = "roomName";
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        List expectedResult = new ArrayList();
        when(roomService.getRoomByName(roomName)).thenReturn(room);
        when(bookingDao.scheduleForRoom(room, start, end)).thenReturn(expectedResult);

        List actualResult = unit.scheduleForRoom(roomName, start, end);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void scheduleForUser() {
        User user = new User();
        String userName = "userName";
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        List expectedResult = new ArrayList();
        when(userService.getUserByLogin(userName)).thenReturn(user);
        when(bookingDao.scheduleForUser(user, start, end)).thenReturn(expectedResult);

        List actualResult = unit.scheduleForUser(userName, start, end);

        assertEquals(expectedResult, actualResult);
    }
}