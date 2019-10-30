package com.mzaru.booking.dao;

import com.mzaru.booking.dto.ScheduleDto;
import com.mzaru.booking.entity.Booking;
import com.mzaru.booking.entity.Room;
import com.mzaru.booking.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BookingDao implements IBookingDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addBooking(Booking booking) {
        Session session = entityManager.unwrap(Session.class);
        session.save(booking);
    }

    @Override
    public boolean isAvailable(Booking booking) {
        Session session = entityManager.unwrap(Session.class);
        long counter = ((long) session.createQuery("SELECT COUNT(B.room)\n" +
                "        FROM Booking B\n" +
                "        WHERE B.room = :bookingroom AND\n" +
                "        ((B.start <= :bookingend AND B.end >= :bookingstart) OR\n" +
                "        ((B.start >= :bookingstart) AND (B.end <= :bookingend)) OR\n" +
                "        ((:bookingstart >= B.start) AND (:bookingend <= B.end)))")
                .setParameter("bookingroom", booking.getRoom())
                .setParameter("bookingstart", booking.getStart())
                .setParameter("bookingend", booking.getEnd())
                .uniqueResult());
        return counter == 0;
    }

    @Override
    public List scheduleForAll(LocalDateTime start, LocalDateTime end) {
        Session session = entityManager.unwrap(Session.class);
        Query<ScheduleDto> query;
        if (start == null && end == null) {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B");
        } else if (start == null) {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.end <= :end").setParameter("end", end);
        } else if (end == null) {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.start >= :start").setParameter("start", start);
        } else {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.start >= :start AND B.end <= :end").setParameter("start", start).setParameter("end", end);
        }
        return query.list();
    }

    @Override
    public List scheduleForRoom(Room room, LocalDateTime start, LocalDateTime end) {
        Session session = entityManager.unwrap(Session.class);
        Query<ScheduleDto> query;
        if (start == null && end == null) {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.room = :room").setParameter("room", room);
        } else if (start == null) {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.room = :room AND B.end <= :end").setParameter("end", end).setParameter("room", room);
        } else if (end == null) {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.room = :room AND B.start >= :start").setParameter("start", start).setParameter("room", room);
        } else {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.room = :room AND (B.start >= :start AND B.end <= :end)").setParameter("start", start).setParameter("end", end).setParameter("room", room);
        }
        return query.list();
    }

    @Override
    public List scheduleForUser(User user, LocalDateTime start, LocalDateTime end) {
        Session session = entityManager.unwrap(Session.class);
        Query<ScheduleDto> query;
        if (start == null && end == null) {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.user = :user").setParameter("user", user);
        } else if (start == null) {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.user = :user AND B.end <= :end").setParameter("user", user).setParameter("end", end);
        } else if (end == null) {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.user = :user AND B.start >= :start").setParameter("start", start).setParameter("user", user);
        } else {
            query = session.createQuery("SELECT new com.mzaru.booking.dto.ScheduleDto(B.start, B.end, B.user.name, B.user.surname, B.room.name) FROM Booking B WHERE B.user = :user AND (B.start >= :start AND B.end <= :end)").setParameter("start", start).setParameter("user", user).setParameter("end", end);
        }
        return query.list();
    }
}
