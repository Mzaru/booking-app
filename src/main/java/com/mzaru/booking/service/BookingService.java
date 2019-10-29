package com.mzaru.booking.service;

import com.mzaru.booking.dao.IBookingDao;
import com.mzaru.booking.entity.Booking;
import com.mzaru.booking.dto.BookingDto;
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
        Booking booking = new Booking();
        booking.setUser(userService.getUserByLogin(wrapper.getUser().getLogin()));
        booking.setRoom(roomService.getRoomByName(wrapper.getRoom_name()));
        booking.setStart(wrapper.getStart());
        booking.setEnd(wrapper.getEnd());
        if (isAvailable(booking)) {
            bookingDao.addBooking(booking);
        } else {
            System.out.println("Not available");
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
}
