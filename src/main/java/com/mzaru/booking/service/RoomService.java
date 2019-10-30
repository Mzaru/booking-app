package com.mzaru.booking.service;

import com.mzaru.booking.dao.IRoomDao;
import com.mzaru.booking.entity.Room;
import com.mzaru.booking.dto.RoomDto;
import com.mzaru.booking.exception.WrongAdminPasswordException;
import com.mzaru.booking.exception.room.RoomAlreadyExistsException;
import com.mzaru.booking.exception.room.RoomNotFoundException;
import com.mzaru.booking.validator.RoomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import util.NullBeanUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomDao roomDao;
    @Autowired
    private Environment env;
    @Autowired
    RoomValidator validator;

    @Override
    @Transactional
    public void addRoom(RoomDto wrapper) {
        if (!wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            throw new WrongAdminPasswordException("The admin password is wrong");
        }
        if (roomDao.getRoomByName(wrapper.getRoom().getName()) != null) {
            throw new RoomAlreadyExistsException(String.format("Room with Name %s already exists", wrapper.getRoom().getName()));
        }
        if (wrapper.getRoom().getHasProjector() == null) {
            wrapper.getRoom().setHasProjector(Boolean.FALSE);
        }
        roomDao.addRoom(wrapper.getRoom());
    }

    @Override
    @Transactional
    public List getAllRooms() {
        return roomDao.getAllRooms();
    }

    @Override
    @Transactional
    public void editRoom(RoomDto wrapper) {
        if (!wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            throw new WrongAdminPasswordException("The admin password is wrong");
        }
        Room room = getRoomByName(wrapper.getRoom().getName());
        if (room == null) {
            throw new RoomNotFoundException(String.format("Room with Name %s was not found in the database", wrapper.getRoom().getName()));
        }
        NullBeanUtils.copyNotNullProperties(wrapper.getRoom(), room);
        validator.validate(room);
        roomDao.editRoom(room);
    }

    @Override
    @Transactional
    public void deleteRoom(RoomDto wrapper) {
        if (!wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            throw new WrongAdminPasswordException("The admin password is wrong");
        }
        Room room = getRoomByName(wrapper.getRoom().getName());
        if (room == null) {
            throw new RoomNotFoundException(String.format("Room with Name %s was not found in the database", wrapper.getRoom().getName()));
        }
        roomDao.deleteRoom(room);
    }

    @Override
    @Transactional
    public Room getRoomByName(String name) {
        return roomDao.getRoomByName(name);
    }
}
