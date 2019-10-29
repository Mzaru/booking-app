package com.mzaru.booking.dao;

import com.mzaru.booking.entity.Room;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoomDao implements IRoomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRoom(Room room) {
        Session session = entityManager.unwrap(Session.class);
        session.save(room);
    }

    @Override
    public List getAllRooms() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Room").getResultList();
    }

    @Override
    public void editRoom(Room room) {
        Session session = entityManager.unwrap(Session.class);
        session.update(room);
    }

    @Override
    public Room getRoomByName(String name) {
        Session session = entityManager.unwrap(Session.class);
        Room room = session.bySimpleNaturalId(Room.class).load(name);
        return room;
    }

    @Override
    public void deleteRoom(Room room) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(room);
    }
}
