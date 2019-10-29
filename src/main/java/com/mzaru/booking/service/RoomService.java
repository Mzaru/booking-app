package com.mzaru.booking.service;

import com.mzaru.booking.dao.IRoomDao;
import com.mzaru.booking.entity.Room;
import com.mzaru.booking.dto.RoomDto;
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

    @Override
    @Transactional
    public void addRoom(RoomDto wrapper) {
        if (wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            if (wrapper.getRoom().getHasProjector() == null) {
                wrapper.getRoom().setHasProjector(Boolean.FALSE);
            }
            roomDao.addRoom(wrapper.getRoom());
        } else {
            System.out.println("Wrong password");
        }
    }

    @Override
    @Transactional
    public List getAllRooms() {
        return roomDao.getAllRooms();
    }

    @Override
    @Transactional
    public void editRoom(RoomDto wrapper) {
        if (wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            Room room = getRoomByName(wrapper.getRoom().getName());
            NullBeanUtils.copyNotNullProperties(wrapper.getRoom(), room);
            roomDao.editRoom(room);
        } else {
            System.out.println("Wrong password");
        }
    }

    @Override
    @Transactional
    public void deleteRoom(RoomDto wrapper) {
        if (wrapper.getPassword().equals(env.getProperty("admin.password"))) {
            Room room = getRoomByName(wrapper.getRoom().getName());
            roomDao.deleteRoom(room);
        } else {
            System.out.println("Wrong password");
        }
    }

    @Override
    @Transactional
    public Room getRoomByName(String name) {
        return roomDao.getRoomByName(name);
    }
}
