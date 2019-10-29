package com.mzaru.booking.dao;

import com.mzaru.booking.dto.BookingDto;
import com.mzaru.booking.entity.Booking;
import com.mzaru.booking.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        Query query;
        if (start == null && end == null) {
            query = session.createQuery("SELECT B.start, B.end, B.user.name, B.room.name FROM Booking B");
        } else if (start == null) {
            query = session.createQuery("SELECT B.start, B.end, B.user.name, B.room.name FROM Booking B WHERE B.end <= :end").setParameter("end", end);
        } else if (end == null) {
            query = session.createQuery("SELECT B.start, B.end, B.user.name, B.room.name FROM Booking B WHERE B.start >= :start").setParameter("start", start);
        } else {
            query = session.createQuery("SELECT B.start, B.end, B.user.name, B.room.name FROM Booking B WHERE B.start >= :start AND B.end <= :end").setParameter("start", start).setParameter("end", end);
        }
        return query.getResultList();
    }
}
