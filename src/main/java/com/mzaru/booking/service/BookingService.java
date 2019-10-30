package com.mzaru.booking.service;

import com.mzaru.booking.dao.IBookingDao;
import com.mzaru.booking.dto.BookingDto;
import com.mzaru.booking.entity.Booking;
import com.mzaru.booking.entity.Room;
import com.mzaru.booking.entity.User;
import com.mzaru.booking.exception.booking.BookingPeriodNotAvailableException;
import com.mzaru.booking.exception.user.WrongUserLoginOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IBookingDao bookingDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private Environment env;

    @Override
    @Transactional
    public void addBooking(BookingDto wrapper) {
        if (!userService.correctPassword(wrapper.getUser())) {
            throw new WrongUserLoginOrPasswordException("Incorrect username or password entered");
        }
        Booking booking = new Booking();
        booking.setUser(userService.getUserByLogin(wrapper.getUser().getLogin()));
        booking.setRoom(roomService.getRoomByName(wrapper.getRoom_name()));
        booking.setStart(wrapper.getStart());
        booking.setEnd(wrapper.getEnd());
        if (isAvailable(booking)) {
            bookingDao.addBooking(booking);
        } else {
            throw new BookingPeriodNotAvailableException(String.format("The period from %s to %s is not available for booking", booking.getStart(), booking.getEnd()));
        }
    }

    @Override
    @Transactional
    public boolean isAvailable(Booking booking) {
        return bookingDao.isAvailable(booking);
    }

    @Override
    @Transactional
    public List scheduleForAll(LocalDateTime start, LocalDateTime end) {
        return bookingDao.scheduleForAll(start, end);
    }

    @Override
    @Transactional
    public List scheduleForRoom(String room_name, LocalDateTime start, LocalDateTime end) {
        Room room = roomService.getRoomByName(room_name);
        return bookingDao.scheduleForRoom(room, start, end);
    }

    @Override
    public List scheduleForUser(String user_login, LocalDateTime start, LocalDateTime end) {
        User user = userService.getUserByLogin(user_login);
        return bookingDao.scheduleForUser(user, start, end);
    }
}
